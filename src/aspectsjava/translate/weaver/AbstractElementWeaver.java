package aspectsjava.translate.weaver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import chameleon.aspects.advice.Advice;
import chameleon.aspects.pointcut.expression.MatchResult;
import chameleon.aspects.pointcut.expression.generic.PointcutExpression;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersSignature;
import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.Method;
import chameleon.oo.type.BasicTypeReference;
import chameleon.oo.type.Type;

public abstract class AbstractElementWeaver<T extends Element> {
	
	public boolean supports(MatchResult<? extends PointcutExpression, T> result) {
		Class c = result.getJoinpoint().getClass();
		
		for (Class supported : supportedTypes()) {
			if (supported.isAssignableFrom(c))
				return true;
		}
		
		return false;
	}
	
	public abstract List<Class> supportedTypes();
	
	public abstract void weave(CompilationUnit source, Advice advice, List<MatchResult<? extends PointcutExpression, T>> joinpoints) throws LookupException;
	
	private Map<DeclarationWithParametersHeader, String> methodNames = new HashMap<DeclarationWithParametersHeader, String>();
	
	protected String getName(Method method) {
		String name = getExisting(method);
		
		if (name == null) {
			do {
				name = getRandomName();
			} while (methodNames.values().contains(name));
			
			methodNames.put(method.header(), name);
		}
		
		return name;
	}
	
	private String getExisting(Method method) {
		DeclarationWithParametersHeader methodToGet = method.header();
		for (DeclarationWithParametersHeader m : methodNames.keySet()) {
			try {
				System.out.println(method.header().name());
				System.out.println(m.name());
				if (method.header().signature().sameAs(m.signature())) {
					methodToGet = m;
					break;
				}
			} catch (LookupException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e2) {
				e2.printStackTrace();
			}
		}
				
		return methodNames.get(methodToGet);
	}
	
	private Random r = new Random();
	private String alphabet = "abcdefghijklmopqrstuvwxyz";
	private String getRandomName() {
		StringBuilder name = new StringBuilder();
		for (int i = 0; i < 8; i++)
			name.append(alphabet.charAt(r.nextInt(alphabet.length())));
		
		return name.toString();
	}
}
