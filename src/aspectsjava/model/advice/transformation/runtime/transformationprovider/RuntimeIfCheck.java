package aspectsjava.model.advice.transformation.runtime.transformationprovider;

import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeExpressionProvider;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.dynamicexpression.IfPointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.expression.Expression;

/**
 * 	Performs a runtime check with a given boolean expression
 * 
 * 	@author Jens
 *
 */
public class RuntimeIfCheck implements RuntimeExpressionProvider<IfPointcutExpression<?>> {
	
	/**
	 * 	{@inheritDoc}
	 * 		
	 *	This is trivial, since the expression is exactly that one contained in the if expression
	 */
	@Override
	public Expression<?> getExpression(IfPointcutExpression<?> expr, NamingRegistry<RuntimePointcutExpression<?>> namingRegistry) {
		return expr.expression().clone();
	}
}
