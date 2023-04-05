package com.example.smart_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.w3c.dom.Document;

import java.io.FileOutputStream;

public class BusinessReport extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_report);

        //Top menu
        toolbar = findViewById(R.id.toolbarReport);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reorder_w);
        toolbar.setTitle("Report");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reorder_w);

        // Handle navigation icon click event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click here
                onBackPressed();
            }
        });

    }
}