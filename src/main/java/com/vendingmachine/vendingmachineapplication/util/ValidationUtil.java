package com.vendingmachine.vendingmachineapplication.util;

import com.vendingmachine.vendingmachineapplication.exception.InvalidCoinTypeException;
import com.vendingmachine.vendingmachineapplication.exception.ProductSlotNotFoundException;
import com.vendingmachine.vendingmachineapplication.model.CoinType;
import com.vendingmachine.vendingmachineapplication.model.ProductSlot;

import java.util.Arrays;

public class ValidationUtil {

    public static void validateProductSlot(String productSlot) {
        boolean isProductSlotPresent = Arrays.stream(ProductSlot.values())
                .anyMatch(s -> s.getSlotName().equals(productSlot));
        if (!isProductSlotPresent) {
            throw new ProductSlotNotFoundException(String.format("The given %s product slot not found",
                    productSlot));
        }
    }

    public static void validateCoinType(Double coinType) {
        boolean isProductSlotPresent = Arrays.stream(CoinType.values())
                .anyMatch(s -> s.getValue() == coinType);
        if (!isProductSlotPresent) {
            throw new InvalidCoinTypeException(String.format("The given %s coin type is not found",
                    coinType));
        }
    }
}
