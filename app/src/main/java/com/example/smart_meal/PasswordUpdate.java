package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordUpdate extends AppCompatActivity {

    DBHelper DB = new DBHelper(this);
    TextView email, pass, name;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        email = findViewById(R.id.editTextTextPersonName);
        pass = findViewById(R.id.editTextTextPersonName2);
        name = findViewById(R.id.checkName);

        btnReset = findViewById(R.id.btnReset);
        SharedPreferences sharedPreferences = getSharedPreferences
                ("data", MODE_PRIVATE);
        String emailSP = sharedPreferences.getString("EmailCust", "");
        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String emailSP = email.getText().toString();
                String password = pass.getText().toString();
                String namePrev = name.getText().toString();

                boolean success = DB.accountUpdate(emailSP, password, namePrev);
                if (success) {
                    Toast.makeText(PasswordUpdate.this,"Password Updated!",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(PasswordUpdate.this, LoginActivity.class));
                } else {
                    Toast.makeText(PasswordUpdate.this, "Please check  if your email/name is correct", Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}