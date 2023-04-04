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


public class CustomerOrderMFragment extends Fragment {

    CustomerOrderModel model;
    private TextView displayOrderNum;
    private TextView displayOrdersItem;
    private TextView displayDate;
    private Button btnCancel;
    private DBHelper DB;
    private ListView myListView;
    private ArrayAdapter<String> myAdapter;
    private List<String> pastOrders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_order_m, container, false);

        // Initialize the ListView and the list
        myListView = view.findViewById(R.id.listViewOldOrders);
        pastOrders = new ArrayList<>();

        // Add some strings to the list
        pastOrders.add("Item 1");
        pastOrders.add("Item 2");
        pastOrders.add("Item 3");

        // Initialize the adapter and set it to the ListView
        myAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, pastOrders);
        myListView.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayOrderNum = getActivity().findViewById(R.id.txtOrderNumber);
        displayOrdersItem = getActivity().findViewById(R.id.orderCustomerItem);
        displayDate = getActivity().findViewById(R.id.txtDate);
        btnCancel = getActivity().findViewById(R.id.btnCancel);

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
            displayOrdersItem.setText("NO DATA");
            displayDate.setText("NO DATA");
            displayOrdersItem.setText("NO DATA");
        }

        c.close();

        //For the user cancel the order
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }

    //Get the data from DB
    //If the customer doesn't has any order
    //It will show that he has no order
    public boolean updateData(Cursor c, String customerID){
        List<String> dataFromDb = new ArrayList<>();
        boolean noPrint = false;
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


//        if(!noPrint){
//            //Separate the data from the last order and display on xml
//            List<String> lastSix = dataFromDb.subList(Math.max(dataFromDb.size() - 6, 0), dataFromDb.size());
//            displayLastOrder(lastSix);
//        } else{
//            displayOrdersItem.setText("NO DATA");
//            displayDate.setText("NO DATA");
//            displayOrdersItem.setText("NO DATA");
//        }
    }

    public void addOrderModel(List<String> list, String customerID){
        ArrayList<OrderModel> listOrders = new ArrayList<>();
        //Make the data being add into the list
        int index = 0;
        while (index < list.size()) {
            OrderModel order = new OrderModel(
                    Integer.parseInt(list.get(index)), //OrderID -  ARRAY 0
                    Integer.parseInt(list.get(index + 1)), //OrderStatus ARRAY 1
                    Integer.parseInt(list.get(index + 5)),//BusinessID ARRAY 5
                    Integer.parseInt(customerID), //CustomerID
                    list.get(index + 3), //DATE ARRAY 3
                    list.get(index + 2), //ItemID ARRAY 2
                    list.get(index + 4)// ItemQty ARRAY 4
            );
            listOrders.add(order);
            index += 6;
        }
        //Get the last item
        OrderModel lastOrder = listOrders.get(listOrders.size()-1);
        displayLastOrder(lastOrder);
//        List<OrderModel> lastSix = listOrders.subList(Math.max(listOrders.size() - 6, 0), dataFromDb.size());
//        displayLastOrder(lastSix);
    }

    //Display the last order
    public void displayLastOrder(OrderModel lastSix){
        DecimalFormat decimalFormat = new DecimalFormat("#");
        DecimalFormat currency = new DecimalFormat("#.##");
        double finalTotal = 0;
        StringBuilder orderToPrint = new StringBuilder();

        //Get the order
        int orderID = lastSix.getOrderID();

        //Order status
        int status = lastSix.getOrderStatus();

        //Get items id
        String itemsID = lastSix.getItemID();
        String[] itemID = itemsID.split("\\$");

        //Get date
        String date = lastSix.getDate();

        //Get items quantity
        String itemsQty = lastSix.getItemQuantity();
        String[] itemQty = itemsQty.split("\\$");

        //Get businessid
        int  businessID = lastSix.getBusinessID();

        for(int i = 0; i < itemQty.length;i++){
            //PEGAR O TIPO DE ITEM E PREÇO COM UMA QUERY E SUBSTITUIR PELA PALAVRA ITEM E PREÇO
            orderToPrint.append(itemQty[i] + "x Item - $ Preco" + "\n");
            //JA FAZ O CALCULO E VAI SOMANDO PRA NO FIM FAZER DISPLAY
        }
        final double FEE = 0.6 * finalTotal;
        orderToPrint.append("\nSubtotal  $" + currency.format(finalTotal)+ "\n");
        orderToPrint.append("Fees  $" + currency.format(FEE)+ "\n");
        orderToPrint.append("\nTotal  $" + currency.format(finalTotal + FEE)+ "\n");

        //Display on Order Fragment
        displayOrderNum.setText("ORDER #" + orderID + " - Restaurant " + businessID);
        displayDate.setText(date);
        displayOrdersItem.setText(String.valueOf(orderToPrint));
    }

//    public List<String> displayPastOrders(){
//
//    }
    
    public void setModel(CustomerOrderModel model){
        this.model = model;
    }
}