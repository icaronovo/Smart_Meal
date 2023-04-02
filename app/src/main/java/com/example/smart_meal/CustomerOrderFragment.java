package com.example.smart_meal;

import android.content.Context;
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

    public void changeText(StringBuilder data){
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

        orderText.setText(String.valueOf(data));

        //Send information of the order
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onButtonClick("Informação do Fragmento 1");
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
        void onButtonClick(String info);
    }
}