package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerRestaurant extends AppCompatActivity implements Communicator {
    DBHelper DB = new DBHelper(this);
    public int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_restaurant);
        TextView restaurantName = findViewById(R.id.txtRestaurantName);
        TextView restaurantAddress = findViewById(R.id.txtRestAddress);

        //Get the restaurant ID
        Intent intent = getIntent();
        restaurantId = intent.getIntExtra("RESTAURANTID",-1);
        String[] restaurantInfo = DB.getUserData(restaurantId);
        restaurantName.setText(restaurantInfo[4]);
        restaurantAddress.setText(restaurantInfo[6] + ", " + restaurantInfo[7]);
    }

    @Override
    public void respond(HashMap<Integer,Integer> itemIdAndQty) {
        DecimalFormat currency = new DecimalFormat("#.##");
        double finalTotal = 0;

        FragmentManager fragmentManager = getSupportFragmentManager();
        CustomerOrderFragment customerOrderFragment = (CustomerOrderFragment)
                fragmentManager.findFragmentById(R.id.selectedOrder);

        StringBuilder str = new StringBuilder();

        for (Integer key : itemIdAndQty.keySet()) {
            Integer quantity = itemIdAndQty.get(key);
            Double price = getPrice(String.valueOf(restaurantId),key);
            String name = getName(String.valueOf(restaurantId),key);
            str.append(quantity + " x " + name + " $ " + currency.format(price) + "\n");
            finalTotal += quantity * price;
        }
        final double FEE = 0.6 * finalTotal;
        str.append("\nSubtotal  $" + currency.format(finalTotal)+ "\n");
        str.append("Fees  $" + currency.format(FEE)+ "\n");
        str.append("\nTotal  $" + currency.format(finalTotal + FEE)+ "\n");
        customerOrderFragment.makeOrder(str, itemIdAndQty);
    }

    //Get the items price from this Restaurant on DB
    public Double getPrice(String restaurantId, int productID){
        Double itemPrice = 0.0;
        Cursor c = DB.displayPrice(restaurantId,productID);
        if(c.getCount()>0){
            while(c.moveToNext()){
                itemPrice = Double.parseDouble(c.getString(0));
            }
        }
        return itemPrice;
    }

    //Get the items price from this Restaurant on DB
    public String getName(String restaurantId, int productID){
        String itemName = "";
        Cursor c = DB.displayName(restaurantId,productID);
        if(c.getCount()>0){
            while(c.moveToNext()){
                itemName = c.getString(0);
            }
        }
        return itemName;
    }
}