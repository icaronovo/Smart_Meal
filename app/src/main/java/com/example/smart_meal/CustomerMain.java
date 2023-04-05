package com.example.smart_meal;

// Importing necessary packages
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CustomerMain extends AppCompatActivity implements ItemAdapter.ItemClickListener {

    private BottomNavigationView bottomNavigationView;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private TextView titleTextView;
    private CustomerProfileFragment customerProfile = new CustomerProfileFragment();
    private CustomerOrderMFragment customerOrder = new CustomerOrderMFragment();
    private SharedPreferences sharedPreferences;
    private ConstraintLayout constraintLayout;
    private List<Integer> restaurantsIdDisplay = new ArrayList<>();
    private String name = "";
    DBHelper DB = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting the layout file for this activity
        setContentView(R.layout.activity_customer_main);

        // Initializing the data needed for this activity
        initData();

        // Setting up SharedPreferences
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("Name", "");
        String email = sharedPreferences.getString("Email","");
        Log.d("TAG", email);

        // Setting the title text view
        titleTextView = findViewById(R.id.topText);
        titleTextView.setText("Welcome " + userName);

        // If email is not empty, then get the user data from the database
        if (!TextUtils.isEmpty(email)) {
            String[] cursor = DB.getUserData(email);
            Log.d("TAG", String.valueOf(cursor));

            if (cursor != null && cursor.length > 0) {

            }
        }

        // Initializing the ConstraintLayout for the fragments
        constraintLayout = findViewById(R.id.fragmentLayout);

        // Initializing the bottom navigation view
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        // When home is clicked, set the title and visibility of views
                        titleTextView.setText("Welcome, " + userName);
                        recyclerView.setVisibility(View.VISIBLE);
                        constraintLayout.setVisibility(View.INVISIBLE);
                        return true;
                    case R.id.search:
                        // When search is clicked, start a new activity
                        startActivity(new Intent(CustomerMain.this, CustomerSearch.class));
                        return true;
                    case R.id.order:
                        // When order is clicked, set the title and visibility of views and replace fragment
                        titleTextView.setText("Order");
                        recyclerView.setVisibility(View.INVISIBLE);
                        constraintLayout.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, customerOrder).commit();
                        return true;
                    case R.id.profile:
                        // When profile is clicked, set the title and visibility of views and replace fragment
                        titleTextView.setText("Account");
                        constraintLayout.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, customerProfile).commit();
                        return true;
                }
                return false;
            }
        });
    }

    //Method for the creation of the recyclerView displaying products
    private void createRecyclerView(ArrayList<CustomerModel> displayRestaurant) {
        recyclerView = findViewById(R.id.mRecyclerView);
        int numOfColumns = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumns));
        itemAdapter = new ItemAdapter(this, displayRestaurant);
        itemAdapter.setClickListener(this);
        recyclerView.setAdapter(itemAdapter);
    }

    //Recycle view  is clickable. When you click in, you go to the restaurant page
    //And when you click on the confirm order
    //It brings you back here
    @Override
    public void onItemClick(View view, int position) {
        // The launcher with the Intent you want to start
        Intent intent = new Intent(CustomerMain.this, CustomerRestaurant.class);
        intent.putExtra("RESTAURANTID",restaurantsIdDisplay.get(position));
        startActivity(intent);
    }

    //Created a list of the recyclerview
    //Latter figure out how to import from database :)
    private void initData() {
        //Get the orders from the customer
        String accountType = "Business";
        Cursor c = DB.displayRestaurant(accountType);
        Boolean hasRestaurant = updateRestaurant(c,accountType);

        if (hasRestaurant == true) {
            recyclerView.setVisibility(View.VISIBLE);
        } else{
            recyclerView.setVisibility(View.INVISIBLE);
        }
        c.close();
    }

    //Get the restaurants from DB
    public boolean updateRestaurant(Cursor c, String businessID) {
        List<String> list = new ArrayList<>();
        //CustomerID, Name, Address, City, Province, Profile
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                list.add(c.getString(0)); //CustomerID
                list.add(c.getString(1)); //Name
                list.add(c.getString(2)); //Address
                list.add(c.getString(3)); //City
                list.add(c.getString(4)); //Province
                list.add(c.getString(5)); //Profile image
            }
        } else {
            return false;
        }
        addRestaurant(list);
        return true;
    }

    public void addRestaurant(List<String> list) {
        Stack<CustomerModel> stackRestaurants = new Stack<>();
        //Make the data being add into the list
        int index = 0;
        while (index < list.size()) {
            CustomerModel restaurant = new CustomerModel(
                    Integer.parseInt(list.get(index)), //UserID -  ARRAY 0
                    list.get(index + 1), //Name ARRAY 1
                    list.get(index + 2), //Address ARRAY 2
                    list.get(index + 3),//City ARRAY 3
                    list.get(index + 4), //Province ARRAY 4
                    Integer.parseInt(list.get(index + 5)) //Image ARRAY  5
            );
            stackRestaurants.push(restaurant);
            index += 6;
        }

        ArrayList<CustomerModel> firstInLastOut = new ArrayList<>();
        while(!stackRestaurants.isEmpty()){
            CustomerModel restaurant = stackRestaurants.pop();
            restaurantsIdDisplay.add(restaurant.getCustomerID());
            firstInLastOut.add(restaurant);
        }
        createRecyclerView(firstInLastOut);
    }
}
