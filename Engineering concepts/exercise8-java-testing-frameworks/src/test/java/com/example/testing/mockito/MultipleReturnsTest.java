package com.example.testing.mockito;

import com.example.testing.external.ExternalApi;
import com.example.testing.external.MyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/** Doc 6 - Exercise 5: Mocking and Stubbing with Multiple Returns */
public class MultipleReturnsTest {

    @Test
    public void testMultipleReturnValues() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData())
                .thenReturn("First Data")
                .thenReturn("Second Data")
                .thenReturn("Third Data");

        MyService service = new MyService(mockApi);

        assertEquals("First Data", service.fetchData());
        assertEquals("Second Data", service.fetchData());
        assertEquals("Third Data", service.fetchData());
        // Mockito keeps returning the last stubbed value after the sequence is exhausted
        assertEquals("Third Data", service.fetchData());
    }
}
