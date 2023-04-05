package com.example.smart_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.io.FileOutputStream;

public class BusinessReport extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_report);

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String customerID = sharedPreferences.getString("CustomerID", "");

        //Top menu
        toolbar = findViewById(R.id.toolbarReport);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reorder_w);
        toolbar.setTitle("Report");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reorder_w);

        // Handle navigation icon click event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click here
                onBackPressed();
            }
        });

        dbh = new DBHelper(this);
        TextView report = findViewById(R.id.txtOutput);
        Button btnShow = findViewById(R.id.btnView);


        btnShow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Cursor c = dbh.viewData();
                StringBuilder str = new StringBuilder();

                String status = String.valueOf(c.getColumnIndex("OrderStatus"));
                switch (status) {
                    case "0":
                        status = "Order sent to restaurant";
                        break;
                    case "1":
                        status = "Order received by the restaurant";
                        break;
                    case "2":
                        status = "Customer CANCELED order";
                        break;
                    case "3":
                        status = "Business CANCELED order";
                        break;
                }

                if (c.getCount() > 0) {
                    while (c.moveToNext()) {
                        if (customerID.equals(c.getString(c.getColumnIndexOrThrow("BusinessID")))) {
                            str.append(" OrderID: " + c.getString(0));
                            str.append("\n");
                            str.append(" OrderStatus: " + status);
                            str.append("\n");
                            str.append(" ItemID: " + c.getString(2));
                            str.append("\n");
                            str.append(" Date: " + c.getString(3));
                            str.append("\n");
                            str.append(" ItemQuantity:" + c.getString(4));
                            str.append("\n");
                            str.append(" BusinessID: " + c.getString(5));
                            str.append("\n");
                            str.append(" CustomerID: " + c.getString(6));
                            str.append("\n");
                            str.append("----------------------------------------------------");
                            str.append("\n");
                        }
                    }
                    report.setText(str);

                } else {
                    Toast.makeText(BusinessReport.this, "nothing to display", Toast.LENGTH_LONG).show();
                }
                c.close();
            }
        });

    }
}