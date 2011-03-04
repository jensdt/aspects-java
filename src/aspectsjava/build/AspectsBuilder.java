package aspectsjava.build;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jnome.core.language.Java;
import jnome.output.CompilationUnitWriter;
import jnome.output.JavaCodeWriter;
import aspectsjava.model.language.AspectsJava;
import aspectsjava.translate.IncrementalJavaTranslator;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.language.Language;
import chameleon.exception.ChameleonProgrammerException;
import chameleon.exception.ModelException;
import chameleon.plugin.Plugin;
import chameleon.plugin.PluginImpl;
import chameleon.plugin.build.Builder;
import chameleon.plugin.output.Syntax;

public class AspectsBuilder extends PluginImpl implements Builder {
	public AspectsBuilder(AspectsJava source, File outputDir) {
		this(outputDir);
		setLanguage(source, Builder.class);
	}
	
	public AspectsBuilder(File outputDir) {
		_writer = new CompilationUnitWriter(outputDir, ".java");
	}	
	
	@Override
	public <T extends Plugin> void setLanguage(Language lang, Class<T> pluginInterface) {
		if(! (lang instanceof AspectsJava)) {
			throw new ChameleonProgrammerException();
		}
		
		super.setLanguage(lang, pluginInterface);
		Java target = new Java();
		target.setPlugin(Syntax.class, new JavaCodeWriter());
		_translator = new IncrementalJavaTranslator((AspectsJava) lang, target);
	}

  public CompilationUnitWriter writer() {
  	return _writer;
  }

	CompilationUnitWriter _writer;

	/*
	public void build(Collection<CompilationUnit> compilationUnits,
			List<CompilationUnit> allProjectCompilationUnits) throws ModelException, IOException {
		
		List<CompilationUnit> allProjectCompilationUnitsWithoutAspects = new ArrayList<CompilationUnit>();
		
		boolean hasAspect = false;
		
		for(CompilationUnit cu: compilationUnits) {
			if (!cu.descendants(Aspect.class).isEmpty()) {
				hasAspect = true;
				build(cu, allProjectCompilationUnits);
				allProjectCompilationUnitsWithoutAspects.remove(cu);
			}
		}
		
		List<CompilationUnit> toBuild;
		// Need a complete rebuild or not?
		if (hasAspect)
			toBuild = allProjectCompilationUnitsWithoutAspects;
		else
			toBuild = allProjectCompilationUnits;
		
		for(CompilationUnit cu: toBuild) {
			build(cu, allProjectCompilationUnits);
		}
	}
	*/
	
	public void build(CompilationUnit compilationUnit, List<CompilationUnit> allProjectCompilationUnits) throws ModelException, IOException {
		try {
			String fileName = _writer.fileName(compilationUnit);
			System.out.println("Building "+fileName);
			List<CompilationUnit> cus = _translator.build(compilationUnit, allProjectCompilationUnits);
			
			for (CompilationUnit translated : cus)
				_writer.write(translated);
			
		} catch(Error e) {
			e.printStackTrace();
			throw e;
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
	}

	public Language targetLanguage() {
		return _translator.targetLanguage();
	}

	public Language sourceLanguage() {
		return _translator.sourceLanguage();
	}

	private IncrementalJavaTranslator _translator;

	@Override
	public Plugin clone() {
		return new AspectsBuilder(writer().outputDir());
	}

}
