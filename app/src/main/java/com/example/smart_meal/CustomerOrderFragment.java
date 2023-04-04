package com.example.smart_meal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class CustomerOrderFragment extends Fragment {

    private OnButtonClickListener mListener;
    private TextView txtYourDelivery;
    private TextView txtDelivery;
    private TextView txtAddress;
    private TextView txtOrders;
    private TextView orderText;
    private RadioButton rdbPickUp;
    private RadioButton rdbDelivery;
    private EditText inputAddress;
    private Button btnConfirm;
    private DBHelper DB;
    String delivery = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_order, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        txtYourDelivery = getActivity().findViewById(R.id.txtYourDelivery);
        txtDelivery = getActivity().findViewById(R.id.txtTypeOfDelivery);
        txtAddress = getActivity().findViewById(R.id.txtDeliveryAdd);
        txtOrders = getActivity().findViewById(R.id.txtOrder);
        orderText = getActivity().findViewById(R.id.orderCustomer);
        rdbPickUp = getActivity().findViewById(R.id.rbtPickUp);
        rdbDelivery = getActivity().findViewById(R.id.rbtDelivery);
        inputAddress = getActivity().findViewById(R.id.txtAddressDelivery);
        btnConfirm = getActivity().findViewById(R.id.btnConfirmOrder);

    }

    public void makeOrder(StringBuilder data, HashMap<String,Double[]> customerOrderList){
        //Set visibility of the items
        txtYourDelivery.setVisibility(View.VISIBLE);
        txtDelivery.setVisibility(View.VISIBLE);
        txtOrders.setVisibility(View.VISIBLE);
        rdbPickUp.setVisibility(View.VISIBLE);
        rdbDelivery.setVisibility(View.VISIBLE);
        orderText.setVisibility(View.VISIBLE);
        btnConfirm.setVisibility(View.VISIBLE);

        // Set a listener on the radio button
        rdbPickUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // If the radio button is checked, is gonna be pickUp
                if (isChecked) {
                    txtAddress.setVisibility(View.GONE);
                    inputAddress.setVisibility(View.GONE);
                    delivery = "Pick up";
                } else {
                    // Otherwise, tell the address for pickup
                    txtAddress.setVisibility(View.VISIBLE);
                    inputAddress.setVisibility(View.VISIBLE);
                    delivery = inputAddress.getText().toString();
                }
            }
        });
        orderText.setText(String.valueOf(data));

        //Click the confirm button and send
        // information of the order to the db
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize the DB object
                DB = new DBHelper(getActivity());

                //Get customer ID
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                String customerIDStr = sharedPreferences.getString("CustomerID", "");
                int customerID = Integer.parseInt(customerIDStr);
                int orderStatus = 0; // 0 for order incomplete; 1 for order complete
                int businessID = 2; // get the businessId
                String itemID = ""; // get the items ids
                String quantityNumber = ""; //get the quantity of items

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date dateNow = new Date();
                String date = formatter.format(dateNow);
                //Puttin the itemsID and the quantity in a string
                //so we can sent more than one item to the db
                for (String key : customerOrderList.keySet()) {
                    Double[] values = customerOrderList.get(key);
                    itemID += key + "$";
                    quantityNumber += values[1] + "$";
                }

//              customerOrderList = Hashmap <Nome do item, Double [preço,quantity]


                OrderModel newOrder = new OrderModel(orderStatus, businessID, customerID, date, itemID, quantityNumber);

                if(DB.addOrder(newOrder)){
                    Toast.makeText(getActivity(), "Order sent!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Couldn't submit order. Try again", Toast.LENGTH_SHORT).show();
                }

                //Here you are gonna send the ORDERID
                mListener.onButtonClick(delivery);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnButtonClickListener");
        }
    }

    public interface OnButtonClickListener {
        void onButtonClick(String delivery);
    }
}