package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnNewAccount;
    DBHelper DB;
    EditText email, pass;
    String accountType;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.txtLogin);
        btnNewAccount = findViewById(R.id.txtNewAccount);
        email = findViewById(R.id.txtEmail);
        pass = findViewById(R.id.txtPassword);
        DB = new DBHelper(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);



        //btn to delete record from db
        Button btnDel = findViewById(R.id.btnDel);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, PasswordRecovery.class));
            }
        });

//        btnDel.setOnClickListener(new View.OnClickListener() {
//            boolean isDeleted;
//            @Override
//            public void onClick(View view) {
//                String id = (email.getText().toString());
//                DB.deleteUserAccount(id);
//                if (isDeleted) {
//                    Toast.makeText(LoginActivity.this, "Nothing to Delete", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Record deleted.", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

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
                        boolean c = DB.checkUserAccount(user, password);
                        if (accountType.equals("Business")) {
                            if (c == true) {
                                startActivity(new Intent(LoginActivity.this, BusinessMain.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Login or Password doesn't exist, try again.", Toast.LENGTH_SHORT).show();
                            }

                        } else if (accountType.equals("Customer")) {
                            if (c == true) {
                                startActivity(new Intent(LoginActivity.this, CustomerMain.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Login or Password doesn't exist, try again.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        SharedPreferences.Editor editor=getSharedPreferences("user",MODE_PRIVATE).edit();
                        editor.putString("user",user);
                        editor.apply();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Account not found.", Toast.LENGTH_SHORT).show();
                    }
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