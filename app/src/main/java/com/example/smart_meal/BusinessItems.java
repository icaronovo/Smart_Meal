package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BusinessItems extends AppCompatActivity {
    private Button btnAddItem;
    private androidx.appcompat.widget.Toolbar toolbar;
    private DBHelper DB;
    private SharedPreferences sharedPreferences;
    private BusinessItemAdapter adapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_items);

        //Toolbar
        toolbar = findViewById(R.id.toolbarAddOne);
        toolbar.setTitle("Add Items");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reorder_w);
        // Handle navigation icon click event on toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click here
                onBackPressed();
            }
        });

        //Get the items already added
        //Start the db
        DB = new DBHelper(this);
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        int businessID = Integer.parseInt(sharedPreferences.getString("CustomerID", ""));

        //Get the items of this restaurant
        Cursor c = DB.itemsDisplay(String.valueOf(businessID));
        Boolean hasNoItems = getItems(c);
        if(hasNoItems == true){
            Toast.makeText(BusinessItems.this,"No",Toast.LENGTH_LONG).show();
        }
        c.close();

        btnAddItem = findViewById(R.id.btnAddNewItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessItems.this,AddFoodItem.class));
            }
        });

    }
    //Get the data from DB
    //If the customer doesn't has any data
    //It will show that he has no data
    public boolean getItems(Cursor c) {
        List<String> list = new ArrayList<>();
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                list.add(c.getString(0)); //ItemID
                list.add(c.getString(1)); //ItemValue
                list.add(c.getString(2)); //ItemQuantity
                list.add(c.getString(3)); //ItemName
                list.add(c.getString(4)); //ItemDescription
                list.add(c.getString(5)); //BusinessID
            }
        } else {
            return true;
        }
        addData(list);
        return false;
    }

    //In case the customer has orders
    //Makes a ItemModel object for display
    public void addData(List<String> list) {
        Stack<ItemModel> stackItems = new Stack<>();
        //Make the data being add into the list
        int index = 0;
        while (index < list.size()) {
            ItemModel item = new ItemModel(
                    Integer.parseInt(list.get(index)), //itemID -  ARRAY 0
                    list.get(index + 3), //itemName ARRAY 3
                    list.get(index + 4), //itemDescription
                    Double.parseDouble(list.get(index + 1)),//itemPrice itemPrice 5
                    Integer.parseInt(list.get(index + 2)), //itemQuantity
                    Integer.parseInt(list.get(index + 5)) //businessID
            );
            stackItems.push(item);
            index += 6;
        }
        ArrayList<ItemModel> firstInLastOut = new ArrayList<>();
        while (!stackItems.isEmpty()) {
            firstInLastOut.add(stackItems.pop());
        }
        createListView(firstInLastOut);
    }

    //Create the view on Listview
    public void createListView(ArrayList<ItemModel> listItems) {
        ListView listView = findViewById(R.id.listViewAdd);
        BusinessItemAdapter adapter = new BusinessItemAdapter(listItems);
        listView.setAdapter(adapter);
    }

}