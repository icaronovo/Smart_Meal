package com.example.smart_meal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //creating db
    public static final String SMART_MEAL_DB = "SmartMealDB";
    public static final int dbVersion = 7;

    //creating customer table and fields
    public static final String CUSTOMER = "CUSTOMER";
    public static final String COLUMN_CUSTOMER_ID = "CustomerID";
    public static final String COLUMN_EMAIL_CUST = "EmailCust";
    public static final String COLUMN_PASSWORD_CUST = "PasswordCust";

    //creating business table and fields
    public static final String BUSINESS = "BUSINESS";
    public static final String COLUMN_BUSINESS_ID = "BusinessID";
    public static final String COLUMN_EMAIL_BUS = "EmailBus";
    public static final String COLUMN_PASSWORD_BUS = "PasswordBus";

    //creating item table and fields
    public static final String ITEM = "ITEM";
    public static final String COLUMN_ITEM_ID = "ItemID";

    public static final String COLUMN_ITEM_NAME = "ItemName";
    public static final String COLUMN_ITEM_DESCRIPTION = "ItemDescription";

    //creating order status table and fields
    public static final String ORDER_STATUS = "ORDER_STATUS";
    public static final String COLUMN_ORDER_ID = "OrderID";

    //creating order_info table and fields
    public static final String ORDER_INFO = "ORDER_INFO";
    public static final String COLUMN_ORDER_STATUS1 = "OrderStatus";
    public static final String COLUMN_ITEM_VALUE = "ItemValue";
    public static final String COLUMN_ITEM_QTY = "ItemQty";

    //creating business info table and fields
    public static final String BUSINESS_INFO = "BUSINESS_INFO";
    public static final String COLUMN_BUSINESS_NAME = "BusinessName";
    public static final String COLUMN_BUS_STATE_PROVINCE = "State_Province";
    public static final String COLUMN_STATE_PROVINCE = COLUMN_BUS_STATE_PROVINCE;
    public static final String COLUMN_BUS_CITY = "City";
    public static final String COLUMN_BUS_ADDRESS = "Address";
    public static final String COLUMN_PHONE_BUS = "PhoneBus";
    //creating customer info table and fields
    public static final String CUSTOMER_INFO = "CUSTOMER_INFO";
    public static final String COLUMN_LAST_NAME = "LastName";
    public static final String COLUMN_FIRST_NAME = "FirstName";
    public static final String COLUMN_PHONE_CUST = "Phone";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_CITY = "City";


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
        super(context, SMART_MEAL_DB, null, dbVersion);
    }
    //this is called the first time a database is accessed. there should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createTable = "CREATE TABLE BUSINESS(BusinessID INTEGER PRIMARY KEY AUTOINCREMENT, EmailBus TEXT)"
        String createTableItem = "CREATE TABLE " + ITEM + "("
                + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ITEM_VALUE + " INTEGER, "
                + COLUMN_ITEM_NAME + " TEXT, "
                + COLUMN_ITEM_DESCRIPTION + " TEXT)";
        db.execSQL(createTableItem);
        String createTableOrderStatus = "CREATE TABLE " + ORDER_STATUS + "("
                + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ORDER_STATUS1 + " TEXT)";
        db.execSQL(createTableOrderStatus);
        String createTableCustomerInfo = "CREATE TABLE " + CUSTOMER_INFO + "("
                + COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EMAIL_CUST + " TEXT, "
                + COLUMN_FIRST_NAME + " TEXT, "
                + COLUMN_LAST_NAME + " TEXT, "
                + COLUMN_PHONE_CUST + " INTEGER, "
                + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_CITY + " TEXT, "
                + COLUMN_STATE_PROVINCE + " TEXT, "
                + COLUMN_PASSWORD_CUST + " TEXT)";
        db.execSQL(createTableCustomerInfo);
        String createTableBusinessInfo = "CREATE TABLE " + BUSINESS_INFO + "("
                + COLUMN_BUSINESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EMAIL_BUS + " TEXT, "
                + COLUMN_BUSINESS_NAME + " TEXT, "
                + COLUMN_PHONE_BUS + " INTEGER, "
                + COLUMN_BUS_ADDRESS + " TEXT, "
                + COLUMN_BUS_CITY + " TEXT, "
                + COLUMN_BUS_STATE_PROVINCE + " TEXT, "
                + COLUMN_PASSWORD_BUS + " TEXT)";
        db.execSQL(createTableBusinessInfo);
        String createTableOrderInfo = "CREATE TABLE " + ORDER_INFO + "("
                + COLUMN_ORDER_ID + " INTEGER references " + ORDER_STATUS + "(" + COLUMN_ORDER_ID + "),"
                + COLUMN_ITEM_ID + " INTEGER references " + ITEM + "(" + COLUMN_ITEM_ID + "),"
                + COLUMN_ITEM_QTY + " INTEGER, "
                + COLUMN_ITEM_VALUE + " INTEGER, "
                + COLUMN_ORDER_STATUS1 + " TEXT, "
                + COLUMN_CUSTOMER_ID + " INTEGER, "
                + COLUMN_BUSINESS_ID + " INTEGER)";
        db.execSQL(createTableOrderInfo);
        String createTableCustomer = " CREATE TABLE " + CUSTOMER + "("
                + COLUMN_EMAIL_CUST + " TEXT, "
                + COLUMN_PASSWORD_CUST + " TEXT, "
                + COLUMN_CUSTOMER_ID + " INTEGER, "+ " FOREIGN KEY (" + COLUMN_CUSTOMER_ID + ") references "
                + CUSTOMER_INFO + "(" + COLUMN_CUSTOMER_ID + ") "+ "ON DELETE CASCADE ON UPDATE CASCADE );";
        db.execSQL(createTableCustomer);
        String createTableBusiness = " CREATE TABLE " + BUSINESS + " ("
                + COLUMN_EMAIL_BUS + " TEXT, "
                + COLUMN_PASSWORD_BUS + " TEXT, "
                + COLUMN_BUSINESS_ID + " INTEGER,"+ " FOREIGN KEY (" + COLUMN_BUSINESS_ID + ") references "
                + BUSINESS_INFO + "(" + COLUMN_BUSINESS_ID + ") "+ "ON DELETE CASCADE ON UPDATE CASCADE );";
        db.execSQL(createTableBusiness);
    }

    // this is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    //CREATING TABLES OR DROPPING IF IT EXISTS
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists CUSTOMER");
        db.execSQL("drop table if exists BUSINESS");
        db.execSQL("drop table if exists ITEM");
        db.execSQL("drop table if exists ORDER_STATUS");
        db.execSQL("drop table if exists CUSTOMER_INFO");
        db.execSQL("drop table if exists BUSINESS_INFO");
        db.execSQL("drop table if exists ORDER_INFO");
        onCreate(db);
    }
