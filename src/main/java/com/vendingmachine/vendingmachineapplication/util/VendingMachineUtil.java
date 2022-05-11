package com.vendingmachine.vendingmachineapplication.util;

import com.vendingmachine.vendingmachineapplication.domain.AddCoinRequest;
import com.vendingmachine.vendingmachineapplication.domain.AddItemRequest;
import com.vendingmachine.vendingmachineapplication.domain.UpdatePriceRequest;
import com.vendingmachine.vendingmachineapplication.model.CoinType;
import com.vendingmachine.vendingmachineapplication.model.ProductSlot;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VendingMachineUtil {

    static  Map<ProductSlot, Integer> product;
    static  Map<CoinType, Integer> coinTypeIntegerMap;

    static  Map<ProductSlot, Double> price;
    static {
        product = new ConcurrentHashMap<>();
        Arrays.stream(ProductSlot.values())
                .forEach( v -> product.put(v, 5));
    }

    static {
        coinTypeIntegerMap = new ConcurrentHashMap<>();
        Arrays.stream(CoinType.values())
                .forEach( v -> coinTypeIntegerMap.put(v, 1));
    }

    static {
        price = new ConcurrentHashMap<>();
        Arrays.stream(ProductSlot.values())
                .forEach( v -> price.put(v, 0.2));
    }

    public static Map<ProductSlot, Integer> getProduct() {
        return product;
    }

    public static void deductProduct(ProductSlot deduct) {
        int count = product.get(deduct);
        product.put(deduct, count - 1);
    }

    public static void deductCoin(CoinType deduct) {
        int count = coinTypeIntegerMap.get(deduct);
        coinTypeIntegerMap.put(deduct, count - 1);
    }

    public static void updateCoin(CoinType deduct) {
        int count = coinTypeIntegerMap.get(deduct);
        coinTypeIntegerMap.put(deduct, count + 1);
    }

    public static void setProductItem(AddItemRequest itemRequest) {
        ProductSlot productSlot = ProductSlot.valueOf(itemRequest.getProductSlot().toUpperCase());
        int count = product.get(productSlot);
        product.put(productSlot, itemRequest.getNumberOfItems() + count);
    }

    public static int getProductItem(String productSlot) {
        ProductSlot slot = ProductSlot.valueOf(productSlot.toUpperCase());
        return product.get(slot);
    }

    public static Map<ProductSlot, Double> getPrice() {
        return price;
    }

    public static void setPrice(UpdatePriceRequest updatePriceRequest) {
        ProductSlot productSlot = ProductSlot.valueOf(updatePriceRequest.getProductSlot().toUpperCase());
        price.put(productSlot,  updatePriceRequest.getPriceOfItem());
    }

    public static double getPricePerItem(String productSlot) {
        ProductSlot slot = ProductSlot.valueOf(productSlot.toUpperCase());
        return price.get(slot);
    }

    public static Map<CoinType, Integer> getCoinType() {
        return coinTypeIntegerMap;
    }

    public static void setCoin(AddCoinRequest addCoinRequest) {
        CoinType coinType = CoinType.valueOfCoinType(addCoinRequest.getCoinType());
        int count = coinTypeIntegerMap.get(coinType);
        coinTypeIntegerMap.put(coinType, addCoinRequest.getNumberOfCoins() + count);
    }

    public static double getNumberOfCoins(Double coin) {
        CoinType coinType = CoinType.valueOfCoinType(coin);
        return coinTypeIntegerMap.get(coinType);
    }
}
