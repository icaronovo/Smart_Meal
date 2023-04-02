package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int customerType = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String loggedUser = sharedPreferences.getString("CustomerID", "");
        String loggedUserType = sharedPreferences.getString("AccountType", "");
        Log.d("Logged user", loggedUser);

        if (!loggedUser.equals("")) {
            if (loggedUserType.equals("Business")) {
                startActivity(new Intent(MainActivity.this, BusinessMain.class));
            } else {
                startActivity((new Intent(MainActivity.this, CustomerMain.class)));
            }
        }


        Button btnCustomer = findViewById(R.id.btnCustomer);
        Button btnBusiness = findViewById(R.id.btnBusiness);

        btnBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                customerType = 0;
                intent.putExtra("TYPE", customerType);
                startActivity(intent);
            }
        });

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                customerType = 1;
                intent.putExtra("TYPE", customerType);
                startActivity(intent);
            }
        });
    }
}