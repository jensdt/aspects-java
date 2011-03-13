package aspectsjava.translate.weaver;

import java.util.ArrayList;
import java.util.List;

import aspectsjava.translate.weaver.elementweaver.Weaver;
import aspectsjava.translate.weaver.elementweaver.catchclause.CatchClauseWeaver;
import aspectsjava.translate.weaver.elementweaver.fieldaccess.FieldReadWeaver;
import aspectsjava.translate.weaver.elementweaver.methodinvocation.MethodInvocationWeaver;
import chameleon.aspects.Aspect;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;

public class WeaveTransformer {

	/**
	 * 	The object that performs the actual weaving. A chain of responsibility pattern is used for this object
	 */
	private Weaver elementWeaver;
	
	/**
	 * 	Constructor
	 */
	public WeaveTransformer() {
		// Initiate the weavers
		Weaver methodInvocationWeaver = new MethodInvocationWeaver();
		Weaver catchClauseWeaver = new CatchClauseWeaver();
		Weaver fieldWeaver = new FieldReadWeaver();
		
		elementWeaver = methodInvocationWeaver;
		methodInvocationWeaver.setNext(catchClauseWeaver);
		catchClauseWeaver.setNext(fieldWeaver);
	}
	
	TranslationExecutor adviceTransformation = new TranslationExecutor();

	/**
	 * 	Reset the state of the weaver
	 */
	public void reset() {
		adviceTransformation.clear();
	}
	
	/**
	 * 	Weave the given compilation unit
	 * 
	 * 	@param 	compilationUnit
	 * 			The compilation unit to weave
	 * 	@param 	aspectCompilationUnits
	 * 			All compilation units that contain aspects
	 * 	@param 	otherCompilationUnits
	 * 			All other compilation units
	 * 	@return	The modified (woven) compilation unit
	 * 	@throws LookupException
	 */
	public CompilationUnit weave(CompilationUnit compilationUnit, List<CompilationUnit> aspectCompilationUnits, List<CompilationUnit> otherCompilationUnits) throws LookupException {
		if (!compilationUnit.descendants(Aspect.class).isEmpty()) {
			for (Aspect<?> aspect : compilationUnit.descendants(Aspect.class)) {
				for (Advice<?> advice : aspect.advices()) {
					if (adviceTransformation.getAdviceTransformationProvider(advice) == null)
						System.out.println("No advice transformation found for " + advice);
					else
						adviceTransformation.getAdviceTransformationProvider(advice).start(advice);
				}					
			}
			
			for (Aspect<?> aspect : compilationUnit.descendants(Aspect.class))
				aspect.disconnect();
			
			return compilationUnit;
		}
		else
			return weaveRegularType(compilationUnit, aspectCompilationUnits, otherCompilationUnits);
	}
	
	/**
	 * 	Weave a given regular type
	 * 
	 * 	@param 	compilationUnit
	 * 			The compilation unit to weave
	 * 	@param 	aspectCompilationUnits
	 * 			All compilation units that contain aspects
	 * 	@param 	otherCompilationUnits
	 * 			All other compilation units
	 * 	@return	The modified (woven) compilation unit
	 * 	@throws LookupException
	 */
	private CompilationUnit weaveRegularType(CompilationUnit compilationUnit, List<CompilationUnit> aspectCompilationUnits, List<CompilationUnit> otherCompilationUnits) throws LookupException {
		// Get a list of all advices
		List<Advice> advices = new ArrayList<Advice>();
		for (CompilationUnit cu : aspectCompilationUnits) {
			advices.addAll(cu.descendants(Advice.class));
		}
		
		// Weave all advices
		for (Advice<?> advice : advices) {
			List<MatchResult<? extends PointcutExpression, ? extends Element>> joinpoints = advice.pointcut().joinpoints(compilationUnit);

			for (MatchResult<? extends PointcutExpression, ? extends Element> joinpoint : joinpoints)
				elementWeaver.start(advice, joinpoint, adviceTransformation);		
		}
		
		return compilationUnit;
	}	
}
