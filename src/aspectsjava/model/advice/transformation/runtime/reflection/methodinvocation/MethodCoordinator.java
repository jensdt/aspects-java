package aspectsjava.model.advice.transformation.runtime.reflection.methodinvocation;

import java.util.List;

import org.rejuse.predicate.SafePredicate;

import aspectsjava.model.advice.transformation.reflection.methodinvocation.ReflectiveMethodInvocation;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.runtimetransformation.AbstractCoordinator;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.pointcut.expression.AbstractPointcutExpression;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.IfPointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ParameterExposurePointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.RegularImplementation;
import chameleon.core.method.exception.TypeExceptionDeclaration;
import chameleon.core.statement.Block;
import chameleon.core.variable.FormalParameter;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.TypeReference;
import chameleon.support.member.simplename.method.NormalMethod;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.CatchClause;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.ThrowStatement;
import chameleon.support.statement.TryStatement;
import chameleon.support.variable.LocalVariableDeclarator;

/**
 * 	The coordinator for method invocations. Implements a three-phased transformation phase:
 * 
 * 		* First phase: arguments- and type checks
 * 		* Second phase: parameter injection
 * 		* Third phase: if-checks (since these can access the parameters!)
 * 
 * 	@author Jens
 */
public class MethodCoordinator extends AbstractCoordinator<NormalMethod> {

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
	 * 	{@inheritDoc}
	 */
	@Override
	public void transform(NormalMethod element, List<FormalParameter> parameters) {
		if (element == null)
			return;
		
		PointcutExpression<?> initialTree = (PointcutExpression<?>) getMatchResult().getExpression();
		
		// Part one: get all the runtime pointcut expressions but maintain the structure (and/or/...)
		SafePredicate<PointcutExpression<?>> runtimeFilter = new SafePredicate<PointcutExpression<?>>() {

			@Override
			public boolean eval(PointcutExpression<?> object) {
				if (!(object instanceof RuntimePointcutExpression))
					return false;
				
				return getAdviceTransformationProvider().supports(object);
			}
		};
		
		// Cast is safe due to the filter
		RuntimePointcutExpression<?> prunedTree = (RuntimePointcutExpression<?>) initialTree.getPrunedTree(runtimeFilter);
		
		if (prunedTree == null)
			return;
		
		// Now, select all pointcut expressions we can weave at the start of the method.
		// Currently, this is all except the 'if'-expression, since it can refer to the value of parameters
		RuntimePointcutExpression<?> initialCheckTree = (RuntimePointcutExpression<?>) prunedTree.removeFromTree(IfPointcutExpression.class);
		
		NamingRegistry<RuntimePointcutExpression> expressionNames = new NamingRegistry<RuntimePointcutExpression>();
		Block finalBody = new Block();
		
		if (initialCheckTree != null) {
			finalBody.addBlock(addTest(initialCheckTree, expressionNames));
		}
		
		// Part two: inject the parameters
		
		if (!parameters.isEmpty()) {
			SafePredicate<PointcutExpression<?>> parameterInjectionFilter = new SafePredicate<PointcutExpression<?>>() {

				@Override
				public boolean eval(PointcutExpression<?> object) {
					if (!(object instanceof ParameterExposurePointcutExpression))
						return false;
					
					return getAdviceTransformationProvider().supports(object);
				}
			};
			
			// Cast is safe due to the filter
			ParameterExposurePointcutExpression<?> parameterTree = (ParameterExposurePointcutExpression<?>) initialTree.getPrunedTree(parameterInjectionFilter);
			
			for (FormalParameter fp : parameters) {
				ParameterExposurePointcutExpression<?> exposingParameter = ((ParameterExposurePointcutExpression<?>) parameterTree.origin()).findExpressionFor(fp);
				
				LocalVariableDeclarator parameterInjector = getAdviceTransformationProvider().getRuntimeParameterInjectionProvider(exposingParameter).getParameterExposureDeclaration(exposingParameter.origin(), fp);
				finalBody.addStatement(parameterInjector);
				
				/*
				LocalVariableDeclarator parameterInjector = new LocalVariableDeclarator(fp.getTypeReference().clone());
				VariableDeclaration parameterInjectorDecl = new VariableDeclaration(fp.getName());					
				parameterInjector.add(parameterInjectorDecl);
				
				int index = getMatchResult().getExpression().indexOfParameter(fp); // This isn't necessarily equal to the index in the 'parameters' list! Need this explicit search!
				
				// Select the correct parameter
				ArrayAccessExpression argumentsAccess = new ArrayAccessExpression(new NamedTargetExpression(getAdviceTransformationProvider().argumentNameParamName));
				argumentsAccess.addIndex(new FilledArrayIndex(new RegularLiteral(new BasicJavaTypeReference("int"), Integer.toString(index))));
				
				// Add the cast, since the arguments is just an Object array
				// Mind boxable-unboxable types
				Java java = fp.language(Java.class);
				
				TypeReference typeToCastTo = null;
				try {
					if (fp.getTypeReference().getType().isTrue(fp.language().property("primitive")))
						typeToCastTo = new BasicTypeReference (java.box(fp.getTypeReference().getType()).getFullyQualifiedName());
					else
						typeToCastTo = fp.getTypeReference().clone();
				} catch (LookupException e) {
					System.out.println("Lookupexception while boxing");
				}

				ClassCastExpression cast = new ClassCastExpression(typeToCastTo, argumentsAccess);
				
				parameterInjectorDecl.setInitialization(cast);
				parameterInjector.add(parameterInjectorDecl);
				
				finalBody.addStatement(parameterInjector);
				*/
			}
			
		}

		// Part Three: add the check for the 'if' expressions
		
		// Get the if-expressions
		RuntimePointcutExpression<?> ifExprTree = (RuntimePointcutExpression<?>) prunedTree.getPrunedTree(IfPointcutExpression.class);
		if (ifExprTree != null) {	
			finalBody.addBlock(addTest(ifExprTree, expressionNames));
		}
		
		finalBody.addBlock(element.body());
		element.setImplementation(new RegularImplementation(finalBody));
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
	 * 	{@inheritDoc}
	 */
	@Override
	protected Block getTerminateBody() {
		Block terminateBody = new Block();
		terminateBody.addStatement(new ReturnStatement(getProceedInvocation()));
		
		return terminateBody;
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
	public MatchResult<? extends AbstractPointcutExpression, ? extends MethodInvocation> getMatchResult() {
		return (MatchResult<? extends AbstractPointcutExpression, ? extends MethodInvocation>) super.getMatchResult();
	}
}