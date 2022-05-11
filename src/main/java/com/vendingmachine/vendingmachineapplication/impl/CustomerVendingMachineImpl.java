package com.vendingmachine.vendingmachineapplication.impl;

import com.vendingmachine.vendingmachineapplication.CustomerVendingMachine;
import com.vendingmachine.vendingmachineapplication.domain.ProductRequest;
import com.vendingmachine.vendingmachineapplication.exception.NoChangeException;
import com.vendingmachine.vendingmachineapplication.exception.InSufficientAmountException;
import com.vendingmachine.vendingmachineapplication.model.CoinType;
import com.vendingmachine.vendingmachineapplication.model.ProductSlot;
import com.vendingmachine.vendingmachineapplication.util.VendingMachineUtil;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerVendingMachineImpl implements CustomerVendingMachine {

    private ProductSlot selectedItem;
    private double currentBalance;

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");


    public CustomerVendingMachineImpl(){
    }


    /**
     * Dispense the remaining change after deducting the product amount
     * @param productRequest containing the product and the product amount
     * @return the change
     */
    @Override
    public List<Double> dispenseChange(ProductRequest productRequest) {
        String productSlot = productRequest.getProductSlot();
        selectedItem = ProductSlot.valueOf(productSlot.toUpperCase());
        currentBalance = productRequest
                .getCoins()
                .stream()
                        .mapToDouble(Double::doubleValue).sum();

        List<CoinType> change = collectChange();
        deductItemsInProductSlot();
        currentBalance = 0;
        selectedItem = null;
        return change
                .stream()
                .map(CoinType::getValue)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the price per item in the product slot
     * @param productSlot containing the items
     * @return the price of the item
     */
    @Override
    public double fetchPricePerItem(String productSlot) {
        return VendingMachineUtil.getPrice().get(ProductSlot.valueOf(productSlot.toUpperCase()));
    }

    /**
     * Deducts the items in product slot once it has been sold
     * @throws NoChangeException when there is no change present in the vending machine
     * @throws InSufficientAmountException when the amount to be paid is less than the item's price
     */
    private void deductItemsInProductSlot() throws NoChangeException,
            InSufficientAmountException {
        double pricePerItem = fetchPricePerItem(selectedItem.getSlotName());
        if(currentBalance >= pricePerItem){
            VendingMachineUtil.deductProduct(selectedItem);
            return;
        }
        double difference = pricePerItem - currentBalance;
        throw new InSufficientAmountException(String
                .format("Amount has not been paid fully, please pay the remaining : %s amount",
                        difference));
    }

    /**
     *change that is need to be collected after deducting the item price
     * @return change
     */
    private List<CoinType> collectChange() {
        double pricePerItem = fetchPricePerItem(selectedItem.getSlotName());
        double changeAmount = Double.parseDouble(decimalFormat
                .format(currentBalance - pricePerItem));
        List<CoinType> change = getCoinTypes(changeAmount);
        List<CoinType> coinTypes = getCoinTypes(pricePerItem);
        coinTypes.forEach(VendingMachineUtil::updateCoin);
        change.forEach(VendingMachineUtil::deductCoin);
        return change;
    }

    /**
     * Retrieve the coin types which is dispensed as a change
     * @param paidAmount price of the item
     * @return the coin types
     * @throws NoChangeException when no change is present
     */
    private List<CoinType> getCoinTypes(double paidAmount) throws NoChangeException{
        List<CoinType> changes = new ArrayList<>();

        if(paidAmount > 0){
            double balanceAmount = paidAmount;
            while(balanceAmount > 0){
                if(balanceAmount >= CoinType.POUND.getValue()
                        && VendingMachineUtil.getCoinType().get(CoinType.POUND) > 0){
                    changes.add(CoinType.POUND);
                    balanceAmount =
                            Double.parseDouble(decimalFormat.format(balanceAmount - CoinType.POUND.getValue()));

                }else if(balanceAmount >= CoinType.FIFTY_PENCE.getValue()
                        && VendingMachineUtil.getCoinType().get(CoinType.FIFTY_PENCE) > 0) {
                    changes.add(CoinType.FIFTY_PENCE);
                    balanceAmount = Double.parseDouble(decimalFormat
                            .format(balanceAmount - CoinType.FIFTY_PENCE.getValue()));

                }else if(balanceAmount >= CoinType.TWENTY_PENCE.getValue()
                        && VendingMachineUtil.getCoinType().get(CoinType.TWENTY_PENCE) > 0) {
                    changes.add(CoinType.TWENTY_PENCE);
                    balanceAmount = Double.parseDouble(decimalFormat
                            .format(balanceAmount - CoinType.TWENTY_PENCE.getValue()));

                }else if(balanceAmount >= CoinType.TEN_PENCE.getValue()
                        && VendingMachineUtil.getCoinType().get(CoinType.TEN_PENCE) > 0) {
                    changes.add(CoinType.TEN_PENCE);
                    balanceAmount = Double.parseDouble(decimalFormat
                            .format(balanceAmount - CoinType.TEN_PENCE.getValue()));

                }else{
                    throw new NoChangeException("Sorry, there are currently no change");
                }
            }
        }

        return changes;
    }
}
