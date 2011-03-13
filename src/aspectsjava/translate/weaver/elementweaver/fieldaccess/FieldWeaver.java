package aspectsjava.translate.weaver.elementweaver.fieldaccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aspectsjava.translate.weaver.elementweaver.AbstractElementWeaver;
import aspectsjava.translate.weaver.weavingprovider.ElementReplaceProvider;
import aspectsjava.translate.weaver.weavingprovider.WeavingProvider;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.AdviceTypeEnum;
import chameleon.aspects.advice.types.translation.AdviceTransformationProvider;
import chameleon.aspects.advice.types.weaving.AdviceWeaveResultProvider;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.element.Element;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.variable.RegularMemberVariable;

public abstract class FieldWeaver extends AbstractElementWeaver<NamedTargetExpression, MethodInvocation> {

	@Override
	public boolean supports(MatchResult<? extends PointcutExpression, NamedTargetExpression> result, AdviceTypeEnum advice) throws LookupException {
		if (!super.supports(result, advice))
			return false;
		
		// We match all NamedTargetExpressions - we only want those that point to RegularMemberVariables
		return (result.getJoinpoint().getElement() instanceof RegularMemberVariable);		
	}
	
	private ElementReplaceProvider<NamedTargetExpression, MethodInvocation> strategy = new ElementReplaceProvider<NamedTargetExpression, MethodInvocation>();
	
	@Override
	public WeavingProvider<NamedTargetExpression, MethodInvocation> getWeavingProvider() {
		return strategy;
	}

	@Override
	public List<Class<NamedTargetExpression>> supportedTypes() {
		return Collections.singletonList(NamedTargetExpression.class);
	}

	@Override
	public List<AdviceTypeEnum> supportedAdviceTypes() {
		List<AdviceTypeEnum> supportedAdviceTypes = new ArrayList<AdviceTypeEnum>();
		supportedAdviceTypes.add(AdviceTypeEnum.AFTER);
		
		return supportedAdviceTypes;
	}

}
