package aspectsjava.model.advice.transformation.runtime.reflection.fieldaccess;

import aspectsjava.model.advice.transformation.reflection.fieldaccess.ReflectiveFieldRead;
import aspectsjava.model.advice.transformation.runtime.AdviceMethodCoordinator;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.StatementExpression;

/**
 * 	The coordinator for field reads. Implements a single-phased transformation phase (since there are no arguments).
 * 
 * 	@author Jens
 */
public class FieldCoordinator extends AdviceMethodCoordinator {

	/**
	 * 	{@inheritDoc}
	 */
	public FieldCoordinator(ReflectiveFieldRead adviceTransformationProvider, MatchResult matchResult, WeavingEncapsulator previousWeavingEncapsulator, WeavingEncapsulator nextWeavingEncapsulator) {
		super(adviceTransformationProvider, matchResult, previousWeavingEncapsulator, nextWeavingEncapsulator);
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
	public MatchResult<? extends MethodInvocation> getMatchResult() {
		return (MatchResult<? extends MethodInvocation>) super.getMatchResult();
	}
}