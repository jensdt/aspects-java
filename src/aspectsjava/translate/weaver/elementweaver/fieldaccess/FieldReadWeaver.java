package aspectsjava.translate.weaver.elementweaver.fieldaccess;

import org.rejuse.property.PropertySet;

import aspectsjava.model.advice.transformation.reflection.fieldaccess.AfterReflectiveFieldRead;
import aspectsjava.model.advice.transformation.reflection.fieldaccess.AroundReflectiveFieldRead;
import aspectsjava.model.advice.transformation.reflection.fieldaccess.BeforeReflectiveFieldRead;
import aspectsjava.model.advice.weaving.reflection.fieldaccess.DefaultReflectiveFieldAccess;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.translation.AdviceTransformationProvider;
import chameleon.aspects.advice.types.weaving.AdviceWeaveResultProvider;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
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
	
	private AdviceWeaveResultProvider<NamedTargetExpression, MethodInvocation> weavingAdviceType = new DefaultReflectiveFieldAccess();

	@Override
	public AdviceWeaveResultProvider<NamedTargetExpression, MethodInvocation> getWeaveResultProvider() {
		return weavingAdviceType;
	}

	@Override
	public AdviceTransformationProvider getTransformationStrategy(Advice advice, MatchResult<? extends PointcutExpression, ? extends Element> joinpoint) {
		PropertySet around = getAround(advice);		
		PropertySet before = getBefore(advice);
		PropertySet after = getAfter(advice);
		PropertySet afterReturning = getAfterReturning(advice);
		
		if (around.containsAll(advice.properties().properties()))
			return new AroundReflectiveFieldRead(joinpoint, advice);
		
		if (before.containsAll(advice.properties().properties()))
			return new BeforeReflectiveFieldRead(joinpoint, advice);
		
		if (after.containsAll(advice.properties().properties()))
			return new AfterReflectiveFieldRead(joinpoint, advice);
					
		if (afterReturning.containsAll(advice.properties().properties()))
			return new AfterReflectiveFieldRead(joinpoint, advice);
	
		throw new RuntimeException();
	}
}
