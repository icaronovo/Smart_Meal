package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerSearch extends AppCompatActivity implements CustomerSearchRecyclerViewInterface {
    private ArrayList<CustomerModel> restaurantList = new ArrayList<CustomerModel>();

    private DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search);

        ArrayList<CustomerModel> businessList;
        businessList = dbHelper.retrieveAllBusinesses();

        RecyclerView recyclerView = findViewById(R.id.customerSearchRecyclerView);

        CustomerSearchAdapter adapter = new CustomerSearchAdapter(this, businessList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onSelectCustomerSearch(int position) {
        Log.d("Position", String.valueOf(position));
        Intent intent = new Intent(CustomerSearch.this, CustomerRestaurant.class);
        DBHelper DB = new DBHelper(this);
        Log.d("Email", restaurantList.get(position).getCustomerEmail());
        int businessID = DB.getBusinessIDFromDB(restaurantList.get(position).getCustomerEmail());
        Log.d("BusinessID", String.valueOf(businessID));
        if (businessID > -1) {
            intent.putExtra("RESTAURANTID", businessID);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(CustomerSearch.this, "Restaurant not found", Toast.LENGTH_LONG).show();
        }
    }
}