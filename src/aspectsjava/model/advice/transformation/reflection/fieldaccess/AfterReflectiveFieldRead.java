package aspectsjava.model.advice.transformation.reflection.fieldaccess;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.types.Returning;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.statement.Block;
import chameleon.core.variable.VariableDeclaration;
import chameleon.exception.ModelException;
import chameleon.oo.type.BasicTypeReference;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.FinallyClause;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.TryStatement;
import chameleon.support.variable.LocalVariable;
import chameleon.support.variable.LocalVariableDeclarator;

public class AfterReflectiveFieldRead extends ReflectiveFieldRead {
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
		 * 	Wrap that in a try { } finally { } statement
		 */
		TryStatement tryStatement = new TryStatement(innerAdviceBody);
		tryStatement.setFinallyClause(new FinallyClause(getAdvice().body().clone()));
		
		Block adviceBody = new Block();
		adviceBody.addStatement(tryStatement);
		
		return adviceBody;
		
	}
}