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

    public CachedCallable(final Callable<V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public V call() throws Exception {
        // TODO
        return null;
    }

}
