package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditFoodItem extends AppCompatActivity {
    private DBHelper DB;
    private Toolbar toolbar;
    private int itemID;
    private Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food_item);

        btnCancel = findViewById(R.id.btnCancelItem);

        // Setting up the toolbar
        toolbar = findViewById(R.id.toolbarEdit);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        itemID = intent.getIntExtra("ITEMID",-1);

        DB = new DBHelper(this);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}