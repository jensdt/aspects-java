package aspectsjava.model.advice.transformation.reflection.fieldaccess;

import java.util.ArrayList;
import java.util.List;

import aspectsjava.model.advice.transformation.reflection.ReflectiveAdviceTransformationProvider;
import aspectsjava.model.advice.transformation.runtime.AdviceMethodCoordinator;
import chameleon.aspects.Aspect;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.runtimetransformation.Coordinator;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.RegularImplementation;
import chameleon.core.statement.Block;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.RegularType;
import chameleon.oo.type.TypeReference;
import chameleon.oo.type.generics.BasicTypeArgument;
import chameleon.oo.type.generics.FormalTypeParameter;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.expression.RegularLiteral;
import chameleon.support.member.simplename.method.NormalMethod;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.modifier.Public;
import chameleon.support.modifier.Static;
import chameleon.support.statement.CatchClause;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.StatementExpression;
import chameleon.support.variable.LocalVariable;
import chameleon.support.variable.LocalVariableDeclarator;

public abstract class ReflectiveFieldRead extends ReflectiveAdviceTransformationProvider {
	public final String fieldName = "_$field";
	public final String retvalName = "_$retval";
	
	public RegularMethodInvocation getNextInvocation(WeavingEncapsulator next) {
		if (next == null)
			return createGetFieldValueInvocation(new NamedTarget(getAdvice().aspect().name()), new NamedTargetExpression(objectParamName), new NamedTargetExpression(fieldName));
		else
			return createNextAdviceInvocation(next);
	}
	
	private RegularMethodInvocation createNextAdviceInvocation(WeavingEncapsulator next) {
		RegularMethodInvocation getInstance = new RegularMethodInvocation("instance", new NamedTarget(next.getAdvice().aspect().name()));
		RegularMethodInvocation adviceInvocation = new RegularMethodInvocation(getAdviceMethodName(next.getAdvice()), getInstance);
		
		
		adviceInvocation.addArgument(new BasicTypeArgument(new BasicTypeReference("T")));
		adviceInvocation.addArgument(new NamedTargetExpression(objectParamName));
		adviceInvocation.addArgument(new NamedTargetExpression(fieldName));
		adviceInvocation.addArgument(new NamedTargetExpression(calleeName));

		return adviceInvocation;
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	protected List<FormalParameter> getReflectiveMethodParameters() {
		List<FormalParameter> resultList = new ArrayList<FormalParameter>();
		
		resultList.add(new FormalParameter(objectParamName, new BasicTypeReference<BasicTypeReference<?>>("Object")));
		resultList.add(new FormalParameter(fieldName, new BasicTypeReference<BasicTypeReference<?>>("String")));
		
		return resultList;
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	protected Block getReflectivePublicCall() {
		Block publicCall = new Block();
		
		// java.lang.reflect.Field f = invocationClass.getField(fieldName);
		LocalVariableDeclarator field = new LocalVariableDeclarator(new BasicTypeReference("java.lang.reflect.Field"));
		VariableDeclaration<LocalVariable> fieldDecl = new VariableDeclaration<LocalVariable>("f");
		
		RegularMethodInvocation<?> getMethod = new RegularMethodInvocation("getField", new NamedTargetExpression("invocationClass"));
		getMethod.addArgument(new NamedTargetExpression(fieldName));
		
		fieldDecl.setInitialization(getMethod);
		field.add(fieldDecl);
		
		publicCall.addStatement(field);
		
		// return (T) f.get(_object);
		RegularMethodInvocation<?> methodInvocation = new RegularMethodInvocation("get", new NamedTarget("f"));
		methodInvocation.addArgument(new NamedTargetExpression(objectParamName));
		ReturnStatement returnStatement = new ReturnStatement(new ClassCastExpression(new BasicTypeReference("T"), methodInvocation));
		
		publicCall.addStatement(returnStatement);
		
		return publicCall;
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	protected Block getReflectivePrivateCall() {
		Block privateCall = new Block();
		
		// java.lang.reflect.Field f = invocationClass.getField(fieldName);
		LocalVariableDeclarator field = new LocalVariableDeclarator(new BasicTypeReference("java.lang.reflect.Field"));
		VariableDeclaration fieldDecl = new VariableDeclaration("f");
		
		RegularMethodInvocation getMethod = new RegularMethodInvocation("getDeclaredField", new NamedTargetExpression("invocationClass"));
		getMethod.addArgument(new NamedTargetExpression(fieldName));
		
		fieldDecl.setInitialization(getMethod);
		field.add(fieldDecl);
		
		privateCall.addStatement(field);
		
		// f.setAccessible(true);
		RegularMethodInvocation setAccessible = new RegularMethodInvocation("setAccessible", new NamedTarget("f"));
		setAccessible.addArgument(new RegularLiteral(new BasicTypeReference("boolean"), "true"));
		
		privateCall.addStatement(new StatementExpression(setAccessible));
		
		// return (T) f.get(_object);
		RegularMethodInvocation methodInvocation = new RegularMethodInvocation("get", new NamedTarget("f"));
		methodInvocation.addArgument(new NamedTargetExpression(objectParamName));
		ReturnStatement returnStatement = new ReturnStatement(new ClassCastExpression(new BasicTypeReference("T"), methodInvocation));
		
		privateCall.addStatement(returnStatement);
		
		return privateCall;
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	protected CatchClause getNotFoundCatchClause() {
		return new CatchClause(new FormalParameter("nsm", new BasicTypeReference("NoSuchFieldException")), new Block());
	}
	
	public RegularMethodInvocation createGetFieldValueInvocation(NamedTarget aspectClassTarget, NamedTargetExpression objectTarget, NamedTargetExpression fieldNameTarget) {
		RegularMethodInvocation getInstance = new RegularMethodInvocation("instance", aspectClassTarget);
		RegularMethodInvocation getFieldValueInvocation = new RegularMethodInvocation("getFieldValue", getInstance);
		getFieldValueInvocation.addArgument((new BasicTypeArgument(new BasicTypeReference("T"))));
		
		getFieldValueInvocation.addArgument(objectTarget);
		getFieldValueInvocation.addArgument(fieldNameTarget);
		
		return getFieldValueInvocation;
	}

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	protected List<FormalParameter> getAdviceMethodParameters() {
		List<FormalParameter> result = new ArrayList<FormalParameter>();
		
		result.add(new FormalParameter(objectParamName, new BasicTypeReference("Object")));
		result.add(new FormalParameter(fieldName, new BasicTypeReference("String")));
		result.add(new FormalParameter(calleeName, new BasicTypeReference("Object")));
		
		return result;
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	protected String getReflectiveMethodName() {
		return "getFieldValue";
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public Coordinator<NormalMethod> getCoordinator(MatchResult joinpoint, WeavingEncapsulator previousWeavingEncapsulator, WeavingEncapsulator nextWeavingEncapsulator) {
		return new AdviceMethodCoordinator(this, getJoinpoint(), previousWeavingEncapsulator, nextWeavingEncapsulator);
	}
}