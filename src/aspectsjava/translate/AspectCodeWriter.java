package aspectsjava.translate;

import java.util.List;

import jnome.output.JavaCodeWriter;

import org.rejuse.java.collections.RobustVisitor;

import chameleon.aspects.Aspect;
import chameleon.aspects.advice.Advice;
import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;

public class AspectCodeWriter extends JavaCodeWriter {
	/*
	@Override
	public String toCode(Element element) throws LookupException {
		String result = null;
		if (isAspect(element)) {
			result = toCodeAspect((Aspect) element);
		} else {
			result = super.toCode(element);
		}

		return result;
	}
*/
	private String toCodeAspect(Aspect element) throws LookupException {
		try {
			final StringBuffer code = new StringBuffer();

			code.append("public class " + element.getName() + " {\n");
			indent();
			// Aspect body
			List<Advice> members = element.advices();

			// Members
			new RobustVisitor<Advice>() {
				public Object visit(Advice element) throws LookupException {
					code.append(toCodeAdvice(element));
					code.append("\n\n");
					return null;
				}

				public void unvisit(Advice element, Object undo) {
					// NOP
				}
			}.applyTo(members);
			undent();
			code.append("}");

			return code.toString();
		} catch (LookupException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error();
		}
	}

	protected String toCodeAdvice(Advice element) throws LookupException {
		StringBuffer result = new StringBuffer();
		
		result.append(startLine());
		result.append("public static void advice_" + element.name() + "() ");
		result.append(toCode(element.body()));
		
		
		return result.toString();
	}

	private boolean isAspect(Element element) {
		return element instanceof Aspect;
	}
}
