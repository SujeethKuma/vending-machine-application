package com.vendingmachine.vendingmachineapplication.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoItemFoundInSlotExceptionTest {

    @Test
    public void shouldRetrieveErrorMessage() {
        NoItemFoundInSlotException noItemFoundInSlotException =
                new NoItemFoundInSlotException("testError");
        assertEquals("testError", noItemFoundInSlotException.getMessage());

    }
}