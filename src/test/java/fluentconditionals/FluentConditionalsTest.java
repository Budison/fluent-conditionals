package fluentconditionals;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

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

    @Test(expectedExceptions = RuntimeException.class)
    public void testOrElseThrowE() {
        FluentConditionals.when(true).orElseThrowE(TestHelper.createException());
        FluentConditionals.when(false).orElseThrowE(TestHelper.createException());

    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testOrElseThrow() {
        FluentConditionals.when(true).orElseThrow(RuntimeException::new);
        FluentConditionals.when(false).orElseThrow(RuntimeException::new);
    }

    public void testThenReturn() {
        // Given
        int expected = TestHelper.getHighNumber();
        int sample = FluentConditionals.when(true).thenReturn(TestHelper::getHighNumber).orElse(0);
        // Then
        assertEquals(sample, expected);
    }
}
