package aspectsjava.model.advice.transformation.runtime.transformationprovider;

import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeExpressionProvider;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.generic.PointcutExpressionAnd;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.exception.ChameleonProgrammerException;
import chameleon.support.member.simplename.operator.infix.InfixOperatorInvocation;

public class RuntimeAnd implements RuntimeExpressionProvider<PointcutExpressionAnd<?>> {

	@Override
	public Expression<?> getExpression(PointcutExpressionAnd<?> expression, NamingRegistry<RuntimePointcutExpression<?>> namingRegistry) {
		PointcutExpressionAnd<?> expr = (PointcutExpressionAnd<?>) expression.origin();
		
		if (!(expr.expression1() instanceof RuntimePointcutExpression) || !(expr.expression2() instanceof RuntimePointcutExpression))
			throw new ChameleonProgrammerException("Executing getExpression on a tree containing not-runtime pointcutexpressions");
		
		
		Expression<?> left = new NamedTargetExpression("_$" + namingRegistry.getName((RuntimePointcutExpression<?>) expr.expression1().origin()));
		Expression<?> right = new NamedTargetExpression("_$" + namingRegistry.getName((RuntimePointcutExpression<?>) expr.expression2().origin()));
		
		InfixOperatorInvocation test = new InfixOperatorInvocation("&&", left);
		test.addArgument(right);
		
		return test;
	}
}
