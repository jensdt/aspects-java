package aspectsjava.translate.weaver.weavingprovider;

import java.util.List;

import aspectsjava.model.advice.transformation.runtime.CatchClauseCoordinator;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeAnd;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeIfCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeNot;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeOr;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeSingleArgumentTypeCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeTypeCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure.CatchClauseArgsParameterExposure;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure.ThisTypeParameterExposure;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.runtimetransformation.Coordinator;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeExpressionProvider;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeParameterExposureProvider;
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
import chameleon.aspects.weaver.weavingprovider.AbstractWeavingProviderSupportingRuntimeTransformation;
import chameleon.core.element.Element;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;
import chameleon.support.expression.ThisLiteral;
import chameleon.support.statement.CatchClause;

/**
 * 	This instance of a weaving provider combines a list of statements with a given block
 * 
 * 	@author Jens De Temmerman
 */
public abstract class CatchClauseInsertProvider extends AbstractWeavingProviderSupportingRuntimeTransformation<Block, List<Statement>> {
	
	/**
	 * 	The argument typecheck 
	 */
	private RuntimeExpressionProvider argumentsTypeCheck;
	private RuntimeParameterExposureProvider<ArgsPointcutExpression<?>> argumentsExposer;
	

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public boolean supports(PointcutExpression<?> pointcutExpression) {
		if (pointcutExpression instanceof ArgsPointcutExpression)
			return true;
		
		if (pointcutExpression instanceof ThisTypePointcutExpression)
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

	/**
	 *  {@inheritDoc}
	 */
	@Override
	public RuntimeExpressionProvider getRuntimeTransformer(RuntimePointcutExpression pointcutExpression) {
		if (pointcutExpression instanceof ArgsPointcutExpression)
			return argumentsTypeCheck;
		
		if (pointcutExpression instanceof ThisTypePointcutExpression)
			return new RuntimeTypeCheck(new ThisLiteral());

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
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public void initialiseRuntimeTransformers(MatchResult<? extends PointcutExpression, ? extends Element> joinpoint) {
		String exceptionParameterName = ((CatchClause) joinpoint.getJoinpoint().parent()).getExceptionParameter().signature().name();
		
		argumentsTypeCheck = new RuntimeSingleArgumentTypeCheck(new NamedTargetExpression(exceptionParameterName));
		argumentsExposer = new CatchClauseArgsParameterExposure(exceptionParameterName);
	}

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public Coordinator<Block> getCoordinator(MatchResult joinpoint, WeavingEncapsulator previous, WeavingEncapsulator next) {
		return new CatchClauseCoordinator(this, joinpoint, previous, next);
	}
	
	@Override
	public RuntimeParameterExposureProvider getRuntimeParameterInjectionProvider(ParameterExposurePointcutExpression expression) {
		if (expression instanceof ThisTypePointcutExpression)
			return new ThisTypeParameterExposure();
		
		if (expression instanceof ArgsPointcutExpression)
			return argumentsExposer;
		
		return null;
	}
}