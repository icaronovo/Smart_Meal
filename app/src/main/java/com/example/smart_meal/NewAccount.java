package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewAccount extends AppCompatActivity {
    Spinner spinnerTypeUser, spnState;
    Button btnCreate;
    EditText txtFName, txtLName, txtPhone, txtAddress, txtCity, txtEmail, txtPassword, txtConfirmPassword;
    TextView txtErrorP, txtErrorE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        spinnerTypeUser = findViewById(R.id.spnType);
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

        //In case it is selected the "Customer" user, the form for customer will appear.
        //Otherwise, will be the "Business" form
        spinnerTypeUser.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0){
                            txtFName.setHint("First Name");
                            txtLName.setVisibility(View.VISIBLE);
                        } else if(position == 1){
                            txtFName.setHint("Business Name");
                            txtLName.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        //When the user click on "Create", it will create a new account
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the information on the form
                String name = txtFName.getText().toString();
                String userType = spinnerTypeUser.getSelectedItem().toString();
                //In case that it is a Customer, get their last name
                if(userType.equals("I am customer")){
                    String lastName = txtLName.getText().toString();
                }
                String phone = txtPhone.getText().toString();
                String address = txtAddress.getText().toString();
                String city = txtCity.getText().toString();
                String state = spnState.getSelectedItem().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();

                if(name.isEmpty() || phone.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty()){
                //Check if the information is empty
                    Toast.makeText(NewAccount.this,"Please make sure to fill the form", Toast.LENGTH_LONG).show();
                }
                else if(!isEmailValid(email)){
                //Check if email is valid
                    txtErrorE.setText("Please make sure to put a valid email");
                }
                else if(!password.equals(confirmPassword)){
                //Check if the passwords match
                    txtErrorP.setText("Please make sure your passwords match");
                }
                else if(!isPasswordValid(password)){
                //Check if the password is valid
                    txtErrorP.setText("The password must contain at least:" +
                            "\n - One lowercase letter" +
                            "\n - One uppercase letter " +
                            "\n - One digit " +
                            "\n -One special character" +
                            "\n - At least 8 characters long\n");
                }
                else{
                //Case everything is ok, create a new account
                    txtErrorP.setText("");
                    txtErrorE.setText("");
                    Toast.makeText(NewAccount.this,"Account created!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        String pattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        // The pattern above uses a regular expression to match the password
        // The password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character
        // The password must be at least 8 characters long

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        return m.matches();
    }
}