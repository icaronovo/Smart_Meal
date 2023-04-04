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
    private RecyclerView recyclerView;

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


    private ArrayList<CustomerModel> restaurantList = new ArrayList<CustomerModel>();

    private ItemAdapter itemAdapter;
    private TextView titleTextView;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search);

        restaurantList.add(business1);
        restaurantList.add(business2);
        restaurantList.add(business3);
        restaurantList.add(business4);
        restaurantList.add(business5);
        restaurantList.add(business6);
        restaurantList.add(business7);
        restaurantList.add(business8);
        restaurantList.add(business9);
        restaurantList.add(business10);

        ArrayList<CustomerModel> businessList = new ArrayList<CustomerModel>();

//        SQLiteDatabase db = get
//        Cursor cursor = db.rawQuery("SELECT * FROM CUSTOMER_INFO WHERE AccountType = ?", new String[] {"Business"});
//        Log.d("cursor", cursor.toString());
//
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(cursor.getColumnIndexOrThrow("CustomerID"));
//            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
//            String email = cursor.getString(cursor.getColumnIndexOrThrow("EmailCust"));
//            String phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
//            String address = cursor.getString(cursor.getColumnIndexOrThrow("Address"));
//            String city = cursor.getString(cursor.getColumnIndexOrThrow("City"));
//            String province = cursor.getString(cursor.getColumnIndexOrThrow("Province"));
//            int image = cursor.getInt(cursor.getColumnIndexOrThrow("ProfileImage"));
//            CustomerModel customer = new CustomerModel(id, name, email, phone, address, city, province, image);
//            businessList.add(customer);
//            Log.d("Business", name);
//        }

        RecyclerView recyclerView = findViewById(R.id.customerSearchRecyclerView);

        CustomerSearchAdapter adapter = new CustomerSearchAdapter(this, restaurantList, this);
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
        } else {
            Toast.makeText(CustomerSearch.this, "Restaurant not found", Toast.LENGTH_LONG).show();
        }
    }
}