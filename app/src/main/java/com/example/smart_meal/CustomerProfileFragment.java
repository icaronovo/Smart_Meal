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

public class CustomerProfileFragment extends Fragment {
    private TextView custName;
    private TextView custPhone;
    private TextView custAddress;
    private TextView custCity;
    private TextView custState;
    private TextView custEmail;
    private TextView custID;
    private Button editProfile;
    private Button logout;
    private DBHelper DB;

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

        //using data stored in shared preferences to fill the proper fields.

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
        custPhone.setText("Phone Number: " + phone);
        custAddress.setText("Address: " + address);
        custCity.setText("City: " + city);
        custState.setText("Province: " + province);
        custEmail.setText("Email: " + email);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                String[] columns = {"CustomerID", "AccountType", "EmailCust", "PasswordCust", "Name", "Phone", "Address", "City", "Province"};

                for (int i = 0; i < columns.length; i++) {
                    editor.putString(columns[i], "");
                    editor.apply();
                    Log.d(columns[i], "");
                }
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