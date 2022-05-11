package com.vendingmachine.vendingmachineapplication.controller;

import com.vendingmachine.vendingmachineapplication.domain.AddCoinRequest;
import com.vendingmachine.vendingmachineapplication.domain.AddItemRequest;
import com.vendingmachine.vendingmachineapplication.domain.UpdatePriceRequest;
import com.vendingmachine.vendingmachineapplication.exception.InvalidCoinTypeException;
import com.vendingmachine.vendingmachineapplication.exception.ProductSlotNotFoundException;
import com.vendingmachine.vendingmachineapplication.impl.AdminVendingMachineImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendingMachineAdminControllerTest {
    @Mock
    AdminVendingMachineImpl adminVendingMachine;

    @InjectMocks
    VendingMachineAdminController vendingMachineAdminController;

    @Test
    public void shouldAddItemToProductSlotSuccessfully() {
        AddItemRequest addItemRequest = new AddItemRequest();
        addItemRequest.setNumberOfItems(2);
        addItemRequest.setProductSlot("A0");

        assertEquals(HttpStatus.CREATED,
                vendingMachineAdminController.addNumberOfItemToProductSlot(addItemRequest).getStatusCode());

        verify(adminVendingMachine, times(1)).addNumberOfItemToProductSlot(addItemRequest);
    }

    @Test
    public void shouldThrowExceptionWhenProductSlotNotFoundInAddItemRequest() {
        AddItemRequest addItemRequest = new AddItemRequest();
        addItemRequest.setNumberOfItems(2);
        addItemRequest.setProductSlot("B0");

        assertThrows(ProductSlotNotFoundException.class, () -> {
            vendingMachineAdminController.addNumberOfItemToProductSlot(addItemRequest);
        });
    }

    @Test
    public void shouldSuccessfullyRetrieveItemCount() {
        when(adminVendingMachine.getItemCount("A1")).thenReturn(2);

        assertEquals(HttpStatus.OK,
                vendingMachineAdminController.getItemCount("A1").getStatusCode());

        assertEquals(2,
                vendingMachineAdminController.getItemCount("A1").getBody());
    }

    @Test
    public void shouldThrowExceptionWhenProductSlotNotFoundInRetrieveItem() {
        assertThrows(ProductSlotNotFoundException.class, () -> {
            vendingMachineAdminController.getItemCount("B1");
        });
    }

    @Test
    public void shouldSuccessfullyUpdatePriceToProductSlot() {

        UpdatePriceRequest updatePriceRequest = new UpdatePriceRequest();
        updatePriceRequest.setPriceOfItem(2.0);
        updatePriceRequest.setProductSlot("A1");

        assertEquals(HttpStatus.CREATED,
                vendingMachineAdminController.updatePriceToProductSlot(updatePriceRequest).getStatusCode());
    }

    @Test
    public void shouldThrowExceptionWhenProductSlotNotFoundInUpdatePriceRequest() {
        UpdatePriceRequest updatePriceRequest = new UpdatePriceRequest();
        updatePriceRequest.setPriceOfItem(2.0);
        updatePriceRequest.setProductSlot("F1");

        assertThrows(ProductSlotNotFoundException.class, () -> {
            vendingMachineAdminController.updatePriceToProductSlot(updatePriceRequest);
        });
    }

    @Test
    public void shouldSuccessfullyRetrievePricePerItem() {
        when(adminVendingMachine.getPricePerItem("A0"))
                .thenReturn(2.0);
        assertEquals(HttpStatus.OK,
                vendingMachineAdminController.getPricePerItem("A0").getStatusCode());

        assertEquals(2.0,
                vendingMachineAdminController.getPricePerItem("A0").getBody());
    }

    @Test
    public void shouldThrowExceptionWhenProductSlotNotFoundInRetrievePricePerItem() {
        assertThrows(ProductSlotNotFoundException.class, () -> {
            vendingMachineAdminController.getPricePerItem("B1");
        });
    }

    @Test
    public void shouldSuccessfullyAddCoinsToCoinType() {
        AddCoinRequest addCoinRequest = new AddCoinRequest();
        addCoinRequest.setCoinType(0.2);
        addCoinRequest.setNumberOfCoins(3);
        assertEquals(HttpStatus.CREATED,
                vendingMachineAdminController.addCoinsToCoinType(addCoinRequest).getStatusCode());
    }

    @Test
    public void shouldThrowExceptionWhenCoinTypeInAddCoinRequestIsNotFound() {
        AddCoinRequest addCoinRequest = new AddCoinRequest();
        addCoinRequest.setCoinType(0.25);
        addCoinRequest.setNumberOfCoins(3);
        assertThrows(InvalidCoinTypeException.class, () -> {
            vendingMachineAdminController.addCoinsToCoinType(addCoinRequest);
        });
    }

    @Test
    public void shouldSuccessfullyRetrieveNumberOfCoins() {
        when(adminVendingMachine.getCoinsForCoinType(0.2))
                .thenReturn(2.0);
        assertEquals(HttpStatus.OK,
                vendingMachineAdminController.getNumberCoins(0.2).getStatusCode());
    }
}