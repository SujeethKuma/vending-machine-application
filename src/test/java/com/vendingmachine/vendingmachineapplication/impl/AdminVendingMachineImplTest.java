package com.vendingmachine.vendingmachineapplication.impl;

import com.vendingmachine.vendingmachineapplication.domain.AddCoinRequest;
import com.vendingmachine.vendingmachineapplication.domain.AddItemRequest;
import com.vendingmachine.vendingmachineapplication.domain.UpdatePriceRequest;
import com.vendingmachine.vendingmachineapplication.util.VendingMachineUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminVendingMachineImplTest {

    AdminVendingMachineImpl adminVendingMachine = new AdminVendingMachineImpl();

    @Test
    public void shouldAddItemToProductSlot() {
        AddItemRequest addItemRequest = new AddItemRequest();

        addItemRequest.setProductSlot("A9");
        addItemRequest.setNumberOfItems(2);

        adminVendingMachine.addNumberOfItemToProductSlot(addItemRequest);

        assertEquals(7, VendingMachineUtil.getProductItem("A9"));
    }

    @Test
    public void shouldGetCorrectItemCount() {
        assertEquals(5, adminVendingMachine.getItemCount("A1"));
    }

    @Test
    public void shouldSuccessfullyUpdatePriceToProductSlot() {
        UpdatePriceRequest updatePriceRequest = new UpdatePriceRequest();

        updatePriceRequest.setProductSlot("A1");
        updatePriceRequest.setPriceOfItem(2.0);

        adminVendingMachine.updatePriceToProductSlot(updatePriceRequest);
        assertEquals(2.0, VendingMachineUtil.getPricePerItem("A1"));
    }

    @Test
    public void shouldSuccessfullyRetrievePricePerItem() {
        assertEquals(0.2, VendingMachineUtil.getPricePerItem("A0"));
    }

    @Test
    public void shouldSuccessfullyAddCoinsToCoinType() {
        AddCoinRequest addCoinRequest = new AddCoinRequest();
        addCoinRequest.setCoinType(0.5);
        addCoinRequest.setNumberOfCoins(3);

        adminVendingMachine.addCoinsToCoinType(addCoinRequest);
        assertEquals(4, VendingMachineUtil.getNumberOfCoins(0.5));
    }

    @Test
    public void shouldSuccessfullyRetrieveCoinsForCoinType() {
        assertEquals(1, VendingMachineUtil.getNumberOfCoins(0.1));
    }
}