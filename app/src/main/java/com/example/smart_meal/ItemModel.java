package com.example.smart_meal;

public class ItemModel {

    private String itemName;
    private int itemImage;
    private String itemDescription;
    private Double itemPrice;
    private int itemQuantity;

    //constructor
    public ItemModel(String itemName, int itemImage, String itemDescription, Double itemPrice) {
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }
//getter and setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
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

    //to string method
    @Override
    public String toString() {
        return "ItemModel{" +
                "itemName='" + itemName + '\'' +
                ", itemImage=" + itemImage +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
