package aspectsjava.translate.weaver.weavingprovider;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.weaver.weavingprovider.WeavingProvider;
import chameleon.core.element.Element;

/**
 * 	This instance of WeavingProvider replaces the join point by the given replacement.
 * 	Note that, in case of multiple weavers for the same joinpoint, we must only replace it once - else the joinpoint will change and not all weavers will be run
 * 
 * 	@author Jens De Temmerman
 */
public class ElementReplaceProvider<T extends Element, U extends Element> implements WeavingProvider<T, U> {

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public void execute(MatchResult<T> joinpoint, U replacement, Advice advice, WeavingEncapsulator previous, WeavingEncapsulator next) {
		if (previous == null)
			joinpoint.getJoinpoint().parentLink().getOtherRelation().replace(joinpoint.getJoinpoint().parentLink(), replacement.parentLink());
	}
}
