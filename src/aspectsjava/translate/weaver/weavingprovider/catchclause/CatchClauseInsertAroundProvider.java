package aspectsjava.translate.weaver.weavingprovider.catchclause;

import java.util.List;

import chameleon.aspects.advice.types.ProceedCall;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.weaver.weavingprovider.WeavingProvider;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;

/**
 * 	This instance of WeavingProvider inserts the given statements around the block (replace the block with the given statements, insert the block at proceedcalls)
 * 
 * 	@author Jens
 */
public class CatchClauseInsertAroundProvider extends CatchClauseInsertProvider implements WeavingProvider<Block, List<Statement>> {

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public void executeWeaving(MatchResult<Block> joinpoint, List<Statement> adviceResult) {
		Block originalCode = joinpoint.getJoinpoint().clone();
		joinpoint.getJoinpoint().clear();
		
		Block adviceResultBlock = new Block();
		adviceResultBlock.addStatements(adviceResult);
		
		List<ProceedCall> descendants = (List<ProceedCall>) adviceResultBlock.descendants(ProceedCall.class);

		for (ProceedCall pc : descendants) {
			Statement statement = (Statement) pc.nearestAncestor(Statement.class);
			statement.parentLink().getOtherRelation().replace(statement.parentLink(), originalCode.clone().parentLink());	
		}
				
		joinpoint.getJoinpoint().addBlock(adviceResultBlock);
	}
}