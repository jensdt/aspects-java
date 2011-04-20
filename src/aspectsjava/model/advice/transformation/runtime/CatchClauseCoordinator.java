package aspectsjava.model.advice.transformation.runtime;

import java.util.List;

import org.rejuse.predicate.SafePredicate;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.runtimetransformation.AbstractCoordinator;
import chameleon.aspects.advice.runtimetransformation.RuntimeTransformationProvider;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.IfPointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ParameterExposurePointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.statement.Block;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.statement.CatchClause;
import chameleon.support.statement.IfThenElseStatement;
import chameleon.support.variable.LocalVariableDeclarator;

public class CatchClauseCoordinator extends AbstractCoordinator<Block> {

	private final Block originalBody;
	
	public CatchClauseCoordinator(RuntimeTransformationProvider adviceTransformationProvider, MatchResult<?, Block> matchResult, WeavingEncapsulator previousWeavingEncapsulator, WeavingEncapsulator nextWeavingEncapsulator) {
		super(adviceTransformationProvider, matchResult, previousWeavingEncapsulator, nextWeavingEncapsulator);
		this.originalBody = matchResult.getJoinpoint().clone();
	}

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public void transform(Block element, List<FormalParameter> parameters) {
		if (element == null)
			return;
		
		PointcutExpression<?> initialTree = (PointcutExpression<?>) getMatchResult().getExpression();
		
		// Part one: get all the runtime pointcut expressions but maintain the structure (and/or/...)
		SafePredicate<PointcutExpression<?>> filter = new SafePredicate<PointcutExpression<?>>() {

			@Override
			public boolean eval(PointcutExpression<?> object) {
				if (!(object instanceof RuntimePointcutExpression))
					return false;
				
				return getAdviceTransformationProvider().supports(object);
			}
		};
		
		// Cast is safe due to the filter
		RuntimePointcutExpression<?> prunedTree = (RuntimePointcutExpression<?>) initialTree.getPrunedTree(filter);
		
		if (prunedTree == null)
			return;
		
		// Now, select all pointcut expressions we can weave at the start of the method.
		// Currently, this is all except the 'if'-expression, since it can refer to the value of parameters
		// Cast is safe, since we already pruned to RuntimePCEs
		RuntimePointcutExpression<?> initialCheckTree = (RuntimePointcutExpression<?>) prunedTree.removeFromTree(IfPointcutExpression.class);
		
		NamingRegistry<RuntimePointcutExpression> expressionNames = new NamingRegistry<RuntimePointcutExpression>();

		IfThenElseStatement firstTest = null;
		if (initialCheckTree != null) {
			firstTest = getTest(initialCheckTree, expressionNames);
		}
		
		// Part two: inject the parameters
		Block secondPart = new Block();
		if (!parameters.isEmpty()) {
			SafePredicate<PointcutExpression<?>> parameterInjectionFilter = new SafePredicate<PointcutExpression<?>>() {

				@Override
				public boolean eval(PointcutExpression<?> object) {
					if (!(object instanceof ParameterExposurePointcutExpression))
						return false;
					
					return getAdviceTransformationProvider().supports(object);
				}
			};
			
			// Cast is safe due to the filter
			ParameterExposurePointcutExpression<?> parameterTree = (ParameterExposurePointcutExpression<?>) initialTree.getPrunedTree(parameterInjectionFilter);

			for (FormalParameter fp : parameters) {
				
				ParameterExposurePointcutExpression<?> exposingParameter = ((ParameterExposurePointcutExpression<?>) parameterTree.origin()).findExpressionFor(fp);
				
				LocalVariableDeclarator parameterInjector = getAdviceTransformationProvider().getRuntimeParameterInjectionProvider(exposingParameter).getParameterExposureDeclaration(exposingParameter.origin(), fp);
				secondPart.addStatement(parameterInjector);
			}
		}
				

		// Get the if-expressions
		RuntimePointcutExpression<?> ifExprTree = (RuntimePointcutExpression<?>) prunedTree.getPrunedTree(IfPointcutExpression.class);
		if (ifExprTree != null) {
			IfThenElseStatement secondTest = getTest(ifExprTree, expressionNames);
			secondTest.setElseStatement(element.clone());
			secondPart.addBlock(addTest(ifExprTree, expressionNames, secondTest));
		} else {
			secondPart.addBlock(element.clone());
		}
		
		Block finalBody = new Block();
		
		// Now we determine the final method body
		if (firstTest != null) {
			firstTest.setElseStatement(secondPart);
			finalBody = addTest(initialCheckTree, expressionNames, firstTest);
		} else {
			finalBody = secondPart;
		}
				
		element.clear();
		element.addBlock(finalBody);
	}
	
	@Override
	protected Block getTerminateBody() {
		return originalBody.clone();
	}

	/**
	 * 	{@inheritDoc}
	 */
	public MatchResult<? extends PointcutExpression, ? extends Block> getMatchResult() {
		return (MatchResult<? extends PointcutExpression, ? extends Block>) super.getMatchResult();
	}
}
