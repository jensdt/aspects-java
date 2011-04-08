package aspectsjava.output;

import jnome.output.JavaCodeWriter;
import chameleon.aspects.Aspect;
import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;

public class AspectsCodeWriter extends JavaCodeWriter {
	
	@Override
	public String toCode(Element element) throws LookupException {
		String result = null;
		if (isAspect(element))
			result = "";
		else
			result = super.toCode(element);
		
		return result;
	}

	private boolean isAspect(Element element) {
		return element instanceof Aspect;
	}
}
