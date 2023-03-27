package com.example.smart_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class CustomerMain extends AppCompatActivity implements ItemAdapter.ItemClickListener {

    BottomNavigationView bottomNavigationView;
    ArrayList<ItemModel> itemList;
    RecyclerView recyclerView;
    ImageView imageView;
    ItemAdapter itemAdapter;
    TextView titleText;
    CustomerProfileFragment customerProfileFragment = new CustomerProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        //Recycler view
        androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        int numOfColumns = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumns));
        itemAdapter = new ItemAdapter(this, initData());
        itemAdapter.setClickListener(this);
        recyclerView.setAdapter(itemAdapter);

        //Top text
        titleText = findViewById(R.id.topText);
        titleText.setText("Welcome User");

        ConstraintLayout constraintLayout = findViewById(R.id.fragmentLayout);

        //Bottom naviagation view
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        titleText.setText("Welcome User");
                        recyclerView.setVisibility(View.VISIBLE);
                        constraintLayout.setVisibility(View.INVISIBLE);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(CustomerMain.this, CustomerSearch.class));
                        return true;
                    case R.id.order:
                        titleText.setText("Order");
                        recyclerView.setVisibility(View.INVISIBLE);
                        return true;
                    case R.id.profile:
                        titleText.setText("Account");
                        constraintLayout.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout,customerProfileFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    public void onItemClick(View view, int position) {
        //Test to see if the items on recycler view is clickable
        Intent intent = new Intent(CustomerMain.this, CustomerSearch.class);
        intent.putExtra("TEST", position);
        startActivity(intent);
        //End
    }

    //Created a list to tryout the recyclervier
    //Latter figure out how to import from database :)
    private List<ItemModel> initData() {
        itemList = new ArrayList<>();
        itemList.add(new ItemModel("Title Item", R.drawable.ic_launcher_background,"Description, description bla bla bla",  9.99));
        itemList.add(new ItemModel("Title Item", R.drawable.ic_launcher_background,"Description, description bla bla bla",  9.99));
        itemList.add(new ItemModel("Title Item", R.drawable.ic_launcher_background,"Description, description bla bla bla",  9.99));
        itemList.add(new ItemModel("Title Item", R.drawable.ic_launcher_background,"Description, description bla bla bla",  9.99));
        itemList.add(new ItemModel("Title Item", R.drawable.ic_launcher_background,"Description, description bla bla bla",  9.99));
        itemList.add(new ItemModel("Title Item", R.drawable.ic_launcher_background,"Description, description bla bla bla",  9.99));
        itemList.add(new ItemModel("Title Item", R.drawable.ic_launcher_background,"Description, description bla bla bla",  9.99));
        itemList.add(new ItemModel("Title Item", R.drawable.ic_launcher_background,"Description, description bla bla bla",  9.99));
        itemList.add(new ItemModel("Title Item", R.drawable.ic_launcher_background,"Description, description bla bla bla",  9.99));
        itemList.add(new ItemModel("Title Item", R.drawable.ic_launcher_background,"Description, description bla bla bla",  9.99));
        return itemList;
    }
}
