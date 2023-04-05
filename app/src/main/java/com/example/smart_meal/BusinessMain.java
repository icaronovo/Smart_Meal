package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BusinessMain extends AppCompatActivity {
    Toolbar toolbar;
    Button btnReport,btnLogout,btnOrders, btnReminder, btnAddFoodItem;

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_main);

        btnReport = findViewById(R.id.btnReports);
        btnLogout = findViewById(R.id.btnLogout);
        btnOrders = findViewById(R.id.btnOrdersFromClient);
        btnReminder =findViewById(R.id.btnReminder);
        btnAddFoodItem = findViewById(R.id.btnAddFoodItem);

        DB = new DBHelper(this);
        //Top menu
        toolbar = findViewById(R.id.toolbarBusiness);
        setSupportActionBar(toolbar);

        //Add food item
        btnAddFoodItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessMain.this, BusinessItems.class));
            }
        });

        //Confirm orders
        btnOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessMain.this,BusinessOrders.class));
            }
        });

        //Send reminders
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessMain.this,BusinessOrders.class));
            }
        });

        //Reports
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessMain.this,BusinessReport.class));
            }
        });

        //Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                String[] columns = {"CustomerID", "AccountType", "EmailCust", "PasswordCust", "Name", "Phone", "Address", "City", "Province"};

                for (int i = 0; i < columns.length; i++) {
                    editor.putString(columns[i], "");
                    editor.apply();
                    Log.d(columns[i], "");
                }
                DB.close();
                startActivity(new Intent(BusinessMain.this,LoginActivity.class));
            }
        });

    }

    //When creating the other activities from the Business Part, if you want to put the top menu
    //Insert all the bottom code with the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu_business, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profile) {
            Intent intent = new Intent(this, BusinessProfile.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}