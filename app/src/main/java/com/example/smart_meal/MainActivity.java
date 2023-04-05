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

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String loggedUser = sharedPreferences.getString("CustomerID", "");
        String loggedUserType = sharedPreferences.getString("AccountType", "");
        Log.d("Logged user", loggedUser);
        DB = new DBHelper(this);

        DB.addSeveralBusinesses();
        DB.retrieveAllBusinesses();
        DB.insertSeveralItems();
        DB.insertSeveralOrders();

        if (!loggedUser.equals("")) {
            if (loggedUserType.equals("Business")) {
                startActivity(new Intent(MainActivity.this, BusinessMain.class));
            } else {
                startActivity((new Intent(MainActivity.this, CustomerMain.class)));
            }
        } else {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

    }
}