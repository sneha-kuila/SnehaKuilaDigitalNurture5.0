package com.example.testing.mockito;

import com.example.testing.external.ExternalApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/** Doc 6 - Exercise 7: Handling Void Methods with Exceptions */
public class VoidMethodExceptionTest {

    @Test
    public void testVoidMethodThrowsException() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doThrow(new RuntimeException("Action failed")).when(mockApi).performAction();

        assertThrows(RuntimeException.class, mockApi::performAction);

        verify(mockApi).performAction();
    }
}
