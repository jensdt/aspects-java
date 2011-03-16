package aspectsjava.translate.weaver.elementweaver.fieldaccess;

import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.AdviceTypeEnum;
import chameleon.aspects.advice.types.translation.AdviceTransformationProvider;
import chameleon.aspects.advice.types.translation.fieldaccess.AfterReflectiveFieldRead;
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
	public boolean supports(MatchResult<? extends PointcutExpression, NamedTargetExpression> result, AdviceTypeEnum advice) throws LookupException {
		if (!super.supports(result, advice))
			return false;

		// Only reads		
		if (result.getJoinpoint().parent() instanceof AssignmentExpression)
			return false;
		
		return true;
	}

	@Override
	public AdviceWeaveResultProvider<NamedTargetExpression, MethodInvocation> getWeaveResultProvider(Advice advice) {
		return new DefaultReflectiveFieldAccess();
	}

	@Override
	public AdviceTransformationProvider getTransformationStrategy(Advice advice, MatchResult<? extends PointcutExpression, ? extends Element> joinpoint) {
		return new AfterReflectiveFieldRead(joinpoint);
	}
}
