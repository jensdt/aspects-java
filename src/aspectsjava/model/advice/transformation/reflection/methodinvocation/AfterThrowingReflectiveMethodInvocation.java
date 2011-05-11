package aspectsjava.model.advice.transformation.reflection.methodinvocation;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.types.Throwing;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.statement.Block;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableDeclaration;
import chameleon.exception.ModelException;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.Type;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.expression.InstanceofExpression;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.CatchClause;
import chameleon.support.statement.IfThenElseStatement;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.ThrowStatement;
import chameleon.support.variable.LocalVariable;
import chameleon.support.variable.LocalVariableDeclarator;

public class AfterThrowingReflectiveMethodInvocation extends ReflectiveMethodInvocation  {
	@Override
	public CatchClause getCatchClause(String name, Type caughtType) {
		Block body = new Block();
		ThrowStatement rethrow = new ThrowStatement(new NamedTargetExpression(name));
		
		try {
			Throwing m = (Throwing) getAdvice().modifiers(getAdvice().language().property("advicetype.throwing")).get(0);
			// Do a type check if there is a type defined
			if (m.hasExceptionParameter()) {
				Type declaredType = m.exceptionParameter().getType();
				// If the declared type is the same or a super type of the type caught, add the advice and expose the parameter
				if (caughtType.assignableTo(declaredType)) {
					LocalVariableDeclarator paramExpose = new LocalVariableDeclarator(m.exceptionParameter().getTypeReference().clone());
					paramExpose.add(new VariableDeclaration<LocalVariable>(m.exceptionParameter().getName(), new NamedTargetExpression(name)));
					body.addStatement(paramExpose);
					body.addBlock(getAdvice().body().clone());
					body.addStatement(rethrow);
				}
				// If the declared type is a subtype of the type caught, we need a runtime check
				else if (declaredType.subTypeOf(caughtType)) {
					Block innerBody = new Block();
					LocalVariableDeclarator paramExpose = new LocalVariableDeclarator(m.exceptionParameter().getTypeReference().clone());
					paramExpose.add(new VariableDeclaration<LocalVariable>(m.exceptionParameter().getName(), new ClassCastExpression(m.exceptionParameter().getTypeReference().clone(), new NamedTargetExpression(name))));
					
					innerBody.addStatement(paramExpose);
					innerBody.addBlock(getAdvice().body().clone());
					
					InstanceofExpression instanceOf = new InstanceofExpression(new NamedTargetExpression(name), new BasicTypeReference(declaredType.getFullyQualifiedName()));
					IfThenElseStatement runtimeTest = new IfThenElseStatement(instanceOf, innerBody, null);
					
					body.addStatement(runtimeTest);
					body.addStatement(rethrow);
				}
				else {
					// The types aren't related, so just re throw the exception
					body.addStatement(rethrow);
				}
			} else {
				body.addBlock(getAdvice().body().clone());
				body.addStatement(rethrow);
			}
				
		} catch (ModelException e) {
			// This shouldn't be able to occur
			e.printStackTrace();
		}
		
		return new CatchClause(new FormalParameter(name, new BasicTypeReference(caughtType.getFullyQualifiedName())), body);
	}

	@Override
	protected Block getInnerBody(WeavingEncapsulator next) {
		Block adviceBody = new Block();

		/*
		 * 	Add the return statement
		 */
		RegularMethodInvocation proceedInvocation = getNextInvocation(next);
		adviceBody.addStatement(new ReturnStatement(proceedInvocation));
		
		return adviceBody;
	}
}