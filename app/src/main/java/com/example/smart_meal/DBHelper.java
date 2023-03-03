package com.example.smart_meal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER = "CUSTOMER";
    public static final String COLUMN_CUSTOMER_ID = "CustomerID";
    public static final String COLUMN_EMAIL_CUST = "EmailCust";
    public static final String COLUMN_PASSWORD_CUST = "PasswordCust";
    public static final String COLUMN_EMAIL_BUS = "EmailBus";
    public static final String COLUMN_PASSWORD_BUS = "PasswordBus";
    public static final String BUSINESS = "BUSINESS";
    public static final String ITEM = "ITEM";
    public static final String COLUMN_BUSINESS_ID = "BusinessID";
    public static final String COLUMN_ITEM_ID = "ItemID";
    public static final String COLUMN_ITEM_VALUE = "ItemValue";
    public static final String COLUMN_ITEM_NAME = "ItemName";
    public static final String COLUMN_ITEM_DESCRIPTION = "ItemDescription";
    public static final String ORDER_STATUS = "ORDER_STATUS";
    public static final String COLUMN_ORDER_ID = "OrderID";
    public static final String COLUMN_ORDER_STATUS1 = "OrderStatus";
    public static final String COLUMN_BUS_STATE_PROVINCE = "State_Province";
    public static final String COLUMN_STATE_PROVINCE = COLUMN_BUS_STATE_PROVINCE;
    public static final String COLUMN_BUS_CITY = "City";
    public static final String COLUMN_CITY = COLUMN_BUS_CITY;
    public static final String COLUMN_BUS_ADDRESS = "Address";
    public static final String COLUMN_ADDRESS = COLUMN_BUS_ADDRESS;
    public static final String COLUMN_PHONE_BUS = "PhoneBus";
    public static final String COLUMN_PHONE_CUST = COLUMN_PHONE_BUS;
    public static final String COLUMN_LAST_NAME = "LastName";
    public static final String COLUMN_FIRST_NAME = "FirstName";
    public static final String CUSTOMER_INFO = "CUSTOMER_INFO";
    public static final String COLUMN_BUSINESS_NAME = "BusinessName";

    //public static final String DBNAMECUSTOMER = "LoginCustomer.db";
    //public static final String DBNAMEBUSINESS = "LoginBusiness.db";

    //data types in SQLITE
    // NULL- NULL VALUE
    //INTEGER - SIGNED INTEGER, STORED IN 1,2,3,4,6,OR 8 BYTES
    //REAL - A FLOATING POINT VALUE
    //TEXT - TEXT STRING
    //BLOB- BYNARY LARGE OBJECT FOR JPG MOV MP3 BITS STORED IN THE TABLE,
    //(ITS BETTER TO STORE IMAGES IN A SEPARATE FOLDER AND STORE ONLY THE FILE NAME IN THE DATABASE)


    public DBHelper(@Nullable Context context) {
        super(context, "SmartMealDB.db", null, 1);
    }


    //this is called the first time a database is accessed. there should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createTable = "CREATE TABLE BUSINESS(BusinessID INTEGER PRIMARY KEY AUTOINCREMENT, EmailBus TEXT)"
        String createTableCustomer = " CREATE TABLE " + CUSTOMER + "(" + COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_EMAIL_CUST + " TEXT, " + COLUMN_PASSWORD_CUST + " TEXT)";
        db.execSQL(createTableCustomer);
        String createTableBusiness = " CREATE TABLE " + BUSINESS + " (" + COLUMN_BUSINESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_EMAIL_BUS + " TEXT," + COLUMN_PASSWORD_BUS + " TEXT)";
        db.execSQL(createTableBusiness);
        String createTableItem = "CREATE TABLE " + ITEM + "(" + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_ITEM_VALUE + " INTEGER, " + COLUMN_ITEM_NAME + " TEXT, " + COLUMN_ITEM_DESCRIPTION + " TEXT)";
        db.execSQL(createTableItem);
        String createTableOrderStatus = "CREATE TABLE " + ORDER_STATUS + "(" + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ORDER_STATUS1 + " TEXT)";
        db.execSQL(createTableOrderStatus);
        String createTableCustomerInfo = "CREATE TABLE " + CUSTOMER_INFO + "(" + COLUMN_CUSTOMER_ID + "INTEGER," + COLUMN_EMAIL_CUST + "TEXT," + COLUMN_FIRST_NAME + " TEXT," + COLUMN_LAST_NAME + " TEXT," + COLUMN_PHONE_CUST + " INTEGER," + COLUMN_ADDRESS + " TEXT," + COLUMN_CITY + " TEXT," + COLUMN_STATE_PROVINCE + " TEXT)";
        db.execSQL(createTableCustomerInfo);
        String createTableBussinessInfo = "CREATE TABLE BUSINESS_INFO(" + COLUMN_BUSINESS_ID + "INTEGER," + COLUMN_EMAIL_BUS + "TEXT," + COLUMN_BUSINESS_NAME + " TEXT," + " " + COLUMN_PHONE_BUS + " INTEGER," + COLUMN_BUS_ADDRESS + " TEXT," + COLUMN_BUS_CITY + " TEXT," + COLUMN_BUS_STATE_PROVINCE + " TEXT  )";
        db.execSQL(createTableBussinessInfo);
        String createTableOrderInfo = "CREATE TABLE ORDER_INFO(" + COLUMN_ORDER_ID + "INTEGER," + COLUMN_ITEM_ID + "INTEGER," + "ItemQty INTEGER," + "ItemValue INTEGER," + COLUMN_ORDER_STATUS1 + "TEXT," + COLUMN_CUSTOMER_ID + "INTEGER," + COLUMN_BUSINESS_ID + "INTEGER)";
        db.execSQL(createTableOrderInfo);
    }

    // this is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