//    METHOD TO ADD A NEW USER, using just email and password.
//    public boolean insertUser(String COLUMN_EMAIL_CUST, String COLUMN_PASSWORD_CUST) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("username", COLUMN_EMAIL_CUST);
//        contentValues.put("password", COLUMN_PASSWORD_CUST);
//        long result = db.insert(CUSTOMER, null, contentValues);
//        return result != -1;
//    }
//
//    //METHOD TO ADD A NEW BUSINESS
//    public boolean insertBusiness(String COLUMN_EMAIL_BUS, String COLUMN_PASSWORD_BUS) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("username", COLUMN_EMAIL_BUS);
//        contentValues.put("password", COLUMN_PASSWORD_BUS);
//        long result = db.insert(BUSINESS, null, contentValues);
//        return result != -1;
//    }

//    checking if the user already exists in the database, tried to run, query error
//    public boolean checkUser(String COLUMN_EMAIL_CUST) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("Select * from  " +  CUSTOMER + " where  COLUMN_EMAIL_CUST = ? ", new String[]{COLUMN_EMAIL_CUST});
//        cursor.close();
//        return cursor.getCount() > 0;
//    }
//    public boolean checkBus(String COLUMN_EMAIL_BUS) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("Select * from " + BUSINESS + " where COLUMN_EMAIL_BUS = ? ", new String[]{COLUMN_EMAIL_BUS});
//        cursor.close();
//        return cursor.getCount() > 0;
//    }

    //CHECKING IF PASSWORD ALREADY EXISTS IN THE DATABASE
//    public boolean checkUserPassword(String COLUMN_EMAIL_CUST, String COLUMN_PASSWORD_CUST, String COLUMN_EMAIL_BUS, String COLUMN_PASSWORD_BUS) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("Select * from users where username = ? and password = ?", new String[]{COLUMN_EMAIL_CUST, COLUMN_PASSWORD_CUST, COLUMN_EMAIL_BUS, COLUMN_PASSWORD_BUS});
//        cursor.close();
//        return cursor.getCount() > 0;
//    }

    //method to add customer data in the database
    public boolean addCustomer(CustomerModel customerModel) {
        //writing data in the database
        SQLiteDatabase db = this.getWritableDatabase();
        //linking data from getters to database fields
        ContentValues cv = new ContentValues();
        //cv.put(COLUMN_CUSTOMER_ID,customerModel.getId());
        cv.put(COLUMN_FIRST_NAME, customerModel.getFirstName());
        cv.put(COLUMN_LAST_NAME, customerModel.getLastName());
        cv.put(COLUMN_EMAIL_CUST, customerModel.getCustomerEmail());
        cv.put(COLUMN_PHONE_CUST, customerModel.getCustomerPhone());
        cv.put(COLUMN_ADDRESS, customerModel.getCustomerAddress());
        cv.put(COLUMN_CITY, customerModel.getCustomerCity());
        cv.put(COLUMN_STATE_PROVINCE, customerModel.getCustomerState());
        cv.put(COLUMN_PASSWORD_CUST, customerModel.getPassword());
        // inserting data above
        long insert = db.insert(CUSTOMER_INFO, null, cv);
        return insert != -1;
    }

    //adding business data to database.
    public boolean addBusiness(BusinessModel businessModel) {
        //writing data in the database
        SQLiteDatabase db = this.getWritableDatabase();
        //linking data from getters to database fields
        ContentValues cv = new ContentValues();
        //cv.put(COLUMN_BUSINESS_ID,businessModel.getId());
        cv.put(COLUMN_BUSINESS_NAME, businessModel.getBusinessName());
        cv.put(COLUMN_BUS_ADDRESS, businessModel.getBusinessAddress());
        cv.put(COLUMN_BUS_CITY, businessModel.getBusinessCity());
        cv.put(COLUMN_EMAIL_BUS, businessModel.getBusinessEmail());
        cv.put(COLUMN_BUS_STATE_PROVINCE, businessModel.getBusinessState());
        cv.put(COLUMN_PHONE_BUS, businessModel.getBusinessPhone());
        cv.put(COLUMN_PASSWORD_BUS, businessModel.getBusPassword());
        long insert = db.insert(BUSINESS_INFO, null, cv);
        return insert != -1;
    }
}


