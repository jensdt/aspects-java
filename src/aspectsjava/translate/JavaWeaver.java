package aspectsjava.translate;

import java.util.ArrayList;
import java.util.List;

import jnome.core.expression.ArrayAccessExpression;
import jnome.core.expression.ArrayCreationExpression;
import jnome.core.expression.ArrayInitializer;
import jnome.core.expression.invocation.ConstructorInvocation;
import jnome.core.type.ArrayTypeReference;
import jnome.core.type.BasicJavaTypeReference;
import jnome.core.variable.JavaVariableDeclaration;
import jnome.input.JavaFactory;

import org.rejuse.predicate.UnsafePredicate;

import aspectsjava.model.expression.ProceedCall;
import chameleon.aspects.Aspect;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.AdviceType;
import chameleon.aspects.pointcut.expression.CrossReferencePointcutExpression;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.expression.Expression;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.expression.VariableReference;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.RegularImplementation;
import chameleon.core.namespacepart.NamespacePart;
import chameleon.core.reference.CrossReference;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.RegularType;
import chameleon.oo.type.TypeReference;
import chameleon.oo.type.generics.BasicTypeArgument;
import chameleon.oo.type.generics.FormalTypeParameter;
import chameleon.support.expression.AssignmentExpression;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.expression.FilledArrayIndex;
import chameleon.support.expression.NullLiteral;
import chameleon.support.expression.RegularLiteral;
import chameleon.support.member.simplename.SimpleNameMethodInvocation;
import chameleon.support.member.simplename.method.NormalMethod;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.member.simplename.operator.infix.InfixOperatorInvocation;
import chameleon.support.member.simplename.operator.postfix.PostfixOperatorInvocation;
import chameleon.support.modifier.Private;
import chameleon.support.modifier.Public;
import chameleon.support.modifier.Static;
import chameleon.support.statement.CatchClause;
import chameleon.support.statement.EmptyStatement;
import chameleon.support.statement.ForStatement;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.SimpleForControl;
import chameleon.support.statement.StatementExprList;
import chameleon.support.statement.StatementExpression;
import chameleon.support.statement.ThrowStatement;
import chameleon.support.statement.TryStatement;
import chameleon.support.variable.LocalVariableDeclarator;

public class JavaWeaver {

	
	public CompilationUnit weave(CompilationUnit source, List<CompilationUnit> allProjectCompilationUnits) throws LookupException {
		if (!source.descendants(Advice.class).isEmpty())
			return translateAdvice(source, allProjectCompilationUnits);
		else
			return weaveRegularType(source, allProjectCompilationUnits);
	}
	
	private CompilationUnit translateAdvice(CompilationUnit source, List<CompilationUnit> allProjectCompilationUnits) throws LookupException {	
		NamespacePart originalNamespacePart = source.namespaceParts().get(0);
				
		Aspect aspect = source.descendants(Aspect.class).get(0);
	  	
		RegularType aspectClass = new RegularType(aspect.getName());
		
		addProceedMethod(aspectClass);
		
		JavaFactory factory = new JavaFactory();
			
		for (Advice advice : (List<Advice>) aspect.advices()) {
			DeclarationWithParametersHeader header = new SimpleNameDeclarationWithParametersHeader("advice_" + advice.name());
			
			TypeReference returnType;
			
			if (advice.returnType().getType().signature().name().equals("void"))
				returnType = new BasicTypeReference("T");
			else
				returnType = advice.returnType().clone();
				
			NormalMethod adviceMethod = new NormalMethod(header, returnType);
			
			adviceMethod.addModifier(new Public());
			adviceMethod.addModifier(new Static());
			
			header.addTypeParameter(new FormalTypeParameter(new SimpleNameSignature("T")));

			
			/*
			 *	Add all the parameters to allow the reflective invocation 
			 */
			FormalParameter object = new FormalParameter("_object", new BasicTypeReference("Object"));
			header.addFormalParameter(object);
			
			FormalParameter methodName = new FormalParameter("_methodName", new BasicTypeReference("String"));
			header.addFormalParameter(methodName);
			
			FormalParameter methodArguments = new FormalParameter("_arguments", new ArrayTypeReference(new BasicJavaTypeReference("Object")));
			header.addFormalParameter(methodArguments);
			
			Block adviceBody = new Block();
			
			RegularMethodInvocation proceedInvocation = new RegularMethodInvocation("proceed", new NamedTarget(aspect.name()));
			proceedInvocation.addArgument(new NamedTargetExpression("_object"));
			proceedInvocation.addArgument(new NamedTargetExpression("_methodName"));
			proceedInvocation.addArgument(new NamedTargetExpression("_arguments"));
			proceedInvocation.addArgument((new BasicTypeArgument(returnType.clone())));
			
			if (advice.type() == AdviceType.AFTER) {
				LocalVariableDeclarator returnVal = new LocalVariableDeclarator(new BasicTypeReference("T"));
				VariableDeclaration returnValDecl = new VariableDeclaration("_retval");
				returnValDecl.setInitialization(proceedInvocation);
				returnVal.add(returnValDecl);
			
				adviceBody.addStatement(returnVal);
				adviceBody.addBlock(((Block) advice.body()).clone()); 
				adviceBody.addStatement(new ReturnStatement(new NamedTargetExpression("_retval")));
			}
			else if (advice.type() == AdviceType.BEFORE) {
				
				adviceBody.addBlock(((Block) advice.body()).clone()); 
				adviceBody.addStatement(new ReturnStatement(proceedInvocation));
			}
			else if (advice.type() == AdviceType.AROUND) {				
				// Replace each proceed call to the method call
				List<ProceedCall> proceedCalls = advice.descendants(ProceedCall.class);
				
				if (!proceedCalls.isEmpty()) {
				
					for (ProceedCall pc : proceedCalls) {
						pc.parentLink().getOtherRelation().replace(pc.parentLink(), proceedInvocation.clone().parentLink());
					}
				}
				
				adviceBody = ((Block) advice.body()).clone();
				
				if (advice.returnType().getType().signature().name().equals("void"))
					adviceBody.addStatement(new ReturnStatement(new NullLiteral()));
				
				adviceBody.addBlock(adviceBody);
			}
			
			
						
			// Set the method body
			adviceMethod.setImplementation(new RegularImplementation(adviceBody));
			
			// Add the method
			aspectClass.add(adviceMethod);
		}

		aspect.disconnect();
		originalNamespacePart.add(aspectClass);
		
		return source;
	}
	
