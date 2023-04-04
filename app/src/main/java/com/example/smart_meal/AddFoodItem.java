package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFoodItem extends AppCompatActivity {
    EditText txtName, txtDescrip, txtQuant, txtPrice;
    Button btnAdd;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        txtName = findViewById(R.id.editName);
        txtDescrip = findViewById(R.id.editDescription);
        txtQuant = findViewById(R.id.editQuantity);
        txtPrice = findViewById(R.id.editPrice);
        btnAdd = findViewById(R.id.btnAdd);
        int businessID = Integer.parseInt(sharedPreferences.getString("CustomerID", ""));
        Log.d("BusinessID", String.valueOf(businessID));

        DBHelper dbHelper = new DBHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean itemAdded = dbHelper.addItem(new ItemModel(txtName.getText().toString(), txtDescrip.getText().toString(), Double.parseDouble(txtPrice.getText().toString()), Integer.parseInt(txtQuant.getText().toString()), businessID));
                if (itemAdded) {
                    txtName.setText("");
                    txtDescrip.setText("");
                    txtQuant.setText("");
                    txtPrice.setText("");
                    Intent intent = new Intent(AddFoodItem.this, BusinessMain.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddFoodItem.this, "Failed to add Item", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}