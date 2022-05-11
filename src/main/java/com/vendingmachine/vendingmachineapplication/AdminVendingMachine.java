package com.vendingmachine.vendingmachineapplication;

import com.vendingmachine.vendingmachineapplication.domain.AddCoinRequest;
import com.vendingmachine.vendingmachineapplication.domain.AddItemRequest;
import com.vendingmachine.vendingmachineapplication.domain.UpdatePriceRequest;

public interface AdminVendingMachine {
    /**
     * adds the number of items to the given product slot
     * @param itemRequest containing the given product slot and the no.of items
     */
    void addNumberOfItemToProductSlot(AddItemRequest itemRequest);

    /**
     * Retrieve the item count for the given product slot
     * @param productSlot product slot
     * @return the number of items
     */
    int getItemCount(String productSlot);

    /**
     * Updates the price of the item in the product slot
     * @param itemRequest containing the price and the product slot
     */
    void updatePriceToProductSlot(UpdatePriceRequest itemRequest);

    /**
     * retrieves the price of the item for the given product slot
     * @param productSlot product slot
     * @return the price per item
     */
    double getPricePerItem(String productSlot);

    /**
     * Add coins to the coin type
     * @param addCoinRequest containing the coin type and coins
     */
    void addCoinsToCoinType(AddCoinRequest addCoinRequest);

    /**
     * retrieves the coins for the given coin type
     * @param coinType coin type
     * @return the coins
     */
    double getCoinsForCoinType(Double coinType);
}
