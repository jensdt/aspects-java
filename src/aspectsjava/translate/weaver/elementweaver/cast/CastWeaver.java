package aspectsjava.translate.weaver.elementweaver.cast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.rejuse.property.PropertySet;

import aspectsjava.model.advice.transformation.cast.AfterCastTransformationProvider;
import aspectsjava.model.advice.transformation.cast.AfterReturningCastTransformationProvider;
import aspectsjava.model.advice.transformation.cast.AfterThrowingCastTransformationProvider;
import aspectsjava.model.advice.transformation.cast.AroundCastTransformationProvider;
import aspectsjava.model.advice.transformation.cast.BeforeCastTransformationProvider;
import aspectsjava.model.advice.weaving.cast.DefaultCastProvider;
import aspectsjava.translate.weaver.weavingprovider.ElementReplaceProvider;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.translation.AdviceTransformationProvider;
import chameleon.aspects.advice.types.weaving.AdviceWeaveResultProvider;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.weaver.AbstractElementWeaver;
import chameleon.aspects.weaver.weavingprovider.WeavingProvider;
import chameleon.core.expression.MethodInvocation;
import chameleon.support.expression.ClassCastExpression;

/**
 * 	Implements a weaver for cast expressions
 * 
 * 	@author Jens
 *
 */
public class CastWeaver extends AbstractElementWeaver<ClassCastExpression, MethodInvocation> {

	private AdviceWeaveResultProvider<ClassCastExpression, MethodInvocation> adviceWeaveResultProvider = new DefaultCastProvider();
		
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public AdviceWeaveResultProvider<ClassCastExpression, MethodInvocation> getWeaveResultProvider() {
		return adviceWeaveResultProvider;
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public AdviceTransformationProvider getTransformationProvider(Advice advice, MatchResult<ClassCastExpression> joinpoint) {
		PropertySet around = getAround(advice);		
		PropertySet before = getBefore(advice);
		PropertySet after = getAfter(advice);
		PropertySet afterReturning = getAfterReturning(advice);
		PropertySet afterThrowing = getAfterThrowing(advice);
		
		if (before.containsAll(advice.properties().properties()))
			return new BeforeCastTransformationProvider();

		if (after.containsAll(advice.properties().properties()))
			return new AfterCastTransformationProvider();
		
		if (afterReturning.containsAll(advice.properties().properties()))
			return new AfterReturningCastTransformationProvider();
		
		if (afterThrowing.containsAll(advice.properties().properties()))
			return new AfterThrowingCastTransformationProvider();
		
		if (around.containsAll(advice.properties().properties()))
			return new AroundCastTransformationProvider();
		
		throw new RuntimeException();
	}
	
	private WeavingProvider<ClassCastExpression, MethodInvocation> strategy = new ElementReplaceProvider<ClassCastExpression, MethodInvocation>();

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public WeavingProvider<ClassCastExpression, MethodInvocation> getWeavingProvider(Advice advice) {
		return strategy;
	}

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public List<Class<ClassCastExpression>> supportedTypes() {
		return Collections.singletonList(ClassCastExpression.class);
	}

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public List<PropertySet> supportedAdviceProperties(Advice advice) {
		List<PropertySet> supportedProperties = new ArrayList<PropertySet>();
		
		supportedProperties.add(getAround(advice));		
		supportedProperties.add(getBefore(advice));
		supportedProperties.add(getAfter(advice));
		supportedProperties.add(getAfterReturning(advice));
		supportedProperties.add(getAfterThrowing(advice));
		
		return supportedProperties;
	}
}