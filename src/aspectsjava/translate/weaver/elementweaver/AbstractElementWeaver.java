package aspectsjava.translate.weaver.elementweaver;

import java.util.Iterator;
import java.util.List;

import org.rejuse.property.PropertySet;

import aspectsjava.translate.weaver.TranslationExecutor;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
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
		if (!supports(advice, joinpoint))
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
	public boolean supports(Advice advice, MatchResult<? extends PointcutExpression, T> result) throws LookupException {
		boolean supports = false;
		
		// Get all supported property sets for the advice
		List<PropertySet> supportedProperties = supportedAdviceProperties(advice);
		// Now, for each property set, ALL properties of the given advice must be in the supported set
		Iterator<PropertySet> propertySetIterator = supportedProperties.iterator();
		while (!supports && propertySetIterator.hasNext()) {
			supports = propertySetIterator.next().containsAll(advice.properties().properties());
		}
		
		if (!supports)
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
		getWeavingProvider(advice).execute(matchResult, getWeaveResultProvider(advice).getWeaveResult(advice, matchResult), advice);
	}
	
	protected PropertySet getAround(Advice advice) {
		return new PropertySet().with(advice.language().property("advicetype.around"));		
	}
	
	protected PropertySet getBefore(Advice advice) {
		return new PropertySet().with(advice.language().property("advicetype.before"));
	}
	
	protected PropertySet getAfter(Advice advice) {
		return new PropertySet().with(advice.language().property("advicetype.after"));
	}
	
	protected PropertySet getAfterReturning(Advice advice) {
		PropertySet afterReturning = new PropertySet();
		afterReturning.add(advice.language().property("advicetype.after"));
		afterReturning.add(advice.language().property("advicetype.returning"));
	
		return afterReturning;
	}
	
	protected PropertySet getAfterThrowing(Advice advice) {
		PropertySet afterThrowing = new PropertySet();
		afterThrowing.add(advice.language().property("advicetype.after"));
		afterThrowing.add(advice.language().property("advicetype.throwing"));
		
		return afterThrowing;
	}
}
