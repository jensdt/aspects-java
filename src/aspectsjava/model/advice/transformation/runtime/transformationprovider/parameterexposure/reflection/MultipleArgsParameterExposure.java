package aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure.reflection;

import java.util.Collections;
import java.util.List;

import jnome.core.expression.ArrayAccessExpression;
import jnome.core.language.Java;
import jnome.core.type.BasicJavaTypeReference;
import aspectsjava.model.advice.transformation.reflection.methodinvocation.ReflectiveMethodInvocation;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeParameterExposureProvider;
import chameleon.aspects.pointcut.expression.dynamicexpression.ArgsPointcutExpression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.exception.ChameleonProgrammerException;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.TypeReference;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.expression.FilledArrayIndex;
import chameleon.support.expression.RegularLiteral;
import chameleon.support.variable.LocalVariableDeclarator;

public class MultipleArgsParameterExposure implements RuntimeParameterExposureProvider<ArgsPointcutExpression<?>> {
	
	private ReflectiveMethodInvocation reflectiveInvocation;
	
	public MultipleArgsParameterExposure(ReflectiveMethodInvocation reflectiveInvocation) {
		this.reflectiveInvocation = reflectiveInvocation;
	}

	@Override
	public List<LocalVariableDeclarator> getParameterExposureDeclaration(ArgsPointcutExpression<?> expression, FormalParameter fp) {
		LocalVariableDeclarator parameterInjector = new LocalVariableDeclarator(fp.getTypeReference().clone());
		VariableDeclaration parameterInjectorDecl = new VariableDeclaration(fp.getName());					
		parameterInjector.add(parameterInjectorDecl);
		
		int index = expression.indexOfParameter(fp); // This isn't necessarily equal to the index in the 'parameters' list! Need this explicit search!
		
		// Sanity check - this should never be able to occur since this method should only be called with the expression that exposes the given parameter
		if (index == -1)
			throw new ChameleonProgrammerException("Formal parameter not found!");
		
		// Select the correct parameter
		ArrayAccessExpression argumentsAccess = new ArrayAccessExpression(new NamedTargetExpression(reflectiveInvocation.argumentNameParamName));
		argumentsAccess.addIndex(new FilledArrayIndex(new RegularLiteral(new BasicJavaTypeReference("int"), Integer.toString(index))));
		
		// Add the cast, since the arguments is just an Object array
		// Mind boxable-unboxable types
		Java java = fp.language(Java.class);
		
		TypeReference typeToCastTo = null;
		try {
			if (fp.getTypeReference().getType().isTrue(fp.language().property("primitive")))
				typeToCastTo = new BasicTypeReference (java.box(fp.getTypeReference().getType()).getFullyQualifiedName());
			else
				typeToCastTo = fp.getTypeReference().clone();
		} catch (LookupException e) {
			System.out.println("Lookupexception while boxing");
		}

		ClassCastExpression cast = new ClassCastExpression(typeToCastTo, argumentsAccess);
		
		parameterInjectorDecl.setInitialization(cast);
		parameterInjector.add(parameterInjectorDecl);
		
		return Collections.singletonList(parameterInjector);
	}

}
