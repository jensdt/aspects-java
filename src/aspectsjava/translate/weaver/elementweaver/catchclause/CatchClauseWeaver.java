package aspectsjava.translate.weaver.elementweaver.catchclause;

import java.util.Collections;
import java.util.List;

import aspectsjava.translate.weaver.elementweaver.AbstractElementWeaver;
import aspectsjava.translate.weaver.weavingprovider.ElementReplaceProvider;
import aspectsjava.translate.weaver.weavingprovider.WeavingProvider;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.AdviceTypeEnum;
import chameleon.aspects.advice.types.translation.AdviceTransformationProvider;
import chameleon.aspects.advice.types.translation.NoOperationTranslator;
import chameleon.aspects.advice.types.weaving.AdviceWeaveResultProvider;
import chameleon.aspects.advice.types.weaving.catchclause.InsideCatchClause;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.element.Element;
import chameleon.support.statement.CatchClause;

/**
 * 	This weaver weaves catch clauses
 * 
 * 	@author Jens
 *
 */
public class CatchClauseWeaver extends AbstractElementWeaver<CatchClause, CatchClause> {

	/**
	 *  {@inheritDoc}
	 */
	@Override
	public AdviceTransformationProvider getTransformationStrategy(Advice advice, MatchResult<? extends PointcutExpression, ? extends Element> joinpoint) {
		return new NoOperationTranslator();
	}

	/**
	 *  {@inheritDoc}
	 */
	@Override
	public List<Class<CatchClause>> supportedTypes() {
		return Collections.singletonList(CatchClause.class);
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public List<AdviceTypeEnum> supportedAdviceTypes() {
		return Collections.singletonList(AdviceTypeEnum.INSIDE);
	}

	private ElementReplaceProvider<CatchClause, CatchClause> strategy = new ElementReplaceProvider<CatchClause, CatchClause>();
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public WeavingProvider<CatchClause, CatchClause> getWeavingProvider() {
		return strategy;
	}

	/**
	 *  {@inheritDoc}
	 */
	@Override
	public AdviceWeaveResultProvider<CatchClause, CatchClause> getWeaveResultProvider(Advice advice) {
		switch (advice.type()) {
			case INSIDE:
				return new InsideCatchClause();
			default:
				throw new RuntimeException();
		}
	}

}
