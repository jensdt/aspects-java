package aspectsjava.model.language;

import chameleon.core.language.Language;
import jnome.core.language.Java;

public class AspectsJava extends Java {
	public AspectsJava() {
		this("AspectsJava");
	}

	protected AspectsJava(String name) {
		super(name);
	}

	@Override
	protected Language cloneThis() {
		return new AspectsJava();
	}
}
