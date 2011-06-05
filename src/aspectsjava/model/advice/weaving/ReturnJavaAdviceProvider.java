package aspectsjava.model.advice.weaving;

import java.util.List;

import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.weaving.AdviceWeaveResultProvider;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;

public class ReturnJavaAdviceProvider<T extends Element> implements AdviceWeaveResultProvider<T, List<Statement>> {
	
	@Override
	public List<Statement> getWeaveResult(Advice advice, MatchResult<T> joinpoint) throws LookupException {
		return ((Block) advice.body()).statements();
	}
}
