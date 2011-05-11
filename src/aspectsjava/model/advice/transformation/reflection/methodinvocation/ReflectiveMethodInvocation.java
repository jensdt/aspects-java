package aspectsjava.model.advice.transformation.reflection.methodinvocation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jnome.core.expression.ArrayCreationExpression;
import jnome.core.expression.ArrayInitializer;
import jnome.core.expression.ClassLiteral;
import jnome.core.expression.invocation.ConstructorInvocation;
import jnome.core.type.ArrayTypeReference;
import jnome.core.type.BasicJavaTypeReference;
import jnome.core.variable.JavaVariableDeclaration;
import aspectsjava.model.advice.transformation.reflection.ReflectiveAdviceTransformationProvider;
import aspectsjava.model.advice.transformation.runtime.reflection.methodinvocation.MethodCoordinator;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeArgumentsTypeCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeTypeCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure.reflection.ReflectiveArgsParameterExposure;
import chameleon.aspects.Aspect;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.runtimetransformation.Coordinator;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeExpressionProvider;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeParameterExposureProvider;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.namingRegistry.NamingRegistryFactory;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ArgsPointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ParameterExposurePointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.TargetTypePointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ThisTypePointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.Method;
import chameleon.core.method.RegularImplementation;
import chameleon.core.method.exception.ExceptionDeclaration;
import chameleon.core.method.exception.TypeExceptionDeclaration;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;
import chameleon.core.variable.FormalParameter;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.RegularType;
import chameleon.oo.type.Type;
import chameleon.oo.type.TypeReference;
import chameleon.oo.type.generics.BasicTypeArgument;
import chameleon.oo.type.generics.FormalTypeParameter;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.member.simplename.method.NormalMethod;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.modifier.Public;
import chameleon.support.modifier.Static;
import chameleon.support.statement.CatchClause;
import chameleon.support.statement.EmptyStatement;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.ThrowStatement;
import chameleon.support.statement.TryStatement;
import chameleon.support.variable.LocalVariableDeclarator;

public abstract class ReflectiveMethodInvocation extends ReflectiveAdviceTransformationProvider {

	public final String methodNameParamName = "_$methodName";
	public final String argumentNameParamName = "_$arguments";
	public final String typesParamName = "_$types";
	public final String retvalName = "_$retval";
	
	@Override
	public MatchResult<? extends MethodInvocation> getJoinpoint() {
		return (MatchResult<? extends MethodInvocation>) super.getJoinpoint();
	}
	
	protected abstract Block getInnerBody(WeavingEncapsulator next);
	
	public RegularMethodInvocation getNextInvocation(WeavingEncapsulator next) {
		return getNextInvocation(next, new NamedTargetExpression(argumentNameParamName));
	}
	
	public RegularMethodInvocation getNextInvocation(WeavingEncapsulator next, Expression parameters) {
		if (next == null)
			return createProceedInvocation(new NamedTarget(getAdvice().aspect().name()), new NamedTargetExpression(objectParamName), new NamedTargetExpression(methodNameParamName), parameters);
		else
			return createNextAdviceInvocation(next, parameters);
	}
	
	private RegularMethodInvocation createNextAdviceInvocation(WeavingEncapsulator next, Expression parameters) {
		RegularMethodInvocation getInstance = new RegularMethodInvocation("instance", new NamedTarget(next.getAdvice().aspect().name()));
		RegularMethodInvocation adviceInvocation = new RegularMethodInvocation(getAdviceMethodName(next.getAdvice()), getInstance);
		
		
		adviceInvocation.addArgument(new BasicTypeArgument(new BasicTypeReference("T")));
		adviceInvocation.addArgument(new NamedTargetExpression(objectParamName));
		adviceInvocation.addArgument(new NamedTargetExpression(methodNameParamName));
		adviceInvocation.addArgument(parameters);
		adviceInvocation.addArgument(new NamedTargetExpression(calleeName));

		return adviceInvocation;
	}
	
	public List<TypeExceptionDeclaration> getCheckedExceptionsWithoutSubtypes(Method method) throws LookupException {
		List<TypeExceptionDeclaration> checkedTypeExceptions = new ArrayList<TypeExceptionDeclaration>();
		
		// Copy all checked exceptions
		for (ExceptionDeclaration exception : method.getExceptionClause().exceptionDeclarations()) {
			if (exception instanceof TypeExceptionDeclaration && ((ObjectOrientedLanguage) method.language(ObjectOrientedLanguage.class)).isCheckedException(((TypeExceptionDeclaration) exception).getType()))
				checkedTypeExceptions.add((TypeExceptionDeclaration) exception);
		}
		
		// Now remove checked exceptions that are a sub type of another declared exception
		Iterator<TypeExceptionDeclaration> exceptionIterator = checkedTypeExceptions.iterator();
		
		while (exceptionIterator.hasNext()) {
			TypeExceptionDeclaration currentException = exceptionIterator.next();
			for (TypeExceptionDeclaration other : checkedTypeExceptions) {
				if (currentException != other && currentException.getType().assignableTo(other.getType())) {
					exceptionIterator.remove();
					break;
				}
			}
		}
		
		return checkedTypeExceptions;
	}
		
