package aspectsjava.translate.weaver.provider.weaving;

import chameleon.core.element.Element;

public class ElementReplaceProvider<T extends Element, U extends Element> implements WeavingProvider<T, U> {
	@Override
	public void execute(T joinpoint, U replacement) {
		joinpoint.parentLink().getOtherRelation().replace(joinpoint.parentLink(), replacement.parentLink());
	}
}
