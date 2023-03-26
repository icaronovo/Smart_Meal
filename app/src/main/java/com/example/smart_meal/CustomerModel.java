package com.example.smart_meal;

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

    //Constructors


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
