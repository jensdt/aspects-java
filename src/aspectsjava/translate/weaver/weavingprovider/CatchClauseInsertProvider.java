package aspectsjava.translate.weaver.weavingprovider;

import java.util.List;

import aspectsjava.model.advice.transformation.runtime.CatchClauseCoordinator;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeIfCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeSingleArgumentTypeCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeTypeCheck;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.runtimetransformation.Coordinator;
import chameleon.aspects.advice.runtimetransformation.RuntimeTransformationProvider;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeExpressionProvider;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.aspects.pointcut.expression.runtime.ArgsPointcutExpression;
import chameleon.aspects.pointcut.expression.runtime.IfPointcutExpression;
import chameleon.aspects.pointcut.expression.runtime.ThisTypePointcutExpression;
import chameleon.aspects.weaver.weavingprovider.WeavingProvider;
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
public abstract class CatchClauseInsertProvider implements WeavingProvider<Block, List<Statement>>, RuntimeTransformationProvider {
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public void execute(MatchResult<? extends PointcutExpression, Block> joinpoint, List<Statement> adviceResult, Advice advice, WeavingEncapsulator previous, WeavingEncapsulator next) {
		Block originalBody = joinpoint.getJoinpoint().clone();
		executeWeaving(joinpoint, adviceResult);
		
		// Register the runtime transformers that are needed
		argumentsTypeCheck = new RuntimeSingleArgumentTypeCheck(new NamedTargetExpression(((CatchClause) joinpoint.getJoinpoint().parent()).getExceptionParameter().signature().name()));
		
		Coordinator<Block> catchCoordinator = new CatchClauseCoordinator(this, joinpoint, previous, next, originalBody);
		catchCoordinator.transform(joinpoint.getJoinpoint(), advice.formalParameters());
	}
	
	/**
	 * 	The argument typecheck 
	 */
	private RuntimeExpressionProvider argumentsTypeCheck;
	
	/**
	 * 	Execute the actual weaving
	 * 
	 * 	@param 	joinpoint
	 * 			The joinpoint to weave at
	 * 	@param 	adviceResult
	 * 			The code to weave in
	 */
	protected abstract void executeWeaving(MatchResult<? extends PointcutExpression, Block> joinpoint, List<Statement> adviceResult);
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public boolean supports(RuntimePointcutExpression pointcutExpression) {
		if (pointcutExpression instanceof ArgsPointcutExpression)
			return true;
		
		if (pointcutExpression instanceof ThisTypePointcutExpression)
			return true;
		
		if (pointcutExpression instanceof IfPointcutExpression)
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

		return null;
	}	
}