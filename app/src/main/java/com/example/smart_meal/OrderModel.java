package com.example.smart_meal;

import android.content.Intent;

public class OrderModel {

    private int orderID;
    private int orderStatus;
    private int businessID;
    private int customerID;
    private String date;
    private String itemID;
    private String itemQuantity;

    //constructor to order

    public OrderModel(int orderID, int orderStatus){
        this.orderID = orderID;
        this.orderStatus = orderStatus;
    }

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
    public OrderModel(int orderID, int orderStatus, int businessID,
                      int customerID, String date,
                      String itemID, String itemQuantity){
        this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.businessID = businessID;
        this.customerID = customerID;
        this.itemID = itemID;
        this.itemQuantity = itemQuantity;
        this.date = date;
    }

    //getters and setters

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

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
        //Get the type of status
        String status = "";
        if(orderStatus == 0){
            status = "PROCESSING";
        }else{
            status = "COMPLETE";
        }

        //Get items id
        String[] itemsID = this.itemID.split("\\$");
        //Get items quantity
        String[] itemQty = this.itemQuantity.split("\\$");
        StringBuilder str = new StringBuilder();

        for(int i = 0; i < itemsID.length; i++){
            str.append("Item ID: #" + itemsID[i] + " x Quantity: " + itemQty[i] + "\n");
        }

        return "Order ID #" + this.orderID + '\n' +
                "OrderStatus = " + status + '\n' +
                "CustomerID = " + this.customerID + '\n' +
                "Date='" + this.date + '\n' +
                String.valueOf(str);
    }
}
