package aspectsjava.translate.weaver.weavingprovider;

import java.util.List;

import aspectsjava.model.advice.transformation.runtime.CatchClauseCoordinator;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeIfCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeSingleArgumentTypeCheck;
import aspectsjava.model.advice.transformation.runtime.transformationprovider.RuntimeTypeCheck;

import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.runtimetransformation.Coordinator;
import chameleon.aspects.advice.runtimetransformation.RuntimeTransformationProvider;
import chameleon.aspects.advice.runtimetransformation.transformationprovider.RuntimeExpressionProvider;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.aspects.pointcut.expression.generic.RuntimePointcutExpression;
import chameleon.aspects.pointcut.expression.runtime.ArgsPointcutExpression;
import chameleon.aspects.pointcut.expression.runtime.IfPointcutExpression;
import chameleon.aspects.pointcut.expression.runtime.ThisTypePointcutExpression;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;
import chameleon.support.expression.ThisLiteral;
import chameleon.support.statement.CatchClause;

public abstract class InsertProvider implements WeavingProvider<Block, List<Statement>>, RuntimeTransformationProvider {
	
	@Override
	public void execute(MatchResult<? extends PointcutExpression, Block> joinpoint, List<Statement> adviceResult, Advice advice) {
		Block originalBody = joinpoint.getJoinpoint().clone();
		executeWeaving(joinpoint, adviceResult);
		
		// Register the runtime transformers that are needed
		argumentsTypeCheck = new RuntimeSingleArgumentTypeCheck(new NamedTargetExpression(((CatchClause) joinpoint.getJoinpoint().parent()).getExceptionParameter().signature().name()));
		
		Coordinator<Block> catchCoordinator = new CatchClauseCoordinator(this, joinpoint, originalBody);
		catchCoordinator.transform(joinpoint.getJoinpoint(), advice.formalParameters());
	}
	
	private RuntimeExpressionProvider argumentsTypeCheck;
	
	protected abstract void executeWeaving(MatchResult<? extends PointcutExpression, Block> joinpoint, List<Statement> adviceResult);
	
	@Override
	public boolean supports(RuntimePointcutExpression pointcutExpression) {
		if (pointcutExpression instanceof ArgsPointcutExpression)
			return true;
		
		if (pointcutExpression instanceof ThisTypePointcutExpression)
			return true;
		
		if (pointcutExpression instanceof IfPointcutExpression)
			return true;
		
		return false;
	}

	@Override
	public RuntimeExpressionProvider getRuntimeTransformer(RuntimePointcutExpression pointcutExpression) {
		if (pointcutExpression instanceof ArgsPointcutExpression)
			return argumentsTypeCheck;
		
		if (pointcutExpression instanceof ThisTypePointcutExpression)
			return new RuntimeTypeCheck(new ThisLiteral());

		if (pointcutExpression instanceof IfPointcutExpression)
			return new RuntimeIfCheck();

		return null;
	}
	
}
