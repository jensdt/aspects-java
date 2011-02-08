package aspectsjava.model.expression;

import java.util.List;

import chameleon.aspects.advice.Advice;
import chameleon.core.expression.Expression;
import chameleon.oo.type.TypeReference;
import chameleon.support.expression.LiteralWithTypeReference;

public class ProceedCall extends LiteralWithTypeReference {

	public ProceedCall(String value) {
		super(value);
	}

	public ProceedCall() {
		this("proceed");
	}

	@Override
	public TypeReference getTypeReference() {
		if (super.getTypeReference() == null) {
			List<Advice> parentAdvice = ancestors(Advice.class);
			
			if (!parentAdvice.isEmpty())
				setTypeReference( parentAdvice.get(0).returnType().clone());
		}
		
		return super.getTypeReference();
	}

	@Override
	public Expression clone() {
		return new ProceedCall();
	}

}
