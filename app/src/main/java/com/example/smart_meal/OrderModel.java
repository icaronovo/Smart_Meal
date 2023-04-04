package com.example.smart_meal;

import android.content.Intent;

public class OrderModel {

    private int orderStatus;
    private int businessID;
    private int customerID;
    private String date;
    private String itemID;
    private String itemQuantity;

    //constructor to order
    public OrderModel(int orderStatus, int businessID,
                      int customerID, String date,
                      String itemID, String itemQuantity){
        this.orderStatus = orderStatus;
        this.businessID = businessID;
        this.customerID = customerID;
        this.itemID = itemID;
        this.itemQuantity = itemQuantity;
        this.date = date;
    }
    //getters and setters
    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
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

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
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
                ", itemID=" + itemID +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
