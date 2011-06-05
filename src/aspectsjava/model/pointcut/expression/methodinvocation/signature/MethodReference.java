package aspectsjava.model.pointcut.expression.methodinvocation.signature;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jnome.core.type.RegularJavaType;

import org.rejuse.association.SingleAssociation;

import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.element.Element;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.lookup.LocalLookupStrategy;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.Method;
import chameleon.core.namespace.NamespaceElementImpl;
import chameleon.core.validation.BasicProblem;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;
import chameleon.core.variable.FormalParameter;
import chameleon.oo.type.Type;
import chameleon.oo.type.TypeReference;
import chameleon.util.Util;

/**
 * 	Represents a reference to a method, used in a pointcut description. References to methods are always fully qualified and
 * 	contain both the return type of the method and the formal parameters.
 *
 * 	@author jensdt
 *
 */
public class MethodReference<E extends MethodReference<E>> extends NamespaceElementImpl<E> {
	
	public MethodReference(QualifiedMethodHeader fqn, TypeReference type, String typeNameWithWC) {
		setTypeNameWithWC(typeNameWithWC);
		setType(type);
		setFqn(fqn);
	}
	
	public boolean hasExplicitType() {
		return type() != null;
	}
	
	public boolean matches(Method e, Type definedType) throws LookupException {		
		
		boolean matches = false;
		if (hasExplicitType())
			 matches = e.returnType().assignableTo(type().getType());
		else {
			matches = sameAsWithWildcard(e.returnType().getFullyQualifiedName(), typeNameWithWC());
			
			Iterator<Type> superTypeIterator = e.returnType().getAllSuperTypes().iterator();
			
			while (!matches && superTypeIterator.hasNext()) {
				matches = sameAsWithWildcard(superTypeIterator.next().getFullyQualifiedName(), typeNameWithWC());
			}
		}
		
		if (!matches)
			return false;
		
		// Check if the signature matches
		if (!sameAsWithWildcard(e.signature().name(), fqn().methodHeader().name()))
			return false;
		
		// Check if the FQN matches
		String definedFqn = getFullyQualifiedName();

		matches = sameFQNWithWildcard(definedType.getFullyQualifiedName(), definedFqn);
		
		Iterator<Type> superTypeIterator = definedType.getAllSuperTypes().iterator();
		while (!matches && superTypeIterator.hasNext()) {
			matches = sameFQNWithWildcard(superTypeIterator.next().getFullyQualifiedName(), definedFqn);
		}
		
		if (!matches)
			return false;
		
		// Check if the parameter types match
		Iterator<FormalParameter> methodArguments = e.formalParameters().iterator();
		Iterator<TypeReference> argumentTypes = fqn().methodHeader().types().iterator();
	
		
		while (methodArguments.hasNext() && argumentTypes.hasNext()) {
			TypeReference argType = argumentTypes.next();
			FormalParameter methodArg = methodArguments.next();
			
			if (!methodArg.getType().assignableTo(argType.getType()))	
				return false;
		}
		
		// If this is true, it means there is a difference in the number of args
		if (methodArguments.hasNext() || argumentTypes.hasNext())
			return false;
		
		return true;
	}
	
	/**
	 * 	Match rules:
	 * 			hrm.Person matches with:
	 * 				- hrm.Person (or any wildcard combo, e.g. h*m.P*)
	 * 				- **.Person  (or any wildcard combo, e.g. **.P*)
	 * 				- hrm.**     (or any wildcard combo, e.g. h*m.**)
	 * 				- **.**
	 * 				- **
	 * 
	 * @param jpFqn_
	 * @param definedFqn_
	 * @return
	 */
	private boolean sameFQNWithWildcard(String jpFqn_, String definedFqn_) {
		String[] jpFqn = jpFqn_.split("\\.");
		String[] definedFqn = definedFqn_.split("\\.");
		
		// Special case: if the FQN of the call is a complete wildcard, match everything
		if (definedFqn.length == 1 && definedFqn[0].equals("**"))
			return true;
		
		if (jpFqn.length != definedFqn.length)
			return false;
		
		for (int i = 0; i < jpFqn.length; i++)
			if (!sameAsWithWildcard(jpFqn[i], definedFqn[i]))
				return false;
		
		return true;
	}

	/**
	 * 	Check if s1 is the same as s2 - s2 can contain a wild card (** = any character 0 or more times), s1 can
	 *  contain the wild card character but it will not be treated as such.
	 *  
	 */
	public boolean sameAsWithWildcard(String s1, String s2) {
		// Turn s2 into a regexp. We convert the wild card character (**) to a regexp-wildcard (.*) and treat
		// all the rest as a literal (between \Q and \E)
		String regexp = "\\Q" + s2.replace("**", "\\E(.*)\\Q") + "\\E"; 
		
		return s1.matches(regexp);
	}
	
	/**
	 *	Return type of the method 	
	 */
	private String typeNameWithWC;
	
	private void setTypeNameWithWC(String typeNameWithWC) {
		this.typeNameWithWC = typeNameWithWC;
	}
	
	public String typeNameWithWC() {
		return typeNameWithWC;
	}
	
	public String getFullyQualifiedName() {
		return fqn().getFullyQualifiedName();
	}

	/**
	 * 	The fully qualified name of the method
	 */
	private SingleAssociation<MethodReference<E>, QualifiedMethodHeader> _fqn = new SingleAssociation<MethodReference<E>, QualifiedMethodHeader>(this);
		
	private void setFqn(QualifiedMethodHeader fqn) {
		setAsParent(_fqn, fqn);
	}

	public QualifiedMethodHeader fqn() {
		return _fqn.getOtherEnd();
	}

	private SingleAssociation<MethodReference<E>, TypeReference> _typeRef = new SingleAssociation<MethodReference<E>, TypeReference>(this);

	private void setType(TypeReference type) {
		setAsParent(_typeRef, type);
	}
	
	public TypeReference type() {
		return _typeRef.getOtherEnd();
	}


	@Override
	public List<? extends Element> children() {
		List<Element> result = new ArrayList<Element>();
		Util.addNonNull(fqn(), result);
		Util.addNonNull(type(), result);
		return result;
	}

	@Override
	public E clone() {
		TypeReference typeClone = null;
		if (type() != null)
			typeClone = type().clone();
		
		return (E) new MethodReference<E>((QualifiedMethodHeader) fqn().clone(), typeClone, typeNameWithWC);
	}

	@Override
	public VerificationResult verifySelf() {
		VerificationResult result = Valid.create();
		
		if (type() == null)
			result.and(new BasicProblem(this, "The type of the method reference may not be null"));
		
		if (fqn() == null)
			result.and(new BasicProblem(this, "The fully qualified name of the method reference may not be null"));
		
		return result;
	}
}
