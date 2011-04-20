package aspectsjava.translate.weaver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import aspectsjava.translate.weaver.elementweaver.catchclause.CatchClauseWeaver;
import aspectsjava.translate.weaver.elementweaver.fieldaccess.FieldReadWeaver;
import aspectsjava.translate.weaver.elementweaver.methodinvocation.MethodInvocationWeaver;
import chameleon.aspects.Aspect;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.aspects.weaver.Weaver;
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
		Weaver<?, ?> methodInvocationWeaver = new MethodInvocationWeaver();
		Weaver<?, ?> catchClauseWeaver = new CatchClauseWeaver();
		Weaver<?, ?> fieldWeaver = new FieldReadWeaver();
		
		elementWeaver = methodInvocationWeaver;
		methodInvocationWeaver.setNext(catchClauseWeaver);
		catchClauseWeaver.setNext(fieldWeaver);
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
		if (!compilationUnit.hasDescendant(Aspect.class)) {
			Map<Element, List<WeavingEncapsulator>> weavingMap = weaveRegularType(compilationUnit, aspectCompilationUnits, otherCompilationUnits);
			
			for (Entry<Element, List<WeavingEncapsulator>> entry : weavingMap.entrySet()) {
				List<WeavingEncapsulator> weavingEncapsulators = entry.getValue();
				
				// Sort all weaving that has to be done
				Collections.sort(weavingEncapsulators, new AdviceTypeComparator());
				
				// Transform the weaving encapsulation list to a double linked list
				WeavingEncapsulator weavingChain = WeavingEncapsulator.fromIterable(weavingEncapsulators);
				
				// Start the weaving
				weavingChain.start();
			}
		}
		
		return compilationUnit;
	}
	
	/**
	 * 
	 * 	Weave a given regular type
	 * 
	 * 	@param 	compilationUnit
	 * 			The compilation unit to weave
	 * 	@param 	aspectCompilationUnits
	 * 			All compilation units that contain aspects
	 * 	@param 	otherCompilationUnits
	 * 			All other compilation units
	 * 	@return	The map of joinpoints to weaving encapsulators that handle this joinpoint
	 * 	@throws LookupException
	 */
	private Map<Element, List<WeavingEncapsulator>> weaveRegularType(CompilationUnit compilationUnit, List<CompilationUnit> aspectCompilationUnits, List<CompilationUnit> otherCompilationUnits) throws LookupException {
		// Get a list of all advices
		List<Advice> advices = new ArrayList<Advice>();
		for (CompilationUnit cu : aspectCompilationUnits) {
			advices.addAll(cu.descendants(Advice.class));
		}
		
		// Keep a map, per joinpoint: the weaving encapsulators that weave it
		Map<Element, List<WeavingEncapsulator>> weavingMap = new HashMap<Element, List<WeavingEncapsulator>>();
		
		// Weave all advices
		for (Advice<?> advice : advices) {
			// Get all joinpoints matched by that expression
			List<MatchResult<? extends PointcutExpression, ? extends Element>> joinpoints = advice.getExpandedPointcutExpression().joinpoints(compilationUnit);
			
			// For each joinpoint, get all necessairy weaving info and add it to the list
			for (MatchResult<? extends PointcutExpression, ? extends Element> joinpoint : joinpoints) {
				if (!weavingMap.containsKey(joinpoint.getJoinpoint()))
					weavingMap.put(joinpoint.getJoinpoint(), new ArrayList<WeavingEncapsulator>());
				
				WeavingEncapsulator encapsulator = elementWeaver.start(advice, joinpoint);
				weavingMap.get(joinpoint.getJoinpoint()).add(encapsulator);
			}
		}
		
		return weavingMap;
	}	
}
