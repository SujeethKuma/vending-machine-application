package com.vendingmachine.vendingmachineapplication.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddItemRequest {
    @JsonProperty
    @NotBlank
    private String productSlot;
    @JsonProperty
    @NotNull
    private Integer numberOfItems;

    public String getProductSlot() {
        return productSlot;
    }

    public void setProductSlot(String productSlot) {
        this.productSlot = productSlot;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}
