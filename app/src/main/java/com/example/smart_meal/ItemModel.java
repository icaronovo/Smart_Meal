package com.example.smart_meal;

import android.text.Editable;

public class ItemModel {

    private String itemName;
    private String itemDescription;
    private Double itemPrice;
    private int itemQuantity;
    private int businessID;
    private int itemID;

    public ItemModel(int itemID, String itemName, String itemDescription, Double itemPrice, int itemQuantity, int businessID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.businessID = businessID;
    }

    public ItemModel(String itemName, String itemDescription, Double itemPrice, int itemQuantity) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public ItemModel(String itemName, String itemDescription, Double itemPrice, int itemQuantity, int businessID) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.businessID = businessID;
    }

    public ItemModel(String itemName, Double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    //constructor
    public ItemModel(String itemName, String itemDescription, Double itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }


//getter and setters

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getBusinessID() {
        return businessID;
    }

    public void setBusinessID(int businessID) {
        this.businessID = businessID;
    }

    //to string method
    @Override
    public String toString() {
        return "\n Item Name: " + itemName + "\n" +
                " Description: " + itemDescription + "\n" +
                " Price: $" + itemPrice + "\n" +
                " Quantity: " + itemQuantity + "\n";
    }
}
