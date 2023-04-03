package com.example.smart_meal;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CustomerOrderMFragment extends Fragment {

    CustomerOrderModel model;
    private TextView displayOrderNum;
    private TextView displayOrdersItem;
    private Button btnCancel;
    private DBHelper DB;

    /*Customer Order Main Fragment
    * */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_order_m, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayOrderNum = getActivity().findViewById(R.id.txtOrderNumber);
        displayOrdersItem = getActivity().findViewById(R.id.orderCustomerItem);
        btnCancel = getActivity().findViewById(R.id.btnCancel);

        //Start the database
        DB = new DBHelper(getActivity());

        //Get customer ID
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String customerID = sharedPreferences.getString("CustomerID", "");

        //Get the orders from the customer
        Cursor c = DB.displayOrder(customerID);
        updateData(c);
        c.close();


        //For the user cancel the order
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }

    //Get the data from the DB
    public void updateData(Cursor c){
        List<String> dataFromDb = new ArrayList<>();
        boolean noPrint = false;
        if(c.getCount()>0){
            while(c.moveToNext()){
                dataFromDb.add(c.getString(0)); //OrderID
                dataFromDb.add(c.getString(1)); //OrderStatus
                dataFromDb.add(c.getString(2)); //ItemID
                dataFromDb.add(c.getString(3)); //ItemQty
                dataFromDb.add(c.getString(4)); //BusinessID
            }
        }
        else{
            noPrint = true;
        }

        if(!noPrint){
            //Separate the data from the last order and display on xml
            List<String> lastFive = dataFromDb.subList(Math.max(dataFromDb.size() - 5, 0), dataFromDb.size());
            displayLastOrder(lastFive);
        } else{
            displayOrdersItem.setText("NO DATA");
        }
    }

    public void displayLastOrder(List<String> lastFive){

        DecimalFormat decimalFormat = new DecimalFormat("#");
        DecimalFormat currency = new DecimalFormat("#.##");
        double finalTotal = 0;

        StringBuilder orderToPrint = new StringBuilder();
        //Get the order
        String orderID = lastFive.get(0);

        //Order status
        String status = lastFive.get(1);

        //Get items id
        String itemsID = lastFive.get(2);
        String[] itemID = itemsID.split("\\$");

        //Get items quantity
        String itemsQty = lastFive.get(3);
        String[] itemQty = itemsQty.split("\\$");

        //Getbusinessid
        String  businessID = lastFive.get(4);

        for(int i = 0; i < itemQty.length;i++){
            //PEGAR O TIPO DE ITEM E PREÇO COM UMA QUERY E SUBSTITUIR PELA PALAVRA ITEM E PREÇO
            orderToPrint.append(itemQty[i] + "x Item - $ Preco");
            //JA FAZ O CALCULO E VAI SOMANDO PRA NO FIM FAZER DISPLAY
        }
        final double FEE = 0.6 * finalTotal;
        orderToPrint.append("\nSubtotal  $" + currency.format(finalTotal)+ "\n");
        orderToPrint.append("Fees  $" + currency.format(FEE)+ "\n");
        orderToPrint.append("\nTotal  $" + currency.format(finalTotal + FEE)+ "\n");

        displayOrderNum.setText("ORDER #" + orderID + " Restaurant " + businessID);
        displayOrdersItem.setText(String.valueOf(orderToPrint));
    }

    public void setModel(CustomerOrderModel model){
        this.model = model;
    }
}