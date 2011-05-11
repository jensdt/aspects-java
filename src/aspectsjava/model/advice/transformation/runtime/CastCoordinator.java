package aspectsjava.model.advice.transformation.runtime;

import aspectsjava.model.advice.transformation.cast.CastTransformationProvider;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.element.Element;

public class CastCoordinator extends AdviceMethodCoordinator{

	public CastCoordinator(CastTransformationProvider adviceTransformationProvider, MatchResult<? extends Element> matchResult, WeavingEncapsulator previousWeavingEncapsulator, WeavingEncapsulator nextWeavingEncapsulator) {
		super(adviceTransformationProvider, matchResult, previousWeavingEncapsulator, nextWeavingEncapsulator);
	}

	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public CastTransformationProvider getAdviceTransformationProvider() {
		return (CastTransformationProvider) super.getAdviceTransformationProvider();
	}
}