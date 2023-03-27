package com.example.smart_meal;

public class OrderModel {

    private String orderStatus;
    private String businessID;
    private String customerID;
    private Double itemValue;
    private int itemID;
    private int itemQuantity;

    //constructor to order
    public OrderModel(String orderStatus, String businessID,
                      String customerID, Double itemValue,
                      int itemID, int itemQuantity){
        this.orderStatus = orderStatus;
        this.businessID = businessID;
        this.customerID = customerID;
        this.itemValue = itemValue;
        this.itemID = itemID;
        this.itemQuantity = itemQuantity;
    }
    //getters and setters
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBusinessID() {
        return businessID;
    }

    public void setBusinessID(String businessID) {
        this.businessID = businessID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Double getItemValue() {
        return itemValue;
    }

    public void setItemValue(Double itemValue) {
        this.itemValue = itemValue;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
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
        return "OrderModel{" +
                "orderStatus='" + orderStatus + '\'' +
                ", businessID='" + businessID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", itemValue=" + itemValue +
                ", itemID=" + itemID +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
