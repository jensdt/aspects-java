package aspectsjava.translate.weaver.weavingprovider;

import java.util.List;


import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeExpressionProvider;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;

/**
 * 	This instance of WeavingProvider inserts the given statements at the front of the block
 * 
 * 	@author Jens
 */
public class InsertBeforeProvider extends InsertProvider implements WeavingProvider<Block, List<Statement>> {

	@Override
	protected void executeWeaving(MatchResult<? extends PointcutExpression, Block> joinpoint, List<Statement> adviceResult) {
		for (int i = adviceResult.size()-1; i >= 0; i--)
			joinpoint.getJoinpoint().addInFront(adviceResult.get(i).clone());
	}
}
