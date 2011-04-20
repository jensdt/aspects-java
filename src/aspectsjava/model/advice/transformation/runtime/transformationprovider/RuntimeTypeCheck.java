package aspectsjava.model.advice.transformation.runtime.transformationprovider;

import jnome.core.expression.ClassLiteral;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeExpressionProvider;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.dynamicexpression.TypePointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.expression.Expression;
import chameleon.support.member.simplename.method.RegularMethodInvocation;

public class RuntimeTypeCheck implements RuntimeExpressionProvider<TypePointcutExpression<?>>  {

	private Expression reference;
	
	public RuntimeTypeCheck(Expression reference) {
		this.reference = reference;
	}
	
	protected Expression getReference() {
		return reference;
	}

	@Override
	public Expression<?> getExpression(TypePointcutExpression<?> expr, NamingRegistry<RuntimePointcutExpression<?>> namingRegistry) {
		// Declared must be a super type of the given
		ClassLiteral getDeclaredClass = new ClassLiteral(expr.getType());
		RegularMethodInvocation getGivenClass = new RegularMethodInvocation("getClass", getReference());
		
		RegularMethodInvocation test = new RegularMethodInvocation("isAssignableFrom", getDeclaredClass);
		test.addArgument(getGivenClass);
		
		return test;
	}
}