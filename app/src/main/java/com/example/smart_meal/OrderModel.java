package com.example.smart_meal;

import android.content.Intent;

public class OrderModel {

    private String orderStatus;
    private int businessID;
    private int customerID;
    private String date;
    private Double itemValue;
    private int itemID;
    private int itemQuantity;

    //constructor to order
    public OrderModel(String orderStatus, int businessID,
                      int customerID, Double itemValue, String date,
                      int itemID, int itemQuantity){
        this.orderStatus = orderStatus;
        this.businessID = businessID;
        this.customerID = customerID;
        this.itemValue = itemValue;
        this.itemID = itemID;
        this.itemQuantity = itemQuantity;
        this.date = date;
    }
    //getters and setters
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getBusinessID() {
        return businessID;
    }

    public void setBusinessID(int businessID) {
        this.businessID = businessID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderStatus='" + orderStatus + '\'' +
                ", businessID='" + businessID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", date='" + date + '\'' +
                ", itemValue=" + itemValue +
                ", itemID=" + itemID +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
