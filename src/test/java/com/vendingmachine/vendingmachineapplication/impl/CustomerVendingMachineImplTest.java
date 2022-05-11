package com.vendingmachine.vendingmachineapplication.impl;

import com.vendingmachine.vendingmachineapplication.domain.ProductRequest;
import com.vendingmachine.vendingmachineapplication.domain.UpdatePriceRequest;
import com.vendingmachine.vendingmachineapplication.exception.InSufficientAmountException;
import com.vendingmachine.vendingmachineapplication.util.VendingMachineUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerVendingMachineImplTest {
    CustomerVendingMachineImpl customerVendingMachine;
    @BeforeEach
    void setUp() {
        customerVendingMachine = new CustomerVendingMachineImpl();
    }
    @Test
    public void shouldReturnCorrectChange() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductSlot("A8");
        List<Double> coins = new ArrayList<>();
        coins.add(1.0);
        coins.add(1.0);
        coins.add(1.0);
        coins.add(0.5);
        productRequest.setCoins(coins);

        List<Double> expectedChange = new ArrayList<>();
        expectedChange.add(1.0);
        expectedChange.add(1.0);
        expectedChange.add(1.0);
        expectedChange.add(0.20);
        expectedChange.add(0.10);
        assertEquals(expectedChange, customerVendingMachine.dispenseChange(productRequest));
    }

    @Test
    public void shouldThrowInsufficientExceptionWhenPaidAmountIsLess() {
        assertThrows(InSufficientAmountException.class, () -> {
            ProductRequest productRequest = new ProductRequest();
            productRequest.setProductSlot("A0");
            List<Double> coins = new ArrayList<>();
            coins.add(0.1);
            productRequest.setCoins(coins);

            List<Double> expectedChange = new ArrayList<>();
            expectedChange.add(0.1);
            assertEquals(expectedChange, customerVendingMachine.dispenseChange(productRequest));
        });

    }

    @Test
    public void shouldFetchCorrectPricePerItemWhenPriceUpdated() {
        UpdatePriceRequest updatePriceRequest = new UpdatePriceRequest();
        updatePriceRequest.setPriceOfItem(2.0);
        updatePriceRequest.setProductSlot("A0");
        VendingMachineUtil.setPrice(updatePriceRequest);
        assertEquals(2.0, customerVendingMachine.fetchPricePerItem("A0"));
    }

}