package aspectsjava.translate.weaver.elementweaver.methodinvocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.rejuse.property.PropertySet;

import aspectsjava.translate.weaver.elementweaver.AbstractElementWeaver;
import aspectsjava.translate.weaver.weavingprovider.ElementReplaceProvider;
import aspectsjava.translate.weaver.weavingprovider.WeavingProvider;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.translation.AdviceTransformationProvider;
import chameleon.aspects.advice.types.translation.reflection.methodInvocation.AfterReflectiveMethodInvocation;
import chameleon.aspects.advice.types.translation.reflection.methodInvocation.AfterReturningReflectiveMethodInvocation;
import chameleon.aspects.advice.types.translation.reflection.methodInvocation.AfterThrowingReflectiveMethodInvocation;
import chameleon.aspects.advice.types.translation.reflection.methodInvocation.AroundReflectiveMethodInvocation;
import chameleon.aspects.advice.types.translation.reflection.methodInvocation.BeforeReflectiveMethodInvocation;
import chameleon.aspects.advice.types.weaving.AdviceWeaveResultProvider;
import chameleon.aspects.advice.types.weaving.methodInvocation.DefaultReflectiveMethodInvocation;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.expression.MethodInvocation;

/**
 * 	This weaver weaves method invocations
 * 
 * 	@author Jens
 *
 */
public class MethodInvocationWeaver extends AbstractElementWeaver<MethodInvocation, MethodInvocation> {

	/**
	 *  {@inheritDoc}
	 */
	@Override
	public List<Class<MethodInvocation>> supportedTypes() {
		return Collections.singletonList(MethodInvocation.class);
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public List<PropertySet> supportedAdviceProperties(Advice advice) {
		List<PropertySet> supportedProperties = new ArrayList<PropertySet>();
		
		supportedProperties.add(getAround(advice));		
		supportedProperties.add(getBefore(advice));
		supportedProperties.add(getAfter(advice));
		supportedProperties.add(getAfterThrowing(advice));
		supportedProperties.add(getAfterReturning(advice));
		
		return supportedProperties;
	}
		
	private ElementReplaceProvider<MethodInvocation, MethodInvocation> strategy = new ElementReplaceProvider<MethodInvocation, MethodInvocation>();
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public WeavingProvider<MethodInvocation, MethodInvocation> getWeavingProvider(Advice advice) {
		return strategy;
	}
	
	private AdviceWeaveResultProvider<MethodInvocation, MethodInvocation> weavingAdviceType = new DefaultReflectiveMethodInvocation();
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public AdviceWeaveResultProvider<MethodInvocation, MethodInvocation> getWeaveResultProvider(Advice advice) {
		return weavingAdviceType;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public AdviceTransformationProvider getTransformationStrategy(Advice advice, MatchResult joinpoint) {	
		PropertySet around = getAround(advice);		
		PropertySet before = getBefore(advice);
		PropertySet after = getAfter(advice);
		PropertySet afterReturning = getAfterReturning(advice);
		PropertySet afterThrowing = getAfterThrowing(advice);
		
		if (around.containsAll(advice.properties().properties()))
			return new AroundReflectiveMethodInvocation(joinpoint);
		
		if (before.containsAll(advice.properties().properties()))
			return new BeforeReflectiveMethodInvocation(joinpoint);
		
		if (after.containsAll(advice.properties().properties()))
			return new AfterReflectiveMethodInvocation(joinpoint);
		
		if (afterThrowing.containsAll(advice.properties().properties()))
			return new AfterThrowingReflectiveMethodInvocation(joinpoint);
			
		if (afterReturning.containsAll(advice.properties().properties()))
			return new AfterReturningReflectiveMethodInvocation(joinpoint);
			
		throw new RuntimeException();
	}
}