	public TryStatement getEnclosingTry(Block tryBody) throws LookupException {
		// Re-throw unchecked exceptions (subclasses of RuntimeException )	
		TryStatement exceptionHandler = new TryStatement(tryBody);
		exceptionHandler.addCatchClause(getCatchClause("unchecked", ((ObjectOrientedLanguage) getAdvice().language(ObjectOrientedLanguage.class)).getUncheckedException()));

		// Add a re-throw for each checked exception
		int exceptionIndex = 0;
		List<TypeExceptionDeclaration> checkedTypeExceptions = getCheckedExceptionsWithoutSubtypes(getJoinpoint().getJoinpoint().getElement());
		
		for (TypeExceptionDeclaration exception : checkedTypeExceptions) {
			exceptionHandler.addCatchClause(getCatchClause("ex" + exceptionIndex, exception.getTypeReference().getType().clone()));
			
			exceptionIndex++;
		}
		
		// Add a catch all. This isn't actually necessary since we already handled all cases, but since the generic proceed method throws a throwable we need it to prevent compile errors
		exceptionHandler.addCatchClause(getCatchAll());
		
		return exceptionHandler;
	}
	
	public CatchClause getCatchClause(String name, Type type) {
		return new CatchClause(new FormalParameter(name, new BasicTypeReference(type.getFullyQualifiedName())), getRethrowBody(name));
	}
	
	public Block getRethrowBody(String name) {
		Block rethrowBody = new Block();
		ThrowStatement rethrow = new ThrowStatement(new NamedTargetExpression(name));

		rethrowBody.addStatement(rethrow);
		
		return rethrowBody;
	}
		
	public CatchClause getCatchAll() {
		Block emptyCatchBody = new Block();
		emptyCatchBody.addStatement(new EmptyStatement());
		
		return new CatchClause(new FormalParameter("thrwbl", new BasicTypeReference("Throwable")), emptyCatchBody);
	}

	protected Statement getDieStatement() {
		ThrowStatement throwError = new ThrowStatement(new ConstructorInvocation(new BasicJavaTypeReference("Error"), null));
		return throwError;
	}
	
	@Override
	public String getAdviceMethodName(Advice advice) {
		// Get the naming registries
		NamingRegistry<Advice> adviceNamingRegistry = NamingRegistryFactory.instance().getNamingRegistryFor("advice");
		NamingRegistry<Method> methodNamingRegistry = NamingRegistryFactory.instance().getNamingRegistryFor("javamethod");			
		
		try {
			return "advice_" + adviceNamingRegistry.getName(advice) + "_" + methodNamingRegistry.getName(getJoinpoint().getJoinpoint().getElement());
		} catch (LookupException e) {
			System.out.println("LookupEx while getting method");
			return "advice_errord";
		}
	}

	@Override
	public NormalMethod transform(WeavingEncapsulator previous, WeavingEncapsulator next) throws LookupException {
		Aspect<?> aspect = getAdvice().aspect();
		CompilationUnit compilationUnit = aspect.nearestAncestor(CompilationUnit.class);
		
		// Get the class we are going to create this method in
		RegularType aspectClass = getOrCreateAspectClass(compilationUnit, aspect.name());
		
		// Check if the method has already been created
		if (isAlreadyDefined(getAdvice(), compilationUnit))
			return null;
				
		String adviceMethodName = getAdviceMethodName(getAdvice());
		Method m = getJoinpoint().getJoinpoint().getElement();
		// Create the method
		DeclarationWithParametersHeader header = new SimpleNameDeclarationWithParametersHeader(adviceMethodName);
		
		TypeReference returnType = new BasicTypeReference("T");			
		NormalMethod adviceMethod = new NormalMethod(header, returnType);
		
		adviceMethod.addModifier(new Public());
		adviceMethod.addModifier(new Static());
		
		header.addTypeParameter(new FormalTypeParameter(new SimpleNameSignature("T")));

		// Copy the exceptions
		adviceMethod.setExceptionClause(m.getExceptionClause().clone());

		// Add all the parameters to allow the reflective invocation 
		FormalParameter object = new FormalParameter(objectParamName, new BasicTypeReference("Object"));
		header.addFormalParameter(object);
		
		FormalParameter methodName = new FormalParameter(methodNameParamName, new BasicTypeReference("String"));
		header.addFormalParameter(methodName);
		
		FormalParameter methodArguments = new FormalParameter(argumentNameParamName, new ArrayTypeReference(new BasicJavaTypeReference("Object")));
		header.addFormalParameter(methodArguments);
				
		FormalParameter callee = new FormalParameter(calleeName, new BasicTypeReference("Object"));
		header.addFormalParameter(callee);
		
		// Get the body
		Block body = getBody(next);
		
		// Set the method body
		adviceMethod.setImplementation(new RegularImplementation(body));
		
		// Add the method
		aspectClass.add(adviceMethod);
		
		return adviceMethod;
	}
	
