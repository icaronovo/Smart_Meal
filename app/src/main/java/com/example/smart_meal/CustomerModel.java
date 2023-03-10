package com.example.smart_meal;

public class CustomerModel {
    private int id;
    private String firstName;
    private String lastName;
    private String customerPhone;

    private String password;
    private String customerAddress;
    private String customerCity;
    private String customerState;
    private String customerEmail;

    //Constructors
    public CustomerModel(int id, String firstName, String lastName, String customerPhone, String password, String customerAddress,
                         String customerCity, String customerState, String customerEmail) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerPhone = customerPhone;
        this.password = password;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerState = customerState;
        this.customerEmail = customerEmail;
    }


    //To String


    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", customerState='" + customerState + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFistName(String fistName) {
        this.firstName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
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

}
