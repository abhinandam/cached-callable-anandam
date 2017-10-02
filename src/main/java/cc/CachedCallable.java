package cc;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A wrapper around a Callable object which ensures that the Callable is only called once.
 *
 * @param <V> the return type of the delegate
 */
public class CachedCallable<V> implements Callable<V> {

    private final Callable<V> delegate;
    private V cachedVariable;
    private boolean isSet;
    private Exception exception;

    public CachedCallable(final Callable<V> delegate) {
        this.delegate = delegate;
    }
    
    private V getInstance() throws Exception{
    	if(!isSet) {
	    	 try {
	 			cachedVariable = delegate.call();
	 			isSet = true;
	 			return cachedVariable;
	 		} catch (Exception e) {
	 			exception = e;
	 			isSet = true;
	 			throw exception;
	 		}
    	} 
    	if(exception != null) {
    		throw exception;
    	}
    	return this.cachedVariable;
    }

    @Override
    public V call() throws Exception {
        // TODO
        return getInstance();
    }

}

