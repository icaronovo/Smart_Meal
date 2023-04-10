package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BusinessOrderUpdate extends AppCompatActivity {
    private Button btnConfirmOrder;
    private Button btnOrderReady;
    private Button btnCancelOrder;
    private Button btnFinishOrder;
    private androidx.appcompat.widget.Toolbar toolbar;
    private DBHelper DB;
    private SharedPreferences sharedPreferences;
    private BusinessItemAdapter adapter;
    private ListView mListView;
    private int orderID;
    private String orderTxt;
    private TextView txtFullOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_order_update);

        //Toolbar
        toolbar = findViewById(R.id.toolbarOrderEdit);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reorder_w);
        // Handle navigation icon click event on toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click here
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        orderID = intent.getIntExtra("ORDERID",-1);
        orderTxt = intent.getStringExtra("ORDERFULL");

        //Start the database
        DB = new DBHelper(this);

        txtFullOrder = findViewById(R.id.txtFullOrder);
        txtFullOrder.setText(orderTxt);

        btnConfirmOrder = findViewById(R.id.btnConfirmOrderBusiness);
        btnOrderReady = findViewById(R.id.btnReadyToPickUp);
        btnCancelOrder = findViewById(R.id.btnCancelOrderBusiness);
        btnFinishOrder = findViewById(R.id.btnFinishOrder);

        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cancelOrder = 1;
                boolean confirmOrder = DB.orderStatusUpdate(orderID, cancelOrder);
                if(confirmOrder){
                    Toast.makeText(BusinessOrderUpdate.this,"The order was received",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        btnOrderReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cancelOrder = 4;
                boolean setOrderReady = DB.orderStatusUpdate(orderID, cancelOrder);
                if(setOrderReady){
                    Toast.makeText(BusinessOrderUpdate.this,"The order is ready for pick-up or delivery",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cancelOrder = 3;
                boolean cancel = DB.orderStatusUpdate(orderID, cancelOrder);
                if(cancel){
                    Toast.makeText(BusinessOrderUpdate.this,"The order has ben canceled",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        btnFinishOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cancelOrder = 5;
                boolean finishedOrder = DB.orderStatusUpdate(orderID, cancelOrder);
                if(finishedOrder){
                    Toast.makeText(BusinessOrderUpdate.this,"The order has ben finished",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
}