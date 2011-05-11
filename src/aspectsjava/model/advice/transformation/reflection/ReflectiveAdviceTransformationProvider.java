package aspectsjava.model.advice.transformation.reflection;

import java.util.ArrayList;
import java.util.List;

import jnome.core.expression.invocation.ConstructorInvocation;
import jnome.core.type.BasicJavaTypeReference;
import jnome.core.variable.JavaVariableDeclaration;

import org.rejuse.predicate.SafePredicate;

import aspectsjava.model.advice.transformation.CreateAdviceMethodTransformationProvider;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeAnd;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeIfCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeNot;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeOr;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeTypeCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure.TypeParameterExposure;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeExpressionProvider;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeParameterExposureProvider;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.IfPointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ParameterExposurePointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.TargetTypePointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ThisTypePointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.TypePointcutExpression;
import chameleon.aspects.pointcut.expression.generic.PointcutExpressionAnd;
import chameleon.aspects.pointcut.expression.generic.PointcutExpressionNot;
import chameleon.aspects.pointcut.expression.generic.PointcutExpressionOr;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.method.RegularImplementation;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.RegularType;
import chameleon.oo.type.generics.FormalTypeParameter;
import chameleon.support.expression.AssignmentExpression;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.expression.InstanceofExpression;
import chameleon.support.expression.ThisLiteral;
import chameleon.support.member.simplename.method.NormalMethod;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.member.simplename.variable.MemberVariableDeclarator;
import chameleon.support.modifier.Constructor;
import chameleon.support.modifier.Private;
import chameleon.support.modifier.Public;
import chameleon.support.modifier.Static;
import chameleon.support.statement.CatchClause;
import chameleon.support.statement.IfThenElseStatement;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.StatementExpression;
import chameleon.support.statement.ThrowStatement;
import chameleon.support.statement.TryStatement;
import chameleon.support.variable.LocalVariableDeclarator;

public abstract class ReflectiveAdviceTransformationProvider extends CreateAdviceMethodTransformationProvider {
	/**
	 *  {@inheritDoc}
	 * 
	 * 	Also create the reflective method
	 */
	@Override
	protected RegularType getOrCreateAspectClass(CompilationUnit compilationUnit, final String name) {
		RegularType aspectClass = super.getOrCreateAspectClass(compilationUnit, name);
		
		// Add the method used to make reflective calls
		createReflectiveMethod(aspectClass);
		
		return aspectClass;
	}
	
	protected abstract String getReflectiveMethodName();
	
	protected DeclarationWithParametersHeader getReflectiveMethodHeader() {
		DeclarationWithParametersHeader header = new SimpleNameDeclarationWithParametersHeader(getReflectiveMethodName());
		
		for (FormalParameter fp : getReflectiveMethodParameters())
			header.addFormalParameter(fp);
		
		header.addTypeParameter(new FormalTypeParameter(new SimpleNameSignature("T")));

		return header;
	}
	
	protected abstract List<FormalParameter> getReflectiveMethodParameters();
	
	protected List<CatchClause> getIgnoredPublicCatchClauses() {
		List<CatchClause> ignoredClauses = getIgnoredPrivateCatchClauses();
		
		CatchClause notFoundCatchClause = getNotFoundCatchClause();
		notFoundCatchClause.getExceptionParameter().setName("nsm_inner");
		
		ignoredClauses.add(notFoundCatchClause);
		
		return ignoredClauses;
	}
	
	protected List<CatchClause> getIgnoredPrivateCatchClauses() {
		List<CatchClause> ignoredClauses = new ArrayList<CatchClause>();
		
		ignoredClauses.add(new CatchClause(new FormalParameter("iarg", new BasicTypeReference("IllegalArgumentException")), new Block()));
		ignoredClauses.add(new CatchClause(new FormalParameter("se", new BasicTypeReference("SecurityException")), new Block()));
		ignoredClauses.add(new CatchClause(new FormalParameter("iac", new BasicTypeReference("IllegalAccessException")), new Block()));
			
		return ignoredClauses;
	}
	
	protected abstract CatchClause getNotFoundCatchClause();
	
	protected NormalMethod getReflectiveMethodDefinition() {
		NormalMethod method = new NormalMethod(getReflectiveMethodHeader(), new BasicTypeReference("T"));
		
		method.addModifier(new Private());
		method.addModifier(new Static());
		
		return method;
	}
	
	protected Block getReflectiveMethodBody() {
		Block body = new Block();
		
		// Get the initialization
		body.addBlock(getReflectiveMethodBodyInitialisation());
		
		// Create the try-catch with the reflective call for public members
		TryStatement publicTry = new TryStatement(getReflectivePublicCall());
		
		// Add each ignored-catch-clause
		publicTry.addAllCatchClauses(getIgnoredPrivateCatchClauses());
		
		// Get the catch clause that indicates the member wasn't found
		CatchClause notFound = getNotFoundCatchClause();
		
		// Set the not-found body the a new try-catch with the private call
		TryStatement privateTry = new TryStatement(getReflectivePrivateCall());
		privateTry.addAllCatchClauses(getIgnoredPublicCatchClauses());
		
		Block privateTryStatement = new Block();
		privateTryStatement.addStatement(privateTry);
		notFound.setStatement(privateTryStatement);
		
		publicTry.addCatchClause(notFound);
		
		body.addStatement(publicTry);
		
		// Add a die statement
		body.addStatement(getDieStatement());
		
		return body;
	}
	
