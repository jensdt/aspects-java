package aspectsjava.translate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import jnome.core.expression.ArrayAccessExpression;
import jnome.core.expression.ArrayCreationExpression;
import jnome.core.expression.ArrayInitializer;
import jnome.core.expression.ClassLiteral;
import jnome.core.expression.invocation.ConstructorInvocation;
import jnome.core.type.ArrayTypeReference;
import jnome.core.type.BasicJavaTypeReference;
import jnome.core.type.RegularJavaType;
import jnome.core.variable.JavaVariableDeclaration;
import jnome.input.JavaFactory;
import aspectsjava.model.expression.ProceedCall;
import chameleon.aspects.Aspect;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.AdviceType;
import chameleon.aspects.pointcut.Pointcut;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.methodinvocation.SignatureMethodInvocationPointcutExpression;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.expression.VariableReference;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.Method;
import chameleon.core.method.RegularImplementation;
import chameleon.core.method.exception.ExceptionDeclaration;
import chameleon.core.method.exception.TypeExceptionDeclaration;
import chameleon.core.namespacepart.NamespacePart;
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
import chameleon.support.expression.InstanceofExpression;
import chameleon.support.expression.NullLiteral;
import chameleon.support.expression.RegularLiteral;
import chameleon.support.expression.ThisLiteral;
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
import chameleon.support.statement.FinallyClause;
import chameleon.support.statement.ForStatement;
import chameleon.support.statement.IfThenElseStatement;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.SimpleForControl;
import chameleon.support.statement.StatementExprList;
import chameleon.support.statement.StatementExpression;
import chameleon.support.statement.ThrowStatement;
import chameleon.support.statement.TryStatement;
import chameleon.support.variable.LocalVariableDeclarator;

public class JavaWeaver {

	public JavaWeaver() {

	}
	
	private Map<Method, String> methodNames = new HashMap<Method, String>();
	
	
	public CompilationUnit weave(CompilationUnit source, List<CompilationUnit> allProjectCompilationUnits) throws LookupException {
		if (!source.descendants(Advice.class).isEmpty())
			return translateAdvice(source, allProjectCompilationUnits);
		else
			return weaveRegularType(source, allProjectCompilationUnits);
	}
	
	private String getName(Method method) {
		String name = getExisting(method);
		
		if (name == null) {
			do {
				name = getRandomName();
			} while (methodNames.values().contains(name));
			
			methodNames.put(method.clone(), name);
		}
		
		return name;
	}
	
