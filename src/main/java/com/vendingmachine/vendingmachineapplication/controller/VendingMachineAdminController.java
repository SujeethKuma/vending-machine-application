package com.vendingmachine.vendingmachineapplication.controller;

import com.vendingmachine.vendingmachineapplication.AdminVendingMachine;
import com.vendingmachine.vendingmachineapplication.domain.AddCoinRequest;
import com.vendingmachine.vendingmachineapplication.domain.AddItemRequest;
import com.vendingmachine.vendingmachineapplication.domain.UpdatePriceRequest;
import com.vendingmachine.vendingmachineapplication.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path="/admin/vendingMachine")
public class VendingMachineAdminController {

    @Autowired
    AdminVendingMachine adminVendingMachine;

    @PutMapping(path = "/productSlots/addNumberOfItem")
    public ResponseEntity addNumberOfItemToProductSlot(@RequestBody  @Valid AddItemRequest itemRequest) {
        ValidationUtil.validateProductSlot(itemRequest.getProductSlot().toUpperCase());
        adminVendingMachine.addNumberOfItemToProductSlot(itemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/productSlots/{productSlot}/itemCount")
    public ResponseEntity getItemCount(@PathVariable @NotBlank String productSlot) {
        ValidationUtil.validateProductSlot(productSlot.toUpperCase());
        int itemCount = adminVendingMachine.getItemCount(productSlot);
        return ResponseEntity
                .status(HttpStatus.OK)
                 .body(itemCount);
    }

    @PutMapping(path = "/productSlots/updatePrice")
    public ResponseEntity<Object> updatePriceToProductSlot(@RequestBody @Valid UpdatePriceRequest updatePriceRequest) {
        ValidationUtil.validateProductSlot(updatePriceRequest.getProductSlot().toUpperCase());
        adminVendingMachine.updatePriceToProductSlot(updatePriceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/productSlots/{productSlot}/pricePerItem")
    public ResponseEntity<Object> getPricePerItem(@PathVariable @NotBlank String productSlot) {
        ValidationUtil.validateProductSlot(productSlot.toUpperCase());
        double itemCount = adminVendingMachine.getPricePerItem(productSlot);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemCount);
    }

    @PutMapping(path = "/coins/addCoin")
    public ResponseEntity<Object> addCoinsToCoinType(@RequestBody  @Valid AddCoinRequest addCoinRequest) {
        ValidationUtil.validateCoinType(addCoinRequest.getCoinType());
        adminVendingMachine.addCoinsToCoinType(addCoinRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/coins/{coinType}/count")
    public ResponseEntity<Object> getNumberCoins(@PathVariable @NotNull Double coinType) {
        ValidationUtil.validateCoinType(coinType);
        double itemCount = adminVendingMachine.getCoinsForCoinType(coinType);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemCount);
    }

}
