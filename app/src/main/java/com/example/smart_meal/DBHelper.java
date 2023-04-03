package com.example.smart_meal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    //creating db
    public static final String SMART_MEAL_DB = "SmartMealDB";
    public static final int dbVersion = 14;

    //creating item table and fields
    public static final String ITEM = "ITEM";
    public static final String COLUMN_ITEM_ID = "ItemID";
    public static final String COLUMN_ITEM_NAME = "ItemName";
    public static final String COLUMN_ITEM_IMAGE = "ItemImage";

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
    public static final String COLUMN_PROFILE_IMAGE = "ProfileImage";
    private static final String COLUMN_TOTAL_ORDER = "TotalOrder";
    private static final String COLUMN_DATE = "Date";

    private Context mContext;

    public DBHelper(@Nullable Context context) {
        super(context, SMART_MEAL_DB, null, dbVersion);
    }


    //this is called the first time a database is accessed. there should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createTable = "CREATE TABLE BUSINESS(BusinessID INTEGER PRIMARY KEY AUTOINCREMENT, EmailBus TEXT)"
        String createTableItem = "CREATE TABLE " + ITEM +
                "(" + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_ITEM_VALUE + " INTEGER, "
                + COLUMN_ITEM_NAME + " TEXT, "
                + COLUMN_ITEM_IMAGE + " INTEGER, "
                + COLUMN_ITEM_DESCRIPTION + " TEXT)";

        db.execSQL(createTableItem);

        String createTableCustomerInfo = "CREATE TABLE " + CUSTOMER_INFO +
                "(" + COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY, "
                + ACCOUNT_TYPE + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_NAME + " REAL, "
                + COLUMN_PHONE + " TEXT, "
                + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_CITY + " TEXT, "
                + COLUMN_PROVINCE + " TEXT, "
                + COLUMN_PROFILE_IMAGE + " INTEGER)";
        db.execSQL(createTableCustomerInfo);

        String createTableOrderInfo = "CREATE TABLE " + ORDER_INFO +
                "(" + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_ORDER_STATUS + " INTEGER , "
                + COLUMN_TOTAL_ORDER + " INTEGER, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_ITEM_ID + " INTEGER, "
                + COLUMN_ITEM_VALUE + " INTEGER, "
                + COLUMN_ITEM_QTY + " INTEGER, "
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

    //query to get user data
    public String[] getUserData(String email) {
        String[] userData = new String[9];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CUSTOMER_INFO WHERE EmailCust= ? ", new String[]{email});
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < userData.length; i++) {
                userData[i] = cursor.getString(i);
            }
        }
        return userData;

    }

    // This query us used to check if the account is of type Business or Customer
    public Cursor checkAccountType(String Email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT AccountType FROM CUSTOMER_INFO WHERE EmailCust= ? ", new String[]{Email});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //query to check if the user exist in DB
    public boolean checkUserAccount(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CUSTOMER_INFO WHERE EmailCust= ? AND PasswordCust = ? ", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    //query to delete account record from db
    public boolean deleteUserAccount(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(CUSTOMER_INFO, "EmailCust=?", new String[]{id});
        return d > 0;
    }

    //query to update password
    public boolean accountUpdate(String username, String password, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor dataRead = database.rawQuery("SELECT * FROM CUSTOMER_INFO WHERE Name = ? AND EmailCust=? ", new String[]{name, username});
        ContentValues values = new ContentValues();
        if (dataRead.getCount() > 0) {
            values.put("PasswordCust", password);
            db.update("CUSTOMER_INFO", values, "EmailCust= ?", null);
            return true;
        } else return false;
    }

    //query to add customer data in the database
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
        cv.put(COLUMN_PROFILE_IMAGE, customerModel.getCustomerImage());
        // inserting data above
        long insert = db.insert(CUSTOMER_INFO, null, cv);
        return insert != -1;
    }

    //query to update user data
    public boolean customerUpdate(String prevEmail, String email, String password, String name, String phone, String address, String city, String province) {
        //writing data in the database
        SQLiteDatabase db = this.getWritableDatabase();
        //linking data from getters to database fields
        ContentValues cv = new ContentValues();
        cv.put("EmailCust", email);
        cv.put("PasswordCust", password);
        cv.put("Name", name);
        cv.put("Phone", phone);
        cv.put("Address", address);
        cv.put("City", city);
        cv.put("Province", province);
        int rowsAffected = db.update("CUSTOMER_INFO", cv, "EmailCust = ?", new String[]{prevEmail});
        return rowsAffected > 0;
    }

    //query to validate if email is already in use
    public boolean checkIfEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CUSTOMER_INFO WHERE EmailCust= ? ", new String[]{email});
        return cursor.getCount() > 0;
    }
    public boolean getBusID(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CUSTOMER_INFO WHERE CustomerID= ? AND AccountType= 'Business' ", new String[]{email});
        return cursor.getCount() > 0;
    }
    public boolean getCustID(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CUSTOMER_INFO WHERE CustomerID= ? AND AccountType= 'Customer' ", new String[]{email});
        return cursor.getCount() > 0;
    }

    //query to add values to ITEM table
    public boolean addItem(ItemModel itemModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_NAME, itemModel.getItemName());
        cv.put(COLUMN_ITEM_VALUE, itemModel.getItemPrice());
        cv.put(COLUMN_ITEM_IMAGE, itemModel.getItemImage());
        cv.put(COLUMN_ITEM_DESCRIPTION, itemModel.getItemDescription());
        cv.put(COLUMN_ITEM_QTY, itemModel.getItemQuantity());
        long insert = sqLiteDatabase.insert(ITEM, null, cv);
        return insert != -1;
    }

    //query to add values to ORDER table
    public boolean addOrder(OrderModel orderModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ORDER_STATUS, orderModel.getOrderStatus());
        cv.put(COLUMN_BUSINESS_ID, orderModel.getBusinessID());
        cv.put(COLUMN_DATE, orderModel.getDate());
        cv.put(COLUMN_CUSTOMER_ID, orderModel.getCustomerID());
        cv.put(COLUMN_ITEM_VALUE, orderModel.getItemValue());
        cv.put(COLUMN_ITEM_ID, orderModel.getItemID());
        cv.put(COLUMN_ITEM_QTY, orderModel.getItemQuantity());
        long insert = sqLiteDatabase.insert(ORDER_INFO, null, cv);
        return insert != -1;
    }

    //query to show items data.
    public boolean itemsDisplay(String username, String items) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ITEM WHERE CustomerID= ? AND ItemID ", new String[]{username, items});
        return cursor.getCount() > 0;
    }

