package aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure.reflection;

import aspectsjava.model.advice.transformation.reflection.ReflectiveAdviceTransformationProvider;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeParameterExposureProvider;
import chameleon.aspects.pointcut.expression.dynamicexpression.TargetTypePointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ThisTypePointcutExpression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.variable.LocalVariableDeclarator;

public class ReflectiveTargetTypeParameterExposure implements RuntimeParameterExposureProvider<TargetTypePointcutExpression<?>>  {
	
	private ReflectiveAdviceTransformationProvider reflectiveInvocation;
	
	public ReflectiveTargetTypeParameterExposure(ReflectiveAdviceTransformationProvider reflectiveInvocation) {
		this.reflectiveInvocation = reflectiveInvocation;
	}

	@Override
	public LocalVariableDeclarator getParameterExposureDeclaration(TargetTypePointcutExpression<?> expression, FormalParameter fp) {
		LocalVariableDeclarator parameterInjector = new LocalVariableDeclarator(fp.getTypeReference().clone());
		VariableDeclaration parameterInjectorDecl = new VariableDeclaration(fp.getName());					
		parameterInjector.add(parameterInjectorDecl);
		
		ClassCastExpression cast = new ClassCastExpression(fp.getTypeReference().clone(), new NamedTargetExpression(reflectiveInvocation.objectParamName));
		
		parameterInjectorDecl.setInitialization(cast);
		parameterInjector.add(parameterInjectorDecl);
		
		return parameterInjector;
	}

}
