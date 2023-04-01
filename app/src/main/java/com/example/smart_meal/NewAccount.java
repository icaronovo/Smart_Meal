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
    EditText username, txtPhone, txtAddress, txtCity, txtEmail, txtPassword, txtConfirmPassword;
    TextView txtErrorP, txtErrorE;
    RadioButton radioCustomer, radioBusiness;
    RadioGroup radioGroup;
    DBHelper DB = new DBHelper(this);

    String accountType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        btnCreate = findViewById(R.id.btnUpdate);
        username = findViewById(R.id.nameUpdate);
        txtPhone = findViewById(R.id.phoneUpdate);
        txtAddress = findViewById(R.id.addressUpdate);
        txtCity = findViewById(R.id.cityUpdate);
        spnState = findViewById(R.id.spnStatesUpdate);
        txtEmail = findViewById(R.id.emailUpdate);
        txtPassword = findViewById(R.id.passUpdate);
        txtConfirmPassword = findViewById(R.id.confirmPassUpdate);
        btnCreate = findViewById(R.id.btnUpdate);
        txtErrorE = findViewById(R.id.txtErrorEmail);
        txtErrorP = findViewById(R.id.txtErrorPassword);
        radioCustomer = findViewById(R.id.radioCustomer);
        radioBusiness = findViewById(R.id.radioBusiness);
        radioGroup = findViewById(R.id.radioGroup);

        //When the user select the type of account, it will change the fields required

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.radioCustomer:
                        username.setHint("Full Name");
                        username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_person_outline_24, 0, 0, 0);
                        accountType = "Customer";
                        break;
                    case R.id.radioBusiness:
                        username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shop_icon, 0, 0, 0);
                        username.setHint("Business");
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
                String name = username.getText().toString();
                String phone = txtPhone.getText().toString();
                String address = txtAddress.getText().toString();
                String city = txtCity.getText().toString();
                String state = spnState.getSelectedItem().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();

                //constructor for  new users
                CustomerModel customerModel;
                if (accountType == null || accountType.equals("")) {
                    Toast.makeText(NewAccount.this, "Please select account type", Toast.LENGTH_LONG).show();

                } else {
                    try {
                        Boolean checkEmail = DB.checkIfEmailExists(email);
                        if (!checkEmail) {
                            customerModel = new CustomerModel(accountType, email, password, name, phone, address, city, state, R.drawable.ic_baseline_person_outline_24);
                            DB.addCustomer(customerModel);

                            if (accountType.equals("Customer")) {
                                startActivity(new Intent(NewAccount.this, CustomerMain.class));
                            } else if (accountType.equals("Business")) {
                                startActivity(new Intent(NewAccount.this, BusinessMain.class));
                            }
                        } else {
                            Toast.makeText(NewAccount.this, "This email is already in use.", Toast.LENGTH_LONG).show();
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

                    }
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
        return true;
    }
}