	protected Block getBody(WeavingEncapsulator next) throws LookupException {
		Block finalBody = new Block();
		
		/*
		 *	Get the inner body 
		 */
		Block adviceBody = getInnerBody(next);
		
		/*
		 *	Check if we need to wrap in a try - catch block. Around advice without proceed calls is one instance where this doesn't have to be done
		 */
		if (encloseWithTry()) {
		
			/*
			 * 	Create the surrounding try-catch block for exception handling
			 */
			TryStatement exceptionHandler = getEnclosingTry(adviceBody);
		
			/*
			 * 	Complete the complete body
			 */
			finalBody.addStatement(exceptionHandler);
			
			
			// Add a 'die statement' - after the try {} catch, since the try-block should return anyway. 
			// If it doesn't, then an error occurred anyway.

			// If this happens, it is due to a bug in the weaver!
			finalBody.addStatement(getDieStatement());
		} else {
			finalBody.addBlock(adviceBody);
		}
		
		return finalBody;
	}
	
	protected boolean encloseWithTry() {
		return true;
	}

	private RegularMethodInvocation createProceedInvocation(InvocationTarget aspectClassTarget, Expression objectTarget, Expression methodNameTarget, Expression argumentsTarget) {
		RegularMethodInvocation getInstance = new RegularMethodInvocation("instance", aspectClassTarget);
		RegularMethodInvocation proceedInvocation = new RegularMethodInvocation("proceed", getInstance);
		proceedInvocation.addArgument((new BasicTypeArgument(new BasicTypeReference("T"))));
		
		proceedInvocation.addArgument(objectTarget);
		proceedInvocation.addArgument(methodNameTarget);
		proceedInvocation.addArgument(argumentsTarget);

		ArrayCreationExpression typesArray = new ArrayCreationExpression(new ArrayTypeReference(new BasicJavaTypeReference("Class")));
		ArrayInitializer typesInitializer = new ArrayInitializer();					
	
		try {
			for (FormalParameter fp : (List<FormalParameter>) getJoinpoint().getJoinpoint().getElement().formalParameters())
				typesInitializer.addInitializer(new ClassLiteral(fp.getTypeReference().clone()));
		} catch (LookupException e) {
			// This shouldn't occur in normal usage, only a bug can cause this
			e.printStackTrace();
		}
		
		typesArray.setInitializer(typesInitializer);
		proceedInvocation.addArgument(typesArray);
		
		return proceedInvocation;
	}

	@Override
	protected NormalMethod getReflectiveMethodDefinition() {
		NormalMethod method = super.getReflectiveMethodDefinition();
		
		method.getExceptionClause().add(new TypeExceptionDeclaration(new BasicJavaTypeReference("Throwable")));
		
		return method;
	}
	
	@Override
	protected Block getReflectivePublicCall() {
		Block publicCall = new Block();
		
		LocalVariableDeclarator method = new LocalVariableDeclarator(new BasicJavaTypeReference("java.lang.reflect.Method"));
		JavaVariableDeclaration methodDecl = new JavaVariableDeclaration("m");
		
		RegularMethodInvocation getMethod = new RegularMethodInvocation("getMethod", new NamedTargetExpression("invocationClass"));
		getMethod.addArgument(new NamedTargetExpression(methodNameParamName));
		getMethod.addArgument(new NamedTargetExpression(typesParamName));
		
		methodDecl.setInitialization(getMethod);
		method.add(methodDecl);
		
		publicCall.addStatement(method);
				
		
		// return (T) m.invoke(_object, _arguments);
		RegularMethodInvocation methodInvocation = new RegularMethodInvocation("invoke", new NamedTarget("m"));
		methodInvocation.addArgument(new NamedTargetExpression(objectParamName));
		methodInvocation.addArgument(new NamedTargetExpression(argumentNameParamName));
		ReturnStatement returnStatement = new ReturnStatement(new ClassCastExpression(new BasicTypeReference("T"), methodInvocation));
		
		publicCall.addStatement(returnStatement);
		
		return publicCall;
	}
	
