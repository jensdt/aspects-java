package aspectsjava.model.advice.transformation.runtime.transformationprovider;

import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.dynamicexpression.ArgsPointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.oo.type.TypeReference;
import chameleon.support.expression.InstanceofExpression;

public class RuntimeSingleArgumentTypeCheck extends RuntimeArgumentsTypeCheck {

	public RuntimeSingleArgumentTypeCheck(NamedTargetExpression argumentReference) {
		super(argumentReference);
	}

	/**
	 *  {@inheritDoc}
	 *  
	 *  Since there is only a single argument, just check that
	 *  
	 */
	@Override
	public Expression<?> getExpression(ArgsPointcutExpression<?> expr, NamingRegistry<RuntimePointcutExpression<?>> namingRegistry) {
		NamedTargetExpression parameter = expr.parameters().get(0);
		TypeReference<?> typeToTest = getTypeToTest(parameter);
		
		// Create the instanceof
		InstanceofExpression test = new InstanceofExpression(getArgumentReference(), typeToTest);

		return test;
	}
}
