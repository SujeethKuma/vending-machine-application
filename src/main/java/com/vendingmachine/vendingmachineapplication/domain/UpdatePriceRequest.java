package com.vendingmachine.vendingmachineapplication.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdatePriceRequest {
    @JsonProperty
    @NotBlank
    private String productSlot;
    @JsonProperty
    @NotNull
    private Double priceOfItem;

    public String getProductSlot() {
        return productSlot;
    }

    public void setProductSlot(String productSlot) {
        this.productSlot = productSlot;
    }

    public Double getPriceOfItem() {
        return priceOfItem;
    }

    public void setPriceOfItem(Double priceOfItem) {
        this.priceOfItem = priceOfItem;
    }
}
