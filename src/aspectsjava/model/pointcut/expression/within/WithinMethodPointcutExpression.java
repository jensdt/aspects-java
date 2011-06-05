package aspectsjava.model.pointcut.expression.within;

import java.util.List;

import org.rejuse.association.SingleAssociation;
import org.rejuse.predicate.SafePredicate;

import aspectsjava.model.pointcut.expression.methodinvocation.signature.MethodReference;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.staticexpression.within.WithinPointcutExpression;
import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.Method;
import chameleon.oo.type.Type;
import chameleon.util.Util;

public class WithinMethodPointcutExpression<E extends WithinMethodPointcutExpression<E>> extends WithinPointcutExpression<E> {
	
	public WithinMethodPointcutExpression() {
		
	}
	
	public WithinMethodPointcutExpression(MethodReference reference) {
		setMethodReference(reference);
	}
	
	
	private SingleAssociation<WithinMethodPointcutExpression<E>, MethodReference> _methodReference = new SingleAssociation<WithinMethodPointcutExpression<E>, MethodReference>(this);
	
	public MethodReference methodReference() {
		return _methodReference.getOtherEnd();
	}
	
	public void setMethodReference(MethodReference methodReference) {
		setAsParent(_methodReference, methodReference);
	}

	@Override
	public MatchResult matches(Element joinpoint) throws LookupException {
		
		@SuppressWarnings("unchecked")
		boolean noMatch = joinpoint.ancestors(Method.class, new SafePredicate<Method>() {

			@Override
			public boolean eval(Method object) {
				// Get the type this method is defined in
				Type definedType = (Type) object.nearestAncestor(Type.class);
				
				try {
					return methodReference().matches(object, definedType);
				} catch (LookupException e) {
					// Shoudn't occur
					e.printStackTrace();
					return false;
				}
			}
			
		}).isEmpty();
		
		if (noMatch)
			return MatchResult.noMatch();
		else
			return new MatchResult<Element>(this, joinpoint);
	}

	@Override
	public List<? extends Element> children() {
		return Util.createNonNullList(methodReference());
	}

	@Override
	public E clone() {
		return (E) new WithinMethodPointcutExpression<E>(Util.cloneOrNull(methodReference()));
	}
}