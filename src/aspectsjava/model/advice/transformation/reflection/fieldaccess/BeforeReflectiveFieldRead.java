package aspectsjava.model.advice.transformation.reflection.fieldaccess;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.statement.Block;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.ReturnStatement;

public class BeforeReflectiveFieldRead extends ReflectiveFieldRead {

	public BeforeReflectiveFieldRead(MatchResult<?, ?> joinpoint, Advice advice) {
		super(joinpoint, advice);
	}
	
	@Override
	protected Block getBody(WeavingEncapsulator next) {
		Block adviceBody = new Block();

		/*
		 *	Add the advice-body itself 
		 */
		adviceBody.addBlock(((Block) advice().body()).clone());
		
		/*
		 *	Create the proceed call
		 */
		RegularMethodInvocation<?> getValueInvocation = getNextInvocation(next);
		
		/*
		 * 	Add the return statement
		 */
		adviceBody.addStatement(new ReturnStatement(getValueInvocation));
		
		return adviceBody;
	}
}