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
import android.widget.TextView;

public class BusinessProfile extends AppCompatActivity {
    Toolbar toolbar;
    DBHelper DB;
    TextView busName, busEmail, busPhone, busAddress;
    Button btnEdit, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_profile);

        //Top menu
        toolbar = findViewById(R.id.toolBarProf);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reorder_w);

        //To do the go back option, don't forget to add this on the AndroidManifest
        // android:parentActivityName=".BusinessMain"
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);

        busName = findViewById(R.id.busName);
        busEmail = findViewById(R.id.busEmail);
        busPhone = findViewById(R.id.busPhone);
        busAddress = findViewById(R.id.busAddress);

        DB = new DBHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);

        String email = sharedPreferences.getString("EmailCust", "");
        String address = sharedPreferences.getString("Address", "");
        String phone = sharedPreferences.getString("Phone", "");
        String name = sharedPreferences.getString("Name", "");

        busName.setText(name);
        busEmail.setText("Email: " + email);
        busPhone.setText("Phone Number: " + phone);
        busAddress.setText("Address: " + address);

        btnEdit = findViewById(R.id.btnEditProfile);
        btnLogout = findViewById(R.id.btnLogoff);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                String[] columns = {"CustomerID", "AccountType", "EmailCust", "PasswordCust", "Name", "Phone", "Address", "City", "Province"};

                for (int i = 0; i < columns.length; i++) {
                    editor.putString(columns[i], "");
                    editor.apply();
                }
                DB.close();
                startActivity(new Intent(BusinessProfile.this, LoginActivity.class));
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessProfile.this, CustomerUpdate.class));
            }
        });

    }

}