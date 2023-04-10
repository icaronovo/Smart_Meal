package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EditFoodItem extends AppCompatActivity {
    private DBHelper DB;
    private Toolbar toolbar;
    private int itemID;
    private Button btnCancel;
    private Button btnEdit;
    private Button btnDelete;
    private EditText txtName;
    private EditText txtDescription;
    private EditText txtQuantity;
    private EditText txtPrice;
    private TextView txtItemId;
    private SharedPreferences sharedPreferences;
    private List<String> itemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food_item);

        // Setting up the toolbar
        toolbar = findViewById(R.id.toolbarEdit);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        itemID = intent.getIntExtra("ITEMID",-1);

        DB = new DBHelper(this);
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        int businessID = Integer.parseInt(sharedPreferences.getString("CustomerID", ""));

        // Getting references to the EditText and Button views in the layout
        btnDelete = findViewById(R.id.btnCancelOrderBusiness);
        txtItemId = findViewById(R.id.txtItemID);
        btnCancel = findViewById(R.id.btnCancelItem);
        txtName = findViewById(R.id.editNameItem);
        txtDescription = findViewById(R.id.editDescriptionItem);
        txtQuantity = findViewById(R.id.editQuantityItem);
        txtPrice = findViewById(R.id.editPriceItem);
        btnEdit = findViewById(R.id.btnEditItem);

        //Recover the item data
        itemData = DB.itemDisplay(businessID,itemID);
        txtItemId.setText("" + itemID);
        txtName.setText("" + itemData.get(3));
        txtDescription.setText("" + itemData.get(4));
        txtQuantity.setText("" + itemData.get(2));
        txtPrice.setText("" + itemData.get(1));

        //Cancel and go back to the BusinessItems activity
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Update orders
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemID = (String) txtItemId.getText();
                String name = String.valueOf(txtName.getText());
                String description = String.valueOf(txtDescription.getText());
                String quantity = String.valueOf(txtQuantity.getText());
                String price = String.valueOf(txtPrice.getText());

                boolean rows = DB.itemsUpdate(itemID, name, description, quantity, price);

                if(rows == true){
                    Toast.makeText(EditFoodItem.this,"Item Updated!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditFoodItem.this, BusinessMain.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(EditFoodItem.this,"Error",Toast.LENGTH_LONG).show();
                }
            }
        });

        //Delete item
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean rows = DB.deleteItem(String.valueOf(itemID));

                if(rows == true){
                    Toast.makeText(EditFoodItem.this,"Item deleted!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditFoodItem.this, BusinessMain.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(EditFoodItem.this,"Error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}