package aspectsjava.model.advice.transformation.reflection.methodinvocation;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.core.statement.Block;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.ReturnStatement;

public class BeforeReflectiveMethodInvocation extends ReflectiveMethodInvocation  {
	@Override
	protected Block getInnerBody(WeavingEncapsulator next) {
		Block adviceBody = new Block();
		
		/*
		 *	Add the advice-body itself 
		 */
		adviceBody.addBlock(getAdvice().body().clone());
		
		/*
		 * 	Add the return statement
		 */
		RegularMethodInvocation proceedInvocation = getNextInvocation(next);			
		adviceBody.addStatement(new ReturnStatement(proceedInvocation));
		
		return adviceBody;
	}
}
