package aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure;

import java.util.ArrayList;
import java.util.List;

import jnome.core.type.BasicJavaTypeReference;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeParameterExposureProvider;
import chameleon.aspects.pointcut.expression.dynamicexpression.TypePointcutExpression;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.variable.LocalVariable;
import chameleon.support.variable.LocalVariableDeclarator;

public class TypeParameterExposure implements RuntimeParameterExposureProvider<TypePointcutExpression<?>>  {
	
	private Expression reference;
	
	public TypeParameterExposure(Expression reference) {
		this.reference = reference;
	}

	
	@Override
	public List<LocalVariableDeclarator> getParameterExposureDeclaration(TypePointcutExpression<?> expression, FormalParameter fp) {
		/*
		 * 	We need the following workaround in this case:
		 * 
		 * 	Assumptions:
		 * 
		 * 	Main and Person are inconvertible types
		 * 	this : Main
		 * 	check: thisType(Person)
		 * 
		 * 	if (!hrm.Person.class.isAssignableFrom(this.getClass())) {
		 * 		execute original joinpoint
		 * 	} else {
		 * 		Person parameter = (Person) this;
		 * 		^-> this line will give a compiler error with 'inconvertible types', while it will never execute due to the runtime check.
		 * 		Workaround:
		 * 
		 * 		Object _$tmp = this;
                Person parameter = (Person)(_$tmp);
		 * 
		 * 	}
		 * 
		 */
		List<LocalVariableDeclarator> resultList = new ArrayList<LocalVariableDeclarator>();
		
		LocalVariableDeclarator workAround = new LocalVariableDeclarator(new BasicJavaTypeReference("Object"));
		VariableDeclaration<LocalVariable> workAroundDecl = new VariableDeclaration<LocalVariable>("_$tmp_thistype");
		workAround.add(workAroundDecl);
		workAroundDecl.setInitialization(reference);
		
		resultList.add(workAround);
		
		LocalVariableDeclarator parameterInjector = new LocalVariableDeclarator(fp.getTypeReference().clone());
		VariableDeclaration<LocalVariable> parameterInjectorDecl = new VariableDeclaration<LocalVariable>(fp.getName());					
		parameterInjector.add(parameterInjectorDecl);
		
		ClassCastExpression cast = new ClassCastExpression(fp.getTypeReference().clone(), new NamedTargetExpression("_$tmp_thistype"));
		
		parameterInjectorDecl.setInitialization(cast);
		parameterInjector.add(parameterInjectorDecl);
		
		resultList.add(parameterInjector);
		
		return resultList;
	}
	
}
