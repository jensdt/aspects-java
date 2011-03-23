package aspectsjava.translate.weaver.elementweaver.catchclause;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.rejuse.property.PropertySet;

import aspectsjava.translate.weaver.elementweaver.AbstractElementWeaver;
import aspectsjava.translate.weaver.weavingprovider.InsertAfterProvider;
import aspectsjava.translate.weaver.weavingprovider.InsertAroundProvider;
import aspectsjava.translate.weaver.weavingprovider.InsertBeforeProvider;
import aspectsjava.translate.weaver.weavingprovider.WeavingProvider;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.translation.AdviceTransformationProvider;
import chameleon.aspects.advice.types.translation.NoOperationTranslator;
import chameleon.aspects.advice.types.weaving.AdviceWeaveResultProvider;
import chameleon.aspects.advice.types.weaving.ReturnAdviceProvider;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.element.Element;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;

/**
 * 	This weaver weaves catch clauses
 * 
 * 	@author Jens
 *
 */
public class CatchClauseWeaver extends AbstractElementWeaver<Block, List<Statement>> {

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
	public List<Class<Block>> supportedTypes() {
		return Collections.singletonList(Block.class);
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
		
		return supportedProperties;
	}

	/**
	 *  {@inheritDoc}
	 */
	@Override
	public WeavingProvider<Block, List<Statement>> getWeavingProvider(Advice advice) {
		PropertySet around = getAround(advice);		
		PropertySet before = getBefore(advice);
		PropertySet after = getAfter(advice);
		
		if (around.containsAll(advice.properties().properties()))
			return new InsertAroundProvider();
		
		if (before.containsAll(advice.properties().properties()))
			return new InsertBeforeProvider();
		
		if (after.containsAll(advice.properties().properties()))
			return new InsertAfterProvider();
		
		throw new RuntimeException();
	}

	/**
	 *  {@inheritDoc}
	 */
	@Override
	public AdviceWeaveResultProvider<Block, List<Statement>> getWeaveResultProvider(Advice advice) {
		return new ReturnAdviceProvider();
	}
}
