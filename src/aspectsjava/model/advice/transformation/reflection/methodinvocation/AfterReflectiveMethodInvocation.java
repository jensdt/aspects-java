package aspectsjava.model.advice.transformation.reflection.methodinvocation;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.lookup.LookupException;
import chameleon.core.statement.Block;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.FinallyClause;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.TryStatement;

public class AfterReflectiveMethodInvocation extends ReflectiveMethodInvocation {

	public AfterReflectiveMethodInvocation(MatchResult<? extends PointcutExpression, ? extends MethodInvocation> joinpoint, Advice advice) {
		super(joinpoint, advice);
		
	}
	
	@Override
	public TryStatement getEnclosingTry(Block tryBody) throws LookupException {
		TryStatement enclosingTry = super.getEnclosingTry(tryBody);
		enclosingTry.setFinallyClause(new FinallyClause(((Block) getAdvice().body()).clone()));
		
		return enclosingTry;
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