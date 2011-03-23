package aspectsjava.translate.weaver.elementweaver.fieldaccess;

import javax.management.RuntimeErrorException;

import org.rejuse.property.PropertySet;

import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.translation.AdviceTransformationProvider;
import chameleon.aspects.advice.types.translation.reflection.fieldaccess.AfterReflectiveFieldRead;
import chameleon.aspects.advice.types.translation.reflection.fieldaccess.AroundReflectiveFieldRead;
import chameleon.aspects.advice.types.translation.reflection.fieldaccess.BeforeReflectiveFieldRead;
import chameleon.aspects.advice.types.translation.reflection.methodInvocation.AfterReturningReflectiveMethodInvocation;
import chameleon.aspects.advice.types.weaving.AdviceWeaveResultProvider;
import chameleon.aspects.advice.types.weaving.fieldaccess.DefaultReflectiveFieldAccess;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.element.Element;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.support.expression.AssignmentExpression;

public class FieldReadWeaver extends FieldWeaver {
	@Override
	public boolean supports(Advice advice,MatchResult<? extends PointcutExpression, NamedTargetExpression> result) throws LookupException {
		if (!super.supports(advice, result))
			return false;

		// Only reads		
		if (result.getJoinpoint().parent() instanceof AssignmentExpression && ((AssignmentExpression) result.getJoinpoint().parent()).getVariable().sameAs(result.getJoinpoint()))
			return false;
		
		return true;
	}

	@Override
	public AdviceWeaveResultProvider<NamedTargetExpression, MethodInvocation> getWeaveResultProvider(Advice advice) {
		return new DefaultReflectiveFieldAccess();
	}

	@Override
	public AdviceTransformationProvider getTransformationStrategy(Advice advice, MatchResult<? extends PointcutExpression, ? extends Element> joinpoint) {
		PropertySet around = getAround(advice);		
		PropertySet before = getBefore(advice);
		PropertySet after = getAfter(advice);
		PropertySet afterReturning = getAfterReturning(advice);
		
		if (around.containsAll(advice.properties().properties()))
			return new AroundReflectiveFieldRead(joinpoint);
		
		if (before.containsAll(advice.properties().properties()))
			return new BeforeReflectiveFieldRead(joinpoint);
		
		if (after.containsAll(advice.properties().properties()))
			return new AfterReflectiveFieldRead(joinpoint);
					
		if (afterReturning.containsAll(advice.properties().properties()))
			return new AfterReflectiveFieldRead(joinpoint);
	
		throw new RuntimeException();
	}
}
