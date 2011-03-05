package aspectsjava.translate.weaver.provider.weaving;

import chameleon.core.element.Element;

public interface WeavingProvider<T extends Element, U> {
	public void execute(T joinpoint, U adviceResult);
}
