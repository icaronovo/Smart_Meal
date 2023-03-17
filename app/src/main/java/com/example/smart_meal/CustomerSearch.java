package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CustomerSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search);

        //Test
        TextView test = findViewById(R.id.test);
        Intent intent = getIntent();
        int test2 = getIntent().getIntExtra("TEST",0);
        test.setText("Restaurant selected is " + test2);
        //End test

    }
}