	private void addProceedMethod(RegularType aspectClass) {
	
		/*
		 *	Create a proceed method for the around advice (only if necessary) 
		 */
		FormalParameter object = new FormalParameter("_object", new BasicTypeReference("Object"));
		FormalParameter methodName = new FormalParameter("_methodName", new BasicTypeReference("String"));
		FormalParameter methodArguments = new FormalParameter("_arguments", new ArrayTypeReference(new BasicJavaTypeReference("Object")));
		
		DeclarationWithParametersHeader pHeader = new SimpleNameDeclarationWithParametersHeader("proceed");
		NormalMethod proceedMethod = new NormalMethod(pHeader, new BasicTypeReference("T"));
		
		pHeader.addFormalParameter(object);
		pHeader.addFormalParameter(methodName);
		pHeader.addFormalParameter(methodArguments);
		
		pHeader.addTypeParameter(new FormalTypeParameter(new SimpleNameSignature("T")));
		
		proceedMethod.addModifier(new Private());
		proceedMethod.addModifier(new Static());
		
		Block proceedMethodBody = new Block();
		
		// Class[] types = new Class[_arguments.length];
		NamedTargetExpression argumentsDotLength = new NamedTargetExpression("length", new NamedTarget("_arguments"));
						
		LocalVariableDeclarator typesArray = new LocalVariableDeclarator(new ArrayTypeReference(new BasicJavaTypeReference("Class")));
		ArrayCreationExpression typesArrayCreation = new ArrayCreationExpression(new BasicJavaTypeReference("Class"));
		typesArrayCreation.addDimensionInitializer(new FilledArrayIndex(argumentsDotLength.clone()));
		typesArray.add(new JavaVariableDeclaration("types", typesArrayCreation));

		/*
		 * 	For - loop
		 */
		
		// int i = 0;
		LocalVariableDeclarator loopVariable = new LocalVariableDeclarator(new BasicJavaTypeReference("int"));
		JavaVariableDeclaration loopVariableDecl = new JavaVariableDeclaration("i");
		loopVariableDecl.setInitialization(new RegularLiteral(new BasicTypeReference("int"), "0"));
		loopVariable.add(loopVariableDecl);
						
		// i < _arguments.length
		InfixOperatorInvocation condition = new InfixOperatorInvocation("<", new NamedTarget("i"));
		condition.addArgument(argumentsDotLength.clone());
		
		// i++
		StatementExpression incr = new StatementExpression(new PostfixOperatorInvocation("++", new NamedTarget("i")));
		StatementExprList update = new StatementExprList();
		update.addStatement(incr);
		
		// types[i] = _arguments[i].getClass()
		ArrayAccessExpression typesAccess = new ArrayAccessExpression(new NamedTargetExpression("types"));
		typesAccess.addIndex(new FilledArrayIndex(new NamedTargetExpression("i")));
	
		ArrayAccessExpression argumentsAccess = new ArrayAccessExpression(new NamedTargetExpression("_arguments"));
		argumentsAccess.addIndex(new FilledArrayIndex(new NamedTargetExpression("i")));
		RegularMethodInvocation getClass = new RegularMethodInvocation("getClass", argumentsAccess);
		
		AssignmentExpression assignment = new AssignmentExpression(typesAccess, getClass);
		
		SimpleForControl forControl = new SimpleForControl(loopVariable, condition, update);
		
		Block forBody = new Block();
		forBody.addStatement(new StatementExpression(assignment));
		ForStatement forLoop = new ForStatement(forControl, forBody);
		
		/*
		 * 	End of For - loop
		 * 
		 * 	Try - catch with Method invocation
		 */
		
		// java.lang.reflect.Method m = _object.getClass().getMethod(_methodName, types);
		LocalVariableDeclarator method = new LocalVariableDeclarator(new BasicJavaTypeReference("java.lang.reflect.Method"));
		JavaVariableDeclaration methodDecl = new JavaVariableDeclaration("m");
		
		RegularMethodInvocation getObjectClass = new RegularMethodInvocation("getClass", new NamedTarget("_object"));
		RegularMethodInvocation getMethod = new RegularMethodInvocation("getMethod", getObjectClass);
		getMethod.addArgument(new NamedTargetExpression("_methodName"));
		getMethod.addArgument(new NamedTargetExpression("types"));
		
		methodDecl.setInitialization(getMethod);
		method.add(methodDecl);
		
		
		Block tryBody = new Block();
		tryBody.addStatement(method);
		
		
		
		// return (T) m.invoke(_object, _arguments);
		RegularMethodInvocation methodInvocation = new RegularMethodInvocation("invoke", new NamedTarget("m"));
		methodInvocation.addArgument(new NamedTargetExpression("_object"));
		methodInvocation.addArgument(new NamedTargetExpression("_arguments"));
		ReturnStatement returnStatement = new ReturnStatement(new ClassCastExpression(new BasicTypeReference("T"), methodInvocation));
		tryBody.addStatement(returnStatement);
		
		
		Block catchBody = new Block();
		catchBody.addStatement(new EmptyStatement());
		
		TryStatement tryCatch = new TryStatement(tryBody);
		tryCatch.addCatchClause(new CatchClause(new FormalParameter("ex", new BasicTypeReference("Throwable")), catchBody));
		
		// Add a -  throw new Error(); - after the try {} catch, 
		// since the try-block should return anyway. If it doesn't, then an error occurred anyway
		ThrowStatement throwError = new ThrowStatement(new ConstructorInvocation(new BasicJavaTypeReference("Error"), null));
		
		/*
		 *	Add all the statements to the body
		 */
		proceedMethodBody.addStatement(typesArray);
		proceedMethodBody.addStatement(forLoop);
		proceedMethodBody.addStatement(tryCatch);
		proceedMethodBody.addStatement(throwError);
		
		proceedMethod.setImplementation(new RegularImplementation(proceedMethodBody));
		
		// Add the proceed method to the aspect
		aspectClass.add(proceedMethod);
	}
	
