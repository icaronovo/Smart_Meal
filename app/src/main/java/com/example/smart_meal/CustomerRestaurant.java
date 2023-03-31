package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class CustomerRestaurant extends AppCompatActivity implements Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_restaurant);


        //Test to see if the Recycler View is clickable
        TextView restaurantDesc = findViewById(R.id.txtRestDesc);
        Intent intent = getIntent();
        int restaurantId = getIntent().getIntExtra("RESTAURANTID",-1);
        restaurantDesc.setText("Restaurant selected is " + restaurantId);
        //End test
    }

    @Override
    public void respond(HashMap<String,Integer> data) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        CustomerOrderFragment customerOrderFragment = (CustomerOrderFragment)
                fragmentManager.findFragmentById(R.id.selectedOrder);
        customerOrderFragment.changeText(String.valueOf(data));
    }
}