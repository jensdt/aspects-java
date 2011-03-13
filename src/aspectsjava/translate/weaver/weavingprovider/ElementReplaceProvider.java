package aspectsjava.translate.weaver.weavingprovider;

import chameleon.core.element.Element;

/**
 * 	This instance of WeavingProvider replaces the join point by the given replacement
 * 
 * 	@author Jens
 */
public class ElementReplaceProvider<T extends Element, U extends Element> implements WeavingProvider<T, U> {

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public void execute(T joinpoint, U replacement) {
		joinpoint.parentLink().getOtherRelation().replace(joinpoint.parentLink(), replacement.parentLink());
	}
}
