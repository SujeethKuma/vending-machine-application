package com.vendingmachine.vendingmachineapplication.model;

public enum CoinType {

    TEN_PENCE(0.1),
    TWENTY_PENCE(0.2),
    FIFTY_PENCE(0.5),
    POUND(1.0);
    private double value;
    CoinType(double value)
    {
        this.value = value;
    }


    public double getValue()
    {
        return value;
    }

    public static CoinType valueOfCoinType(Double coinType) {
        for (CoinType coinTypes : values()) {
            if (coinTypes.value == coinType) {
                return coinTypes;
            }
        }
        return null;
    }

}
