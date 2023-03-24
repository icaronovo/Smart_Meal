package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CustomerRestaurant extends AppCompatActivity {

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
}