package aspectsjava.translate.weaver;

import java.util.ArrayList;
import java.util.List;

import aspectsjava.translate.weaver.elementweaver.catchclause.CatchClauseWeaver;
import aspectsjava.translate.weaver.elementweaver.methodinvocation.MethodInvocationWeaver;
import chameleon.aspects.Aspect;
import chameleon.aspects.advice.Advice;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.lookup.LookupException;

public class JavaWeaver {

	public JavaWeaver() {
		weaverChain.add(new MethodInvocationWeaver());
		weaverChain.add(new CatchClauseWeaver());
	}
	
	WeaverChain weaverChain = new WeaverChain();
	TranslationChain adviceTranslation = new TranslationChain();
	
	public void reset() {
		adviceTranslation.clear();
	}
	
	public CompilationUnit weave(CompilationUnit compilationUnit, List<CompilationUnit> aspectCompilationUnits, List<CompilationUnit> otherCompilationUnits) throws LookupException {
		if (!compilationUnit.descendants(Aspect.class).isEmpty()) {
			for (Aspect aspect : compilationUnit.descendants(Aspect.class)) {
				for (Advice adv : (List<Advice>) aspect.advices()) {
					adviceTranslation.execute(compilationUnit, adv, otherCompilationUnits);
				}					
			}
			
			for (Aspect aspect : compilationUnit.descendants(Aspect.class))
				aspect.nonRecursiveDisconnect();
			
			return compilationUnit;
		}
		else
			return weaveRegularType(compilationUnit, aspectCompilationUnits, otherCompilationUnits);
	}
	
	private CompilationUnit weaveRegularType(CompilationUnit compilationUnit, List<CompilationUnit> aspectCompilationUnits, List<CompilationUnit> otherCompilationUnits) throws LookupException {
		// Get a list of all advices
		List<Advice> advices = new ArrayList<Advice>();
		for (CompilationUnit cu : aspectCompilationUnits) {
			advices.addAll(cu.descendants(Advice.class));
		}
		
		// Weave all advices
		for (Advice a : advices) {
			weaverChain.weave(compilationUnit, a, aspectCompilationUnits, otherCompilationUnits, adviceTranslation);
		}
		
		return compilationUnit;
	}	
}
