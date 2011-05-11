package aspectsjava.model.advice.weaving.cast;

import java.util.ArrayList;
import java.util.List;

import jnome.core.language.Java;
import aspectsjava.model.advice.weaving.AdviceMethodProvider;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.namingRegistry.NamingRegistryFactory;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.expression.Expression;
import chameleon.core.lookup.LookupException;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.Type;
import chameleon.oo.type.generics.BasicTypeArgument;
import chameleon.support.expression.ClassCastExpression;

public class DefaultCastProvider extends AdviceMethodProvider<ClassCastExpression> {
	
	@Override
	protected String getName(Advice advice, MatchResult<ClassCastExpression> joinpoint) throws LookupException {
		NamingRegistry<Advice> adviceNamingRegistry = NamingRegistryFactory.instance().getNamingRegistryFor("advice");
		NamingRegistry<ClassCastExpression> methodNamingRegistry = NamingRegistryFactory.instance().getNamingRegistryFor("classcast");
		
		ClassCastExpression method = joinpoint.getJoinpoint();
		
		return "advice_" + adviceNamingRegistry.getName(advice) + "_" + methodNamingRegistry.getName(method);
	}
	
	@Override
	protected List<Expression> getParameters(Advice advice,	MatchResult<ClassCastExpression> joinpoint) throws LookupException {
		List<Expression> parameters = new ArrayList<Expression>();
		
		parameters.add(joinpoint.getJoinpoint().getExpression().clone());

		Expression self = getSelf(joinpoint);
		
		parameters.add(self);
		
		return parameters;
	}
	
	//TODO delete
	@Override
	protected BasicTypeArgument getGenericParameter(Advice advice, MatchResult<ClassCastExpression> joinpoint)	throws LookupException {
		return null;
	}
}