//    public boolean itemNamePrice(String email, Double itemPrice, String itemName){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT ItemName,ItemValue FROM ITEM WHERE EmailCust= ? ", new String[]{email,itemPrice.toString(),itemName});
//        return cursor.getCount() > 0;
//    }

    //query to update item qty
    public boolean itemsQtyUpdate(String username, String itemQty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ItemQty", itemQty);
        int rowsAffected = db.update("ITEM", values, "EmailCust = ?", new String[]{username});

        return rowsAffected > 0;
    }

    //query to update item value
    public boolean itemsValueUpdate(String username, String itemValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ItemValue", itemValue);
        int rowsAffected = db.update("ITEM", values, "EmailCust = ?", new String[]{username});

        return rowsAffected > 0;
    }
    //query to update order status
    public boolean orderStatusUpdate(String username, String orderStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("OrderStatus", orderStatus);
        int rowsAffected = db.update("ORDER_INFO", values, "CustomerID = ?", new String[]{username});

        return rowsAffected > 0;
    }
    //query to show all orders
    public List<String> ordersDisplay(String username, String orders) {
        List<String> ordersData = new ArrayList<>(7);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ORDER_INFO WHERE CustomerID= ? AND OrderID= ? ", new String[]{username, orders});
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < ordersData.size(); i++) {
                ordersData.set(i, cursor.getString(i));
            }
        }
        return ordersData;
    }
    public Cursor displayOrder(String customerID){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + ORDER_INFO + " WHERE " + COLUMN_BUSINESS_ID + "=?";
        Cursor cursor = database.rawQuery(query,new String[]{customerID});
        return cursor;
    }

}

