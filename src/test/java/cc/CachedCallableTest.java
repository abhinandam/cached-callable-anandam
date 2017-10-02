package cc;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CachedCallableTest {

    private CachedCallable<String> cachedCallable;
    private Callable<String> delegate;

    @Before
    public void setUp() {
        delegate = (Callable<String>) mock(Callable.class);
        cachedCallable = new CachedCallable<>(delegate);
    }

    @Test
    public void testValue() throws Exception {
        String returnValue = "yo!";
        when(delegate.call()).thenReturn(returnValue);
        assertEquals(returnValue, cachedCallable.call());
        assertEquals(returnValue, cachedCallable.call());
        verify(delegate, times(1)).call();
    }
    
    @Test
    public void testNullValue() throws Exception {
        String returnValue = null;
        when(delegate.call()).thenReturn(returnValue);
        assertEquals(returnValue, cachedCallable.call());
        assertEquals(returnValue, cachedCallable.call());
        verify(delegate, times(1)).call();
    }
    
    @Test
    public void testException() throws Exception {
        when(delegate.call()).thenThrow(new Exception());
        int exceptionsCaught = 0;
        for (int i = 0; i < 2; i++) {
            try {
                cachedCallable.call();
            } catch (Exception e) {
                exceptionsCaught++;
            }
        }
        assertEquals(2, exceptionsCaught);
        verify(delegate, times(1)).call();
    }
}

