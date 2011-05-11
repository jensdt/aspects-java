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
import chameleon.core.element.Element;
import chameleon.core.statement.Block;
import chameleon.core.variable.FormalParameter;
import chameleon.support.variable.LocalVariableDeclarator;

public abstract class ThreePhasedCoordinator<T extends Element<?>> extends AbstractCoordinator<T> {

	public ThreePhasedCoordinator(RuntimeTransformationProvider adviceTransformationProvider, MatchResult<? extends Element> matchResult, WeavingEncapsulator previousWeavingEncapsulator, WeavingEncapsulator nextWeavingEncapsulator) {
		super(adviceTransformationProvider, matchResult, previousWeavingEncapsulator, nextWeavingEncapsulator);
	}
	
	protected RuntimePointcutExpression<?> getRuntimeTree(PointcutExpression<?> initialTree) {
		// Part one: get all the runtime pointcut expressions but maintain the structure (and/or/...)
		SafePredicate<PointcutExpression<?>> runtimeFilter = new SafePredicate<PointcutExpression<?>>() {

			@Override
			public boolean eval(PointcutExpression<?> object) {
				if (!(object instanceof RuntimePointcutExpression))
					return false;
				
				return getAdviceTransformationProvider().supports(object);
			}
		};
		
		// Cast is safe due to the filter
		return (RuntimePointcutExpression<?>) initialTree.getPrunedTree(runtimeFilter);
	}

	protected Block getFirstPhase(RuntimePointcutExpression<?> prunedTree, NamingRegistry<RuntimePointcutExpression> expressionNames) {
		// Select all pointcut expressions we can weave at the start of the method.
		// Currently, this is all except the 'if'-expression, since it can refer to the value of parameters
		RuntimePointcutExpression<?> initialCheckTree = (RuntimePointcutExpression<?>) prunedTree.removeFromTree(IfPointcutExpression.class);
		
		if (initialCheckTree != null)
			return addTest(initialCheckTree, expressionNames);
		else
			return new Block();
	}
	
	protected Block getSecondPhase(PointcutExpression<?> initialTree, List<FormalParameter> parameters) {
		Block secondPhase = new Block();
		
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
			
			List<LocalVariableDeclarator> parameterInjector = getAdviceTransformationProvider().getRuntimeParameterInjectionProvider(exposingParameter).getParameterExposureDeclaration((ParameterExposurePointcutExpression<?>) exposingParameter.origin(), fp);
			secondPhase.addStatements(parameterInjector);
		}
		
		return secondPhase;
	}
	
	protected Block getThirdPhase(RuntimePointcutExpression<?> prunedTree, NamingRegistry<RuntimePointcutExpression> expressionNames) {

		// Get the if-expressions
		RuntimePointcutExpression<?> ifExprTree = (RuntimePointcutExpression<?>) prunedTree.getPrunedTree(IfPointcutExpression.class);
		
		if (ifExprTree != null)
			return addTest(ifExprTree, expressionNames);
		else
			return new Block();
	}
}