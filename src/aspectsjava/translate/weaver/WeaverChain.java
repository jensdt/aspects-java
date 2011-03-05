package aspectsjava.translate.weaver;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import aspectsjava.translate.weaver.elementweaver.Weaver;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;
// chain of resp.
public class WeaverChain {
	private LinkedList<Weaver> chain = new LinkedList<Weaver>();
	
	public WeaverChain() {
		
	}
	
	public void add(Weaver<?, ?> weaver) {
		chain.add(weaver);
	}

	public void weave(CompilationUnit compilationUnit, Advice advice, List<CompilationUnit> aspectCompilationUnits, List<CompilationUnit> otherCompilationUnits, TranslationChain adviceTranslation) throws LookupException {
		 List<MatchResult<? extends PointcutExpression, ? extends Element>> joinpoints = advice.pointcut().joinpoints(compilationUnit);
		 
		 for (MatchResult<? extends PointcutExpression, ? extends Element> joinpoint : joinpoints) {
			 Iterator<Weaver> weaverIterator = chain.iterator();
			 
			 boolean supports = false;
			 while (!supports && weaverIterator.hasNext()) {
				 Weaver weaver = weaverIterator.next();
				 
				 supports = weaver.supports(joinpoint, advice.type());
				 if (supports) {
					 weaver.weave(compilationUnit, advice, joinpoint);
					 adviceTranslation.add(advice, weaver.getTranslationStrategy(advice, joinpoint));
				 }
			 }
			 
			 if (!supports)
				 throw new RuntimeException("No matching weaver found in chain for joinpoint of type " + joinpoint.getJoinpoint().getClass());
		 }
	}
}
