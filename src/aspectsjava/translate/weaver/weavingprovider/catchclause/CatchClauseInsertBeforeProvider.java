package aspectsjava.translate.weaver.weavingprovider.catchclause;

import java.util.List;

import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.weaver.weavingprovider.WeavingProvider;
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
	protected void executeWeaving(MatchResult<Block> joinpoint, List<Statement> adviceResult) {
		Block adviceResultBlock = new Block();
		adviceResultBlock.addStatements(adviceResult);
		
		Block finalBlock = new Block();
		finalBlock.addStatement(adviceResultBlock);
		finalBlock.addStatement(joinpoint.getJoinpoint().clone());
		
		joinpoint.getJoinpoint().clear();
		joinpoint.getJoinpoint().addBlock(finalBlock);
		
	}
}