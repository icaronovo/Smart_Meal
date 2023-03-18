package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int customerType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCustomer = findViewById(R.id.btnCustomer);
        Button btnBusiness = findViewById(R.id.btnBusiness);

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerType = 0;
                intent.putExtra("TEST",customerType);
                startActivity(intent);
            }
        });

        btnBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerType = 1;
                intent.putExtra("TEST",customerType);
                startActivity(intent);
            }
        });
    }
}