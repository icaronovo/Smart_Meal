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
import android.widget.Button;

public class BusinessMain extends AppCompatActivity {
    Toolbar toolbar;
    Button btnLougout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_main);

        btnLougout = findViewById(R.id.btnLogout);

        //Top menu
        toolbar = findViewById(R.id.toolbarBusiness);
        setSupportActionBar(toolbar);

        btnLougout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    //When creating the other activities from the Business Part, if you want to put the top menu
    //Insert all the bottom code with the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu_business,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                startActivity(new Intent(BusinessMain.this,BusinessProfile.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}