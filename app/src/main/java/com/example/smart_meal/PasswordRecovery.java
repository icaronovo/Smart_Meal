package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordRecovery extends AppCompatActivity {

    DBHelper DB = new DBHelper(this);
    TextView email,pass;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        email=findViewById(R.id.editTextTextPersonName);
        pass = findViewById(R.id.editTextTextPersonName2);

        btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String user = email.getText().toString();
                String password = pass.getText().toString();

               boolean success = DB.accountUpdate(user,password);
               if (success){
                   startActivity(new Intent(PasswordRecovery.this,LoginActivity.class));
               }
                else {
                   Toast.makeText(PasswordRecovery.this, "Please check  if your email is correct", Toast.LENGTH_LONG).show();

               }

            }
        });
    }
}