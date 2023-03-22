package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnNewAccount;
    DBHelper DB;
    EditText email, pass;
    String accountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

                //checking if user exists and validating with db, simple validation to prevent empty fields
                if (user.equals("") || password.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        Cursor cursor = DB.checkAccountType(user);
                        accountType = cursor.getString(0);
                        if(accountType.equals("Business")){
                            startActivity(new Intent(LoginActivity.this, BusinessMain.class));
                        } else if (accountType.equals("Customer")){
                            startActivity(new Intent(LoginActivity.this, CustomerMain.class));
                        }
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Account not found.", Toast.LENGTH_SHORT).show();
                    }
                }

                //Apagar dps

//                Intent intent = getIntent();
//                int typeCustomer = getIntent().getIntExtra("TYPE",-1);
//                if(typeCustomer == 0){
//                    //UNCOMMENT TO TEST THE CUSTOMER CLASS
//                    startActivity(new Intent(LoginActivity.this, BusinessMain.class));
//                } else if (typeCustomer == 1) {
//                    //UNCOMMENT TO TEST THE  BUSINESS CLASS
//                    startActivity(new Intent(LoginActivity.this, CustomerMain.class));
//                }
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