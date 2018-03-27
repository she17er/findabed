package com.she17er.seanm.findabed;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activity containing a signup form for users
 */

public class SignupScreen extends AppCompatActivity {

    //UI references
    private EditText username, email, phone, password, passwordCheck, age;
    private Spinner genderSpinner, vetSpinner, roleSpinner, accountSpinner;
    private Button submit;

    //Temporary map for M4 - M6 that holds account data
    public static Map<String, String[]> accounts = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializes all UI components
        submit = (Button) findViewById(R.id.submitButton);
        username = (EditText) findViewById(R.id.usernameField);
        email = (EditText) findViewById(R.id.emailField);
        phone = (EditText) findViewById(R.id.phoneField);
        password = (EditText) findViewById(R.id.passwordField);
        passwordCheck = (EditText) findViewById(R.id.reenterPasswordField);
        age = (EditText) findViewById(R.id.ageField);

        populateSpinners();
        addButtonListener();

        //Generates back button on action bar
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Adds all predefined options to signup spinners
     * These spinners include genderSpinner, vetSpinner, roleSpinner, and accountSpinner
     */
    private void populateSpinners() {
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        List<String> genderEntries = new ArrayList<>();
        genderEntries.add("Male");
        genderEntries.add("Female");
        genderEntries.add("Other");
        ArrayAdapter<String> dataAdapterGender = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, genderEntries);
        dataAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(dataAdapterGender);

        vetSpinner = (Spinner) findViewById(R.id.vetStatusSpinner);
        List<String> vetStatEntries = new ArrayList<>();
        vetStatEntries.add("Non-Veteran");
        vetStatEntries.add("Veteran");
        ArrayAdapter<String> dataAdapterVet = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, vetStatEntries);
        dataAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vetSpinner.setAdapter(dataAdapterVet);

        accountSpinner = (Spinner) findViewById(R.id.accountStateSpinner);
        List<String> accountEntries = new ArrayList<>();
        accountEntries.add("User");
        accountEntries.add("Admin");
        ArrayAdapter<String> dataAdapterAccount = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, accountEntries);
        dataAdapterAccount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(dataAdapterAccount);

        roleSpinner = (Spinner) findViewById(R.id.roleDescriptionSpinner);
        List<String> roleEntries = new ArrayList<>();
        roleEntries.add("Shelter Seeker");
        roleEntries.add("Shelter Worker");
        ArrayAdapter<String> dataAdapterRole = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, roleEntries);
        dataAdapterRole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(dataAdapterRole);
    }
    /**
     * Creates a button listener that adds user accounts to the accounts map
     */
    private void addButtonListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid()) {
                    String[] accountData = {password.getText().toString(), accountSpinner.getSelectedItem().toString()};
                    accounts.put(username.getText().toString().toLowerCase(), accountData);
                    Intent successIntent = new Intent(v.getContext(), WelcomeScreen.class);
                    successIntent.putExtra("FromSignup", "Account successfully created!");
                    startActivityForResult(successIntent, 0);
                }
            }
        });
    }


    /**
     * Checks if all entered signup data is valid
     * Throws error messages on signup textboxes that are invalid
     * @return Whether or not the signup data is valid
     */
    private boolean checkValid() {
        boolean validLogin = true;
        if (TextUtils.isEmpty(username.getText())) {
            username.setError("Username is required");
            validLogin = false;
        }
        if (TextUtils.isEmpty(email.getText())) {
            email.setError("Email is required");
            validLogin = false;
        } else if (!email.getText().toString().contains("@")) {
            email.setError("Not a valid email");
            validLogin = false;
        }
        if (TextUtils.isEmpty(phone.getText())) {
            phone.setError("Phone number is required");
            validLogin = false;
        }
        if (TextUtils.isEmpty(password.getText())) {
            password.setError("Password is required");
            validLogin = false;
        } else if (password.getText().toString().length() < 6) {
            password.setError("Passwords must contain more than 5 characters");
            validLogin = false;
        }
        if (TextUtils.isEmpty(passwordCheck.getText())) {
            passwordCheck.setError("Please re-enter your password");
            validLogin = false;
        }
        //@todo Fix password confirmation, I have no clue why this always failes :(
        // else if (!password.getText().equals(passwordCheck.getText().toString())) {
//            passwordCheck.setError("Passwords must match");
//            validLogin = false;
//        }
        if (TextUtils.isEmpty(age.getText())) {
            age.setError("Age is required");
            validLogin = false;
        } else if (age.getText().length() > 3) {
            age.setError("Please enter a valid age");
            validLogin = false;
        }
        return validLogin;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
