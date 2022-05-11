package com.vendingmachine.vendingmachineapplication.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCoinTypeExceptionTest {
    @Test
    public void shouldRetrieveErrorMessage() {
        InvalidCoinTypeException invalidCoinTypeException =
                new InvalidCoinTypeException("testError");
        assertEquals("testError", invalidCoinTypeException.getMessage());

    }
}