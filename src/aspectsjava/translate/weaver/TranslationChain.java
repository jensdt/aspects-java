package aspectsjava.translate.weaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.translation.AdviceTranslationProvider;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.lookup.LookupException;

public class TranslationChain {
	private Map<Advice, List<AdviceTranslationProvider>> strategyMap = new HashMap<Advice, List<AdviceTranslationProvider>>();
	
	public void add(Advice advice, AdviceTranslationProvider strategy) {
		if (!strategyMap.containsKey(advice))
			strategyMap.put(advice, new ArrayList<AdviceTranslationProvider>());
		
		strategyMap.get(advice).add(strategy);
	}
	
	public void execute(CompilationUnit compilationUnit, Advice advice, List<CompilationUnit> compilationUnits) throws LookupException {
		if (!strategyMap.containsKey(advice)) {
			System.out.println("No strategy for the given advice found. Either no joinpoints match this advice, or an error occured.");
		} else {
			for (AdviceTranslationProvider strategy : strategyMap.get(advice))
				strategy.transform(compilationUnit, advice);
		}
	}

	public void clear() {
		strategyMap.clear();
	}
}
