package aspectsjava.translate.weaver.elementweaver;

import aspectsjava.translate.weaver.TranslationExecutor;
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
	 * 	The next weaver in the chain
	 */
	private Weaver next;
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public void setNext(Weaver next) {
		this.next = next;
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public Weaver next() {
		return next;
	}

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public final void start(Advice advice, MatchResult<? extends PointcutExpression, T> joinpoint, TranslationExecutor adviceTransformation) throws LookupException {
		boolean isHandled = handle(advice, joinpoint, adviceTransformation);
		
		if (!isHandled) {
			if (next() == null)
				throw new RuntimeException("No matching weaver found in chain for joinpoint of type " + joinpoint.getJoinpoint().getClass());
			
			next().start(advice, joinpoint, adviceTransformation);
		}
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public boolean handle(Advice advice, MatchResult<? extends PointcutExpression, T> joinpoint, TranslationExecutor adviceTransformation) throws LookupException {
		if (!supports(joinpoint, advice.type()))
			return false;
		
		weave(advice, joinpoint);
		adviceTransformation.add(advice, getTransformationStrategy(advice, joinpoint));
		return true;
	}
	
	/**
	 * 	{@inheritDoc}
	 * 
	 * 	True if the type of the join point is a sub type of a supported type, false otherwise
	 */
	@Override
	public boolean supports(MatchResult<? extends PointcutExpression, T> result, AdviceTypeEnum advice) throws LookupException {
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
	 * 	{@inheritDoc}
	 */
	@Override
	public void weave(Advice advice, MatchResult<? extends PointcutExpression, T> matchResult) throws LookupException {
		getWeavingProvider().execute(matchResult.getJoinpoint(), getWeaveResultProvider(advice).getWeaveResult(advice, matchResult));
	}
}