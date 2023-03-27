package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerHome extends AppCompatActivity {


    Button btnSearch, btnOrderFood, btnOrderDetails,btnOrderHistory,btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        btnSearch = findViewById(R.id.btnSearchRestaurant);
        btnOrderFood = findViewById(R.id.btnOrderFood);
        btnOrderDetails = findViewById(R.id.btnOrderDetails);
        btnOrderHistory = findViewById(R.id.btnOrderHistory);
        btnLogout = findViewById(R.id.btnLogout);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerHome.this,CustomerMain.class));
            }
        });
    }
}