	private CompilationUnit weaveRegularType(CompilationUnit source, List<CompilationUnit> allProjectCompilationUnits) throws LookupException {
		// Get a list of all advices
		List<Advice> advices = new ArrayList<Advice>();
		for (CompilationUnit cu : allProjectCompilationUnits) {
			advices.addAll(cu.descendants(Advice.class));
		}
		
		// For each advice, see if we have to weave this advice
		for (Advice a : advices) {
			final PointcutExpression p = a.pointcut().expression();
			
			//if (p instanceof CrossReferencePointcutExpression) {
				
				List<CrossReference> refs = source.descendants(CrossReference.class,
				new UnsafePredicate<CrossReference, LookupException>() {

					@Override
					public boolean eval(final CrossReference cr) throws LookupException {
						try {
							return p.matches(cr);
						} catch (LookupException e) {
							e.printStackTrace(); 
							
							return false;
						}
					}

				});
								
				// We found a match! Weave !
				for (CrossReference cr : refs) {
					// Find the statement we need to weave at
					Statement st = (Statement) cr.nearestAncestor(Statement.class);
					// Now find the block that statement belongs to
					Block bl = (Block) st.nearestAncestor(Block.class);
					
					// Create a call to the advice method
					RegularMethodInvocation adviceInvocation = new RegularMethodInvocation("advice_" + a.name(), new NamedTarget(a.aspect().name()));
					Statement call = new StatementExpression(adviceInvocation);
					
					adviceInvocation.addArgument(new VariableReference("object", ((MethodInvocation) cr).getTarget().clone()));
					adviceInvocation.addArgument(new RegularLiteral(new BasicTypeReference("String"), "\"" + ((SimpleNameMethodInvocation) cr).name()+ "\""));
					List<Expression> methodParameters = ((MethodInvocation) cr).getActualParameters();
					ArrayCreationExpression parameterArray = new ArrayCreationExpression(new ArrayTypeReference(new BasicJavaTypeReference("Object")));
					ArrayInitializer parameterInitializer = new ArrayInitializer();					
				
					for (Expression e : methodParameters)
						parameterInitializer.addInitializer(e);
					
					parameterArray.setInitializer(parameterInitializer);
					
					adviceInvocation.addArgument(parameterArray);
					
					
					// Set the generic parameter
					if (!((MethodInvocation) cr).getType().signature().name().equals("void"))
						adviceInvocation.addArgument(new BasicTypeArgument(new BasicTypeReference(((MethodInvocation) cr).getType().getFullyQualifiedName())));
					
					// Weave
					cr.parentLink().getOtherRelation().replace(cr.parentLink(), adviceInvocation.parentLink());
				}	
			//}
		}
		
		return source;
	}
	
}
