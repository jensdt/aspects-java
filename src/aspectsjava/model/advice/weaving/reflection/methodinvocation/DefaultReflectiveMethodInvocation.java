package aspectsjava.model.advice.weaving.reflection.methodinvocation;

import java.util.ArrayList;
import java.util.List;

import jnome.core.expression.ArrayCreationExpression;
import jnome.core.expression.ArrayInitializer;
import jnome.core.language.Java;
import jnome.core.type.ArrayTypeReference;
import jnome.core.type.BasicJavaTypeReference;
import aspectsjava.model.advice.weaving.TargetedAdviceMethodProvider;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.namingRegistry.NamingRegistry;
import chameleon.aspects.namingRegistry.NamingRegistryFactory;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.Method;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.Type;
import chameleon.oo.type.TypeIndirection;
import chameleon.oo.type.generics.BasicTypeArgument;
import chameleon.support.expression.RegularLiteral;

public class DefaultReflectiveMethodInvocation extends TargetedAdviceMethodProvider<MethodInvocation> {

	@Override
	protected String getName(Advice advice, MatchResult<MethodInvocation> joinpoint) throws LookupException {
		NamingRegistry<Advice> adviceNamingRegistry = NamingRegistryFactory.instance().getNamingRegistryFor("advice");
		NamingRegistry<Method> methodNamingRegistry = NamingRegistryFactory.instance().getNamingRegistryFor("javamethod");
		
		Method method = joinpoint.getJoinpoint().getElement();
		
		return "advice_" + adviceNamingRegistry.getName(advice) + "_" + methodNamingRegistry.getName(method);
	}
	
	@Override
	protected List<Expression> getParameters(Advice advice, MatchResult<MethodInvocation> joinpoint) throws LookupException {
		List<Expression> parameters = new ArrayList<Expression>();
		
		// Cast is ok because a MI is a NamedTargetExpression which will return an InvocationTarget
		InvocationTarget target = (InvocationTarget) getTarget(joinpoint);
		
		Expression reference = null;
		if (target instanceof Expression)
			reference = (Expression) target.clone();
		else if (target instanceof NamedTarget)
			reference = new NamedTargetExpression(((NamedTarget) target).name());

		parameters.add(reference);
		parameters.add(new RegularLiteral(new BasicTypeReference("String"), "\"" + joinpoint.getJoinpoint().getElement().name() + "\""));
		
		List<Expression> methodParameters = joinpoint.getJoinpoint().getActualParameters();
		ArrayCreationExpression parameterArray = new ArrayCreationExpression(new ArrayTypeReference(new BasicJavaTypeReference("Object")));
		ArrayInitializer parameterInitializer = new ArrayInitializer();					
	
		for (Expression e : methodParameters)
			parameterInitializer.addInitializer(e.clone());
		
		parameterArray.setInitializer(parameterInitializer);
		
		parameters.add(parameterArray);
		
		Expression self = getSelf(joinpoint);
		
		parameters.add(self);
		
		return parameters;
	}
	
	@Override
	protected BasicTypeArgument getGenericParameter(Advice advice, MatchResult<MethodInvocation> joinpoint)	throws LookupException {
		Type type = joinpoint.getJoinpoint().getElement().returnType();
		Java java = (Java) joinpoint.getJoinpoint().language(Java.class);
		
		if (type instanceof TypeIndirection)
			type = ((TypeIndirection) type).aliasedType();
				
		// Set the generic parameter
		if (joinpoint.getJoinpoint().getType() != ((ObjectOrientedLanguage) joinpoint.getJoinpoint().language(ObjectOrientedLanguage.class)).voidType()) {
			if (type.isTrue(java.property("primitive")))
				type = java.box(type);
			
			return new BasicTypeArgument(new BasicTypeReference(type.getFullyQualifiedName()));
		}
		
		return null;
	}
}