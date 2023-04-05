package com.example.smart_meal;

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
    CustomerOrderModel model = new CustomerOrderModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        initData();

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("Name", "");
        String email = sharedPreferences.getString("Email","");
        Log.d("TAG", email);

        //TextView for the titles
        titleTextView = findViewById(R.id.topText);
        titleTextView.setText("Welcome " + userName);

        if (!TextUtils.isEmpty(email)) {
            String[] cursor = DB.getUserData(email);
            Log.d("TAG", String.valueOf(cursor));

            if (cursor != null && cursor.length > 0) {

            }
        }
        //Constraint layout where is the Fragments
        constraintLayout = findViewById(R.id.fragmentLayout);

        //Bottom navigation view
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        titleTextView.setText("Welcome " + userName);
                        recyclerView.setVisibility(View.VISIBLE);
                        constraintLayout.setVisibility(View.INVISIBLE);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(CustomerMain.this, CustomerSearch.class));
                        return true;
                    case R.id.order:
                        titleTextView.setText("Order");
                        recyclerView.setVisibility(View.INVISIBLE);
                        constraintLayout.setVisibility(View.VISIBLE);
                        model.setMyString(name);
                        customerOrder.setModel(model);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, customerOrder).commit();
                        return true;
                    case R.id.profile:
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
        launchSomeActivity.launch(intent);
    }

    //Get the data from the Customer Restaurant Activity
    private ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        name = data.getStringExtra("ORDERID");
                        String typeAdd = data.getStringExtra("DELIVERY");
                        model.setMyString(name);
                        customerOrder.setModel(model);
                    }
                }
            });

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
