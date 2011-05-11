package aspectsjava.model.advice.transformation.cast;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.core.expression.Expression;
import chameleon.core.statement.Block;
import chameleon.support.statement.ReturnStatement;

public class BeforeCastTransformationProvider extends CastTransformationProvider {
	@Override
	protected Block getBody(WeavingEncapsulator next) {
		Block adviceBody = new Block();

		/*
		 *	Add the advice-body itself 
		 */
		adviceBody.addBlock(getAdvice().body().clone());
		
		/*
		 *	Create the proceed call
		 */
		Expression<?> getValueInvocation = getNextInvocation(next);
		
		/*
		 * 	Add the return statement
		 */
		adviceBody.addStatement(new ReturnStatement(getValueInvocation));
		
		return adviceBody;
	}
}
