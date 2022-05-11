package com.vendingmachine.vendingmachineapplication.impl;

import com.vendingmachine.vendingmachineapplication.AdminVendingMachine;
import com.vendingmachine.vendingmachineapplication.domain.AddCoinRequest;
import com.vendingmachine.vendingmachineapplication.domain.AddItemRequest;
import com.vendingmachine.vendingmachineapplication.domain.UpdatePriceRequest;
import com.vendingmachine.vendingmachineapplication.util.VendingMachineUtil;
import org.springframework.stereotype.Service;

@Service
public class AdminVendingMachineImpl implements AdminVendingMachine {

    /**
     * adds the number of items to the given product slot
     * @param itemRequest containing the given product slot and the no.of items
     */
    @Override
    public void addNumberOfItemToProductSlot(AddItemRequest itemRequest) {
        VendingMachineUtil.setProductItem(itemRequest);
    }

    /**
     * Retrieve the item count for the given product slot
     * @param productSlot product slot
     * @return the number of items
     */
    @Override
    public int getItemCount(String productSlot) {
        return VendingMachineUtil.getProductItem(productSlot);
    }

    /**
     * Updates the price of the item in the product slot
     * @param itemRequest containing the price and the product slot
     */
    @Override
    public void updatePriceToProductSlot(UpdatePriceRequest itemRequest) {
        VendingMachineUtil.setPrice(itemRequest);
    }

    /**
     * retrieves the price of the item for the given product slot
     * @param productSlot product slot
     * @return the price per item
     */
    @Override
    public double getPricePerItem(String productSlot) {
        return VendingMachineUtil.getPricePerItem(productSlot);
    }

    /**
     * Add coins to the coin type
     * @param addCoinRequest containing the coin type and coins
     */
    @Override
    public void addCoinsToCoinType(AddCoinRequest addCoinRequest) {
        VendingMachineUtil.setCoin(addCoinRequest);
    }

    /**
     * retrieves the coins for the given coin type
     * @param coinType coin type
     * @return the coins
     */
    @Override
    public double getCoinsForCoinType(Double coinType) {
        return VendingMachineUtil.getNumberOfCoins(coinType);
    }
}