	@Override
	protected List<CatchClause> getIgnoredPrivateCatchClauses() {
		List<CatchClause> ignoredClauses = super.getIgnoredPrivateCatchClauses();
		
		Block rethrowBody = new Block();
		ThrowStatement rethrow = new ThrowStatement(new RegularMethodInvocation("getCause", new NamedTarget("invo")));
		rethrowBody.addStatement(rethrow);
		
		ignoredClauses.add(new CatchClause(new FormalParameter("invo", new BasicTypeReference("java.lang.reflect.InvocationTargetException")), rethrowBody));
		
		return ignoredClauses;
	}
	
	@Override
	protected Block getReflectivePrivateCall() {
		Block privateCall = new Block();
		
		LocalVariableDeclarator method = new LocalVariableDeclarator(new BasicJavaTypeReference("java.lang.reflect.Method"));
		JavaVariableDeclaration methodDecl = new JavaVariableDeclaration("m");
		
		RegularMethodInvocation getMethod = new RegularMethodInvocation("getMethod", new NamedTargetExpression("invocationClass"));
		getMethod.addArgument(new NamedTargetExpression(methodNameParamName));
		getMethod.addArgument(new NamedTargetExpression(typesParamName));
		
		methodDecl.setInitialization(getMethod);
		method.add(methodDecl);
		
		privateCall.addStatement(method);
				
		
		// return (T) m.invoke(_object, _arguments);
		RegularMethodInvocation methodInvocation = new RegularMethodInvocation("invoke", new NamedTarget("m"));
		methodInvocation.addArgument(new NamedTargetExpression(objectParamName));
		methodInvocation.addArgument(new NamedTargetExpression(argumentNameParamName));
		ReturnStatement returnStatement = new ReturnStatement(new ClassCastExpression(new BasicTypeReference("T"), methodInvocation));
		
		privateCall.addStatement(returnStatement);
		
		return privateCall;
	}
	
	@Override
	protected CatchClause getNotFoundCatchClause() {
		return new CatchClause(new FormalParameter("nsm", new BasicTypeReference("NoSuchMethodException")), new Block());
	}
	
	@Override
	protected List<FormalParameter> getReflectiveMethodParameters() {
		List<FormalParameter> resultList = new ArrayList<FormalParameter>();
		
		resultList.add(new FormalParameter(objectParamName, new BasicTypeReference("Object")));
		resultList.add(new FormalParameter(methodNameParamName, new BasicTypeReference("String")));
		resultList.add(new FormalParameter(argumentNameParamName, new ArrayTypeReference(new BasicJavaTypeReference("Object"))));
		resultList.add(new FormalParameter(typesParamName, new ArrayTypeReference(new BasicJavaTypeReference("Class"))));
		
		return resultList;
	}

	@Override
	protected String getReflectiveMethodName() {
		return "proceed";
	}
	
	@Override
	public boolean supports(PointcutExpression<?> pointcutExpression) {
		if (super.supports(pointcutExpression))
			return true;
		
		if (pointcutExpression instanceof ArgsPointcutExpression)
			return true;
		
		return false;
	}

	@Override
	public RuntimeExpressionProvider getRuntimeExpressionProvider(RuntimePointcutExpression pointcutExpression) {
		if (pointcutExpression instanceof ArgsPointcutExpression)
			return new RuntimeArgumentsTypeCheck(new NamedTargetExpression(argumentNameParamName));

		return super.getRuntimeExpressionProvider(pointcutExpression);
	}
	
	@Override
	public RuntimeParameterExposureProvider getRuntimeParameterInjectionProvider(ParameterExposurePointcutExpression<?> expression) {
		if (expression instanceof ArgsPointcutExpression)
			return new ReflectiveArgsParameterExposure(this);
		
		return super.getRuntimeParameterInjectionProvider(expression);
	}
	
	@Override
	public Coordinator<NormalMethod> getCoordinator(MatchResult<?> joinpoint, WeavingEncapsulator previousWeavingEncapsulator, WeavingEncapsulator nextEncapsulator) {
		return new MethodCoordinator(this, getJoinpoint(), previousWeavingEncapsulator, nextEncapsulator);
	}
	

}