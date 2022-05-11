package com.vendingmachine.vendingmachineapplication.model;

public enum ProductSlot {

    A0("A0"),
    A1("A1"),
    A2("A2"),
    A3("A3"),
    A4("A4"),
    A5("A5"),
    A6("A6"),
    A7("A7"),
    A8("A8"),
    A9("A9");

    private String slotName;
    ProductSlot(String slotName){
        this.slotName = slotName;
    }
    public String getSlotName(){
        return slotName;
    }

}