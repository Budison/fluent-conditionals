package fluentconditionals;

import org.mockito.Mockito;
import org.testng.annotations.Test;

/**
 * @author Kevin Nowak
 */
@Test
public class FluentConditionalsTest {
    public void testWhen() {
        Runnable mockedRunnable1 = Mockito.mock(Runnable.class);
        Runnable mockedRunnable2 = Mockito.mock(Runnable.class);
        FluentConditionals.when(true).then(mockedRunnable1).orElse(mockedRunnable2);
        Mockito.verify(mockedRunnable1).run();
        Mockito.verify(mockedRunnable2, Mockito.never()).run();
    }

    public void TestWhenWithSupplier() {
        Runnable mockedRunnable1 = Mockito.mock(Runnable.class);
        Runnable mockedRunnable2 = Mockito.mock(Runnable.class);
        FluentConditionals.when(() -> true).then(mockedRunnable1).orElse(mockedRunnable2);
        Mockito.verify(mockedRunnable1).run();
        Mockito.verify(mockedRunnable2, Mockito.never()).run();
    }

    public void testOrElse() {
        Runnable mockedRunnable1 = Mockito.mock(Runnable.class);
        Runnable mockedRunnable2 = Mockito.mock(Runnable.class);
        FluentConditionals.when(false).then(mockedRunnable1).orElse(mockedRunnable2);
        Mockito.verify(mockedRunnable1, Mockito.never()).run();
        Mockito.verify(mockedRunnable2).run();
    }

    public void testDoNothing() {
        Runnable mockedRunnable = Mockito.mock(Runnable.class);
        FluentConditionals.when(TestHelper.somethingIsTrue()).then(FluentConditionals.doNothing).orElse(mockedRunnable);
        Mockito.verify(mockedRunnable, Mockito.never()).run();
    }
}
