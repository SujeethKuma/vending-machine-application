package com.vendingmachine.vendingmachineapplication.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class ProductRequest {
    @JsonProperty
    @NotBlank
    String productSlot;
    @JsonProperty
    @NotNull
    List<Double> coins = new ArrayList<>();

    public String getProductSlot() {
        return productSlot;
    }

    public void setProductSlot(String productSlot) {
        this.productSlot = productSlot;
    }

    public List<Double> getCoins() {
        return coins;
    }

    public void setCoins(List<Double> coins) {
        this.coins = coins;
    }
}
