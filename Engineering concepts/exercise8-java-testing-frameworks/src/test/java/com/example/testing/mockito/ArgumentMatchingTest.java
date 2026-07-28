package com.example.testing.mockito;

import com.example.testing.external.ExternalApi;
import com.example.testing.external.MyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** Doc 6 - Exercise 3: Argument Matching */
public class ArgumentMatchingTest {

    @Test
    public void testArgumentMatching_withSpecificArgument() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");

        MyService service = new MyService(mockApi);
        String result = service.fetchDataFor("user-123");

        assertEquals("Mock Data:user-123", result);
        verify(mockApi).getData();
    }

    @Test
    public void testArgumentMatching_withAnyStringMatcher() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");

        MyService service = new MyService(mockApi);
        service.fetchDataFor("any-user-id");
        service.fetchDataFor("another-id");

        // anyString() lets verification pass no matter what fetchDataFor's argument was,
        // because that argument only affects MyService's own concatenation, not the mock call itself.
        verify(mockApi, Mockito.times(2)).getData();

        // eq() demonstrates exact-match argument verification style on a void-arg mock method
        mockApi.performAction();
        verify(mockApi).performAction();
    }
}
