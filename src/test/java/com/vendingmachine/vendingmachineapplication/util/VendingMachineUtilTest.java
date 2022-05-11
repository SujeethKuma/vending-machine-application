package com.vendingmachine.vendingmachineapplication.util;

import com.vendingmachine.vendingmachineapplication.domain.AddItemRequest;
import com.vendingmachine.vendingmachineapplication.domain.UpdatePriceRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineUtilTest {

    @Test
    public void shouldRetrieveProductDetails() {
        AddItemRequest addItemRequest = new AddItemRequest();

        addItemRequest.setProductSlot("A2");
        addItemRequest.setNumberOfItems(1);
        VendingMachineUtil.setProductItem(addItemRequest);

        assertEquals(6, VendingMachineUtil.getProductItem("A2"));
    }

    @Test
    public void shouldRetrievePriceDetails() {
        UpdatePriceRequest updatePriceRequest = new UpdatePriceRequest();
        updatePriceRequest.setProductSlot("A3");
        updatePriceRequest.setPriceOfItem(0.5);

        VendingMachineUtil.setPrice(updatePriceRequest);

        assertEquals(0.5, VendingMachineUtil.getPricePerItem("A3"));
    }

}