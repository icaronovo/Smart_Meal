package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnNewAccount;
    DBHelper DB;
    EditText email, pass;

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
                    Boolean checkUser = DB.checkUser(DBHelper.COLUMN_EMAIL_CUST, DBHelper.COLUMN_EMAIL_BUS);
                    Boolean checkUserPassword = DB.checkUserPassword(DBHelper.COLUMN_EMAIL_CUST, DBHelper.COLUMN_PASSWORD_CUST, DBHelper.COLUMN_EMAIL_BUS, DBHelper.COLUMN_PASSWORD_BUS);
                    if (checkUser == false || checkUserPassword == false) {
                        startActivity(new Intent(LoginActivity.this, NewAccount.class));
                    }
                }
                //Está indo direto para o Business Main só irmos avançando enquanto nao termino/testo db
                startActivity(new Intent(LoginActivity.this, BusinessMain.class));
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