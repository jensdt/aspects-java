package aspectsjava.translate.weaver.weavingprovider;

import java.util.List;

import chameleon.aspects.advice.types.ProceedCall;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;

/**
 * 	This instance of WeavingProvider inserts the given statements at the end of the block
 * 
 * 	@author Jens
 */
public class InsertAroundProvider extends InsertProvider implements WeavingProvider<Block, List<Statement>> {

	@Override
	public void executeWeaving(MatchResult<? extends PointcutExpression, Block> joinpoint, List<Statement> adviceResult) {
		Block originalCode = joinpoint.getJoinpoint().clone();
		joinpoint.getJoinpoint().clear();
		
		for (Statement st : adviceResult) {
			List<ProceedCall> descendants = (List<ProceedCall>) st.descendants(ProceedCall.class);
			for (ProceedCall pc : descendants) {
				Statement statement = (Statement) pc.nearestAncestor(Statement.class);
				statement.parentLink().getOtherRelation().replace(statement.parentLink(), originalCode.clone().parentLink());
			}
				
			joinpoint.getJoinpoint().addStatement(st.clone());
		}
	}
}