package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class BusinessOrderUpdate extends AppCompatActivity {
    private Button btnConfirmOrder;
    private Button btnOrderReady;
    private Button btnCancelOrder;
    private Button btnReturn;
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

        Intent intent = getIntent();
        orderID = intent.getIntExtra("ORDERID",-1);
        orderTxt = intent.getStringExtra("ORDERFULL");

        txtFullOrder = findViewById(R.id.txtFullOrder);
        txtFullOrder.setText(orderTxt);

        btnConfirmOrder = findViewById(R.id.btnConfirmOrderBusiness);
        btnOrderReady = findViewById(R.id.btnReadyToPickUp);
        btnCancelOrder = findViewById(R.id.btnCancelOrderBusiness);
        btnReturn = findViewById(R.id.btnReturnMain);


    }
}