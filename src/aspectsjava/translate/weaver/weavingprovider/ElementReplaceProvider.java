package aspectsjava.translate.weaver.weavingprovider;

import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
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
	public void execute(MatchResult<? extends PointcutExpression, T> joinpoint, U replacement, Advice advice) {
		joinpoint.getJoinpoint().parentLink().getOtherRelation().replace(joinpoint.getJoinpoint().parentLink(), replacement.parentLink());
	}
}
