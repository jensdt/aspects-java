package aspectsjava.model.advice.transformation.reflection.fieldaccess;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.types.Returning;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.statement.Block;
import chameleon.core.variable.VariableDeclaration;
import chameleon.exception.ModelException;
import chameleon.oo.type.BasicTypeReference;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.variable.LocalVariable;
import chameleon.support.variable.LocalVariableDeclarator;

public class AfterReturningReflectiveFieldRead extends ReflectiveFieldRead {

	@Override
	protected Block getBody(WeavingEncapsulator next) {
		Block adviceBody = new Block();

		/*
		 *	Create the proceed call
		 */
		Expression<?> getValueInvocation = getNextInvocation(next);
		
		/*
		 *	Add the proceed-invocation, assign it to a local variable 
		 */
		LocalVariableDeclarator returnVal = new LocalVariableDeclarator(new BasicTypeReference<BasicTypeReference<?>>("T"));
		
		/*
		 *	Find the name of the local variable to assign the value to 	
		 */
		String returnName = retvalName;
		try {
			Returning m = (Returning) getAdvice().modifiers(getAdvice().language().property("advicetype.returning")).get(0);
			if (m.hasReturnParameter())
				returnName = m.returnParameter().getName();
		} catch (ModelException e) {
			
		}
				
		VariableDeclaration<LocalVariable> returnValDecl = new VariableDeclaration<LocalVariable>(returnName);
		returnValDecl.setInitialization(getValueInvocation);
		returnVal.add(returnValDecl);
	
		adviceBody.addStatement(returnVal);
		
		/*
		 *	Add the advice-body itself 
		 */
		adviceBody.addBlock(getAdvice().body().clone());
		
		/*
		 * 	Add the return statement
		 */
		adviceBody.addStatement(new ReturnStatement(new NamedTargetExpression(returnName)));
		
		return adviceBody;
	}
}