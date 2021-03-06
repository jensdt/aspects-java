package aspectsjava.model.pointcut.expression.methodinvocation.signature;

import java.util.ArrayList;
import java.util.List;

import org.rejuse.association.MultiAssociation;

import chameleon.core.declaration.SimpleNameDeclarationWithParametersSignature;
import chameleon.core.element.Element;
import chameleon.core.namespace.NamespaceElementImpl;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;
import chameleon.oo.type.TypeReference;

public class PointcutMethodHeader<E extends PointcutMethodHeader<E>> extends NamespaceElementImpl<E> {
	
	private String name;
	private MultiAssociation<PointcutMethodHeader<E>, TypeReference> _formalParameterTypes = new MultiAssociation<PointcutMethodHeader<E>, TypeReference>(this);

	public PointcutMethodHeader(String name) {
		setName(name);
	}
	
	public PointcutMethodHeader(String name, List<TypeReference> formalParameterTypes) {
		setName(name);
		addFormalParameterType(formalParameterTypes);
	}
	
	public void addFormalParameterType(List<TypeReference> types) {
		if (types == null)
			return;
		
		for (TypeReference type : types)
			addFormalParameterType(type);
	}
	
	private void addFormalParameterType(TypeReference type) {
		setAsParent(_formalParameterTypes, type);
	}

	public List<TypeReference> formalParameterTypes() {
		return _formalParameterTypes.getOtherEnds();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String name() {
		return name;
	}
	
	@Override
	public List<? extends Element> children() {
		List<TypeReference> result = new ArrayList<TypeReference>();
		result.addAll(formalParameterTypes());
		return result;
	}
	
	public SimpleNameDeclarationWithParametersSignature signature() {
		return new SimpleNameDeclarationWithParametersSignature(name());
	}

	@Override
	public E clone() {
		PointcutMethodHeader<E> clone = new PointcutMethodHeader<E>(name());
		
		for (TypeReference fpt : formalParameterTypes())
			clone.addFormalParameterType(fpt.clone());
		
		
		return (E) clone;
	}

	@Override
	public VerificationResult verifySelf() {
		return Valid.create();
	}

}
