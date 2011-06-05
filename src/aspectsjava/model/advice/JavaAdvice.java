package aspectsjava.model.advice;

import chameleon.aspects.advice.Advice;
import chameleon.core.element.Element;
import chameleon.core.statement.Block;
import chameleon.oo.type.TypeReference;

public class JavaAdvice<E extends JavaAdvice<E>> extends Advice<E> {

	public JavaAdvice() {
		
	}
	
	public JavaAdvice(TypeReference returnType) {
		super(returnType);
	}

	@Override
	public Block body() {
		return (Block) super.body();
	}
	
	@Override
	public void setBody(Element element) {
		if (!(element instanceof Block))
			throw new IllegalArgumentException();
		
		super.setBody(element);
	}
	
	@Override
	protected Advice cloneThis() {
		return new JavaAdvice();
	}
}
