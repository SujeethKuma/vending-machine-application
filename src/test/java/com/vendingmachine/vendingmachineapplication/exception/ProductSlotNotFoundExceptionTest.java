package com.vendingmachine.vendingmachineapplication.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductSlotNotFoundExceptionTest {
    @Test
    public void shouldRetrieveErrorMessage() {
        ProductSlotNotFoundException productSlotNotFoundException =
                new ProductSlotNotFoundException("testError");
        assertEquals("testError", productSlotNotFoundException.getMessage());

    }
}