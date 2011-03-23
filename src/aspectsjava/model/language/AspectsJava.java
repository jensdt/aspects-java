package aspectsjava.model.language;

import jnome.core.language.Java;

import org.rejuse.property.PropertyMutex;

import chameleon.aspects.advice.properties.AfterProperty;
import chameleon.aspects.advice.properties.AroundProperty;
import chameleon.aspects.advice.properties.BeforeProperty;
import chameleon.aspects.advice.properties.ReturningProperty;
import chameleon.aspects.advice.properties.ThrowingProperty;
import chameleon.core.language.Language;
import chameleon.core.property.ChameleonProperty;

public class AspectsJava extends Java {
	/**
	 * A property mutex for the scope property.
	 */
	public final PropertyMutex<ChameleonProperty> ADVICETYPE_MUTEX = new PropertyMutex<ChameleonProperty>();;
	public final PropertyMutex<ChameleonProperty> ADVICETYPE_SPEC_MUTEX = new PropertyMutex<ChameleonProperty>();;
	
	public final ChameleonProperty BEFORE;
	public final ChameleonProperty AFTER;
	public final ChameleonProperty AROUND;
	public final ChameleonProperty THROWING;
	public final ChameleonProperty RETURNING;
	
	public AspectsJava() {
		this("AspectsJava");
	}

	protected AspectsJava(String name) {
		super(name);
		
		BEFORE = new BeforeProperty(this, ADVICETYPE_MUTEX);
		AFTER = new AfterProperty(this, ADVICETYPE_MUTEX);
		AROUND = new AroundProperty(this, ADVICETYPE_MUTEX);
		
		THROWING = new ThrowingProperty(this, ADVICETYPE_SPEC_MUTEX);
		RETURNING = new ReturningProperty(this, ADVICETYPE_SPEC_MUTEX);
	}

	@Override
	protected Language cloneThis() {
		return new AspectsJava();
	}
}
