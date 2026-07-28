package com.example.testing.mockito;

import com.example.testing.external.ExternalApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

/** Doc 6 - Exercise 4: Handling Void Methods */
public class VoidMethodTest {

    @Test
    public void testVoidMethod() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub the void method to do nothing (default) - explicit for clarity
        doNothing().when(mockApi).performAction();

        mockApi.performAction();

        verify(mockApi, times(1)).performAction();
    }
}
