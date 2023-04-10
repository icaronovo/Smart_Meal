package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BusinessReport extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private DBHelper DBH;
    private TextView report;
    private SharedPreferences sharedPreferences;
    private String businessID;
    private final int REQUESTRECEIVED = 0;
    private final int PREPARINGORDER = 1;
    private final int CUSTOMERCANCELED = 2;
    private final int RESTAURANTCANCELED = 3;
    private final int ORDERFINISHE = 5;
    private String received;
    private String prepared;
    private String custCanceled;
    private String busCanceled;
    private String orFinished;
    private StringBuilder displayReport = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_report);

        //Get the business id
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        businessID = sharedPreferences.getString("CustomerID", "");

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

        DBH = new DBHelper(this);
        report = findViewById(R.id.txtOutput);

        received = DBH.viewData(businessID, REQUESTRECEIVED);
        displayReport.append("This business has " + received + " order(s) active \n\n");

        prepared = DBH.viewData(businessID, PREPARINGORDER);
        displayReport.append("- The total of " + prepared + " order(s) are being prepared\n\n");

        custCanceled = DBH.viewData(businessID, CUSTOMERCANCELED);
        displayReport.append("- The number of " + custCanceled + " customers canceled the order(s)\n\n");

        busCanceled = DBH.viewData(businessID, RESTAURANTCANCELED);
        displayReport.append("- We had to cancel " + busCanceled + " order(s)\n\n");

        orFinished = DBH.viewData(businessID, ORDERFINISHE);
        displayReport.append("We had a total of " + orFinished + " order(s) finished \n\n");

        report.setText(String.valueOf(displayReport));

    }
}