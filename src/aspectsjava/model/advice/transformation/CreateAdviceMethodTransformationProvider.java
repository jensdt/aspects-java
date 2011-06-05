package aspectsjava.model.advice.transformation;

import java.util.Collections;
import java.util.List;

import jnome.core.expression.invocation.ConstructorInvocation;
import jnome.core.type.BasicJavaTypeReference;

import org.rejuse.predicate.SafePredicate;

import aspectsjava.model.advice.JavaAdvice;
import chameleon.aspects.Aspect;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.translation.AbstractAdviceTransformationProviderSupportingRuntime;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.namingRegistry.NamingRegistryFactory;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.RegularImplementation;
import chameleon.core.statement.Block;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.RegularType;
import chameleon.oo.type.TypeReference;
import chameleon.oo.type.generics.TypeParameter;
import chameleon.oo.type.inheritance.SubtypeRelation;
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
	 * 	Get the return type for the advice method
	 * 
	 * 	@return	The return type for the advice method
	 */
	protected abstract TypeReference getAdiceMethodReturnType();
	
	
	/**
	 * 	Get the body for the advice method
	 * 
	 * 	@param next
	 * 			The next weavingEncapsulator in the chain
	 * 	@return	The body of the advice method
	 */
	protected abstract Block getBody(WeavingEncapsulator next);
	

	/**
	 * 	Get the list of formal parameters for the advice method
	 * 
	 * 	@return	The list of formal parameters
	 */
	protected abstract List<FormalParameter> getAdviceMethodParameters();
	
	/**
	 * 	Get the lits of type parameters for the advice method
	 * 
	 * 	@return	The list of type parameters
	 */
	protected List<TypeParameter> getAdviceMethodTypeParameters() {
		return Collections.emptyList();
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public NormalMethod transform(WeavingEncapsulator previous,	WeavingEncapsulator next) throws LookupException {
		Aspect<?> aspect = getAdvice().aspect();
		CompilationUnit compilationUnit = aspect.nearestAncestor(CompilationUnit.class);
		
		// Get the class we are going to create this method in
		RegularType aspectClass = getOrCreateAspectClass(compilationUnit, aspect.name());
		
		// Check if the method has already been created
		if (isAlreadyDefined(getAdvice(), compilationUnit))
			return null;
		
		String adviceMethodName = getAdviceMethodName(getAdvice());
		
		DeclarationWithParametersHeader header = new SimpleNameDeclarationWithParametersHeader(adviceMethodName);
		
		NormalMethod adviceMethod = new NormalMethod(header, getAdiceMethodReturnType());
		
		adviceMethod.addModifier(new Public());
		adviceMethod.addModifier(new Static());
		
		// Add the parameters
		for (FormalParameter fp : getAdviceMethodParameters())
			header.addFormalParameter(fp);
		
		for (TypeParameter tp : getAdviceMethodTypeParameters())
			header.addTypeParameter(tp);
		
		// Get the body
		Block body = getBody(next);
		
		// Set the method body
		adviceMethod.setImplementation(new RegularImplementation(body));
		
		// Add the method
		aspectClass.add(adviceMethod);
		
		return adviceMethod;
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
			
			aspectClass.addInheritanceRelation(new SubtypeRelation(new BasicTypeReference("java.lang.Object")));
			
			// Create new empty constructor
			NormalMethod m = new NormalMethod(new SimpleNameDeclarationWithParametersHeader(name), new BasicTypeReference(name));
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

	@Override
	public JavaAdvice getAdvice() {
		return (JavaAdvice) super.getAdvice();
	}
	
	@Override
	protected void setAdvice(Advice advice) {
		if (!(advice instanceof JavaAdvice))
			throw new IllegalArgumentException();
		
		super.setAdvice(advice);
	}
}
