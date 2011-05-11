package aspectsjava.model.advice.transformation;

import java.util.List;

import jnome.core.expression.invocation.ConstructorInvocation;
import jnome.core.type.BasicJavaTypeReference;

import org.rejuse.predicate.SafePredicate;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.translation.AbstractAdviceTransformationProviderSupportingRuntime;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.namingRegistry.NamingRegistryFactory;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.method.RegularImplementation;
import chameleon.core.statement.Block;
import chameleon.core.variable.VariableDeclaration;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.RegularType;
import chameleon.support.member.simplename.method.NormalMethod;
import chameleon.support.member.simplename.variable.MemberVariableDeclarator;
import chameleon.support.modifier.Constructor;
import chameleon.support.modifier.Private;
import chameleon.support.modifier.Public;
import chameleon.support.modifier.Static;
import chameleon.support.statement.ReturnStatement;

public abstract class CreateAdviceMethodTransformationProvider extends AbstractAdviceTransformationProviderSupportingRuntime<NormalMethod> {
	public String getAdviceMethodName(Advice advice) {
		NamingRegistry<Advice> adviceNamingRegistry = NamingRegistryFactory.instance().getNamingRegistryFor("advice");
		
		return "advice_" + adviceNamingRegistry.getName(advice);
	}
	
	public boolean isAlreadyDefined(Advice advice, CompilationUnit cu) {
		final String name = getAdviceMethodName(advice);
		
		return cu.hasDescendant(NormalMethod.class, new SafePredicate<NormalMethod>() {

			@Override
			public boolean eval(NormalMethod object) {
				return name.equals(object.name());
			}
		});
	}
	
	/**
	 * 	Return the type used for implementing the advice - methods. If it doesn't exist, it is created. It is always created in the same
	 * 	compilation unit as the aspect.
	 * 
	 * 	@param 	compilationUnit
	 * 			The compilation unit the aspect belongs to
	 * 	@param 	name
	 * 			The name of the aspect
	 * 	@return	The type representing the aspect
	 */
	protected RegularType getOrCreateAspectClass(CompilationUnit compilationUnit, final String name) {
		// Find the aspect class
		List<RegularType> aspectClasses = compilationUnit.descendants(RegularType.class, new SafePredicate<RegularType>() {
			@Override
			public boolean eval(RegularType object) {
				return object.getName().equals(name);
			}
		});
		
		// Sanity check
		if (aspectClasses.size() > 1)
			throw new RuntimeException("More than one aspect class");
		
		// Create the aspect class, or get it if it already exists
		RegularType aspectClass;
		if (aspectClasses.isEmpty()) {
			// No aspect class yet
			aspectClass = new RegularType(name);
			aspectClass.addModifier(new Public());
			
			// Create new empty constructor
			NormalMethod m = new NormalMethod(new SimpleNameDeclarationWithParametersHeader(name), null);
			m.addModifier(new Constructor());
			m.addModifier(new Private());
			m.setImplementation(new RegularImplementation(new Block()));
			
			aspectClass.add(m);
			
			// Add instance variable
			MemberVariableDeclarator decl = new MemberVariableDeclarator(new BasicTypeReference(name));
			VariableDeclaration varDecl = new VariableDeclaration("instance");
			
			decl.add(varDecl);
			decl.addModifier(new Static());
			decl.addModifier(new Private());
			varDecl.setInitialization(new ConstructorInvocation(new BasicJavaTypeReference(name), null));
			aspectClass.add(decl);
			
			// Getter for the instance
			NormalMethod getter = new NormalMethod(new SimpleNameDeclarationWithParametersHeader("instance"), new BasicTypeReference(name));
			getter.addModifier(new Static());
			getter.addModifier(new Public());
			
			Block getterBody = new Block();
			getterBody.addStatement(new ReturnStatement(new NamedTargetExpression("instance")));
			getter.setImplementation(new RegularImplementation(getterBody));
			aspectClass.add(getter);
			
			compilationUnit.namespacePart(1).add(aspectClass);
		} else {
			// Aspect class already exist!
			aspectClass = aspectClasses.get(0);
		}
				
		return aspectClass;
	}
	
	protected boolean hasMethodWithName(RegularType type, final String methodName) {
		return type.hasDescendant(NormalMethod.class, new SafePredicate<NormalMethod>() {

			@Override
			public boolean eval(NormalMethod object) {
				return object.signature().name().equals(methodName);
			}
		});
	}

	public abstract Expression getNextInvocation(WeavingEncapsulator nextWeavingEncapsulator);
}
