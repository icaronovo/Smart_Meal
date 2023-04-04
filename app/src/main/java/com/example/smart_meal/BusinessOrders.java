package com.example.smart_meal;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class BusinessOrders extends AppCompatActivity {
    DBHelper DB;
    private BusinessAdapter adapter;
    private CheckBox checkBox;
//    private ArrayList<OrderModel> listItems;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_orders);

        checkBox = findViewById(R.id.checkboxSelectAll);
        listView = findViewById(R.id.listViewBusiness);

        //Start the database
        DB = new DBHelper(this);

        //Get business ID
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String businessID = sharedPreferences.getString("CustomerID", "");

        //Get the orders from the customer
        Cursor c = DB.displayOrderBusiness(businessID);

        //Check if the customer has orders
        Boolean hasNoData = updateData(c, businessID);
        if (hasNoData == true) {
            checkBox.setText("You have no order");
        } else{
            checkBox.setText("Select all orders");
        }
        c.close();
    }

    //Get the data from DB
    //If the customer doesn't has any data
    //It will show that he has no data
    public boolean updateData(Cursor c, String businessID) {
        List<String> list = new ArrayList<>();
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                list.add(c.getString(0)); //OrderID
                list.add(c.getString(1)); //OrderStatus
                list.add(c.getString(2)); //ItemID
                list.add(c.getString(3)); //Date
                list.add(c.getString(4)); //ItemQty
                list.add(c.getString(5)); //CustomerID
            }
        } else {
            return true;
        }
        addDataListModel(list, businessID);
        return false;
    }

    //In case the customer has orders
    //Makes a OrderModel object for display
    public void addDataListModel(List<String> list, String businessID) {
        Stack<OrderModel> stackItems = new Stack<>();
        //Make the data being add into the list
        int index = 0;
        while (index < list.size()) {
            OrderModel order = new OrderModel(
                    Integer.parseInt(list.get(index)), //OrderID -  ARRAY 0
                    Integer.parseInt(list.get(index + 1)), //OrderStatus ARRAY 1
                    Integer.parseInt(businessID), //BusinessID
                    Integer.parseInt(list.get(index + 5)),//CustomerID ARRAY 5
                    list.get(index + 3), //DATE ARRAY 3
                    list.get(index + 2), //ItemID ARRAY 2
                    list.get(index + 4)// ItemQty ARRAY 4
            );
            stackItems.push(order);
            index += 6;
        }

        ArrayList<OrderModel> firstInLastOut = new ArrayList<>();
        while(!stackItems.isEmpty()){
            firstInLastOut.add(stackItems.pop());
        }
        createListView(firstInLastOut);
    }

    //Create the view on Listview
    public void createListView(ArrayList<OrderModel> listItems) {
        adapter = new BusinessAdapter(this, listItems);
        listView.setAdapter(adapter);
    }

}
