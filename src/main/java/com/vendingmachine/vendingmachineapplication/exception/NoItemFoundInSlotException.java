package com.vendingmachine.vendingmachineapplication.exception;

public class NoItemFoundInSlotException extends RuntimeException {
    private final String message;

    public NoItemFoundInSlotException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
