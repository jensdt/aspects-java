package aspectsjava.translate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jnome.core.language.Java;
import aspectsjava.model.language.AspectsJava;
import aspectsjava.translate.weaver.JavaWeaveTransformer;
import chameleon.aspects.Aspect;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.weaver.WeaveTransformer;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.language.Language;
import chameleon.core.lookup.LookupException;
import chameleon.core.namespacepart.NamespacePart;
import chameleon.exception.ModelException;

public class IncrementalJavaTranslator {

	public IncrementalJavaTranslator(AspectsJava source, Java target) {
		_sourceLanguage = source;
		_targetLanguage = target;
		_translator = new JavaWeaveTransformer();
	}
	
	private boolean _initialized=false;
	
	private void initTargetLanguage() throws LookupException {
		Set<CompilationUnit> compilationUnits = new HashSet<CompilationUnit>();
		for(NamespacePart nsp: sourceLanguage().defaultNamespace().descendants(NamespacePart.class)) {
			CompilationUnit cu = nsp.nearestAncestor(CompilationUnit.class);
			if(cu != null) {
				compilationUnits.add(cu);
			}
		}
		for(CompilationUnit compilationUnit: compilationUnits) {
			implementationCompilationUnit(compilationUnit);
		}
		_initialized=true;
	}
	
	public Language sourceLanguage() {
		return _sourceLanguage;
	}
	
	private Language _sourceLanguage;
	
	public Language targetLanguage() {
		return _targetLanguage;
	}
	
	private Language _targetLanguage;
	
	private WeaveTransformer _translator;
	
	public WeaveTransformer basicTranslator() {
		return _translator;
	}
	
 /*@
   @ public behavior
   @
   @ pre compilationUnit != null;
   @
   @ post \result != null;
   @ post \fresh(\result);
   @*/
	public List<CompilationUnit> build(CompilationUnit source, List<CompilationUnit> allProjectCompilationUnits) throws ModelException {
		if(!_initialized) {
			initTargetLanguage();
		}
		
		// If we need to build an advice, rebuild the entire project!
		for (CompilationUnit cu : allProjectCompilationUnits) {
			if (!cu.descendants(Advice.class).isEmpty())
				return completeRebuild(allProjectCompilationUnits);
		}
		
		List<CompilationUnit> result = new ArrayList<CompilationUnit>();
		result.add(buildSingle(source, Collections.<CompilationUnit>emptyList(), allProjectCompilationUnits));

		return result;
	}
	
	private CompilationUnit buildSingle(CompilationUnit compilationUnit, List<CompilationUnit> aspectCompilationUnits, List<CompilationUnit> otherCompilationUnits) throws LookupException {
		CompilationUnit transformed = _translator.weave(compilationUnit, aspectCompilationUnits, otherCompilationUnits);
				
		return transformed;
	}
	
	private List<CompilationUnit> completeRebuild(List<CompilationUnit> allProjectCompilationUnits) throws LookupException {
		System.out.println("-- Complete rebuild");
		List<CompilationUnit> result = new ArrayList<CompilationUnit>();
		
		List<CompilationUnit> aspects = new ArrayList<CompilationUnit>();
		List<CompilationUnit> other = new ArrayList<CompilationUnit>();
		
		// First rebuild aspects
		for (CompilationUnit cu : allProjectCompilationUnits) {
			CompilationUnit clone = implementationCompilationUnit(cu);
			
			if (!clone.descendants(Aspect.class).isEmpty())
				aspects.add(clone);
			else
				other.add(clone);
		}
				
		for (CompilationUnit cu : other) {
			result.add(buildSingle(cu, aspects, other));
		}
		
		for (CompilationUnit cu : aspects) {
			result.add(buildSingle(cu, aspects, other));
		}
		
		System.out.println("Rebuilt " + result.size() + " compilationUnit(s)");
		
		return result;
	}
	
	private Map<CompilationUnit,CompilationUnit> _implementationMap = new HashMap<CompilationUnit,CompilationUnit>();

	public CompilationUnit implementationCompilationUnit(CompilationUnit compilationUnit) throws LookupException {
		CompilationUnit clone = compilationUnit.cloneTo(targetLanguage());
		store(compilationUnit, clone,_implementationMap);
    return clone;
	}

	private void store(CompilationUnit compilationUnit, CompilationUnit generated, Map<CompilationUnit,CompilationUnit> storage) throws LookupException {
		CompilationUnit old = storage.get(compilationUnit);
		if(old != null) {
			if(generated != old) {
				old.namespacePart(1).getNamespaceLink().unlock();
			}
			old.disconnect();
		}
		// connect the namespacepart of the clone compilation unit
		// to the proper namespace in the target model. The cloned
		// namespace part is not connected to a namespace, so we
		// need the original namespacepart to obtain the fqn.
		storage.put(compilationUnit, generated);
	}
	
}

