package com.vendingmachine.vendingmachineapplication;

import com.vendingmachine.vendingmachineapplication.domain.ProductRequest;

import java.util.List;

public interface CustomerVendingMachine {

    /**
     * Dispense the remaining change after deducting the product amount
     * @param productRequest containing the product and the product amount
     * @return the change
     */
    List<Double> dispenseChange(ProductRequest productRequest);

    /**
     * Retrieves the price per item in the product slot
     * @param productSlot containing the items
     * @return the price of the item
     */
    double fetchPricePerItem(String productSlot);
}
