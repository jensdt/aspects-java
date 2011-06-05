package aspectsjava.model.advice.weaving.reflection.fieldaccess;

import java.util.ArrayList;
import java.util.List;

import jnome.core.language.Java;
import aspectsjava.model.advice.weaving.TargetedAdviceMethodProvider;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.expression.VariableReference;
import chameleon.core.lookup.LookupException;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.Type;
import chameleon.oo.type.generics.BasicTypeArgument;
import chameleon.support.expression.RegularLiteral;

public class DefaultReflectiveFieldAccess extends TargetedAdviceMethodProvider<NamedTargetExpression> {
	@Override
	protected List<Expression> getParameters(Advice advice, MatchResult<NamedTargetExpression> joinpoint) throws LookupException {
		List<Expression> parameters = new ArrayList<Expression>();
		
		// Cast is ok because a MI is a NamedTargetExpression which will return an InvocationTarget
		InvocationTarget target = (InvocationTarget) getTarget(joinpoint);

		parameters.add(new VariableReference("object", target));
		parameters.add(new RegularLiteral(new BasicTypeReference("String"), "\"" + joinpoint.getJoinpoint().signature().name()+ "\""));

		Expression self = getSelf(joinpoint);
		
		parameters.add(self);
		
		return parameters;
	}
	
	@Override
	protected BasicTypeArgument getGenericParameter(Advice advice,	MatchResult<NamedTargetExpression> joinpoint) throws LookupException {
		Type type = joinpoint.getJoinpoint().getElement().declarationType();
		Java java = joinpoint.getJoinpoint().language(Java.class);

		if (type.isTrue(java.property("primitive")))
			type = java.box(type);

		return new BasicTypeArgument(new BasicTypeReference(type.getFullyQualifiedName()));		
	}
}