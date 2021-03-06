package aspectsjava.model.advice.transformation.runtime.transformationprovider;

import java.util.List;

import jnome.core.expression.ArrayAccessExpression;
import jnome.core.language.Java;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeExpressionProvider;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.dynamicexpression.ArgsPointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.TypeReference;
import chameleon.support.expression.FilledArrayIndex;
import chameleon.support.expression.InstanceofExpression;
import chameleon.support.expression.RegularLiteral;
import chameleon.support.member.simplename.operator.infix.InfixOperatorInvocation;

/**
 * 	Performs a runtime type check of arguments
 * 
 * 	FIXME: correct indexing
 * 
 * 	@author Jens
 *
 */
public class RuntimeArgumentsTypeCheck implements RuntimeExpressionProvider<ArgsPointcutExpression<?>> {
	
	/**
	 * 	Reference to the parameter containing the arguments
	 */
	private NamedTargetExpression argumentReference;
	
	/**
	 * 	Constructor
	 * 
	 * 	@param 	argumentReference
	 * 			Reference to the parameter containing the arguments
	 */
	public RuntimeArgumentsTypeCheck(NamedTargetExpression argumentReference) {
		this.argumentReference = argumentReference;
	}
	
	/**
	 * 	Return the parameter containing the arguments
	 * 
	 * 	@return	The parameter
	 */
	public NamedTargetExpression getArgumentReference() {
		return argumentReference;
	}
	
	/**
	 *  {@inheritDoc}
	 *  
	 *  The expression is always of the following form: first a check for the correct count (so we get short-circuited if this isn't correct and avoid an IndexOutOfBounds).
	 *  Then a type check for each parameter. Primitive parameter types are always boxed to perform the check.
	 *  
	 *  e.g. arguments(String, int) => (args.length == 2) && (args[0] instanceof String) && (args[1] instanceof Integer)
	 *  
	 *  
	 */
	@Override
	public Expression<?> getExpression(ArgsPointcutExpression<?> expr, NamingRegistry<RuntimePointcutExpression<?>> namingRegistry) {
		// First, add a check if the number of parameters matches
		NamedTargetExpression parLength = new NamedTargetExpression("length", argumentReference.clone());
		InfixOperatorInvocation equals = new InfixOperatorInvocation("==", parLength);
		equals.addArgument(new RegularLiteral(new BasicTypeReference<BasicTypeReference<?>>("int"), Integer.toString(expr.parameters().size())));
		
		InfixOperatorInvocation fullTest = equals;
		
		// Add a check for each parameter defined in the Arguments expression
		int i = 0;
		for (NamedTargetExpression parameter : (List<NamedTargetExpression>) expr.parameters()) {

			// Access the correct element of the array
			ArrayAccessExpression arrayAccess = new ArrayAccessExpression(argumentReference.clone());
			arrayAccess.addIndex(new FilledArrayIndex(new RegularLiteral(new BasicTypeReference<BasicTypeReference<?>>("int"), Integer.toString(i++))));

			TypeReference<?> typeToTest = getTypeToTest(parameter);
			
			// Create the instanceof
			InstanceofExpression test = new InstanceofExpression(arrayAccess, typeToTest);
			
			fullTest = new InfixOperatorInvocation("&&", fullTest.clone());
			fullTest.addArgument(test);
		}
		
		return fullTest;
	}
	
	/**
	 * 	Get the type for which to check. If the type is a primitive, we check for its boxed type (since 'a instanceof int' is illegal)
	 */
	protected TypeReference getTypeToTest(NamedTargetExpression parameter) {
		TypeReference<?> typeToTest = null;
		Java java = parameter.language(Java.class);
		try {
			if (parameter.getType().isTrue(java.property("primitive")))
				typeToTest = new BasicTypeReference<BasicTypeReference<?>>(java.box(parameter.getType()).getFullyQualifiedName());
			else
				typeToTest = new BasicTypeReference<BasicTypeReference<?>>(parameter.getType().getFullyQualifiedName());
		} catch (LookupException e) {
			System.out.println("Lookupexception while boxing");
		}
		
		return typeToTest;
	}
}