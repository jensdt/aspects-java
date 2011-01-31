package aspectsjava.input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import jnome.input.JavaModelFactory;
import jnome.output.JavaCodeWriter;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;

import antlr.Parser;
import aspectsjava.model.language.AspectsJava;
import chameleon.input.ModelFactory;
import chameleon.input.ParseException;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.plugin.output.Syntax;
import chameleon.support.input.ChameleonParser;
import chameleon.support.input.ModelFactoryUsingANTLR;

public class AspectsJavaModelFactory extends JavaModelFactory {
	/**
	 * BE SURE TO CALL INIT() IF YOU USE THIS CONSTRUCTOR.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public AspectsJavaModelFactory() {
		AspectsJava lang = new AspectsJava();
		lang.setPlugin(Syntax.class, new JavaCodeWriter());
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

	@Override
	  public ChameleonParser getParser(InputStream inputStream, String fileName) throws IOException {
	    ANTLRInputStream input = new ANTLRInputStream(inputStream);
	    AspectLexer lexer = new AspectLexer(input);
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	    AspectParser parser = new AspectParser(tokens);
	    parser.setLanguage((ObjectOrientedLanguage) language());
	    return parser;
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
