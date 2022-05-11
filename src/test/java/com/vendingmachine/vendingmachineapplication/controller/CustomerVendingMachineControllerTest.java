package com.vendingmachine.vendingmachineapplication.controller;

import com.vendingmachine.vendingmachineapplication.domain.AddItemRequest;
import com.vendingmachine.vendingmachineapplication.domain.ProductRequest;
import com.vendingmachine.vendingmachineapplication.exception.NoItemFoundInSlotException;
import com.vendingmachine.vendingmachineapplication.exception.ProductSlotNotFoundException;
import com.vendingmachine.vendingmachineapplication.impl.CustomerVendingMachineImpl;
import com.vendingmachine.vendingmachineapplication.util.VendingMachineUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


class CustomerVendingMachineControllerTest {
    @Mock
    CustomerVendingMachineImpl customerVendingMachine;
    @InjectMocks
    CustomerVendingMachineController customerVendingMachineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void shouldReturn200WhenFetchingPrice() {
        when(customerVendingMachine.fetchPricePerItem(anyString()))
                .thenReturn(0.2);

        assertEquals(0.2, customerVendingMachineController
                .fetchPrice("A7").getBody());

        assertEquals(HttpStatus.OK, customerVendingMachineController
                .fetchPrice("A7").getStatusCode());
    }

    @Test
    public void shouldReturn200OnSuccessfulPurchaseOfProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductSlot("A0");
        List<Double> coins = new ArrayList<>();
        coins.add(1.0);
        productRequest.setCoins(coins);

        List<Double> expectedCoins = new ArrayList<>();
        expectedCoins.add(0.5);

        when(customerVendingMachine.dispenseChange(productRequest))
                .thenReturn((expectedCoins));

        assertEquals(expectedCoins, customerVendingMachineController
                .purchaseProduct(productRequest).getBody());

        assertEquals(HttpStatus.OK, customerVendingMachineController
                .purchaseProduct(productRequest).getStatusCode());
    }

    @Test
    public void shouldThrowExceptionWhenProductSlotNotFound() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductSlot("testSlot");
        List<Double> coins = new ArrayList<>();
        coins.add(1.0);
        productRequest.setCoins(coins);

        assertThrows(ProductSlotNotFoundException.class, () -> {
            customerVendingMachineController
                .purchaseProduct(productRequest);
        });
    }

    @Test
    public void shouldThrowExceptionWhenItemNotFoundInSlot() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductSlot("A0");
        List<Double> coins = new ArrayList<>();
        coins.add(9.0);
        productRequest.setCoins(coins);

        AddItemRequest addItemRequest = new AddItemRequest();
        addItemRequest.setNumberOfItems(-5);
        addItemRequest.setProductSlot("A0");
        VendingMachineUtil.setProductItem(addItemRequest);
        assertThrows(NoItemFoundInSlotException.class, () -> {
            customerVendingMachineController
                    .purchaseProduct(productRequest);
        });
    }

}