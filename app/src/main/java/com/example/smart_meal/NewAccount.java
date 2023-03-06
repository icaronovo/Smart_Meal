package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        btnCreate = findViewById(R.id.btnCreate);
        txtFName = findViewById(R.id.txtName);
        txtLName = findViewById(R.id.txtLastName);
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
                        txtLName.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioBusiness:
                        businessName.setVisibility(View.VISIBLE);
                        txtFName.setVisibility(View.GONE);
                        txtLName.setVisibility(View.GONE);
                        break;
                }
            }
        });
        //When the user click on "Create", it will create a new account
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the information on the form
                String name = txtFName.getText().toString();
                int selectedRadioButton = radioGroup.getCheckedRadioButtonId();
                //In case that it is a Customer, get their last name
                if (selectedRadioButton == radioCustomer.getId()) {
                    String lastName = txtLName.getText().toString();
                }
                String phone = txtPhone.getText().toString();
                String address = txtAddress.getText().toString();
                String city = txtCity.getText().toString();
                String state = spnState.getSelectedItem().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();
                String bName = businessName.getText().toString();

                //constructor for  new users
                CustomerModel customerModel = null;
                BusinessModel businessModel = null;
                try {
                    if (selectedRadioButton == radioBusiness.getId()) {
                        businessModel = new BusinessModel(-1, bName, phone, address, password, city, state, email);
                        DB.addBusiness(businessModel);

                    } else if (selectedRadioButton == radioCustomer.getId()) {
                        customerModel = new CustomerModel(-1, name, txtLName.getText().toString(), phone, password, address, city, state, email);
                        DB.addCustomer(customerModel);
                    }

                } catch (Exception e) {
                    if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || bName.isEmpty()) {
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
                    DBHelper database = new DBHelper(NewAccount.this);
                    boolean success = database.addCustomer(customerModel);
                    database.addBusiness(businessModel);
                    Toast.makeText(NewAccount.this, "success", Toast.LENGTH_SHORT).show();

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
        return m.matches();
    }
}