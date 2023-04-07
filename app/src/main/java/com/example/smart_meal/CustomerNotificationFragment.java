package com.example.smart_meal;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CustomerNotificationFragment extends Fragment {

    private ListView notificationList;
    private DBHelper DB;
    private ArrayAdapter<String> myAdapter;
    private DecimalFormat currency = new DecimalFormat("#.##");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_notification, container, false);

        // Initialize the ListView and the list
        notificationList = view.findViewById(R.id.listViewNotification);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Start the database
        DB = new DBHelper(getActivity());

        //Get customer ID
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String customerID = sharedPreferences.getString("CustomerID", "");

        //Get the orders from the customer
        Cursor c = DB.displayOrder(customerID);

        //Check if the customer has orders
        Boolean hasNoData = updateData(c,customerID);
        if (hasNoData == true) {
            notificationList.setVisibility(View.INVISIBLE);
        } else{
            notificationList.setVisibility(View.VISIBLE);
        }
        c.close();
    }

    //Get the data from DB
    //If the customer doesn't has any order
    //It will show that he has no order
    public boolean updateData(Cursor c, String customerID){
        List<String> dataFromDb = new ArrayList<>();
        if(c.getCount()>0){
            while(c.moveToNext()){
                dataFromDb.add(c.getString(0)); //OrderID
                dataFromDb.add(c.getString(1)); //OrderStatus
                dataFromDb.add(c.getString(2)); //ItemID
                dataFromDb.add(c.getString(3)); //Date
                dataFromDb.add(c.getString(4)); //ItemQty
                dataFromDb.add(c.getString(5)); //BusinessID
            }
        }
        else{
            return true;
        }
        addOrderModel(dataFromDb, customerID);
        return false;
    }

    public void addOrderModel(List<String> list, String customerID){
        Stack<OrderModel> stackOrders = new Stack<>();
        //Make the data being add into the list
        int index = 0;
        while (index < list.size()) {
            OrderModel order = new OrderModel(
                    Integer.parseInt(list.get(index)), //OrderID
                    Integer.parseInt(list.get(index + 1)), //OrderStatus
                    Integer.parseInt(list.get(index + 5)),//BusinessID
                    Integer.parseInt(customerID), //CustomerID
                    list.get(index + 3), //DATE
                    list.get(index + 2), //ItemID
                    list.get(index + 4)// ItemQty
            );
            stackOrders.push(order);
            index += 6;
        }
        //Get the last item
        displayPastOrders(stackOrders);
    }

    public void displayPastOrders(Stack<OrderModel> pastOrders){
        List<String> ordersDisplay = new ArrayList<>();
        int count = 0;
        //Make the display until the stack is empty
        while(count < 10){
            OrderModel order = pastOrders.pop();
            StringBuilder display = new StringBuilder();

            display.append("About order ID #" + order.getOrderID() + "\n");
            //Get restaurant name
            String businessID = String.valueOf(order.getBusinessID());
            String businessName = DB.displayBusinessName(businessID,"Business");

            switch (order.getOrderStatus()){
                case 0:
                    //Request sended
                    display.append("Your request from "+ businessName + " has been sent \n");
                    break;
                case 1:
                    //Restaurant is prepering your order
                    display.append("Your order has been received by " + businessName + "\n");
                    break;
                case 2:
                    //Customer cancel the order
                    display.append("You canceled your order");
                    break;
                case 3:
                    //Business cancel the order
                    display.append(" The "+ businessName + " has canceled your order");
                    break;
                case 4:
                    //Order is ready
                    display.append(" Your order with "+ businessName + " is ready!!");
                    break;
                case 5:
                    //Order is ready
                    display.append(" Your order with "+ businessName + " was completed!!");
                    break;
                default:
                    display.append("No information");
                    break;
            }

            ordersDisplay.add(String.valueOf(display));
            if(pastOrders.isEmpty()){
                count = 10;
            } else{
                count++;
            }
        }
        createListView(ordersDisplay);
    }

    //Create the view on Listview
    public void createListView(List<String> listItems) {
        myAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listItems);
        notificationList.setAdapter(myAdapter);
    }
}