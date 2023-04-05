package com.example.smart_meal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    //creating db
    public static final String SMART_MEAL_DB = "SmartMealDB";
    public static final int dbVersion = 16;

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
    public static final String COLUMN_DATE = "Date";
    //    public static final String COLUMN_ITEM_VALUE = "ItemValue";
    public static final String COLUMN_ITEM_QTY = "ItemQuantity";
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
    private static final String COLUMN_ITEM_QUANTITY = "ItemQuantity";

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
                + COLUMN_ITEM_QUANTITY + " INTEGER, "
                + COLUMN_ITEM_NAME + " TEXT, "
                + COLUMN_ITEM_DESCRIPTION + " TEXT,"
                + COLUMN_BUSINESS_ID + " INTEGER)";
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
                + COLUMN_ITEM_ID + " TEXT, "
                + COLUMN_DATE + " TEXT, "
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

    public String[] getUserData(Integer businessID) {
        String[] userData = new String[9];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CUSTOMER_INFO WHERE CustomerID= ? ", new String[]{businessID.toString()});
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

    public void addSeveralBusinesses() {
        CustomerModel business1 = new CustomerModel("Business", "mcdonalds@mcdonalds", "mcdonalds", "McDonald's", "123", "McDonalds Avenue", "Burnaby", "British Columbia", R.drawable.ic_baseline_person_outline_24);
        CustomerModel business2 = new CustomerModel("Business", "bk@bk", "bk", "Burger King", "123", "Burger King Avenue", "Vancouver", "British Columbia", R.drawable.ic_baseline_person_outline_24);
        CustomerModel business3 = new CustomerModel("Business", "wendys@wendys", "wendys", "Wendy's", "123", "Wendy's Avenue", "New Westminster", "British Columbia", R.drawable.ic_baseline_person_outline_24);
        CustomerModel business4 = new CustomerModel("Business", "kfc@kfc", "kfc", "KFC", "123", "KFC Avenue", "Surrey", "British Columbia", R.drawable.ic_baseline_person_outline_24);
        CustomerModel business5 = new CustomerModel("Business", "togosushi@togosushi", "togosushi", "ToGo Sushi", "123", "ToGo Sushi Avenue", "Burnaby", "British Columbia", R.drawable.ic_baseline_person_outline_24);
        CustomerModel business6 = new CustomerModel("Business", "dominos@dominos", "dominos", "Domino's", "123", "Domino's Avenue", "Vancouver", "British Columbia", R.drawable.ic_baseline_person_outline_24);
        CustomerModel business7 = new CustomerModel("Business", "pizzahut@pizzahut", "pizzahut", "Pizza Hut", "123", "Pizza Hut Avenue", "New Westminster", "British Columbia", R.drawable.ic_baseline_person_outline_24);
        CustomerModel business8 = new CustomerModel("Business", "pizzapizza@pizzapizza", "pizzapizza", "Pizza Pizza", "123", "Pizza Pizza Avenue", "Surrey", "British Columbia", R.drawable.ic_baseline_person_outline_24);
        CustomerModel business9 = new CustomerModel("Business", "freshslice@freshslice", "freshslice", "Fresh Slice", "123", "Fresh Slice Avenue", "Burnaby", "British Columbia", R.drawable.ic_baseline_person_outline_24);
        CustomerModel business10 = new CustomerModel("Business", "pizzagarden@pizzagarden", "pizzagarden", "Pizza Garden", "123", "Pizza Garden Avenue", "Vancouver", "British Columbia", R.drawable.ic_baseline_person_outline_24);

        CustomerModel[] listOfBusiness = {business1, business2, business3, business4, business5, business6, business7, business8, business9, business10};

        for (int i = 0; i < listOfBusiness.length; i++) {
            boolean emailUsed = checkIfEmailExists(listOfBusiness[i].getCustomerEmail());
            if (!emailUsed) {
                addCustomer(listOfBusiness[i]);
            }
        }
    }

    public ArrayList<CustomerModel> retrieveAllBusinesses () {
    //public Cursor retrieveAllBusinesses() {
        ArrayList<CustomerModel> businessList = new ArrayList<CustomerModel>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CUSTOMER_INFO WHERE AccountType = 'Business'", null);
        Log.d("cursor", cursor.toString());
        int i = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("CustomerID"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("EmailCust"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("Address"));
            String city = cursor.getString(cursor.getColumnIndexOrThrow("City"));
            String province = cursor.getString(cursor.getColumnIndexOrThrow("Province"));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("ProfileImage"));
            CustomerModel customer = new CustomerModel(id, name, email, phone, address, city, province, image);
            businessList.add(customer);
            Log.d("Business", name);
        }
        return businessList;
    }

    public int getBusinessIDFromDB(String customerEmail) {
        SQLiteDatabase database = this.getReadableDatabase();

        Integer id = -1;
        Cursor cursor = database.rawQuery("SELECT * FROM " + CUSTOMER_INFO + " WHERE " + COLUMN_EMAIL + "=?", new String[]{customerEmail});
        if (cursor != null && cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_ID));
        }

        cursor.close();
        return id;
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
        cv.put(COLUMN_ITEM_VALUE, itemModel.getItemPrice());
        cv.put(COLUMN_ITEM_QTY, itemModel.getItemQuantity());
        cv.put(COLUMN_ITEM_NAME, itemModel.getItemName());
        cv.put(COLUMN_ITEM_DESCRIPTION, itemModel.getItemDescription());
        cv.put(COLUMN_BUSINESS_ID, itemModel.getBusinessID());
        long insert = sqLiteDatabase.insert(ITEM, null, cv);
        return insert != -1;
    }

    //add several items when app is loaded
    public void insertSeveralItems() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ITEM WHERE ItemID= ? ", new String[]{"1"});
        if (cursor.getCount() < 0) {
            ArrayList<ItemModel> itemList = new ArrayList<ItemModel>();
            itemList.add(new ItemModel("Title Item", "Description, description bla bla bla", 9.99, 10, 1));
            itemList.add(new ItemModel("Title Item 2", "Description, description bla bla bla", 19.99, 10, 1));
            itemList.add(new ItemModel("Title Item 3", "Description, description bla bla bla", 6.99, 10, 1));
            itemList.add(new ItemModel("Title Item 4", "Description, description bla bla bla", 5.99, 10, 1));
            itemList.add(new ItemModel("Title Item 5", "Description, description bla bla bla", 4.99, 10, 2));
            itemList.add(new ItemModel("Title Item 6", "Description, description bla bla bla", 3.99, 10, 2));
            itemList.add(new ItemModel("Title Item 7", "Description, description bla bla bla", 21.99, 10, 2));
            itemList.add(new ItemModel("Title Item 8", "Description, description bla bla bla", 5.99, 10, 2));
            itemList.add(new ItemModel("Title Item 9", "Description, description bla bla bla", 6.99, 10, 3));
            itemList.add(new ItemModel("Title Item 10", "Description, description bla bla bla", 1.99, 10, 3));
            itemList.add(new ItemModel("Title Item 11", "Description, description bla bla bla", 45.99, 10, 3));
            itemList.add(new ItemModel("Title Item 12", "Description, description bla bla bla", 32.99, 10, 3));
            itemList.add(new ItemModel("Title Item 13", "Description, description bla bla bla", 12.99, 10, 3));
            itemList.add(new ItemModel("Title Item 14", "Description, description bla bla bla", 21.99, 10, 4));
            itemList.add(new ItemModel("Title Item 15", "Description, description bla bla bla", 31.99, 10, 4));
            itemList.add(new ItemModel("Title Item 16", "Description, description bla bla bla", 13.99, 10, 4));
            itemList.add(new ItemModel("Title Item 17", "Description, description bla bla bla", 41.99, 10, 4));
            itemList.add(new ItemModel("Title Item 18", "Description, description bla bla bla", 14.99, 10, 4));
            itemList.add(new ItemModel("Title Item 19", "Description, description bla bla bla", 15.99, 10, 5));
            itemList.add(new ItemModel("Title Item 20", "Description, description bla bla bla", 23.99, 10, 5));
            itemList.add(new ItemModel("Title Item 21", "Description, description bla bla bla", 32.99, 10, 5));
            itemList.add(new ItemModel("Title Item 22", "Description, description bla bla bla", 25.99, 10, 5));
            itemList.add(new ItemModel("Title Item 23", "Description, description bla bla bla", 15.99, 10, 5));
            itemList.add(new ItemModel("Title Item 24", "Description, description bla bla bla", 16.99, 10, 5));

            for (int i = 0; i < itemList.size(); i++) {
                boolean isInserted = addItem(itemList.get(i));
                if (!isInserted) {
                    Log.d("Item " + i, "Failed to add item.");
                } else {
                    Log.d("Item " + i, "Added successfully");
                }
            }
        }
    }

    //query to add values to ORDER table
    public boolean addOrder(OrderModel orderModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ORDER_STATUS, orderModel.getOrderStatus());
        cv.put(COLUMN_BUSINESS_ID, orderModel.getBusinessID());
        cv.put(COLUMN_DATE, orderModel.getDate());
        cv.put(COLUMN_CUSTOMER_ID, orderModel.getCustomerID());
        cv.put(COLUMN_ITEM_ID, orderModel.getItemID());
        cv.put(COLUMN_ITEM_QTY, orderModel.getItemQuantity());
        long insert = sqLiteDatabase.insert(ORDER_INFO, null, cv);
        return insert != -1;
    }

    //query to show items data.
    public Cursor itemsDisplay(String businessID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ITEM WHERE BusinessID= ?", new String[]{businessID});
        return cursor;
    }

