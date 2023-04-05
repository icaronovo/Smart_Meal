package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessOrderList extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this);
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_order_list);
        toolbar = findViewById(R.id.toolbarOrder);
        setSupportActionBar(toolbar); // set the toolbar as the action bar for the activity
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reorder_w);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click here
                onBackPressed();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE); // retrieve the shared preferences object from the context
        String businessID = sharedPreferences.getString("CustomerID", "");

        Cursor cursor = dbHelper.displayOrderBusiness(businessID);
        ArrayList<String> allOrders = null;
        ArrayList<OrderModel> orders;
//
//        while (cursor.moveToFirst() && cursor.moveToNext()) {
//            int orderID = cursor.getInt(cursor.getColumnIndexOrThrow("OrderID"));
//            int orderStatus = cursor.getInt(cursor.getColumnIndexOrThrow("OrderStatus"));
//            String itemID = cursor.getString(cursor.getColumnIndexOrThrow("ItemID"));
//            String date = cursor.getString(cursor.getColumnIndexOrThrow("Date"));
//            String itemQuantity = cursor.getString(cursor.getColumnIndexOrThrow("ItemQuantity"));
//            int businessId = cursor.getInt(cursor.getColumnIndexOrThrow("BusinessID"));
//            int customerID = cursor.getInt(cursor.getColumnIndexOrThrow("CustomerID"));
//            allOrders.add("Order ID: " + String.valueOf(orderID)
//                + "\nOrder status"
//            );
//        }



        ListView listView = findViewById(R.id.ListView);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,);



    }
}