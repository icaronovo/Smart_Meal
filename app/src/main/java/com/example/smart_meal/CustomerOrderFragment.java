package com.example.smart_meal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerOrderFragment extends Fragment {

    TextView orderText;
    Button btnConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_order, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        orderText = getActivity().findViewById(R.id.orderCustomer);
        btnConfirm = getActivity().findViewById(R.id.btnConfirmOrder);
    }

    public void changeText(ArrayList<String> data){
        orderText.setText(String.valueOf(data));
        btnConfirm.setVisibility(View.VISIBLE);
    }
}