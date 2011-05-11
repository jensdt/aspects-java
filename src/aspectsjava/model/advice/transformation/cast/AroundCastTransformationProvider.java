package aspectsjava.model.advice.transformation.cast;

import java.util.List;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.types.ProceedCall;
import chameleon.core.expression.Expression;
import chameleon.core.statement.Block;
import chameleon.exception.ChameleonProgrammerException;

public class AroundCastTransformationProvider extends CastTransformationProvider {
	@Override
	protected Block getBody(WeavingEncapsulator next) {
		Block adviceBody = getAdvice().body().clone();
		
		// Replace each proceed call to the method call
		List<ProceedCall> proceedCalls = adviceBody.descendants(ProceedCall.class);
		
		for (ProceedCall pc : proceedCalls) {
			// Select the correct parameter for the proceed call
			if (pc.getActualParameters().size() > 1)
				throw new ChameleonProgrammerException("Proceed invocations for casts may have at most 1 parameter");
			
			Expression<?> getValueInvocation;
			if (pc.getActualParameters().isEmpty())
				getValueInvocation = getNextInvocation(next);
			else
				getValueInvocation = getNextInvocation(next, pc.getActualParameters().get(0).clone());

					
			pc.parentLink().getOtherRelation().replace(pc.parentLink(), getValueInvocation.parentLink());
		}
		
		return adviceBody;
	}
}
