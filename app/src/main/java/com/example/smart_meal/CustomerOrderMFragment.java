package com.example.smart_meal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CustomerOrderMFragment extends Fragment {
    CustomerOrderModel model;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_order_m, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = getActivity().findViewById(R.id.txtFrag2);
        String str = model.getMyString();
        if(!str.isEmpty()){
            textView.setText(str);
        }
    }

    public void setModel(CustomerOrderModel model){
        this.model = model;
    }
}