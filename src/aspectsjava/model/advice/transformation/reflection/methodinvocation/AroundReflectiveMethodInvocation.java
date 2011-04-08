package aspectsjava.model.advice.transformation.reflection.methodinvocation;

import java.util.List;

import jnome.core.expression.ArrayCreationExpression;
import jnome.core.expression.ArrayInitializer;
import jnome.core.language.Java;
import jnome.core.type.ArrayTypeReference;
import jnome.core.type.BasicJavaTypeReference;
import chameleon.aspects.WeavingEncapsulator;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.AdviceReturnStatement;
import chameleon.aspects.advice.types.ProceedCall;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.expression.Expression;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.lookup.LookupException;
import chameleon.core.statement.Block;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.Type;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.expression.NullLiteral;
import chameleon.support.statement.ReturnStatement;

public class AroundReflectiveMethodInvocation extends ReflectiveMethodInvocation {

	public AroundReflectiveMethodInvocation(MatchResult<? extends PointcutExpression, ? extends MethodInvocation> joinpoint, Advice advice) {
		super(joinpoint, advice);
	}

	protected boolean encloseWithTry() {
		return !getAdvice().body().descendants(ProceedCall.class).isEmpty();
	}

	@Override
	protected Block getInnerBody(WeavingEncapsulator next) {
		Block adviceBody = getAdvice().body().clone();
		
		// Replace each proceed call to the method call
		List<ProceedCall> proceedCalls = adviceBody.descendants(ProceedCall.class);
		
		for (ProceedCall pc : proceedCalls) {
			// Create the correct parameters for the proceed call
			ArrayCreationExpression actualArgumentsArray = new ArrayCreationExpression(new ArrayTypeReference(new BasicJavaTypeReference("Object")));
			ArrayInitializer actualArgumentsInitializer = new ArrayInitializer();					
		
			for (Expression e : pc.getActualParameters())
				actualArgumentsInitializer.addInitializer(e);
			
			actualArgumentsArray.setInitializer(actualArgumentsInitializer);
			
			MethodInvocation reflectiveCall = getNextInvocation(next);
			
			Expression reflectiveCallInvocation = null;
			// Note that if the return type is a primitive, we first have to cast the primitive to its boxed variant, then cast to T
			try {
				Type type = getAdvice().actualReturnType().getType();
				Java java = (Java) getAdvice().language(Java.class);
				
				if (type.isTrue(java.property("primitive")) && !type.getFullyQualifiedName().equals("void"))
					reflectiveCallInvocation = new ClassCastExpression(new BasicTypeReference(java.box(type).getFullyQualifiedName()), reflectiveCall.clone());
				else
					reflectiveCallInvocation = reflectiveCall.clone();

				pc.parentLink().getOtherRelation().replace(pc.parentLink(), reflectiveCallInvocation.parentLink());
			} catch (LookupException e) {
				System.out.println("Error while getting advice type");
				e.printStackTrace();
			}		
		}
		
		try {
			Type type = getAdvice().actualReturnType().getType();
			if (type.signature().name().equals("void"))
				// We need an explicit return because the return type of the advice method is never 'void'
				adviceBody.addStatement(new ReturnStatement(new NullLiteral()));
			else {
				// We need to find all return statements and cast the return expression to the return type.
				// For instance in Java, the return type will be generic (T), so we need to cast to T. This isn't *always* necessary (e.g. return proceed())
				// but no harm is done if we do it anyway in those cases.
				for (AdviceReturnStatement st : adviceBody.descendants(AdviceReturnStatement.class)) {
					// Note that if the type is a primitive, we first have to cast the primitive to its boxed variant, then cast to T
					Java java = (Java) getAdvice().language(Java.class);
					
					Expression expressionToCast = null;
					if (type.isTrue(java.property("primitive")))
						expressionToCast = new ClassCastExpression(new BasicTypeReference(java.box(type).getFullyQualifiedName()), st.getExpression());
					else
						expressionToCast = st.getExpression();

					ClassCastExpression cast = new ClassCastExpression(new BasicTypeReference("T"), expressionToCast);
					st.setExpression(cast);
				}
			}
		} catch (LookupException e) {
			System.out.println("Error while getting advice type");
		}
		
		return adviceBody;
	}
}
