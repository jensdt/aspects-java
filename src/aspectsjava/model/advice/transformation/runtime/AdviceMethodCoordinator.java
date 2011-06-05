package aspectsjava.model.advice.transformation.runtime;

import java.util.List;

import aspectsjava.model.advice.transformation.CreateAdviceMethodTransformationProvider;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.element.Element;
import chameleon.core.method.RegularImplementation;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;
import chameleon.core.variable.FormalParameter;
import chameleon.support.member.simplename.method.NormalMethod;
import chameleon.support.statement.ReturnStatement;

public class AdviceMethodCoordinator extends ThreePhasedCoordinator<NormalMethod> {
	
	public AdviceMethodCoordinator(CreateAdviceMethodTransformationProvider adviceTransformationProvider, MatchResult<? extends Element> matchResult, WeavingEncapsulator previousWeavingEncapsulator, WeavingEncapsulator nextWeavingEncapsulator) {
		super(adviceTransformationProvider, matchResult, previousWeavingEncapsulator, nextWeavingEncapsulator);
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public void transform(NormalMethod element, List<FormalParameter> parameters) {
		PointcutExpression<?> initialTree = (PointcutExpression<?>) getMatchResult().getExpression();
		
		RuntimePointcutExpression<?> prunedTree = getRuntimeTree(initialTree);
		
		if (prunedTree == null)
			return;
		
		NamingRegistry<RuntimePointcutExpression> expressionNames = new NamingRegistry<RuntimePointcutExpression>();

		// Get the first phase - runtime checks without if
		Block finalBody = getFirstPhase(prunedTree, expressionNames);
		
		// Get the second phase - parameter injection
		if (!parameters.isEmpty()) {
			finalBody.addBlock(getSecondPhase(initialTree, parameters));
		}

		// Part Three: add the check(s) for the 'if' expressions
		finalBody.addBlock(getThirdPhase(prunedTree, expressionNames));
		
		finalBody.addBlock(element.body());
		element.setImplementation(new RegularImplementation(finalBody));
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	protected Statement getTerminateBody() {
		Block terminateBody = new Block();
		terminateBody.addStatement(new ReturnStatement(getAdviceTransformationProvider().getNextInvocation(getNextWeavingEncapsulator())));
		
		return terminateBody;
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public CreateAdviceMethodTransformationProvider getAdviceTransformationProvider() {
		return (CreateAdviceMethodTransformationProvider) super.getAdviceTransformationProvider();
	}
}