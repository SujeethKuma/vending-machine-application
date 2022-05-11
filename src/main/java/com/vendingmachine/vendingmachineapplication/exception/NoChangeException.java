package com.vendingmachine.vendingmachineapplication.exception;

public class NoChangeException extends RuntimeException {

    private final String message;

    public NoChangeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
