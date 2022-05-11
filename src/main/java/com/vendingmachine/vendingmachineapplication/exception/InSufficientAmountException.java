package com.vendingmachine.vendingmachineapplication.exception;

public class InSufficientAmountException extends RuntimeException {

    private final String message;

    public InSufficientAmountException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
