package aspectsjava.model.advice.transformation.cast;

import jnome.core.language.Java;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.types.Throwing;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.statement.Block;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.exception.ModelException;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.Type;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.expression.InstanceofExpression;
import chameleon.support.statement.CatchClause;
import chameleon.support.statement.IfThenElseStatement;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.ThrowStatement;
import chameleon.support.statement.TryStatement;
import chameleon.support.variable.LocalVariable;
import chameleon.support.variable.LocalVariableDeclarator;

public class AfterThrowingCastTransformationProvider extends CastTransformationProvider {
	@Override
	protected Block getBody(WeavingEncapsulator next) {
		Block innerAdviceBody = new Block();

		/*
		 *	Create the proceed call
		 */
		Expression<?> getValueInvocation = getNextInvocation(next);
		
		/*
		 * 	Add the return statement
		 */
		innerAdviceBody.addStatement(new ReturnStatement(getValueInvocation));
		
		/*
		 * 	Wrap that in a try { } catch { } statement - the catch statement will catch the exception, then rethrow
		 */
		TryStatement tryStatement = new TryStatement(innerAdviceBody);
		
		// There is only one possible type thrown
		Type caughtType = null;
		try {
			caughtType = ((Java) getAdvice().language(Java.class)).findType("java.lang.ClassCastException");
		} catch (LookupException e1) {
			// Shouldn't be able to occur
			e1.printStackTrace();
		}
		
		Block catchBlockBody = new Block();
		ThrowStatement rethrow = new ThrowStatement(new NamedTargetExpression("_$classCastEx"));
		try {
			Throwing m = (Throwing) getAdvice().modifiers(getAdvice().language().property("advicetype.throwing")).get(0);
			// Do a type check if there is a type defined
			if (m.hasExceptionParameter()) {
				Type declaredType = m.exceptionParameter().getType();
				// If the declared type is the same or a super type of the type caught, add the advice and expose the parameter
				if (caughtType.assignableTo(declaredType)) {
					LocalVariableDeclarator paramExpose = new LocalVariableDeclarator(m.exceptionParameter().getTypeReference().clone());
					paramExpose.add(new VariableDeclaration<LocalVariable>(m.exceptionParameter().getName(), new NamedTargetExpression("_$classCastEx")));
					catchBlockBody.addStatement(paramExpose);
					catchBlockBody.addBlock(getAdvice().body().clone());
					catchBlockBody.addStatement(rethrow);
				}
				// If the declared type is a subtype of the type caught, we need a runtime check
				else if (declaredType.subTypeOf(caughtType)) {
					Block innerBody = new Block();
					LocalVariableDeclarator paramExpose = new LocalVariableDeclarator(m.exceptionParameter().getTypeReference().clone());
					paramExpose.add(new VariableDeclaration<LocalVariable>(m.exceptionParameter().getName(), new ClassCastExpression(m.exceptionParameter().getTypeReference().clone(), new NamedTargetExpression("_$classCastEx"))));
					
					innerBody.addStatement(paramExpose);
					innerBody.addBlock(getAdvice().body().clone());
					
					InstanceofExpression instanceOf = new InstanceofExpression(new NamedTargetExpression("_$classCastEx"), new BasicTypeReference(declaredType.getFullyQualifiedName()));
					IfThenElseStatement runtimeTest = new IfThenElseStatement(instanceOf, innerBody, null);
					
					catchBlockBody.addStatement(runtimeTest);
					catchBlockBody.addStatement(rethrow);
				}
				else {
					// The types aren't related, so just re throw the exception
					catchBlockBody.addStatement(rethrow);
				}
			} else {
				catchBlockBody.addBlock(getAdvice().body().clone());
				catchBlockBody.addStatement(rethrow);
			}
				
		} catch (ModelException e) {
			// This shouldn't be able to occur
			e.printStackTrace();
		}
		
		tryStatement.addCatchClause(new CatchClause(new FormalParameter("_$classCastEx", new BasicTypeReference(caughtType.getFullyQualifiedName())), catchBlockBody));
		
		Block adviceBody = new Block();
		adviceBody.addStatement(tryStatement);
		
		return adviceBody;
	}

}
