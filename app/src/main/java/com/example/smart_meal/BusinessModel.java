package com.example.smart_meal;

public class BusinessModel {

    private int id;
    private String businessName;
    private String businessPhone;
    private String businessAddress;
    private String businessCity;
    private String businessState;
    private String businessEmail;

    //Constructor
    public BusinessModel(int id, String businessName, String businessPhone, String businessAddress, String businessCity,
                         String businessState, String businessEmail) {
        super();
        this.id = id;
        this.businessName = businessName;
        this.businessPhone = businessPhone;
        this.businessAddress = businessAddress;
        this.businessCity = businessCity;
        this.businessState = businessState;
        this.businessEmail = businessEmail;
    }

    public BusinessModel(){

    }

    //To String


    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }



}
