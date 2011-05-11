package aspectsjava.model.advice.weaving;

import jnome.core.expression.ClassLiteral;
import jnome.core.type.RegularJavaType;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.TargetedExpression;
import chameleon.core.lookup.LookupException;
import chameleon.oo.type.BasicTypeReference;
import chameleon.support.expression.ThisLiteral;

public abstract class TargetedAdviceMethodProvider<T extends TargetedExpression> extends AdviceMethodProvider<T> {
	protected InvocationTarget getTarget(MatchResult<T> joinpoint)	throws LookupException {
		InvocationTarget target = joinpoint.getJoinpoint().getTarget();
		if (target == null)
			target = new ThisLiteral();
		else {
			if (target instanceof NamedTarget && ((NamedTarget) target).getElement() instanceof RegularJavaType) {
				target = new ClassLiteral(new BasicTypeReference(((RegularJavaType) ((NamedTarget) target).getElement()).getType().getFullyQualifiedName()));
			} else {
				target = target.clone();
			}
		}
		return target;
	}	
}