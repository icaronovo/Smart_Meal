package com.example.smart_meal;

public class OrderModel {

    private int orderStatus;
    private int businessID;
    private int customerID;
//    private Double itemValue;
    private String itemID;
    private String itemQuantity;

    //constructor to order
    public OrderModel(int orderStatus, int businessID,
                      int customerID, String itemID, String itemQuantity){
        this.orderStatus = orderStatus;
        this.businessID = businessID;
        this.customerID = customerID;
        this.itemID = itemID;
        this.itemQuantity = itemQuantity;
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
    @Override
    public String toString() {
        return "OrderModel{" +
                "orderStatus='" + orderStatus + '\'' +
                ", businessID='" + businessID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", itemID=" + itemID +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
