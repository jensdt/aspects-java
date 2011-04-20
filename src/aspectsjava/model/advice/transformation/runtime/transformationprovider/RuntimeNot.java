package aspectsjava.model.advice.transformation.runtime.transformationprovider;

import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeExpressionProvider;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.generic.PointcutExpressionNot;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.exception.ChameleonProgrammerException;
import chameleon.support.member.simplename.operator.prefix.PrefixOperatorInvocation;

public class RuntimeNot implements RuntimeExpressionProvider<PointcutExpressionNot<?>> {

	@Override
	public Expression<?> getExpression(PointcutExpressionNot<?> expression, NamingRegistry<RuntimePointcutExpression<?>> namingRegistry) {
		PointcutExpressionNot<?> expr = (PointcutExpressionNot<?>) expression.origin();
		
		if (!(expr.expression() instanceof RuntimePointcutExpression))
			throw new ChameleonProgrammerException("Executing getExpression on a tree containing not-runtime pointcutexpressions");
		
		Expression<?> sub = new NamedTargetExpression("_$" + namingRegistry.getName((RuntimePointcutExpression<?>) expr.expression().origin()));
		
		PrefixOperatorInvocation test = new PrefixOperatorInvocation("!", sub);
		
		return test;
	}
}