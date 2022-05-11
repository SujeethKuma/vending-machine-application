package com.vendingmachine.vendingmachineapplication.controller.advice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    @JsonProperty
    private int value;
    @JsonProperty
    private String reasonPhrase;
    @JsonProperty
    private String errorMsg;

    public ErrorResponse(int value, String reasonPhrase, String errorMsg) {

        this.value = value;
        this.reasonPhrase = reasonPhrase;
        this.errorMsg = errorMsg;
    }
}
