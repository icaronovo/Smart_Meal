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
    // Defining variables
    private EditText txtName;
    private EditText txtDescrip;
    private EditText txtQuant;
    private EditText txtPrice;
    private Button btnAdd;
    private SharedPreferences sharedPreferences;
    private androidx.appcompat.widget.Toolbar toolbar;

    // Overriding onCreate method to set up the view of the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting the layout of the activity
        setContentView(R.layout.activity_add_food_item);

        // Getting the shared preferences of the application
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);

        // Getting references to the EditText and Button views in the layout
        txtName = findViewById(R.id.editName);
        txtDescrip = findViewById(R.id.editDescription);
        txtQuant = findViewById(R.id.editQuantity);
        txtPrice = findViewById(R.id.editPrice);
        btnAdd = findViewById(R.id.btnAdd);

        // Setting up the toolbar
        toolbar = findViewById(R.id.toolbarAdd);
        toolbar.setTitle("Add Items");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reorder_w);
        // Handling the navigation icon click event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Getting the business ID from the shared preferences and logging it
        int businessID = Integer.parseInt(sharedPreferences.getString("CustomerID", ""));
        Log.d("BusinessID", String.valueOf(businessID));

        // Creating a DBHelper instance
        DBHelper dbHelper = new DBHelper(this);

        // Handling the button click event
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adding an item to the database and handling the result
                Boolean itemAdded = dbHelper.addItem(new ItemModel(txtName.getText().toString(), txtDescrip.getText().toString(), Double.parseDouble(txtPrice.getText().toString()), Integer.parseInt(txtQuant.getText().toString()), businessID));
                if (itemAdded) {
                    // Clearing the EditText views if the item was added successfully
                    txtName.setText("");
                    txtDescrip.setText("");
                    txtQuant.setText("");
                    txtPrice.setText("");
                    // Redirecting to the BusinessMain activity if the item was added successfully
                    Intent intent = new Intent(AddFoodItem.this, BusinessMain.class);
                    startActivity(intent);
                } else {
                    // Showing a toast message if the item was not added successfully
                    Toast.makeText(AddFoodItem.this, "Failed to add Item", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
