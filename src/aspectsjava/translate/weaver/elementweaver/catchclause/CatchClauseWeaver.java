package aspectsjava.translate.weaver.elementweaver.catchclause;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.rejuse.property.PropertySet;

import aspectsjava.model.advice.weaving.ReturnJavaAdviceProvider;
import aspectsjava.translate.weaver.weavingprovider.catchclause.CatchClauseInsertAfterProvider;
import aspectsjava.translate.weaver.weavingprovider.catchclause.CatchClauseInsertAroundProvider;
import aspectsjava.translate.weaver.weavingprovider.catchclause.CatchClauseInsertBeforeProvider;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.translation.AdviceTransformationProvider;
import chameleon.aspects.advice.types.translation.NoOperationTransformationProvider;
import chameleon.aspects.advice.types.weaving.AdviceWeaveResultProvider;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.weaver.AbstractElementWeaver;
import chameleon.aspects.weaver.weavingprovider.WeavingProvider;
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
	public AdviceTransformationProvider getTransformationProvider(Advice advice, MatchResult<Block> joinpoint) {
		return new NoOperationTransformationProvider();
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
			return new CatchClauseInsertAroundProvider();
		
		if (before.containsAll(advice.properties().properties()))
			return new CatchClauseInsertBeforeProvider();
		
		if (after.containsAll(advice.properties().properties()))
			return new CatchClauseInsertAfterProvider();
		
		throw new RuntimeException();
	}
	
	AdviceWeaveResultProvider<Block, List<Statement>> weavingAdviceType = new ReturnJavaAdviceProvider<Block>();

	/**
	 *  {@inheritDoc}
	 */
	@Override
	public AdviceWeaveResultProvider<Block, List<Statement>> getWeaveResultProvider() {
		return weavingAdviceType;
	}
}
