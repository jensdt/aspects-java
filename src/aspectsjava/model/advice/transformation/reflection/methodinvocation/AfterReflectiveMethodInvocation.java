package aspectsjava.model.advice.transformation.reflection.methodinvocation;

import chameleon.aspects.WeavingEncapsulator;
import chameleon.core.lookup.LookupException;
import chameleon.core.statement.Block;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.statement.FinallyClause;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.TryStatement;

public class AfterReflectiveMethodInvocation extends ReflectiveMethodInvocation {
	@Override
	public TryStatement getEnclosingTry(Block tryBody) throws LookupException {
		TryStatement enclosingTry = super.getEnclosingTry(tryBody);
		enclosingTry.setFinallyClause(new FinallyClause(getAdvice().body().clone()));
		
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