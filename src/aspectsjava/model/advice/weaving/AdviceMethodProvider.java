package aspectsjava.model.advice.weaving;

import java.util.List;

import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.weaving.AdviceWeaveResultProvider;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.namingRegistry.NamingRegistryFactory;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTarget;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.Method;
import chameleon.core.statement.Statement;
import chameleon.oo.type.generics.BasicTypeArgument;
import chameleon.support.expression.NullLiteral;
import chameleon.support.expression.ThisLiteral;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.member.simplename.variable.MemberVariableDeclarator;
import chameleon.support.statement.StatementExpression;

public abstract class AdviceMethodProvider<T extends Element> implements AdviceWeaveResultProvider<T, MethodInvocation>  {

	@Override
	public MethodInvocation getWeaveResult(Advice advice, MatchResult<T> joinpoint)	throws LookupException {
		RegularMethodInvocation getInstance = new RegularMethodInvocation("instance", new NamedTarget(advice.aspect().name()));
		RegularMethodInvocation adviceInvocation = new RegularMethodInvocation(getName(advice, joinpoint), getInstance);
		Statement call = new StatementExpression(adviceInvocation);
		
		for (Expression e : getParameters(advice, joinpoint))
			adviceInvocation.addArgument(e);
		
		BasicTypeArgument genericParameter = getGenericParameter(advice, joinpoint);
		if (genericParameter != null)
			adviceInvocation.addArgument(genericParameter);
		
		return adviceInvocation;
	}
	
	protected Expression getSelf(MatchResult<T> joinpoint) {
		// Note, we can not use 'ElementWithModifier', e.g. in this scenario: static void foo() { final int i = p.a; } would match the local variable 
		Element currentElement = joinpoint.getJoinpoint();
		boolean found = false;
		boolean isStatic = false;
		while (!found && currentElement.parent() != null) {
			currentElement = currentElement.parent();
			if (currentElement instanceof MemberVariableDeclarator) {
				found = true;
				isStatic = (((MemberVariableDeclarator) currentElement).isTrue(currentElement.language().property("class")));
			} else if (currentElement instanceof Method) {
				found = true;
				isStatic = (((Method) currentElement).isTrue(currentElement.language().property("class")));
			}
		}
		
		Expression self;
		if (isStatic)
			self = new NullLiteral();
		else
			self = new ThisLiteral();
		
		return self;
	}
	
	
	protected String getName(Advice advice, MatchResult<T> joinpoint) throws LookupException {
		NamingRegistry<Advice> adviceNamingRegistry = NamingRegistryFactory.instance().getNamingRegistryFor("advice");
		
		return "advice_" + adviceNamingRegistry.getName(advice);
	}
	
	protected BasicTypeArgument getGenericParameter(Advice advice, MatchResult<T> joinpoint) throws LookupException {
		return null;
	}
	
	protected abstract List<Expression> getParameters(Advice advice, MatchResult<T> joinpoint) throws LookupException ;
}
