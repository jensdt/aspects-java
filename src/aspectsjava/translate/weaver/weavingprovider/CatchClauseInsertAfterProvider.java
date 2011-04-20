package aspectsjava.translate.weaver.weavingprovider;

import java.util.List;

import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.aspects.weaver.weavingprovider.WeavingProvider;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;

/**
 * 	This instance of WeavingProvider inserts the given statements at the end of the block
 * 
 * 	@author Jens
 */
public class CatchClauseInsertAfterProvider extends CatchClauseInsertProvider implements WeavingProvider<Block, List<Statement>> {

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	protected void executeWeaving(MatchResult<? extends PointcutExpression, Block> joinpoint, List<Statement> adviceResult) {
		for (Statement<?> st : adviceResult)
			joinpoint.getJoinpoint().addStatement(st.clone());
	}
}