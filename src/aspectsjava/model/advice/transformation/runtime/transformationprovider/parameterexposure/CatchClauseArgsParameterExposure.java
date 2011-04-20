package aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure;

import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeParameterExposureProvider;
import chameleon.aspects.pointcut.expression.dynamicexpression.ArgsPointcutExpression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.variable.LocalVariableDeclarator;

public class CatchClauseArgsParameterExposure implements RuntimeParameterExposureProvider<ArgsPointcutExpression<?>> {
	
	private String exceptionParameterName;
	
	public CatchClauseArgsParameterExposure(String exceptionParameterName) {
		this.exceptionParameterName = exceptionParameterName;
	}

	@Override
	public LocalVariableDeclarator getParameterExposureDeclaration(ArgsPointcutExpression<?> expression, FormalParameter fp) {
		LocalVariableDeclarator parameterInjector = new LocalVariableDeclarator(fp.getTypeReference().clone());
		VariableDeclaration parameterInjectorDecl = new VariableDeclaration(fp.getName());					
		parameterInjector.add(parameterInjectorDecl);

		ClassCastExpression cast = new ClassCastExpression(fp.getTypeReference().clone(), new NamedTargetExpression(exceptionParameterName));

		parameterInjectorDecl.setInitialization(cast);
		parameterInjector.add(parameterInjectorDecl);
		
		return parameterInjector;
	}

}
