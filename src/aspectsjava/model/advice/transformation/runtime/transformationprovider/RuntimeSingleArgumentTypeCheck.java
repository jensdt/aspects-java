package aspectsjava.model.advice.transformation.runtime.transformationprovider;

import jnome.core.expression.ClassLiteral;
import jnome.core.language.Java;
import jnome.core.type.BasicJavaTypeReference;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.dynamicexpression.ArgsPointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.Type;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.member.simplename.method.RegularMethodInvocation;

public class RuntimeSingleArgumentTypeCheck extends RuntimeArgumentsTypeCheck {

	public RuntimeSingleArgumentTypeCheck(NamedTargetExpression argumentReference) {
		super(argumentReference);
	}
	
	private Type type;

	public RuntimeSingleArgumentTypeCheck(NamedTargetExpression argumentReference, Type type) {
		this(argumentReference);
		this.type = type;
	}

	/**
	 *  {@inheritDoc}
	 *  
	 *  Since there is only a single argument, just check that
	 *  
	 */
	@Override
	public Expression<?> getExpression(ArgsPointcutExpression<?> expr, NamingRegistry<RuntimePointcutExpression<?>> namingRegistry) {
		ClassLiteral getDeclaredClass;
		try {
			getDeclaredClass = new ClassLiteral(new BasicJavaTypeReference(expr.parameters().get(0).getType().getFullyQualifiedName()));
			
			Expression getGivenClass = getClassOfParameter(getArgumentReference());
			
			RegularMethodInvocation test = new RegularMethodInvocation("isAssignableFrom", getDeclaredClass);
			test.addArgument(getGivenClass);
			
			return test;
		} catch (LookupException e) {
			// Shouldn't be able to occur
			e.printStackTrace();
			return null;
		}
	}

	private Expression getClassOfParameter(NamedTargetExpression argumentReference) {
		
		if (type != null && type.isTrue(type.language().property("primitive")))
			return new ClassLiteral(new BasicTypeReference(type.getFullyQualifiedName()));
		else
			return new RegularMethodInvocation("getClass", argumentReference.clone());
	}
}
