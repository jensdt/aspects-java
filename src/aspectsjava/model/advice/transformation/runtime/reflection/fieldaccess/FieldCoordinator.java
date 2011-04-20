package aspectsjava.model.advice.transformation.runtime.reflection.fieldaccess;

import java.util.List;

import org.rejuse.predicate.SafePredicate;

import aspectsjava.model.advice.transformation.reflection.fieldaccess.ReflectiveFieldRead;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.runtimetransformation.AbstractCoordinator;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.AbstractPointcutExpression;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.method.RegularImplementation;
import chameleon.core.statement.Block;
import chameleon.core.variable.FormalParameter;
import chameleon.support.member.simplename.method.NormalMethod;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.ReturnStatement;

/**
 * 	The coordinator for field reads. Implements a single-phased transformation phase (since there are no arguments).
 * 
 * 	@author Jens
 */
public class FieldCoordinator extends AbstractCoordinator<NormalMethod> {

	/**
	 * 	{@inheritDoc}
	 */
	public FieldCoordinator(ReflectiveFieldRead adviceTransformationProvider, MatchResult matchResult, WeavingEncapsulator previousWeavingEncapsulator, WeavingEncapsulator nextWeavingEncapsulator) {
		super(adviceTransformationProvider, matchResult, previousWeavingEncapsulator, nextWeavingEncapsulator);
	}

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public void transform(NormalMethod element, List<FormalParameter> parameters) {
		if (element == null)
			return;
		
		// Part one: get all the runtime pointcut expressions but maintain the structure (and/or/...)
		SafePredicate<PointcutExpression<?>> filter = new SafePredicate<PointcutExpression<?>>() {

			@Override
			public boolean eval(PointcutExpression<?> object) {
				if (!(object instanceof RuntimePointcutExpression))
					return false;
				
				return getAdviceTransformationProvider().supports((RuntimePointcutExpression<?>) object);
			}
		};
		
		// Cast is safe due to the filter
		RuntimePointcutExpression<?> prunedTree = (RuntimePointcutExpression<?>) ((PointcutExpression<?>) getMatchResult().getExpression()).getPrunedTree(filter);
		
		if (prunedTree == null)
			return;
		
		NamingRegistry<RuntimePointcutExpression> expressionNames = new NamingRegistry<RuntimePointcutExpression>();
		Block finalBody = addTest(prunedTree, expressionNames);
		
		finalBody.addBlock(element.body());
		element.setImplementation(new RegularImplementation(finalBody));
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	protected Block getTerminateBody() {
		Block terminateBody = new Block();
		terminateBody.addStatement(new ReturnStatement(getProceedInvocation()));
		
		return terminateBody;
	}
	
	/**
	 * 	Get the proceed invocation
	 * 
	 * 	@return	The proceed invocation
	 */
	private RegularMethodInvocation getProceedInvocation() {
		return getAdviceTransformationProvider().createGetFieldValueInvocation(new NamedTarget(getAdviceTransformationProvider().getAdvice().aspect().name()), new NamedTargetExpression(getAdviceTransformationProvider().objectParamName), new NamedTargetExpression(getAdviceTransformationProvider().fieldName));
	}

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public ReflectiveFieldRead getAdviceTransformationProvider() {
		return (ReflectiveFieldRead) super.getAdviceTransformationProvider();
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	public MatchResult<? extends AbstractPointcutExpression, ? extends MethodInvocation> getMatchResult() {
		return (MatchResult<? extends AbstractPointcutExpression, ? extends MethodInvocation>) super.getMatchResult();
	}
}