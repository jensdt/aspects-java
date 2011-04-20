package aspectsjava.translate.weaver.weavingprovider;

import java.util.List;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.runtimetransformation.Coordinator;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeParameterExposureProvider;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ParameterExposurePointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.aspects.weaver.weavingprovider.WeavingProvider;
import chameleon.core.element.Element;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;

/**
 * 	This instance of WeavingProvider inserts the given statements at the front of the block
 * 
 * 	@author Jens
 */
public class CatchClauseInsertBeforeProvider extends CatchClauseInsertProvider implements WeavingProvider<Block, List<Statement>> {

	/**
	 *  {@inheritDoc}
	 */
	@Override
	protected void executeWeaving(MatchResult<? extends PointcutExpression, Block> joinpoint, List<Statement> adviceResult) {
		for (int i = adviceResult.size()-1; i >= 0; i--)
			joinpoint.getJoinpoint().addInFront(adviceResult.get(i).clone());
	}
}