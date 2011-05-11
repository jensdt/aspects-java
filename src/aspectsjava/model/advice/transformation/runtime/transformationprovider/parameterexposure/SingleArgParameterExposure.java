package aspectsjava.model.advice.transformation.runtime.transformationprovider.parameterexposure;

import java.util.ArrayList;
import java.util.List;

import jnome.core.language.Java;
import jnome.core.type.BasicJavaTypeReference;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeParameterExposureProvider;
import chameleon.aspects.pointcut.expression.dynamicexpression.ArgsPointcutExpression;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.oo.type.BasicTypeReference;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.variable.LocalVariable;
import chameleon.support.variable.LocalVariableDeclarator;

public class SingleArgParameterExposure implements RuntimeParameterExposureProvider<ArgsPointcutExpression<?>> {
	
	private String exceptionParameterName;
	
	public SingleArgParameterExposure(String exceptionParameterName) {
		this.exceptionParameterName = exceptionParameterName;
	}

	@Override
	public List<LocalVariableDeclarator> getParameterExposureDeclaration(ArgsPointcutExpression<?> expression, FormalParameter fp) {
		// For the workaround, see the explanation at TypeParameterExposure
		List<LocalVariableDeclarator> resultList = new ArrayList<LocalVariableDeclarator>();
		
		LocalVariableDeclarator workAround = new LocalVariableDeclarator(new BasicJavaTypeReference("Object"));
		VariableDeclaration<LocalVariable> workAroundDecl = new VariableDeclaration<LocalVariable>("_$tmp_argument");
		workAround.add(workAroundDecl);
		workAroundDecl.setInitialization(new NamedTargetExpression(exceptionParameterName));
		
		resultList.add(workAround);
		
		LocalVariableDeclarator parameterInjector = new LocalVariableDeclarator(fp.getTypeReference().clone());
		VariableDeclaration parameterInjectorDecl = new VariableDeclaration(fp.getName());					
		parameterInjector.add(parameterInjectorDecl);
		
		/* Add additional cast for primitive types, can't do:
		 	parameter: int param = 5;
		 	Object o = param;
		 	int p = (int) o;
		 	
		 	so we do:
		 	
		 	int p = (int) (Integer) o;
		*/
		
		// For some reason I haven't found, fp.getTypeReference().isTrue(java.property("primitive")) returns false even for primitives.
		// So for now, use this workaround - try to box, if not it fails: it isn't boxable
		Expression targetExpression = null;
		Java java = fp.language(Java.class);
		try {
			targetExpression = new ClassCastExpression(new BasicTypeReference(java.box(fp.getTypeReference().getType()).getFullyQualifiedName()), new NamedTargetExpression("_$tmp_argument"));
		} catch (LookupException e) {
			// Indicates it isn't boxable
			targetExpression = new NamedTargetExpression("_$tmp_argument");
		}

		ClassCastExpression cast = new ClassCastExpression(fp.getTypeReference().clone(), targetExpression);

		parameterInjectorDecl.setInitialization(cast);
		parameterInjector.add(parameterInjectorDecl);
		
		resultList.add(parameterInjector);
		
		return resultList;
	}

}
