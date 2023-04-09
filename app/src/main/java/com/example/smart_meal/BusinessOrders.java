package com.example.smart_meal;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class BusinessOrders extends AppCompatActivity {
    private DBHelper DB; // instance of DBHelper class
    private BusinessAdapter adapter; // instance of BusinessAdapter class
    private CheckBox checkBox; // instance of CheckBox class
    private ListView listView; // instance of ListView class
    private androidx.appcompat.widget.Toolbar toolbar; // instance of Toolbar class
    private ArrayList<OrderModel> firstInLastOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_orders);

        checkBox = findViewById(R.id.checkboxSelectAll);
        listView = findViewById(R.id.listViewBusiness);
        toolbar = findViewById(R.id.toolbarOrder); // retrieve the toolbar object from the activity layout
        setSupportActionBar(toolbar); // set the toolbar as the action bar for the activity
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reorder_w); // set the navigation icon for the toolbar

        // Handle navigation icon click event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click here
                onBackPressed();
            }
        });

        //Start the database
        DB = new DBHelper(this); // instantiate the DBHelper class

        //Get business ID
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE); // retrieve the shared preferences object from the context
        String businessID = sharedPreferences.getString("CustomerID", ""); // retrieve the business ID from the shared preferences

        //Get the orders from the customer
        Cursor c = DB.displayOrderBusiness(businessID); // retrieve the orders for the business from the database

        //Check if the customer has orders
        Boolean hasNoData = updateData(c, businessID); // update the data in the activity
        if (hasNoData == true) {
            checkBox.setText("You have no orders");
        } else{
            checkBox.setText("Select all orders");
        }
        c.close(); // close the cursor

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.checkAll();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // handle back button click event
        return true;
    }

    //Get the data from DB
    //If the customer doesn't has any data
    //It will show that he has no data
    public boolean updateData(Cursor c, String businessID) {
        List<String> list = new ArrayList<>(); // create a new array list for storing the orders
        if (c.getCount() > 0) { // if there are orders in the cursor
            while (c.moveToNext()) { // loop through the cursor to retrieve the orders
                list.add(c.getString(0)); // add the order ID to the list
                list.add(c.getString(1)); // add the order status to the list
                list.add(c.getString(2)); // add the item ID to the list
                list.add(c.getString(3)); // add the date to the list
                list.add(c.getString(4)); // add the item quantity
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
