package com.vendingmachine.vendingmachineapplication.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InSufficientAmountExceptionTest {

    @Test
    public void shouldRetrieveErrorMessage() {
        InSufficientAmountException inSufficientAmountException =
                new InSufficientAmountException("testError");
        assertEquals("testError", inSufficientAmountException.getMessage());

    }

}