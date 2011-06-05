package aspectsjava.build;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import jnome.output.CompilationUnitWriter;
import aspectsjava.model.language.AspectsJava;
import aspectsjava.output.AspectsCodeWriter;
import aspectsjava.translate.IncrementalJavaTranslator;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.language.Language;
import chameleon.exception.ChameleonProgrammerException;
import chameleon.exception.ModelException;
import chameleon.plugin.Plugin;
import chameleon.plugin.PluginImpl;
import chameleon.plugin.build.BuildProgressHelper;
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
		AspectsJava target = new AspectsJava();
		target.setPlugin(Syntax.class, new AspectsCodeWriter());
		_translator = new IncrementalJavaTranslator((AspectsJava) lang, target);
	}

  public CompilationUnitWriter writer() {
  	return _writer;
  }

	CompilationUnitWriter _writer;

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

	@Override
	public void build(List<CompilationUnit> compilationUnits, List<CompilationUnit> allProjectCompilationUnits,	BuildProgressHelper buildProgressHelper) throws ModelException, IOException {
		try {
			Collection<CompilationUnit> cus = _translator.completeRebuild(allProjectCompilationUnits, buildProgressHelper);
			
			for (CompilationUnit translated : cus)
				_writer.write(translated);
			
		} catch(Error e) {
			e.printStackTrace();
			throw e;
		} catch(RuntimeException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public int totalAmountOfWork(List<CompilationUnit> compilationUnits, List<CompilationUnit> allProjectCompilationUnits) {
		return allProjectCompilationUnits.size();
	}
}