package com.vendingmachine.vendingmachineapplication.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoChangeExceptionTest {
    @Test
    public void shouldRetrieveErrorMessage() {
        NoChangeException noChangeException =
                new NoChangeException("testError");
        assertEquals("testError", noChangeException.getMessage());

    }
}