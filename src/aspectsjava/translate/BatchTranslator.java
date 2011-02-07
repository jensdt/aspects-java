package aspectsjava.translate;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import aspectsjava.build.AspectsBuilder;
import aspectsjava.input.AspectsJavaModelFactory;
import aspectsjava.model.language.AspectsJava;
import chameleon.core.Config;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.namespace.Namespace;
import chameleon.exception.ChameleonProgrammerException;
import chameleon.exception.ModelException;
import chameleon.input.ParseException;
import chameleon.oo.type.AspectOrType;
import chameleon.oo.type.Type;
import chameleon.plugin.Plugin;
import chameleon.test.provider.BasicDescendantProvider;
import chameleon.test.provider.ElementProvider;

public class BatchTranslator {

	public BatchTranslator(AspectsJava language, ElementProvider<Namespace> namespaceProvider, File outputDir) throws ParseException, IOException {
		_sourceLanguage = language;
		_typeProvider = new BasicDescendantProvider<AspectOrType>(namespaceProvider, AspectOrType.class);
		_builder = new AspectsBuilder(_sourceLanguage, outputDir);
	}

	private AspectsBuilder _builder;
	
	public AspectsBuilder builder() {
		return _builder;
	}
	
	public AspectsJava sourceLanguage() {
		return _sourceLanguage;
	}
	
	private AspectsJava _sourceLanguage;
	
	public ElementProvider<AspectOrType> typeProvider() {
		return _typeProvider;
	}

	private ElementProvider<AspectOrType> _typeProvider;


	public void translate() throws ParseException, IOException, ChameleonProgrammerException, ModelException {
		
		for(AspectOrType type: typeProvider().elements(sourceLanguage())) {
			_builder.build((CompilationUnit) type.nearestAncestor(CompilationUnit.class), null);
		}
	}
	
  /**
   * args[0] = path for the directory to write output
   * args[1] = path to read input files
   * ...1 or more input paths possible...
   * args[i] = fqn of package to read, let this start with "@" to read the package recursively
   *...1 or more packageFqns possible...
   * args[n] = fqn of package to read, let this start with "#" to NOT read the package recursively.
   *...1 or more packageFqns possible...
   *
   * Example 
   * java Copy outputDir apiInputDir customInputDir1 customInputDir2 @myPackage.subPackage 
   */
  public static void main(String[] args) throws Exception {
    if(args.length < 2) {
      System.out.println("Usage: java .... JavaTranslator outputDir apiDir inputDir* @recursivePackageFQN* #packageFQN* $typeFQN*");
    }
    BasicConfigurator.configure();
    Logger.getRootLogger().setLevel(Level.FATAL);
    Config.setCaching(true);
    ProviderProvider provider = new ProviderProvider(new AspectsJavaModelFactory(),".aspect",true,true);
    provider.processArguments(args);
    File outputDir = provider.outputDir();
    long start = System.currentTimeMillis();
    new BatchTranslator((AspectsJava) provider.language(), provider.namespaceProvider(),outputDir).translate();
    long stop = System.currentTimeMillis();
    System.out.println("Translation took "+(stop - start) + " milliseconds.");
  }

}
