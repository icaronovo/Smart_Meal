package com.example.smart_meal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CustomerModel {

    private String accountType;
    private String customerEmail;
    private String password;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String customerCity;
    private String customerProvince;
    private int customerImage;
    private int customerID;

    //Constructors

    //This contructor will be used to generate the recycler view in the Customer Main Activity
    public CustomerModel(Integer id, String name, String address, String city, String province, Integer image){
        this.customerID = id;
        this.customerName = name;
        this.customerAddress = address;
        this.customerCity = city;
        this.customerProvince = province;
        this.customerImage = image;
    }

    // This constructor will be used to generate the recycler view in the Customer search activity
    public CustomerModel (Integer id, String name, String email, String phone,
                          String address, String city, String province, Integer image) {
        this.customerName = name;
        this.customerEmail = email;
        this.customerPhone = phone;
        this.customerAddress = address;
        this.customerCity = city;
        this.customerProvince = province;
        this.customerImage = image;
    }

    public CustomerModel(String accountType, String customerEmail, String password,
                         String customerName, String customerPhone, String customerAddress,
                         String customerCity, String customerProvince, int customerImage) {
        this.accountType = accountType;
        this.customerEmail = customerEmail;
        this.password = password;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerProvince = customerProvince;
        this.customerImage = customerImage;
    }

//getters and setters


    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public int getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(int customerImage) {
        this.customerImage = customerImage;
    }

//to string method
    @Override
    public String toString() {
        return "CustomerModel{" +
                "accountType='" + accountType + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", password='" + password + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", customerProvince='" + customerProvince + '\'' +
                ", customerImage=" + customerImage +
                '}';
    }
}
