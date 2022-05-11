package com.vendingmachine.vendingmachineapplication.controller;

import com.vendingmachine.vendingmachineapplication.CustomerVendingMachine;
import com.vendingmachine.vendingmachineapplication.domain.ProductRequest;
import com.vendingmachine.vendingmachineapplication.exception.NoItemFoundInSlotException;
import com.vendingmachine.vendingmachineapplication.model.ProductSlot;
import com.vendingmachine.vendingmachineapplication.util.ValidationUtil;
import com.vendingmachine.vendingmachineapplication.util.VendingMachineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(path="/user/vendingMachine")
public class CustomerVendingMachineController {

    @Autowired
    CustomerVendingMachine customerVendingMachine;

    @GetMapping(path = "/productSlots/{productSlot}/price")
    public ResponseEntity<Object> fetchPrice(@PathVariable @NotBlank String productSlot) {
        ValidationUtil.validateProductSlot(productSlot.toUpperCase());
        double pricePerItem = customerVendingMachine.fetchPricePerItem(productSlot);
        return ResponseEntity.ok(pricePerItem);
    }

    @PostMapping(path = "/purchaseProduct")

    public ResponseEntity<Object> purchaseProduct(@RequestBody @Valid ProductRequest productRequest) {
        ValidationUtil.validateProductSlot(productRequest.getProductSlot().toUpperCase());

        boolean isItemAvailable = VendingMachineUtil
                .getProduct()
                .get(ProductSlot.valueOf(productRequest.getProductSlot().toUpperCase())) > 0;

        if (!isItemAvailable) {
            throw new NoItemFoundInSlotException(String.format("The given %s product slot has no items",
                    productRequest.getProductSlot()));
        }

        List<Double> coins = customerVendingMachine.dispenseChange(productRequest);
        return ResponseEntity.ok(coins);
    }

}
