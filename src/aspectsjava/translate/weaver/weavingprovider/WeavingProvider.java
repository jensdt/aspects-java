package aspectsjava.translate.weaver.weavingprovider;

import chameleon.core.element.Element;

/**
 * 	Instances of WeavingProvider are responsible for determining how the join point and weaving result are combined to form the final result
 * 
 * 	@author Jens
 *
 * 	@param <T>	The type of the join point
 * 	@param <U>	The result type
 */
public interface WeavingProvider<T extends Element, U> {
	
	/**
	 * 	Execute the weaving
	 * 
	 * 	@param 	joinpoint
	 * 			The matched join point
	 * 	@param 	adviceResult
	 * 			The advice result
	 */
	public void execute(T joinpoint, U adviceResult);
}
