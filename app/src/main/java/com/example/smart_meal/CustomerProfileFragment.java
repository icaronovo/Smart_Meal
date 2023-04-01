package com.example.smart_meal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerProfileFragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerProfileFragment extends Fragment {
    TextView custName;
    TextView custPhone;
    TextView custAddress;
    TextView custCity;
    TextView custState;
    TextView custEmail;
    TextView custID;
    Button editProfile, logout;
    DBHelper DB;

    private SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_profile, container, false);
        // Get references to the views
        custName = view.findViewById(R.id.custName);
        custID = view.findViewById(R.id.custID);
        custPhone = view.findViewById(R.id.custPhone);
        custAddress = view.findViewById(R.id.custAddress);
        custCity = view.findViewById(R.id.custCity);
        custState = view.findViewById(R.id.custState);
        custEmail = view.findViewById(R.id.custEmail);
        editProfile = view.findViewById(R.id.btnEdit);
        logout = view.findViewById(R.id.btnLog);

        // Initialize the DB object
        DB = new DBHelper(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("EmailCust","");
        String address = sharedPreferences.getString("Address", "");
        String phone = sharedPreferences.getString("Phone", "");
        String customerID = sharedPreferences.getString("CustomerID", "");
        String city = sharedPreferences.getString("City", "");
        String accountType = sharedPreferences.getString("AccountType", "");
        String province = sharedPreferences.getString("Province", "");
        String name = sharedPreferences.getString("Name", "");

        custName.setText(name);
        custID.setText("Customer ID: " + customerID);
        custPhone.setText(phone);
        custAddress.setText(address);
        custCity.setText(city);
        custState.setText(province);
        custEmail.setText(email);



//
//        Cursor cursor = DB.getReadableDatabase().query("CUSTOMER_INFO",columns,selection,selectionArgs,null,null,null);
//
//        if(cursor.moveToFirst()) {
//            custID.setText(cursor.getString(0));
//            custEmail.setText(cursor.getString(2));
//            custName.setText(cursor.getString(4));
//            custPhone.setText(cursor.getString(5));
//            custAddress.setText(cursor.getString(6));
//            custCity.setText(cursor.getString(7));
//            custState.setText(cursor.getString(8));
//        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.close();
                startActivity(new Intent(getContext(),LoginActivity.class));
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),CustomerUpdate.class));
            }
        });

        // Inflate the layout for this fragment
        return view;

    }
}