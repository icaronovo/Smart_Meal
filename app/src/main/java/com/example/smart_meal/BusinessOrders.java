package com.example.smart_meal;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusinessOrders extends AppCompatActivity implements BusinessAdapter.ItemClickListener {
    private DBHelper DB;
    private BusinessAdapter adapter;
    private androidx.recyclerview.widget.RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_orders);

        //Start the database
        DB = new DBHelper(this);

        //Get business ID
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String customerID = sharedPreferences.getString("CustomerID", "");

        //Get the orders from the customer
        Cursor c = DB.displayOrder(customerID);
        List<OrderModel> ordersFromDB = updateData(c, "1");

        recyclerView = findViewById(R.id.recyclerFromBusiness);
        int numOfColumns = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumns));
        adapter = new BusinessAdapter(this,ordersFromDB);
        adapter.setCLickListener(this);
        recyclerView.setAdapter(adapter);
        c.close();

    }

    @Override
    public void onItemClick(View view, int position){
        Toast.makeText(this,"Selected", Toast.LENGTH_LONG).show();
    }

    //Get the data from DB
    public List<OrderModel> updateData(Cursor c, String businessID){
        List<OrderModel> dataFromDb = new ArrayList<>();

        boolean noPrint = false;
        if(c.getCount()>0){
            while(c.moveToNext()){

                String id = c.getString(0); //OrderID
                String orderStatus =c.getString(1); //OrderStatus
                String itemId =c.getString(2); //ItemID
                String date =c.getString(3); //Date
                String qty =c.getString(4); //ItemQty
                String cust =c.getString(5); //CustomerID

                OrderModel order = new OrderModel(Integer.parseInt(id),
                                                    Integer.parseInt(orderStatus),
                                                    Integer.parseInt(businessID),
                                                    Integer.parseInt(cust),
                                                    date,itemId,qty);
                dataFromDb.add(order);
            }
        }
        else{
            noPrint = true;
        }
        return dataFromDb;
    }

    public List<String> fixOrderFromDB(List<String> orderFromDB){
        List<String> newList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;

        for (String item : orderFromDB) {
            stringBuilder.append(item).append("&");
            count++;

            if (count == 5) {
                newList.add(stringBuilder.toString().trim());
                stringBuilder.setLength(0);
                count = 0;
            }
        }

        // add any remaining items if the list size is not a multiple of 5
        if (count > 0) {
            newList.add(stringBuilder.toString().trim());
        }

        // the groupedStrings list now contains the strings with 5 items each
        return newList;
    }


//    public List<String> getData(Cursor c) {
//        List<String> dataFromDb = new ArrayList<>();
//        if (c.getCount() > 0) {
//            while (c.moveToNext()) {
//                dataFromDb.add("\nOrderID# " + c.getString(0)); //OrderID
//                dataFromDb.add("\nOrder Status: " + c.getString(1)); //OrderStatus
//                dataFromDb.add("\nTotal Order: " + c.getString(2)); //TotalOrder
//                dataFromDb.add("\nDate: " + c.getString(3)); //Date
//                dataFromDb.add("\nItemID: " + c.getString(4)); //ItemID
//                dataFromDb.add("\nItem Value: " + c.getString(5)); //ItemValue
//                dataFromDb.add("\nItem Qty: " + c.getString(6)); //ItemQty
//                dataFromDb.add("\nBusinessID: " + c.getString(7)); //BusinessID
//                dataFromDb.add("\nCustomerID: " + c.getString(8)); //CustomerID
//            }
//        } else {
//            order.setText("No data");
//        }
//        order.setText(String.valueOf(dataFromDb));
//        return dataFromDb;
//    }

//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        switch (position) {
//            case 0:
//                break;
//            case 1:
//                break;
//            case 2:
//                break;
//            case 4:
//                break;
//        }
//    }

}