	private String getExisting(Method method) {
		Method methodToGet = method;
		for (Method m : methodNames.keySet()) {
			try {
				if (method.signature().sameAs(m.signature()))
					methodToGet = m;
			} catch (LookupException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		return methodNames.get(methodToGet);
	}
	
	private Random r = new Random();
	private String alphabet = "abcdefghijklmopqrstuvwxyz";
	private String getRandomName() {
		StringBuilder name = new StringBuilder();
		for (int i = 0; i < 8; i++)
			name.append(alphabet.charAt(r.nextInt(alphabet.length())));
		
		return name.toString();
	}
	
	private CompilationUnit translateAdvice(CompilationUnit source, List<CompilationUnit> allProjectCompilationUnits) throws LookupException {	
		NamespacePart originalNamespacePart = source.namespaceParts().get(0);
				
		Aspect aspect = source.descendants(Aspect.class).get(0);
	  	
		RegularType aspectClass = new RegularType(aspect.getName());
		
		addProceedMethod(aspectClass);
		
		JavaFactory factory = new JavaFactory();
			
		for (Advice advice : (List<Advice>) aspect.advices()) {
			Set<Method> methods = new HashSet<Method>();
			
			for (CompilationUnit cu : allProjectCompilationUnits)
				for (MatchResult<SignatureMethodInvocationPointcutExpression, MethodInvocation> matchResult : (List<MatchResult<SignatureMethodInvocationPointcutExpression, MethodInvocation>>) advice.pointcut().joinpoints(cu))
					if (matchResult.isMatch())
						methods.add(matchResult.getJoinpoint().getElement());
			
			for (Method m : methods) {
				DeclarationWithParametersHeader header = new SimpleNameDeclarationWithParametersHeader("advice_" + advice.name() + "_" + getName(m));
				
				TypeReference returnType;
				
				if (advice.returnType().getType().signature().name().equals("void"))
					returnType = new BasicTypeReference("T");
				else
					returnType = advice.returnType().clone();
					
				NormalMethod adviceMethod = new NormalMethod(header, returnType);
				
				adviceMethod.addModifier(new Public());
				adviceMethod.addModifier(new Static());
				
				header.addTypeParameter(new FormalTypeParameter(new SimpleNameSignature("T")));
	
				// Copy the exceptions
				adviceMethod.setExceptionClause(m.getExceptionClause().clone());
		
				/*
				 *	Add all the parameters to allow the reflective invocation 
				 */
				FormalParameter object = new FormalParameter("_object", new BasicTypeReference("Object"));
				header.addFormalParameter(object);
				
				FormalParameter methodName = new FormalParameter("_methodName", new BasicTypeReference("String"));
				header.addFormalParameter(methodName);
				
				FormalParameter methodArguments = new FormalParameter("_arguments", new ArrayTypeReference(new BasicJavaTypeReference("Object")));
				header.addFormalParameter(methodArguments);
				
				FormalParameter methodArgumentsIndex = new FormalParameter("_$argumentIndex", new ArrayTypeReference(new BasicJavaTypeReference("int")));
				header.addFormalParameter(methodArgumentsIndex);
				
				/*
				 * 	Start the method block
				 */
				Block adviceBody = new Block();
				
				/*
				 * 	Inject the parameters
				 */
				for (FormalParameter fp : (List<FormalParameter>) advice.formalParameters()) {
					int index = advice.pointcutReference().indexOfParameter(fp);
					
					LocalVariableDeclarator parameterInjector = new LocalVariableDeclarator(fp.getTypeReference().clone());
					VariableDeclaration parameterInjectorDecl = new VariableDeclaration(fp.getName());
					
					// Add the indirection to the correct parameter
					ArrayAccessExpression argumentsIndexAccess = new ArrayAccessExpression(new NamedTargetExpression("_$argumentIndex"));
					argumentsIndexAccess.addIndex(new FilledArrayIndex(new RegularLiteral(new BasicJavaTypeReference("int"), Integer.toString(index))));
	
					ArrayAccessExpression argumentsAccess = new ArrayAccessExpression(new NamedTargetExpression("_arguments"));
					argumentsAccess.addIndex(new FilledArrayIndex(argumentsIndexAccess));
	
					// Add the cast, since the arguments is just an Object array
					ClassCastExpression cast = new ClassCastExpression(fp.getTypeReference().clone(), argumentsAccess);
					
					parameterInjectorDecl.setInitialization(cast);
					parameterInjector.add(parameterInjectorDecl);
					
					adviceBody.addStatement(parameterInjector);
				}
				
				/*
				 *	Create the proceed call
				 */
				RegularMethodInvocation proceedInvocation = new RegularMethodInvocation("proceed", new NamedTarget(aspect.name()));
				proceedInvocation.addArgument(new NamedTargetExpression("_object"));
				proceedInvocation.addArgument(new NamedTargetExpression("_methodName"));
				proceedInvocation.addArgument((new BasicTypeArgument(returnType.clone())));
				
				if (advice.type() == AdviceType.AFTER || advice.type() == AdviceType.AFTER_RETURNING || advice.type() == AdviceType.AFTER_THROWING) {
					proceedInvocation.addArgument(new NamedTargetExpression("_arguments"));
					
					LocalVariableDeclarator returnVal = new LocalVariableDeclarator(new BasicTypeReference("T"));
					VariableDeclaration returnValDecl = new VariableDeclaration("_retval");
					returnValDecl.setInitialization(proceedInvocation);
					returnVal.add(returnValDecl);
				
					adviceBody.addStatement(returnVal);
					
					if (advice.type() == AdviceType.AFTER_RETURNING)
						adviceBody.addBlock(((Block) advice.body()).clone());
					
					adviceBody.addStatement(new ReturnStatement(new NamedTargetExpression("_retval")));
				}
				else if (advice.type() == AdviceType.BEFORE) {
					proceedInvocation.addArgument(new NamedTargetExpression("_arguments"));
					
					adviceBody.addBlock(((Block) advice.body()).clone()); 
					adviceBody.addStatement(new ReturnStatement(proceedInvocation));
				}
				else if (advice.type() == AdviceType.AROUND) {		
					// Replace each proceed call to the method call
					List<ProceedCall> proceedCalls = advice.descendants(ProceedCall.class);
					
					if (!proceedCalls.isEmpty()) {
					
						for (ProceedCall pc : proceedCalls) {
							RegularMethodInvocation proceedInvoc = (RegularMethodInvocation) proceedInvocation.clone();
							
							// Create the correct parameters for the proceed call
							ArrayCreationExpression actualArgumentsArray = new ArrayCreationExpression(new ArrayTypeReference(new BasicJavaTypeReference("Object")));
							ArrayInitializer actualArgumentsInitializer = new ArrayInitializer();					
						
							for (Expression e : pc.getActualParameters())
								actualArgumentsInitializer.addInitializer(e);
							
							actualArgumentsArray.setInitializer(actualArgumentsInitializer);
							
							proceedInvoc.addArgument(actualArgumentsArray);
							
							pc.parentLink().getOtherRelation().replace(pc.parentLink(), proceedInvoc.parentLink());
						}
					}
					
					Block modifiedBody = ((Block) advice.body()).clone();
					
					adviceBody.addBlock(modifiedBody);
					if (advice.returnType().getType().signature().name().equals("void"))
						adviceBody.addStatement(new ReturnStatement(new NullLiteral()));
				}
				
				// Wrap everything in a try-catch block
				Block emptyCatchBody = new Block();
				emptyCatchBody.addStatement(new EmptyStatement());
				
				// Re-throw unchecked exceptions (subclasses of RuntimeException )
				Block rethrowBody = new Block();
				ThrowStatement rethrow = new ThrowStatement(new NamedTargetExpression("unchecked"));
				if (advice.type() == AdviceType.AFTER_THROWING)
					rethrowBody.addBlock(((Block) advice.body()).clone());
				rethrowBody.addStatement(rethrow);
				
				TryStatement exceptionHandler = new TryStatement(adviceBody);
				exceptionHandler.addCatchClause(new CatchClause(new FormalParameter("unchecked", new BasicTypeReference("RuntimeException")), rethrowBody.clone()));

				// Add a rethrow for each checked exception
				int exceptionIndex = 0;
				for (ExceptionDeclaration exception : m.getExceptionClause().exceptionDeclarations()) {
					if (exception instanceof TypeExceptionDeclaration) {
						rethrowBody = new Block();
						rethrow = new ThrowStatement(new NamedTargetExpression("ex" + exceptionIndex));
						
						if (advice.type() == AdviceType.AFTER_THROWING)
							rethrowBody.addBlock(((Block) advice.body()).clone());
						
						rethrowBody.addStatement(rethrow);
						
						exceptionHandler.addCatchClause(new CatchClause(new FormalParameter("ex" + exceptionIndex++, ((TypeExceptionDeclaration) exception).getTypeReference().clone()), rethrowBody));
					}
				}
				
				// Add a catch all. This isn't actually necessary since we already handled all cases, but since the generic proceed method throws a throwable we need it to prevent compile errors
				exceptionHandler.addCatchClause(new CatchClause(new FormalParameter("thrwbl", new BasicTypeReference("Throwable")), emptyCatchBody));
				
				// Add a finally clause if we need to
				if (advice.type() == AdviceType.AFTER) {
					exceptionHandler.setFinallyClause(new FinallyClause(((Block) advice.body()).clone()));
				}
				
				Block finalBody = new Block();
				finalBody.addStatement(exceptionHandler);
						
				// Add a -  throw new Error(); - after the try {} catch, 
				// since the try-block should return anyway. If it doesn't, then an error occurred anyway
				ThrowStatement throwError = new ThrowStatement(new ConstructorInvocation(new BasicJavaTypeReference("Error"), null));
				finalBody.addStatement(throwError);
				
				// Set the method body
				adviceMethod.setImplementation(new RegularImplementation(finalBody));
				
				// Add the method
				aspectClass.add(adviceMethod);
			}
		}

		aspect.disconnect();
		originalNamespacePart.add(aspectClass);
		
		return source;
	}
	
	private void addProceedMethod(RegularType aspectClass) {
		
		final String objectName = "_$object";
		final String methodName = "_$methodName";
		final String argumentsName = "_$arguments";
		
		/*
		 *	Create method header
		 */
		
		FormalParameter object = new FormalParameter(objectName, new BasicTypeReference("Object"));
		FormalParameter methodParam = new FormalParameter(methodName, new BasicTypeReference("String"));
		FormalParameter methodArguments = new FormalParameter(argumentsName, new ArrayTypeReference(new BasicJavaTypeReference("Object")));
		
		DeclarationWithParametersHeader pHeader = new SimpleNameDeclarationWithParametersHeader("proceed");
		NormalMethod proceedMethod = new NormalMethod(pHeader, new BasicTypeReference("T"));
		
		pHeader.addFormalParameter(object);
		pHeader.addFormalParameter(methodParam);
		pHeader.addFormalParameter(methodArguments);

		
		pHeader.addTypeParameter(new FormalTypeParameter(new SimpleNameSignature("T")));
		
		proceedMethod.addModifier(new Private());
		proceedMethod.addModifier(new Static());

		//proceedMethod.getExceptionClause().add(new TypeExceptionDeclaration(new BasicJavaTypeReference("java.lang.reflect.InvocationTargetException")));
		proceedMethod.getExceptionClause().add(new TypeExceptionDeclaration(new BasicJavaTypeReference("Throwable")));
		
		/*
		 * 	Create method body
		 */
		Block proceedMethodBody = new Block();

		// Class invocationClass;
		LocalVariableDeclarator invocationClass = new LocalVariableDeclarator(new BasicJavaTypeReference("Class"));
		JavaVariableDeclaration invocationClassDecl = new JavaVariableDeclaration("invocationClass");
		
		invocationClass.add(invocationClassDecl);
		proceedMethodBody.addStatement(invocationClass);
		/* if (_$object instanceof Class) {
			invocationClass = (Class) _$object;
		}
		else {
			invocationClass = _$object.getClass();
		}
		*/
		InstanceofExpression testObject = new InstanceofExpression(new NamedTargetExpression(objectName), new BasicJavaTypeReference("Class"));
		ClassCastExpression objectCastToClass = new ClassCastExpression(new BasicJavaTypeReference("Class"), new NamedTargetExpression(objectName));
		AssignmentExpression assignObjIf = new AssignmentExpression(new NamedTargetExpression("invocationClass"), objectCastToClass);
		AssignmentExpression assignObjElse = new AssignmentExpression(new NamedTargetExpression("invocationClass"), new RegularMethodInvocation("getClass", new NamedTarget(objectName)));
		
		Block ifBody = new Block();
		ifBody.addStatement(new StatementExpression(assignObjIf));
		
		Block elseBody = new Block();
		elseBody.addStatement(new StatementExpression(assignObjElse));
		
		IfThenElseStatement invocationIte = new IfThenElseStatement(testObject, ifBody, elseBody);
		
		proceedMethodBody.addStatement(invocationIte);
		
		// Class[] types = new Class[_arguments.length];
		NamedTargetExpression argumentsDotLength = new NamedTargetExpression("length", new NamedTarget(argumentsName));
						
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
	
		ArrayAccessExpression argumentsAccess = new ArrayAccessExpression(new NamedTargetExpression(argumentsName));
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
		
		RegularMethodInvocation getMethod = new RegularMethodInvocation("getMethod", new NamedTargetExpression("invocationClass"));
		getMethod.addArgument(new NamedTargetExpression(methodName));
		getMethod.addArgument(new NamedTargetExpression("types"));
		
		methodDecl.setInitialization(getMethod);
		method.add(methodDecl);
		
		
		Block tryBody = new Block();
		tryBody.addStatement(method);
				
		
		// return (T) m.invoke(_object, _arguments);
		RegularMethodInvocation methodInvocation = new RegularMethodInvocation("invoke", new NamedTarget("m"));
		methodInvocation.addArgument(new NamedTargetExpression(objectName));
		methodInvocation.addArgument(new NamedTargetExpression(argumentsName));
		ReturnStatement returnStatement = new ReturnStatement(new ClassCastExpression(new BasicTypeReference("T"), methodInvocation));
		
		tryBody.addStatement(returnStatement);
		
		TryStatement tryCatch = new TryStatement(tryBody);
		
		// catch (NoSuchMethodException nsm)
		Block nsmBody = new Block();
		
		// new try - catch block
		Block innerBody = new Block();
		LocalVariableDeclarator invocationClone = method.clone();
		((RegularMethodInvocation) invocationClone.variableDeclarations().get(0).initialization()).setName("getDeclaredMethod");
		innerBody.addStatement(invocationClone);
		
		// m.setAccessible(true);
		RegularMethodInvocation setAccessible = new RegularMethodInvocation("setAccessible", new NamedTarget("m"));
		setAccessible.addArgument(new RegularLiteral(new BasicTypeReference("boolean"), "true"));
		
		innerBody.addStatement(new StatementExpression(setAccessible));
		
		innerBody.addStatement(returnStatement.clone());

		TryStatement innerTryCatch = new TryStatement(innerBody);
		
		// All catch clauses required for reflection
		Block emptyCatchBody = new Block();
		emptyCatchBody.addStatement(new EmptyStatement());
		
		Block rethrowBody = new Block();
		ThrowStatement rethrow = new ThrowStatement(new RegularMethodInvocation("getCause", new NamedTarget("invo")));
		rethrowBody.addStatement(rethrow);
		
		CatchClause catchIllegalArg = new CatchClause(new FormalParameter("iarg", new BasicTypeReference("IllegalArgumentException")), emptyCatchBody.clone());
		CatchClause catchSecurity = new CatchClause(new FormalParameter("se", new BasicTypeReference("SecurityException")), emptyCatchBody.clone());
		CatchClause catchIllegalAcc = new CatchClause(new FormalParameter("iac", new BasicTypeReference("IllegalAccessException")), emptyCatchBody.clone());
		CatchClause catchNoSuchMethod = new CatchClause(new FormalParameter("nsmInner", new BasicTypeReference("NoSuchMethodException")), emptyCatchBody.clone());
		CatchClause catchInvocationT = new CatchClause(new FormalParameter("invo", new BasicTypeReference("java.lang.reflect.InvocationTargetException")), rethrowBody.clone());
		
		innerTryCatch.addCatchClause(catchIllegalArg);
		innerTryCatch.addCatchClause(catchSecurity);
		innerTryCatch.addCatchClause(catchIllegalAcc);
		innerTryCatch.addCatchClause(catchInvocationT);
		innerTryCatch.addCatchClause(catchNoSuchMethod);
		
		
		nsmBody.addStatement(innerTryCatch);
		
		 // catch (NoSuchMethodException nsm) {
		tryCatch.addCatchClause(new CatchClause(new FormalParameter("nsm", new BasicTypeReference("NoSuchMethodException")), nsmBody));
		
		// All catch clauses required for reflection
		tryCatch.addCatchClause(catchIllegalArg.clone());
		tryCatch.addCatchClause(catchSecurity.clone());
		tryCatch.addCatchClause(catchIllegalAcc.clone());
		tryCatch.addCatchClause(catchInvocationT.clone());
		
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
		
		// Weave all advices
		for (Advice a : advices) {
			// Fetch all joinpoints for this advice
			List<MatchResult<SignatureMethodInvocationPointcutExpression, MethodInvocation>> joinpoints = a.pointcut().joinpoints(source);
			
			// Weave those joinpoints
			for (MatchResult<SignatureMethodInvocationPointcutExpression, MethodInvocation> matchResult : joinpoints) {	
				// Create a call to the advice method
				RegularMethodInvocation adviceInvocation = new RegularMethodInvocation("advice_" + a.name() + "_" + getName(matchResult.getJoinpoint().getElement()), new NamedTarget(a.aspect().name()));
				Statement call = new StatementExpression(adviceInvocation);
				
				InvocationTarget target = matchResult.getJoinpoint().getTarget();
				if (target == null)
					target = new ThisLiteral();
				else {
					if (target instanceof NamedTarget && ((NamedTarget) target).getElement() instanceof RegularJavaType) {
						target = new ClassLiteral(new BasicTypeReference(((RegularJavaType) ((NamedTarget) target).getElement()).getType().getFullyQualifiedName()));
					} else {
						target = target.clone();
					}
				}

				adviceInvocation.addArgument(new VariableReference("object", target));
				adviceInvocation.addArgument(new RegularLiteral(new BasicTypeReference("String"), "\"" + ((SimpleNameMethodInvocation)matchResult.getJoinpoint()).name()+ "\""));
				List<Expression> methodParameters = matchResult.getJoinpoint().getActualParameters();
				ArrayCreationExpression parameterArray = new ArrayCreationExpression(new ArrayTypeReference(new BasicJavaTypeReference("Object")));
				ArrayInitializer parameterInitializer = new ArrayInitializer();					
			
				for (Expression e : methodParameters)
					parameterInitializer.addInitializer(e.clone());
				
				parameterArray.setInitializer(parameterInitializer);
				
				adviceInvocation.addArgument(parameterArray);
				
				ArrayCreationExpression indexArray = new ArrayCreationExpression(new ArrayTypeReference(new BasicJavaTypeReference("int")));
				ArrayInitializer indexInitializer = new ArrayInitializer();
				
				Pointcut pc = a.pointcut();
				for (FormalParameter param : (List<FormalParameter>) pc.header().formalParameters()) {
					// Find the index of the parameter with the same name as 'param' in the matched pointcut ref
					int index = matchResult.getExpression().indexOfParameter(param);
					
					indexInitializer.addInitializer(new RegularLiteral(new BasicJavaTypeReference("int"), Integer.toString(index)));
				}
				
				indexArray.setInitializer(indexInitializer);
				adviceInvocation.addArgument(indexArray);

				
				
				// Set the generic parameter
				if (!matchResult.getJoinpoint().getType().signature().name().equals("void"))
					adviceInvocation.addArgument(new BasicTypeArgument(new BasicTypeReference(matchResult.getJoinpoint().getType().getFullyQualifiedName())));
				
				// Weave
				matchResult.getJoinpoint().parentLink().getOtherRelation().replace(matchResult.getJoinpoint().parentLink(), adviceInvocation.parentLink());
			}
		}
		
		return source;
	}	
}
