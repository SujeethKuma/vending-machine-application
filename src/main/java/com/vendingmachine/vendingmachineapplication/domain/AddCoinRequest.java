package com.vendingmachine.vendingmachineapplication.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class AddCoinRequest {

    @JsonProperty
    private double coinType;
    @JsonProperty
    @NotNull
    private Integer numberOfCoins;

    public double getCoinType() {
        return coinType;
    }

    public void setCoinType(Double coinType) {
        this.coinType = coinType;
    }

    public Integer getNumberOfCoins() {
        return numberOfCoins;
    }

    public void setNumberOfCoins(Integer numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
    }
}
