package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnNewAccount;
    DBHelper DB;
    EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        int typeCustomer = getIntent().getIntExtra("TYPE",-1);

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

                // UNCOMMENT TO TEST THE DATABASE
                //checking if user exists and validating with db, simple validation to prevent empty fields
//                if (user.equals("") || password.equals(""))
//                    Toast.makeText(LoginActivityBusiness.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
//                else {
//                    //Boolean checkBus = DB.checkBus(DBHelper.COLUMN_EMAIL_BUS);
//                    Boolean checkUserPasswordBusiness = DB.checkUserPasswordBusiness(email.getText().toString(), pass.getText().toString());
////                    checkUserPassword == false // add it on if when working
//                    if (checkUserPasswordBusiness == false) { //checkBus == false ||
//                        startActivity(new Intent(LoginActivityBusiness.this, NewAccount.class));
//                    } else {
//                        startActivity(new Intent(LoginActivityBusiness.this, CHomeFragment.class));
//                    }
//                }

//                Log.d("MinhaTag","Entrou");
                if(typeCustomer == 0){
                    //UNCOMMENT TO TEST THE CUSTOMER CLASS
                    startActivity(new Intent(LoginActivity.this, BusinessMain.class));
                } else if (typeCustomer == 1){
                    //UNCOMMENT TO TEST THE  BUSINESS CLASS
                    startActivity(new Intent(LoginActivity.this, CustomerMain.class));
                }

            }
        });

        //Create new account
        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, NewAccount.class));
            }
        });
    }
}