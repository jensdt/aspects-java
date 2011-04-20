package aspectsjava.translate.weaver.weavingprovider;

import java.util.List;

import chameleon.aspects.advice.types.ProceedCall;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
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
	public void executeWeaving(MatchResult<? extends PointcutExpression, Block> joinpoint, List<Statement> adviceResult) {
		Block originalCode = joinpoint.getJoinpoint().clone();
		joinpoint.getJoinpoint().clear();
		
		for (Statement st : adviceResult) {
			List<ProceedCall> descendants = (List<ProceedCall>) st.descendants(ProceedCall.class);
			
			if (descendants.size() == 1 && descendants.get(0).parent() == st) {
				joinpoint.getJoinpoint().addStatement(originalCode.clone());
			}
			else {
				for (ProceedCall pc : descendants) {
					Statement statement = (Statement) pc.nearestAncestor(Statement.class);
					statement.parentLink().getOtherRelation().replace(statement.parentLink(), originalCode.clone().parentLink());	
				}
				
				joinpoint.getJoinpoint().addStatement(st.clone());
			}		
		}
	}
}