package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewAccount extends AppCompatActivity {
    Spinner spnState;
    Button btnCreate;
    EditText txtFName, txtLName, txtPhone, txtAddress, txtCity, txtEmail, txtPassword, txtConfirmPassword, businessName;
    TextView txtErrorP, txtErrorE;
    RadioButton radioCustomer, radioBusiness;
    RadioGroup radioGroup;
    DBHelper DB = new DBHelper(this);

    String accountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        btnCreate = findViewById(R.id.btnCreate);
        txtFName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        txtCity = findViewById(R.id.txtCity);
        spnState = findViewById(R.id.spnStates);
        txtEmail = findViewById(R.id.txtEmailNewAccount);
        txtPassword = findViewById(R.id.txtCreatePassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        btnCreate = findViewById(R.id.btnCreate);
        txtErrorE = findViewById(R.id.txtErrorEmail);
        txtErrorP = findViewById(R.id.txtErrorPassword);
        radioCustomer = findViewById(R.id.radioCustomer);
        radioBusiness = findViewById(R.id.radioBusiness);
        radioGroup = findViewById(R.id.radioGroup);
        businessName = findViewById(R.id.txtBusiness);
        businessName.setVisibility(View.GONE);

        //When the user select the type of account, it will change the fields required
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioCustomer:
                        businessName.setVisibility(View.GONE);
                        txtFName.setVisibility(View.VISIBLE);
                        accountType = "Customer";
                        break;
                    case R.id.radioBusiness:
                        businessName.setVisibility(View.VISIBLE);
                        txtFName.setVisibility(View.GONE);
                        accountType = "Business";
                        break;
                }
            }
        });
        //When the user click on "Create", it will create a new account
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the information on the form
                //Get the name depending of the type of account
                String name = "";
                if (accountType.equals("Business")){
                    name = businessName.getText().toString();
                }else if(accountType.equals("Customer")){
                    name = txtFName.getText().toString();
                }
                String type = accountType;
                String phone = txtPhone.getText().toString();
                String address = txtAddress.getText().toString();
                String city = txtCity.getText().toString();
                String state = spnState.getSelectedItem().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();

                //constructor for  new users
                CustomerModel customerModel ;
//                BusinessModel businessModel ;

                try {
                        customerModel = new CustomerModel(type, email, password, name, phone, address, city, state);
                        DB.addCustomer(customerModel);
                        if(type.equals("Customer")){
                            startActivity(new Intent(NewAccount.this,CustomerMain.class));
                        } else if (type.equals("Business")){
                            startActivity(new Intent(NewAccount.this,BusinessMain.class));
                        }
                } catch (Exception e) {
                    if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty()) {
                        //Check if the information is empty
                        Toast.makeText(NewAccount.this, "Please make sure to fill the form", Toast.LENGTH_LONG).show();
                    } else if (!isEmailValid(email)) {
                        //Check if email is valid
                        txtErrorE.setText("Please make sure to put a valid email");
                    } else if (!password.equals(confirmPassword)) {
                        //Check if the passwords match
                        txtErrorP.setText("Please make sure your passwords match");
                    } else if (!isPasswordValid(password)) {
                        //Check if the password is valid
                        txtErrorP.setText("The password must contain at least:" + "\n - One lowercase letter" + "\n - One uppercase letter " + "\n - One digit " + "\n -One special character" + "\n - At least 8 characters long\n");
                    } else {
                        //Case everything is ok, create a new account
                        txtErrorP.setText("");
                        txtErrorE.setText("");
                        Toast.makeText(NewAccount.this, "Account created!!", Toast.LENGTH_LONG).show();
                    }
//                    DB.close();
                }
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        String pattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        // The pattern above uses a regular expression to match the password
        // The password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character
        // The password must be at least 8 characters long

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        //return m.matches();
        return  true;
    }
}