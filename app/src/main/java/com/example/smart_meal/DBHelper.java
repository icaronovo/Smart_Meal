package com.example.smart_meal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //creating db
    public static final String SMART_MEAL_DB = "SmartMealDB";
    public static final int dbVersion = 12;


    //creating item table and fields
    public static final String ITEM = "ITEM";
    public static final String COLUMN_ITEM_ID = "ItemID";
    public static final String COLUMN_ITEM_NAME = "ItemName";
    public static final String COLUMN_ITEM_VALUE = "ItemValue";
    public static final String COLUMN_ITEM_DESCRIPTION = "ItemDescription";


    //creating order_info table and fields
    public static final String ORDER_INFO = "ORDER_INFO";
    public static final String COLUMN_ORDER_ID = "OrderID";
    public static final String COLUMN_ORDER_STATUS = "OrderStatus";
    //    public static final String COLUMN_ITEM_ID = "ItemID";
//    public static final String COLUMN_ITEM_VALUE = "ItemValue";
    public static final String COLUMN_ITEM_QTY = "ItemQty";
    public static final String COLUMN_BUSINESS_ID = "BusinessID";
    public static final String COLUMN_CUSTOMER_ID = "CustomerID";


    //creating general account info table
    public static final String CUSTOMER_INFO = "CUSTOMER_INFO";
    public static final String ACCOUNT_TYPE = "AccountType";
    public static final String COLUMN_EMAIL = "EmailCust";
    public static final String COLUMN_PASSWORD = "PasswordCust";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_PHONE = "Phone";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_CITY = "City";
    public static final String COLUMN_PROVINCE = "Province";


    public DBHelper(@Nullable Context context) {
        super(context, SMART_MEAL_DB, null, dbVersion);
    }

    //this is called the first time a database is accessed. there should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createTable = "CREATE TABLE BUSINESS(BusinessID INTEGER PRIMARY KEY AUTOINCREMENT, EmailBus TEXT)"
        String createTableItem = "CREATE TABLE " + ITEM + "("
                + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_ITEM_VALUE + " INTEGER, "
                + COLUMN_ITEM_NAME + " TEXT, "
                + COLUMN_ITEM_DESCRIPTION + " TEXT)";
        db.execSQL(createTableItem);

        String createTableCustomerInfo = "CREATE TABLE " + CUSTOMER_INFO + "("
                + COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY, "
                + ACCOUNT_TYPE + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_NAME + " INTEGER, "
                + COLUMN_PHONE + " TEXT, "
                + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_CITY + " TEXT, "
                + COLUMN_PROVINCE + " TEXT)";
        db.execSQL(createTableCustomerInfo);

        String createTableOrderInfo = "CREATE TABLE " + ORDER_INFO + "("
                + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_ORDER_STATUS + " INTEGER , "
                + COLUMN_ITEM_ID + " INTEGER, "
                + COLUMN_ITEM_VALUE + " INTEGER, "
                + COLUMN_ITEM_QTY + " TEXT, "
                + COLUMN_BUSINESS_ID + " INTEGER, "
                + COLUMN_CUSTOMER_ID + " INTEGER)";
        db.execSQL(createTableOrderInfo);
    }

    // this is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    //CREATING TABLES OR DROPPING IF IT EXISTS
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists CUSTOMER_INFO");
        db.execSQL("drop table if exists ITEM");
        db.execSQL("drop table if exists ORDER_INFO");
        onCreate(db);
    }

    // This method us used to check if the account is of type Business or Customer
    public Cursor checkAccountType(String Email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT AccountType FROM CUSTOMER_INFO WHERE EmailCust= ?", new String[]{Email});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public boolean checkUserAccount (String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CUSTOMER_INFO WHERE EmailCust= ? AND PasswordCust = ? ", new String[]{username, password});
        if(cursor.getCount()>0){
            return true;
        }
        return false;
    }
   //method to delete record from db
    public boolean deleteUserAccount(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(CUSTOMER_INFO, "EmailCust=?", new String[]{id});
        if (d > 0) {
            return true;
        } else {
            return false;
        }
    }

    //method to add customer data in the database
    public boolean addCustomer(CustomerModel customerModel) {
        //writing data in the database
        SQLiteDatabase db = this.getWritableDatabase();
        //linking data from getters to database fields
        ContentValues cv = new ContentValues();
        cv.put(ACCOUNT_TYPE, customerModel.getAccountType());
        cv.put(COLUMN_EMAIL, customerModel.getCustomerEmail());
        cv.put(COLUMN_PASSWORD, customerModel.getPassword());
        cv.put(COLUMN_NAME, customerModel.getCustomerName());
        cv.put(COLUMN_PHONE, customerModel.getCustomerPhone());
        cv.put(COLUMN_ADDRESS, customerModel.getCustomerAddress());
        cv.put(COLUMN_CITY, customerModel.getCustomerCity());
        cv.put(COLUMN_PROVINCE, customerModel.getCustomerProvince());
        // inserting data above
        long insert = db.insert(CUSTOMER_INFO, null, cv);
        return insert != -1;
    }
}

