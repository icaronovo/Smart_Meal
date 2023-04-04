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

public class CustomerMain extends AppCompatActivity implements ItemAdapter.ItemClickListener {

    private BottomNavigationView bottomNavigationView;
    private ArrayList<ItemModel> itemList;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private TextView titleTextView;
    private CustomerProfileFragment customerProfile = new CustomerProfileFragment();
    private CustomerOrderMFragment customerOrder = new CustomerOrderMFragment();
    private SharedPreferences sharedPreferences;
    private ConstraintLayout constraintLayout;
    private String name = "";

    DBHelper DB = new DBHelper(this);
    CustomerOrderModel model = new CustomerOrderModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        createRecyclerView();

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
    private void createRecyclerView() {
        recyclerView = findViewById(R.id.mRecyclerView);
        int numOfColumns = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumns));
        itemAdapter = new ItemAdapter(this, initData());
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
    private List<ItemModel> initData() {
        itemList = new ArrayList<>();
        //String itemName, int itemImage, String itemDescription, Double itemPrice
        itemList.add(new ItemModel("Title Restaurant", "Description, description bla bla bla", 9.99));
        itemList.add(new ItemModel("Title Restaurant 1", "Description, description bla bla bla", 19.99));
        itemList.add(new ItemModel("Title Restaurant 2", "Description, description bla bla bla", 92.99));
        itemList.add(new ItemModel("Title Restaurant 3", "Description, description bla bla bla", 39.99));
        itemList.add(new ItemModel("Title Restaurant 5", "Description, description bla bla bla", 49.99));
        itemList.add(new ItemModel("Title Restaurant 6", "Description, description bla bla bla", 59.99));
        itemList.add(new ItemModel("Title Restaurant 7", "Description, description bla bla bla", 39.99));
        return itemList;
    }
}
