package aspectsjava.model.advice.transformation.runtime.reflection.methodinvocation;

import java.util.List;

import aspectsjava.model.advice.transformation.reflection.methodinvocation.ReflectiveMethodInvocation;
import aspectsjava.model.advice.transformation.runtime.AdviceMethodCoordinator;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.exception.TypeExceptionDeclaration;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;
import chameleon.core.variable.FormalParameter;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.TypeReference;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.CatchClause;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.StatementExpression;
import chameleon.support.statement.ThrowStatement;
import chameleon.support.statement.TryStatement;

/**
 * 	The coordinator for method invocations. Implements a three-phased transformation phase:
 * 
 * 		* First phase: arguments- and type checks
 * 		* Second phase: parameter injection
 * 		* Third phase: if-checks (since these can access the parameters!)
 * 
 * 	@author Jens
 */
public class MethodCoordinator extends AdviceMethodCoordinator {

	/**
	 * 	Constructor
	 * 
	 * 	@param 	adviceTransformationProvider
	 * 			The given transformation provider
	 * 	@param 	matchResult
	 * 			The joinpoint
	 */
	public MethodCoordinator(ReflectiveMethodInvocation adviceTransformationProvider, MatchResult matchResult, WeavingEncapsulator previousWeavingEncapsulator, WeavingEncapsulator nextWeavingEncapsulator) {
		super(adviceTransformationProvider, matchResult, previousWeavingEncapsulator, nextWeavingEncapsulator);
	}
	
	/**
	 * 	Add the test corresponding to the items in the tree
	 * 
	 * 	@param 	tree
	 * 			The expression tree
	 * 	@param 	expressionNames
	 * 			The naming registry for expressions
	 */
	@Override
	protected Block addTest(RuntimePointcutExpression tree, NamingRegistry<RuntimePointcutExpression> expressionNames) {
		Block body = new Block();
		
		// Re-throw unchecked exceptions (subclasses of RuntimeException )	
		TryStatement exceptionHandler = null;
		try {
			Block tryBody = super.addTest(tree, expressionNames);
			exceptionHandler = new TryStatement(tryBody);
			exceptionHandler.addCatchClause(getRethrow("unchecked", new BasicTypeReference("RuntimeException")));
	
			// Add a re-throw for each checked exception
			int exceptionIndex = 0;
			List<TypeExceptionDeclaration> checkedTypeExceptions = getAdviceTransformationProvider().getCheckedExceptionsWithoutSubtypes(getMatchResult().getJoinpoint().getElement());
			
			for (TypeExceptionDeclaration exception : checkedTypeExceptions) {
				exceptionHandler.addCatchClause(getRethrow("ex" + exceptionIndex, exception.getTypeReference().clone()));
				
				exceptionIndex++;
			}
			
			// Add a catch all. This isn't actually necessary since we already handled all cases, but since the generic proceed method throws a throwable we need it to prevent compile errors
			exceptionHandler.addCatchClause(getAdviceTransformationProvider().getCatchAll());
		} catch (LookupException e){
			// Will only occur with a bug, not in normal usage
			System.out.println("Creating surrounding try in runtime check threw LookupEx");
			e.printStackTrace();
			
		}
		
		body.addStatement(exceptionHandler);
		
		return body;
	}
	
	/**
	 * 	Get a re-throw clause
	 * 
	 * 	@param 	name
	 * 			The name of the exception parameter
	 * 	@param 	type
	 * 			The type of the exception parameter
	 * 	@return
	 */
	private CatchClause getRethrow(String name, TypeReference type) {
		Block rethrowBody = new Block();
		ThrowStatement rethrow = new ThrowStatement(new NamedTargetExpression(name));

		rethrowBody.addStatement(rethrow);
		
		return new CatchClause(new FormalParameter(name, type), rethrowBody);
	}
	
	/**
	 * 	Get the proceed invocation
	 * 
	 * 	@return	The proceed invocation
	 */
	private RegularMethodInvocation getProceedInvocation() {
		return getAdviceTransformationProvider().getNextInvocation(getNextWeavingEncapsulator());
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	@Override
	public ReflectiveMethodInvocation getAdviceTransformationProvider() {
		return (ReflectiveMethodInvocation) super.getAdviceTransformationProvider();
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	public MatchResult<? extends MethodInvocation> getMatchResult() {
		return (MatchResult<? extends MethodInvocation>) super.getMatchResult();
	}
}