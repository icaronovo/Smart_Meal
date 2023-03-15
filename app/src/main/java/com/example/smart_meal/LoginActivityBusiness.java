package com.example.smart_meal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivityBusiness extends AppCompatActivity {
    Button btnLogin, btnNewAccount;
    DBHelper DB;
    EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);

        btnLogin = findViewById(R.id.txtLogin);
        btnNewAccount = findViewById(R.id.txtNewAccount);
        email = findViewById(R.id.txtEmail);
        pass = findViewById(R.id.txtPassword);
        DB = new DBHelper(this);

        //Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString();
                String password = pass.getText().toString();

//                 UNCOMMENT TO TEST THE DATABASE, tried to run, query problem
//                checking if user exists and validating with db, simple validation to prevent empty fields
                if (user.equals("") || password.equals(""))
                    Toast.makeText(LoginActivityBusiness.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    //Boolean checkBus = DB.checkBus(DBHelper.COLUMN_EMAIL_BUS);
                    Boolean checkUserPasswordBusiness = DB.checkUserPasswordBusiness(email.getText().toString(), pass.getText().toString());
//                    checkUserPassword == false // add it on if when working
                    if (checkUserPasswordBusiness == false) { //checkBus == false ||
                        startActivity(new Intent(LoginActivityBusiness.this, NewAccount.class));
                    } else {
                        startActivity(new Intent(LoginActivityBusiness.this, CHomeFragment.class));
                    }
                }
//
//                //UNCOMMENT TO TEST THE CUSTOMER CLASS
//                startActivity(new Intent(LoginActivity.this, CustomerMain.class));
//
//                //UNCOMMENT TO TEST THE  BUSINESS CLASS
//                startActivity(new Intent(LoginActivity.this, BusinessMain.class));
            }
        });
        //Create new account
        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivityBusiness.this, NewAccount.class));
            }
        });
    }
}