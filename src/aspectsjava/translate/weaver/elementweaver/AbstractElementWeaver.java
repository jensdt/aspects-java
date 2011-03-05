package aspectsjava.translate.weaver.elementweaver;

import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.AdviceTypeEnum;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;

/**
 *	For general info about how the elementWeavers work, see Weaver.java 
 * 
 * 	@author Jens
 *
 */
public abstract class AbstractElementWeaver<T extends Element, U> implements Weaver<T, U> {
	
	/**
	 * 	{@inheritDoc}
	 * 
	 * 	True if the type of the join point is a sub type of a supported type, false otherwise
	 */
	public boolean supports(MatchResult<? extends PointcutExpression, T> result, AdviceTypeEnum advice) {
		if (!supportedAdviceTypes().contains(advice))
			return false;
		
		Class<? extends Element> c = result.getJoinpoint().getClass();
		
		for (Class<T> supported : supportedTypes()) {
			if (supported.isAssignableFrom(c))
				return true;
		}
		
		return false;
	}
	
	/**
	 * 	Execute the weaving itself. This delegates the weaving to two other objects:
	 * 		-	One to get what to weave
	 * 		- 	One that does the actual weaving
	 */
	public void weave(CompilationUnit compilationUnit, Advice advice, MatchResult<? extends PointcutExpression, T> matchResult) throws LookupException {
		getWeavingProvider().execute(matchResult.getJoinpoint(), getWeaveResultProvider(advice).getWeaveResult(compilationUnit, advice, matchResult));
	}
}
