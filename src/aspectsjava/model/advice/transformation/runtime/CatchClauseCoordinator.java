package aspectsjava.model.advice.transformation.runtime;

import java.util.List;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.runtimetransformation.RuntimeTransformationProvider;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.IfPointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;
import chameleon.core.variable.FormalParameter;
import chameleon.support.statement.IfThenElseStatement;

public class CatchClauseCoordinator extends ThreePhasedCoordinator<Block> {

	private final Block originalBody;
	
	public CatchClauseCoordinator(RuntimeTransformationProvider adviceTransformationProvider, MatchResult<Block> matchResult, WeavingEncapsulator previousWeavingEncapsulator, WeavingEncapsulator nextWeavingEncapsulator) {
		super(adviceTransformationProvider, matchResult, previousWeavingEncapsulator, nextWeavingEncapsulator);
		this.originalBody = matchResult.getJoinpoint().clone();
	}

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public void transform(Block element, List<FormalParameter> parameters) {
		PointcutExpression<?> initialTree = (PointcutExpression<?>) getMatchResult().getExpression();

		RuntimePointcutExpression<?> prunedTree = getRuntimeTree(initialTree);
		
		if (prunedTree == null)
			return;
		
		// Now, select all pointcut expressions we can weave at the start of the method.
		// Currently, this is all except the 'if'-expression, since it can refer to the value of parameters
		// Cast is safe, since we already pruned to RuntimePCEs
		RuntimePointcutExpression<?> initialCheckTree = (RuntimePointcutExpression<?>) prunedTree.removeFromTree(IfPointcutExpression.class);
		
		NamingRegistry<RuntimePointcutExpression> expressionNames = new NamingRegistry<RuntimePointcutExpression>();

		IfThenElseStatement firstTest = null;
		if (initialCheckTree != null) {
			firstTest = getTest(initialCheckTree, expressionNames);
		}
		
		// Part two: inject the parameters
		Block secondPart = getSecondPhase(initialTree, parameters);

		// Get the if-expressions
		RuntimePointcutExpression<?> ifExprTree = (RuntimePointcutExpression<?>) prunedTree.getPrunedTree(IfPointcutExpression.class);
		if (ifExprTree != null) {
			IfThenElseStatement secondTest = getTest(ifExprTree, expressionNames);
			secondTest.setElseStatement(element.clone());
			secondPart.addBlock(addTest(ifExprTree, expressionNames, secondTest));
		} else {
			secondPart.addBlock(element.clone());
		}
		
		Block finalBody = new Block();
		
		// Now we determine the final method body
		if (firstTest != null) {
			firstTest.setElseStatement(secondPart);
			finalBody = addTest(initialCheckTree, expressionNames, firstTest);
		} else {
			finalBody = secondPart;
		}
				
		element.clear();
		element.addBlock(finalBody);
	}
	
	@Override
	protected Statement getTerminateBody() {
		return originalBody.clone();
	}

	/**
	 * 	{@inheritDoc}
	 */
	public MatchResult<? extends Block> getMatchResult() {
		return (MatchResult<? extends Block>) super.getMatchResult();
	}
}
