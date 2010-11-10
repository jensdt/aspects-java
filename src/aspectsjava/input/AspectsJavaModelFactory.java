package aspectsjava.input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;

import aspectsjava.model.language.AspectsJava;

import chameleon.input.ModelFactory;
import chameleon.input.ParseException;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.output.Syntax;
import chameleon.support.input.ChameleonParser;
import chameleon.support.input.ModelFactoryUsingANTLR;
import jnome.input.JavaModelFactory;
import jnome.output.JavaCodeWriter;

public class AspectsJavaModelFactory extends JavaModelFactory {
	/**
	 * BE SURE TO CALL INIT() IF YOU USE THIS CONSTRUCTOR.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public AspectsJavaModelFactory() {
		AspectsJava lang = new AspectsJava();
		lang.setConnector(Syntax.class, new JavaCodeWriter());
		setLanguage(lang, ModelFactory.class);
	}
	
	/**
	 * BE SURE TO CALL INIT() IF YOU USE THIS CONSTRUCTOR.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public AspectsJavaModelFactory(AspectsJava language) throws IOException, ParseException {
		super(language);
	}
	
	
	/**
	 * Initialize a new Java model factory with the given collection of base classes.
	 * All predefined elements of the language will be initialized. 
	 */
	public AspectsJavaModelFactory(Collection<File> base) throws IOException, ParseException {
		this(new AspectsJava(), base);
	}
	
	//FIXME: Object and String must be parsed.
	public AspectsJavaModelFactory(AspectsJava language, Collection<File> base) throws IOException, ParseException {
		super(language, base);
	}

    // TODO: documentation (Jens)
	@Override
    public Lexer lexer(ANTLRInputStream input) {
    	return new AspectLexer(input);
    }
    
    // TODO: documentation (Jens)
	@Override
    public ChameleonParser parser(CommonTokenStream tokens) {
    	return new AspectParser(tokens);
    }
	
  @Override
	public ModelFactoryUsingANTLR clone() {
		try {
			AspectsJavaModelFactory result = new AspectsJavaModelFactory();
			return result;
		} catch (Exception e) {
			throw new RuntimeException("Exception while cloning a JavaModelFactory", e);
		}
	}
}