//    public boolean itemNamePrice(String email, Double itemPrice, String itemName){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT ItemName,ItemValue FROM ITEM WHERE EmailCust= ? ", new String[]{email,itemPrice.toString(),itemName});
//        return cursor.getCount() > 0;
//    }

    //query to update item qty
    public boolean itemsQtyUpdate(int itemID, int itemQty) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT ItemQuantity FROM ITEM WHERE ItemID = ?", new String[]{String.valueOf(itemID)});
        int currentAmount = cursor.getInt(cursor.getColumnIndexOrThrow("ItemQuantity"));
        int finalAmount = currentAmount - itemQty;
        ContentValues values = new ContentValues();
        if (finalAmount < 0) {
            return false;
        } else {
            values.put("ItemQuantity", itemQty);
            int rowsAffected = db.update("ITEM", values, "ItemID = ?", new String[]{String.valueOf(itemID)});
            return true;
        }
    }

    //query to update item value
    public boolean itemsValueUpdate(int itemID, String itemValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ItemValue", itemValue);
        int rowsAffected = db.update("ITEM", values, "ItemID = ?", new String[]{String.valueOf(itemID)});

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

    //Method to show all the orders from a customer
    public Cursor displayOrder(String customerID) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + ORDER_INFO + " WHERE " + COLUMN_CUSTOMER_ID + "=?";
        Cursor cursor = database.rawQuery(query, new String[]{customerID});
        return cursor;
    }

    //Method to display all the orders that a business has
    public Cursor displayOrderBusiness(String businessID) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + ORDER_INFO + " WHERE " + COLUMN_BUSINESS_ID + "=?";
        Cursor cursor = database.rawQuery(query, new String[]{businessID});
        return cursor;
    }

    public Cursor displayPrice(String businessID, int itemID){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ITEM_VALUE
                    + " FROM " + ITEM
                    + " WHERE " + COLUMN_BUSINESS_ID + "=? "
                    + " AND " + COLUMN_ITEM_ID + "=? ";
        Cursor cursor = database.rawQuery(query,new String[]{businessID, String.valueOf(itemID)});
        return cursor;
    }

    public Cursor displayName(String businessID, int itemID){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ITEM_NAME
                + " FROM " + ITEM
                + " WHERE " + COLUMN_BUSINESS_ID + "=? "
                + " AND " + COLUMN_ITEM_ID + "=? ";
        Cursor cursor = database.rawQuery(query,new String[]{businessID, String.valueOf(itemID)});
        return cursor;
    }

    public String displayBusinessName(String businessID, String accountType){
        SQLiteDatabase database = this.getReadableDatabase();
        String businessName = "";
        String query = "SELECT " + COLUMN_NAME
                + " FROM " + CUSTOMER_INFO
                + " WHERE " + COLUMN_CUSTOMER_ID + "=? "
                + " AND " + ACCOUNT_TYPE + "=? ";
        Cursor cursor = database.rawQuery(query,new String[]{businessID, String.valueOf(accountType)});
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                businessName = cursor.getString(0);
            }
        }
        return businessName;
    }

    //Method to display all the restaurants
    public Cursor displayRestaurant(String account) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_CUSTOMER_ID + ", "
                + COLUMN_NAME + ", "
                + COLUMN_ADDRESS + ", "
                + COLUMN_CITY + ", "
                + COLUMN_PROVINCE + ", "
                + COLUMN_PROFILE_IMAGE + " FROM " + CUSTOMER_INFO + " WHERE " + ACCOUNT_TYPE + "=?";
        Cursor cursor = database.rawQuery(query, new String[]{account});
        return cursor;
    }

    public void removeQuantityfromItem(int businessID) {
        SQLiteDatabase database = this.getReadableDatabase();

    }
}

