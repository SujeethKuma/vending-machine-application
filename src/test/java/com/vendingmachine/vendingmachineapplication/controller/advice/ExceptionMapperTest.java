package com.vendingmachine.vendingmachineapplication.controller.advice;

import com.vendingmachine.vendingmachineapplication.exception.InSufficientAmountException;
import com.vendingmachine.vendingmachineapplication.exception.InvalidCoinTypeException;
import com.vendingmachine.vendingmachineapplication.exception.NoChangeException;
import com.vendingmachine.vendingmachineapplication.exception.NoItemFoundInSlotException;
import com.vendingmachine.vendingmachineapplication.exception.ProductSlotNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionMapperTest {

    ExceptionMapper exceptionMapper = new ExceptionMapper();

    @Test
    public void shouldHandleProductSlotNotFoundException() {
        ProductSlotNotFoundException productSlotNotFoundException = new ProductSlotNotFoundException("testError");

        assertEquals(HttpStatus.NOT_FOUND, exceptionMapper
                .handleProductSlotNotFoundException(productSlotNotFoundException).getStatusCode());
    }

    @Test
    public void shouldHandleInsufficientAmountException() {
        InSufficientAmountException inSufficientAmountException =
                new InSufficientAmountException("testError");

        assertEquals(HttpStatus.BAD_REQUEST, exceptionMapper
                .handleInsufficientAmountException(inSufficientAmountException).getStatusCode());
    }

    @Test
    public void shouldHandleNoChangeException() {
        NoChangeException noChangeException = new NoChangeException("testError");

        assertEquals(HttpStatus.NOT_FOUND, exceptionMapper
                .handleNoChangeException(noChangeException).getStatusCode());
    }

    @Test
    public void shouldHandleNoItemFoundInSlotException() {
        NoItemFoundInSlotException noItemFoundInSlotException = new NoItemFoundInSlotException("testError");

        assertEquals(HttpStatus.NOT_FOUND, exceptionMapper
                .handleNoItemFoundInSlotException(noItemFoundInSlotException).getStatusCode());
    }

    @Test
    public void shouldHandleInvalidCoinTypeException() {
        InvalidCoinTypeException invalidCoinTypeException = new InvalidCoinTypeException("testError");

        assertEquals(HttpStatus.NOT_FOUND, exceptionMapper
                .handleInvalidCoinTypeException(invalidCoinTypeException).getStatusCode());
    }

    @Test
    public void shouldHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException invalidCoinTypeException = new HttpMessageNotReadableException("testError");

        assertEquals(HttpStatus.BAD_REQUEST, exceptionMapper
                .handleMessageNotReadableException(invalidCoinTypeException).getStatusCode());
    }
}