	protected Statement getDieStatement() {
		ThrowStatement throwError = new ThrowStatement(new ConstructorInvocation(new BasicJavaTypeReference("Error"), null));
		return throwError;
	}
	
	protected abstract Block getReflectivePublicCall();
	protected abstract Block getReflectivePrivateCall();
	
	public final String objectParamName = "_$object";
	public final String calleeName = "_$callee";
	
	protected Block getReflectiveMethodBodyInitialisation() {
		Block initialisation = new Block();
		
		LocalVariableDeclarator invocationClass = new LocalVariableDeclarator(new BasicJavaTypeReference("Class"));
		JavaVariableDeclaration invocationClassDecl = new JavaVariableDeclaration("invocationClass");
		
		invocationClass.add(invocationClassDecl);
		initialisation.addStatement(invocationClass);
		/* if (_$object instanceof Class) {
			invocationClass = (Class) _$object;
		}
		else {
			invocationClass = _$object.getClass();
		}
		*/
		InstanceofExpression testObject = new InstanceofExpression(new NamedTargetExpression(objectParamName), new BasicJavaTypeReference("Class"));
		ClassCastExpression objectCastToClass = new ClassCastExpression(new BasicJavaTypeReference("Class"), new NamedTargetExpression(objectParamName));
		AssignmentExpression assignObjIf = new AssignmentExpression(new NamedTargetExpression("invocationClass"), objectCastToClass);
		AssignmentExpression assignObjElse = new AssignmentExpression(new NamedTargetExpression("invocationClass"), new RegularMethodInvocation("getClass", new NamedTarget(objectParamName)));
		
		Block ifBody = new Block();
		ifBody.addStatement(new StatementExpression(assignObjIf));
		
		Block elseBody = new Block();
		elseBody.addStatement(new StatementExpression(assignObjElse));
		
		IfThenElseStatement invocationIte = new IfThenElseStatement(testObject, ifBody, elseBody);
		
		initialisation.addStatement(invocationIte);
		
		return initialisation;
	}

	protected void createReflectiveMethod(RegularType aspectClass) {
		// Terminate if the method already exists
		if (hasMethodWithName(aspectClass, getReflectiveMethodName()))
			return;
		
		// Get the method definition
		NormalMethod method = getReflectiveMethodDefinition();
		
		// Get the method body
		Block methodBody = getReflectiveMethodBody();
		
		// Attach the body
		method.setImplementation(new RegularImplementation(methodBody));
		
		// Add the method
		aspectClass.add(method);
	}
	@Override
	public boolean supports(PointcutExpression<?> pointcutExpression) {
		if (pointcutExpression instanceof TypePointcutExpression)
			return true;
		
		if (pointcutExpression instanceof IfPointcutExpression)
			return true;
		
		if (pointcutExpression instanceof PointcutExpressionOr)
			return true;
		
		if (pointcutExpression instanceof PointcutExpressionAnd)
			return true;
		
		if (pointcutExpression instanceof PointcutExpressionNot)
			return true;
		
		return false;
	}

	@Override
	public RuntimeExpressionProvider getRuntimeExpressionProvider(RuntimePointcutExpression pointcutExpression) {		
		if (pointcutExpression instanceof ThisTypePointcutExpression)
			return new RuntimeTypeCheck(new NamedTargetExpression(calleeName));
		
		if (pointcutExpression instanceof TargetTypePointcutExpression)
			return new RuntimeTypeCheck(new NamedTargetExpression(objectParamName));
		
		if (pointcutExpression instanceof IfPointcutExpression)
			return new RuntimeIfCheck();
		
		if (pointcutExpression instanceof PointcutExpressionOr)
			return new RuntimeOr();
		
		if (pointcutExpression instanceof PointcutExpressionAnd)
			return new RuntimeAnd();
		
		if (pointcutExpression instanceof PointcutExpressionNot)
			return new RuntimeNot();
		
		return null;
	}
	
	@Override
	public void initialiseRuntimeTransformers(MatchResult<? extends Element> joinpoint) {
		// Nothing needs to be done
	}
	
	
	@Override
	public RuntimeParameterExposureProvider getRuntimeParameterInjectionProvider(ParameterExposurePointcutExpression<?> expression) {
		if (expression instanceof ThisTypePointcutExpression)
			return new TypeParameterExposure(new NamedTargetExpression(calleeName));
		
		if (expression instanceof TargetTypePointcutExpression)
			return new TypeParameterExposure(new NamedTargetExpression(objectParamName));
		
		return null;
	}
}