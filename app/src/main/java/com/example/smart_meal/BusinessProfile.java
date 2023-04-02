package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BusinessProfile extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_profile);

        //Top menu
        toolbar = findViewById(R.id.toolbarBusiness);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reorder_w);

        //To do the go back option, don't forget to add this on the AndroidManifest
        // android:parentActivityName=".BusinessMain"
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);
    }

}