package aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure;

import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeParameterExposureProvider;
import chameleon.aspects.pointcut.expression.dynamicexpression.ThisTypePointcutExpression;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.expression.ThisLiteral;
import chameleon.support.variable.LocalVariable;
import chameleon.support.variable.LocalVariableDeclarator;

public class ThisTypeParameterExposure implements RuntimeParameterExposureProvider<ThisTypePointcutExpression<?>>  {

	@Override
	public LocalVariableDeclarator getParameterExposureDeclaration(ThisTypePointcutExpression<?> expression, FormalParameter fp) {
		LocalVariableDeclarator parameterInjector = new LocalVariableDeclarator(fp.getTypeReference().clone());
		VariableDeclaration<LocalVariable> parameterInjectorDecl = new VariableDeclaration<LocalVariable>(fp.getName());					
		parameterInjector.add(parameterInjectorDecl);
		
		ClassCastExpression cast = new ClassCastExpression(fp.getTypeReference().clone(), new ThisLiteral());
		
		parameterInjectorDecl.setInitialization(cast);
		parameterInjector.add(parameterInjectorDecl);
		
		return parameterInjector;
	}
	
}
