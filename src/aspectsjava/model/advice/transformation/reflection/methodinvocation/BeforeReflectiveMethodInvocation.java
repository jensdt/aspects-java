package aspectsjava.model.advice.transformation.reflection.methodinvocation;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.statement.Block;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.ReturnStatement;

public class BeforeReflectiveMethodInvocation extends ReflectiveMethodInvocation  {

	public BeforeReflectiveMethodInvocation(MatchResult<? extends PointcutExpression, ? extends MethodInvocation> joinpoint, Advice advice) {
		super(joinpoint, advice);
	}

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
