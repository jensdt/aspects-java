package aspectsjava.translate.weaver;

import java.util.HashMap;
import java.util.Map;

import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.types.translation.AdviceTransformationProvider;
import chameleon.core.lookup.LookupException;

/**
 * 	Instances of this class are responsible for maintaining a mapping of Advices and the chain of transformations that have to be
 * 	performed on that advice.
 * 
 * 	@author Jens
 *
 */
public class TranslationExecutor {
	/**
	 * 	The mapping between advices and transformations
	 */
	private Map<Advice, AdviceTransformationProvider> transformation = new HashMap<Advice, AdviceTransformationProvider>();
	
	/**
	 * 	Add a transformation provider for a given advice
	 * 
	 * 	@param 	advice
	 * 			The advice to transform
	 * 	@param 	transformationProvider
	 * 			The transformation provider
	 */
	public void add(Advice advice, AdviceTransformationProvider transformationProvider) {
		if (!transformation.containsKey(advice))
			transformation.put(advice, transformationProvider);
		else {
			AdviceTransformationProvider currentTransformationProvider = transformation.get(advice);
			
			while (currentTransformationProvider.next() != null)
				currentTransformationProvider = currentTransformationProvider.next();
			
			currentTransformationProvider.setNext(transformationProvider);
		}
	}
	
	/**
	 * 	Return the chain of AdviceTransformationProviders for the given advice
	 * 
	 * 	@param 	advice
	 * 			The advice that the wanted chain transforms
	 * 	@return	The chain of AdviceTransformationProviders to transform the given advice
	 * 	@throws LookupException
	 */
	public AdviceTransformationProvider  getAdviceTransformationProvider(Advice advice) throws LookupException {
		return transformation.get(advice);
	}

	/**
	 * 	Clear the mapping
	 */
	public void clear() {
		transformation.clear();
	}
}
