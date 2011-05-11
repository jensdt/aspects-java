package aspectsjava.model.advice.transformation.cast;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.core.expression.Expression;
import chameleon.core.statement.Block;
import chameleon.support.statement.FinallyClause;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.TryStatement;

public class AfterCastTransformationProvider extends CastTransformationProvider {
	@Override
	protected Block getBody(WeavingEncapsulator next) {
		Block innerAdviceBody = new Block();

		/*
		 *	Create the proceed call
		 */
		Expression<?> getValueInvocation = getNextInvocation(next);
		
		/*
		 * 	Add the return statement
		 */
		innerAdviceBody.addStatement(new ReturnStatement(getValueInvocation));
		
		/*
		 * 	Wrap that in a try { } finally { } statement
		 */
		TryStatement tryStatement = new TryStatement(innerAdviceBody);
		tryStatement.setFinallyClause(new FinallyClause(getAdvice().body().clone()));
		
		Block adviceBody = new Block();
		adviceBody.addStatement(tryStatement);
		
		return adviceBody;
	}

}
