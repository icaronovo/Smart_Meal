package com.example.smart_meal;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusinessOrders extends AppCompatActivity implements BusinessAdapter.ItemClickListener {
    DBHelper DB;
    private BusinessAdapter adapter;
    private CheckBox checkBox;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private List<OrderModel> ordersFromDB = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_orders);

        checkBox = findViewById(R.id.checkboxSelectAll);
        recyclerView = findViewById(R.id.recyclerFromBusiness);

        //Start the database
        DB = new DBHelper(this);

        //Get business ID
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String businessID = sharedPreferences.getString("CustomerID", "");

        //Get the orders from the customer
        Cursor c = DB.displayOrderBusiness(businessID);
        List<String> list = new ArrayList<>();

        boolean noPrint = false;
        if(c.getCount()>0){
            while(c.moveToNext()){
                list.add(c.getString(0)); //OrderID
                list.add(c.getString(1)); //OrderStatus
                list.add(c.getString(2)); //ItemID
                list.add(c.getString(3)); //Date
                list.add(c.getString(4)); //ItemQty
                list.add(c.getString(5)); //CustomerID
            }
        }
        else{
            noPrint = true;
        }
        checkBox.setText(String.valueOf(list));
//        6
//        Resultado: [1,0,ITem 2$Item 5-$ITem 7$, data, preco,customerid]

//public OrderModel(int orderID, int orderStatus, int businessID,
//        int customerID, String date,
//                String itemID, String itemQuantity){
        if(noPrint){
            checkBox.setText("NO DATA");
        } else{
            //Make the data being add into the list
            int index = 0;
            while (index < list.size()) {
                OrderModel order = new OrderModel(
                        Integer.parseInt(list.get(index)), //OrderID -  ARRAY 0
                        Integer.parseInt(list.get(index + 1)), //OrderStatus ARRAY 1
                        Integer.parseInt(businessID), //BusinessID
                        Integer.parseInt(list.get(index + 5)),//CustomerID ARRAY 5
                        list.get(index + 3), //DATE ARRAY 3
                        list.get(index + 2), //ItemID ARRAY 2
                        list.get(index + 4)// ItemQty ARRAY 4
                );
                ordersFromDB.add(order);
                index += 6;
                checkBox.setText(String.valueOf(ordersFromDB));
            }
        }
//        int numOfColumns = 1;
//        recyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumns));
//        adapter = new BusinessAdapter(this,ordersFromDB);
//        adapter.setCLickListener(this);
//        recyclerView.setAdapter(adapter);
        c.close();

    }

    @Override
    public void onItemClick(View view, int position){
        Toast.makeText(this,"Selected", Toast.LENGTH_LONG).show();
    }

    //Get the data from DB
    public void updateData(Cursor c, String businessID){

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
