package com.vendingmachine.vendingmachineapplication.exception;

public class ProductSlotNotFoundException extends RuntimeException {

    private final String message;

    public ProductSlotNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
