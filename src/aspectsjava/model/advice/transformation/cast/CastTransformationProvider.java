package aspectsjava.model.advice.transformation.cast;

import aspectsjava.model.advice.transformation.CreateAdviceMethodTransformationProvider;
import aspectsjava.model.advice.transformation.runtime.CastCoordinator;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeAnd;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeIfCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeNot;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeOr;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeSingleArgumentTypeCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeTypeCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure.SingleArgParameterExposure;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure.TypeParameterExposure;
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
import chameleon.aspects.pointcut.expression.dynamicexpression.IfPointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ParameterExposurePointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ThisTypePointcutExpression;
import chameleon.aspects.pointcut.expression.generic.PointcutExpressionAnd;
import chameleon.aspects.pointcut.expression.generic.PointcutExpressionNot;
import chameleon.aspects.pointcut.expression.generic.PointcutExpressionOr;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.Method;
import chameleon.core.method.RegularImplementation;
import chameleon.core.statement.Block;
import chameleon.core.variable.FormalParameter;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.RegularType;
import chameleon.oo.type.TypeReference;
import chameleon.oo.type.generics.BasicTypeArgument;
import chameleon.oo.type.generics.FormalTypeParameter;
import chameleon.oo.type.generics.TypeParameter;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.member.simplename.method.NormalMethod;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.modifier.Public;
import chameleon.support.modifier.Static;

public abstract class CastTransformationProvider extends CreateAdviceMethodTransformationProvider {

	@Override
	public String getAdviceMethodName(Advice advice) {
		// Get the naming registries
		NamingRegistry<Advice> adviceNamingRegistry = NamingRegistryFactory.instance().getNamingRegistryFor("advice");
		NamingRegistry<ClassCastExpression> methodNamingRegistry = NamingRegistryFactory.instance().getNamingRegistryFor("classcast");			
		
		return "advice_" + adviceNamingRegistry.getName(advice) + "_" + methodNamingRegistry.getName(getJoinpoint().getJoinpoint());
	}
	
	@Override
	public boolean supports(PointcutExpression<?> pointcutExpression) {
		if (pointcutExpression instanceof ThisTypePointcutExpression)
			return true;
		
		if (pointcutExpression instanceof IfPointcutExpression)
			return true;
		
		if (pointcutExpression instanceof ArgsPointcutExpression)
			return true;
		
		if (pointcutExpression instanceof PointcutExpressionOr)
			return true;
		
		if (pointcutExpression instanceof PointcutExpressionAnd)
			return true;
		
		if (pointcutExpression instanceof PointcutExpressionNot)
			return true;
		
		return false;
	}

	RuntimeExpressionProvider singleArgumentTypeCheck;
	
	@Override
	public void initialiseRuntimeTransformers(MatchResult<? extends Element> joinpoint) {
		try {
			singleArgumentTypeCheck = new RuntimeSingleArgumentTypeCheck(new NamedTargetExpression(expressionName), ((ClassCastExpression) joinpoint.getJoinpoint()).getExpression().getType());
		} catch (LookupException e) {
			// Shouldn't occur, only due to a bug
			e.printStackTrace();
		}
	}

	@Override
	public Coordinator<NormalMethod> getCoordinator(MatchResult<? extends Element> joinpoint, WeavingEncapsulator previous, WeavingEncapsulator next) {
		return new CastCoordinator(this, joinpoint, previous, next);
	}

	@Override
	public RuntimeExpressionProvider getRuntimeExpressionProvider(RuntimePointcutExpression pointcutExpression) {
		if (pointcutExpression instanceof ThisTypePointcutExpression)
			return new RuntimeTypeCheck(new NamedTargetExpression(calleeName));
	
		if (pointcutExpression instanceof ArgsPointcutExpression)
			return singleArgumentTypeCheck;
		
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
	public RuntimeParameterExposureProvider getRuntimeParameterInjectionProvider(ParameterExposurePointcutExpression<?> expression) {
		if (expression instanceof ThisTypePointcutExpression)
			return new TypeParameterExposure(new NamedTargetExpression(calleeName));
		
		if (expression instanceof ArgsPointcutExpression)
			return new SingleArgParameterExposure(expressionName);
		
		return null;
	}
	
	public final String calleeName = "_$callee";
	public final String expressionName = "_$expr";
	public final String retvalName = "_$retval";
	
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
		
		DeclarationWithParametersHeader header = new SimpleNameDeclarationWithParametersHeader(adviceMethodName);
		
		TypeReference returnType = getJoinpoint().getJoinpoint().getTypeReference().clone();		
		NormalMethod adviceMethod = new NormalMethod(header, returnType);
		
		adviceMethod.addModifier(new Public());
		adviceMethod.addModifier(new Static());
		
		// Add all the parameters 
		FormalParameter object = new FormalParameter(expressionName, new BasicTypeReference(getJoinpoint().getJoinpoint().getExpression().getType().getFullyQualifiedName()));
		header.addFormalParameter(object);
		
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
	
	protected abstract Block getBody(WeavingEncapsulator next);
	
	@Override
	public Expression getNextInvocation(WeavingEncapsulator next) {
		return getNextInvocation(next, new NamedTargetExpression(expressionName));
	}
	
	public Expression getNextInvocation(WeavingEncapsulator next, Expression parameter) {
		if (next == null)
			return new ClassCastExpression(getJoinpoint().getJoinpoint().getTypeReference().clone(), parameter);
		else
			return createNextAdviceInvocation(next, parameter);
	}
	
	@Override
	public MatchResult<? extends ClassCastExpression> getJoinpoint() {
		return (MatchResult<? extends ClassCastExpression>) super.getJoinpoint();
	}
	
	private RegularMethodInvocation createNextAdviceInvocation(WeavingEncapsulator next, Expression parameter) {
		RegularMethodInvocation getInstance = new RegularMethodInvocation("instance", new NamedTarget(next.getAdvice().aspect().name()));
		RegularMethodInvocation adviceInvocation = new RegularMethodInvocation(getAdviceMethodName(next.getAdvice()), getInstance);
		
		adviceInvocation.addArgument(parameter);
		adviceInvocation.addArgument(new NamedTargetExpression(calleeName));

		return adviceInvocation;
	}
}