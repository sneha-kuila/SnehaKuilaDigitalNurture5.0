package com.example.testing.mockito;

import com.example.testing.external.ExternalApi;
import com.example.testing.external.MyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

/** Doc 6 - Exercise 2: Verifying Interactions */
public class VerifyInteractionTest {

    @Test
    public void testVerifyInteraction() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        verify(mockApi).getData();
    }
}
