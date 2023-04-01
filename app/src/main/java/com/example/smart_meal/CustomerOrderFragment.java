package com.example.smart_meal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerOrderFragment extends Fragment {

    TextView txtYourDelivery, txtDelivery, txtAddress, txtOrders, orderText;
    RadioButton rdbPickUp, rdbDelivery;
    RadioGroup radioGroup;
    EditText inputAddress;
    Button btnConfirm;

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
        radioGroup = getActivity().findViewById(R.id.radioGroup);
        btnConfirm = getActivity().findViewById(R.id.btnConfirmOrder);




    }

    public void changeText(ArrayList<String> data){
        //Set visibility of the date
        txtYourDelivery.setVisibility(View.VISIBLE);
        txtDelivery.setVisibility(View.VISIBLE);
        txtOrders.setVisibility(View.VISIBLE);
        rdbPickUp.setVisibility(View.VISIBLE);
        rdbDelivery.setVisibility(View.VISIBLE);
        orderText.setVisibility(View.VISIBLE);

        // Set a listener on the radio button
        rdbPickUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // If the radio button is checked, show the Address and the input
                if (isChecked) {
                    txtAddress.setVisibility(View.GONE);
                    inputAddress.setVisibility(View.GONE);
                } else {
                    // Otherwise, hide the the Address and the input
                    txtAddress.setVisibility(View.VISIBLE);
                    inputAddress.setVisibility(View.VISIBLE);
                }
            }
        });

        btnConfirm.setVisibility(View.VISIBLE);
        orderText.setText(String.valueOf(data));
    }
}