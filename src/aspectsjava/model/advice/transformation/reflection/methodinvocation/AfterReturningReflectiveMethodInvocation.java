package aspectsjava.model.advice.transformation.reflection.methodinvocation;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.types.Returning;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.statement.Block;
import chameleon.core.variable.VariableDeclaration;
import chameleon.exception.ModelException;
import chameleon.oo.type.BasicTypeReference;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.variable.LocalVariableDeclarator;

public class AfterReturningReflectiveMethodInvocation extends ReflectiveMethodInvocation {
	@Override
	protected Block getInnerBody(WeavingEncapsulator next) {
		Block adviceBody = new Block();

		RegularMethodInvocation proceedInvocation = getNextInvocation(next);
		
		/*
		 *	Add the proceed-invocation, assign it to a local variable 
		 */
		LocalVariableDeclarator returnVal = new LocalVariableDeclarator(new BasicTypeReference("T"));
		
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
		
		
		VariableDeclaration returnValDecl = new VariableDeclaration(returnName);
		returnValDecl.setInitialization(proceedInvocation);
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