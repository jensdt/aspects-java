package aspectsjava.translate.weaver.elementweaver.fieldaccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.rejuse.property.PropertySet;

import aspectsjava.translate.weaver.elementweaver.AbstractElementWeaver;
import aspectsjava.translate.weaver.weavingprovider.ElementReplaceProvider;
import aspectsjava.translate.weaver.weavingprovider.WeavingProvider;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.variable.RegularMemberVariable;

public abstract class FieldWeaver extends AbstractElementWeaver<NamedTargetExpression, MethodInvocation> {

	@Override
	public boolean supports(Advice advice, MatchResult<? extends PointcutExpression, NamedTargetExpression> result) throws LookupException {
		if (!super.supports(advice, result))
			return false;
		
		// We match all NamedTargetExpressions - we only want those that point to RegularMemberVariables
		return (result.getJoinpoint().getElement() instanceof RegularMemberVariable);		
	}
	
	private ElementReplaceProvider<NamedTargetExpression, MethodInvocation> strategy = new ElementReplaceProvider<NamedTargetExpression, MethodInvocation>();
	
	@Override
	public WeavingProvider<NamedTargetExpression, MethodInvocation> getWeavingProvider(Advice advice) {
		return strategy;
	}

	@Override
	public List<Class<NamedTargetExpression>> supportedTypes() {
		return Collections.singletonList(NamedTargetExpression.class);
	}

	@Override
	public List<PropertySet> supportedAdviceProperties(Advice advice) {
		List<PropertySet> supportedProperties = new ArrayList<PropertySet>();
		
		supportedProperties.add(getAround(advice));		
		supportedProperties.add(getBefore(advice));
		supportedProperties.add(getAfter(advice));
		supportedProperties.add(getAfterReturning(advice));
		
		return supportedProperties;
	}

}
