package com.vendingmachine.vendingmachineapplication.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;


public class ProductRequest {
    @JsonProperty
    @NotBlank
    String productSlot;
    @JsonProperty
    @NotBlank
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
