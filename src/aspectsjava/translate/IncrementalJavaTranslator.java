package aspectsjava.translate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import aspectsjava.model.language.AspectsJava;
import aspectsjava.translate.weaver.JavaWeaveTransformer;
import chameleon.aspects.Aspect;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.weaver.WeaveTransformer;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.lookup.LookupException;
import chameleon.exception.ModelException;
import chameleon.plugin.build.BuildProgressHelper;
import chameleon.support.translate.IncrementalTranslator;

public class IncrementalJavaTranslator extends IncrementalTranslator<AspectsJava, AspectsJava> {

	public IncrementalJavaTranslator(AspectsJava source, AspectsJava target) {
		super(source, target);
		_translator = new JavaWeaveTransformer();
	}
	
	private WeaveTransformer _translator;
	
	public WeaveTransformer basicTranslator() {
		return _translator;
	}
	
	public List<CompilationUnit> completeRebuild(List<CompilationUnit> allProjectCompilationUnits, BuildProgressHelper buildProgressHelper) throws LookupException {
		initTargetLanguage();
		
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
				
		result.addAll(aspects);
		result.addAll(other);
		for (CompilationUnit cu : other) {
			buildProgressHelper.checkForCancellation();
			_translator.weave(cu, aspects);
			buildProgressHelper.addWorked(1);
		}
		
		System.out.println("Rebuilt " + result.size() + " compilationUnit(s)");
		
		return result;
	}
}

