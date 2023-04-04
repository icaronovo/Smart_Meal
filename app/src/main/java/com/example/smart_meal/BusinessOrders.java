package com.example.smart_meal;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BusinessOrders extends ListActivity {
    private ListView listView;
    private MyAdapter adapter;
    public TextView order;
    private Cursor orders;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_business_orders);
        order = findViewById(R.id.text_view);

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String customerID = sharedPreferences.getString("CustomerID", "");

        DB = new DBHelper(this);
        Cursor cursor = DB.displayOrder(customerID);
        List<String> orders = getData(cursor);
        cursor.close();

    }

    public List<String> getData(Cursor c) {
        List<String> dataFromDb = new ArrayList<>();
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                dataFromDb.add("\nOrderID# " + c.getString(0)); //OrderID
                dataFromDb.add("\nOrder Status: " + c.getString(1)); //OrderStatus
                dataFromDb.add("\nTotal Order: " + c.getString(2)); //TotalOrder
                dataFromDb.add("\nDate: " + c.getString(3)); //Date
                dataFromDb.add("\nItemID: " + c.getString(4)); //ItemID
                dataFromDb.add("\nItem Value: " + c.getString(5)); //ItemValue
                dataFromDb.add("\nItem Qty: " + c.getString(6)); //ItemQty
                dataFromDb.add("\nBusinessID: " + c.getString(7)); //BusinessID
                dataFromDb.add("\nCustomerID: " + c.getString(8)); //CustomerID
            }
        } else {
            order.setText("No data");
        }
        order.setText(String.valueOf(dataFromDb));
        return dataFromDb;
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 4:
                break;
        }
    }

}
