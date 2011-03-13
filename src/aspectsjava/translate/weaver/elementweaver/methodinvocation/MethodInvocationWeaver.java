package aspectsjava.translate.weaver.elementweaver.methodinvocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aspectsjava.translate.weaver.elementweaver.AbstractElementWeaver;
import aspectsjava.translate.weaver.weavingprovider.ElementReplaceProvider;
import aspectsjava.translate.weaver.weavingprovider.WeavingProvider;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.AdviceTypeEnum;
import chameleon.aspects.advice.types.translation.AdviceTransformationProvider;
import chameleon.aspects.advice.types.translation.methodInvocation.AfterReflectiveMethodInvocation;
import chameleon.aspects.advice.types.translation.methodInvocation.AfterReturningReflectiveMethodInvocation;
import chameleon.aspects.advice.types.translation.methodInvocation.AfterThrowingReflectingMethodInvocation;
import chameleon.aspects.advice.types.translation.methodInvocation.AroundReflectiveMethodInvocation;
import chameleon.aspects.advice.types.translation.methodInvocation.BeforeReflectiveMethodInvocation;
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
	public List<AdviceTypeEnum> supportedAdviceTypes() {
		List<AdviceTypeEnum> supported = new ArrayList<AdviceTypeEnum>();
		
		supported.add(AdviceTypeEnum.AFTER);
		supported.add(AdviceTypeEnum.AFTER_RETURNING);
		supported.add(AdviceTypeEnum.AFTER_THROWING);
		supported.add(AdviceTypeEnum.BEFORE);
		supported.add(AdviceTypeEnum.AROUND);
		
		return supported;
	}
		
	private ElementReplaceProvider<MethodInvocation, MethodInvocation> strategy = new ElementReplaceProvider<MethodInvocation, MethodInvocation>();
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public WeavingProvider<MethodInvocation, MethodInvocation> getWeavingProvider() {
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
		switch (advice.type()) {
			case AFTER:
				return new AfterReflectiveMethodInvocation(joinpoint);
			case AFTER_RETURNING:		
				return new AfterReturningReflectiveMethodInvocation(joinpoint);
			case AFTER_THROWING:
				return new AfterThrowingReflectingMethodInvocation(joinpoint);
			case AROUND:
				return new AroundReflectiveMethodInvocation(joinpoint);
			case BEFORE:		
				return new BeforeReflectiveMethodInvocation(joinpoint);
			default:
					// Note: normally, this can't occur since the weaver checks the supported types
					throw new RuntimeException("Unsupported advice type: " + advice.type());
		}
	}
}
