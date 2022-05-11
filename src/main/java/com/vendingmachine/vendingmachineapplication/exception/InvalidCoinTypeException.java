package com.vendingmachine.vendingmachineapplication.exception;

public class InvalidCoinTypeException extends RuntimeException {
    private final String message;

    public InvalidCoinTypeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
