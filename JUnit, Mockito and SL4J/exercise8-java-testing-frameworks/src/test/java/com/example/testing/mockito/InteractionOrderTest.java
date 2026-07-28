package com.example.testing.mockito;

import com.example.testing.external.ExternalApi;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

/** Doc 6 - Exercise 6: Verifying Interaction Order */
public class InteractionOrderTest {

    @Test
    public void testInteractionOrder() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Data");

        // Call methods in a specific order
        mockApi.getData();
        mockApi.performAction();

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).performAction();
    }
}
