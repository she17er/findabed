package com.she17er.seanm.findabed;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignupScreen extends AppCompatActivity {

    public static Map<String, String> accounts = new HashMap<>();
    private EditText username, email, phone, password, passwordCheck;
    private Spinner genderSpinner, vetSpinner, roleSpinner, accountSpinner;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        submit = (Button) findViewById(R.id.submitButton);
        username = (EditText) findViewById(R.id.usernameField);
        email = (EditText) findViewById(R.id.emailField);
        phone = (EditText) findViewById(R.id.phoneField);
        password = (EditText) findViewById(R.id.passwordField);
        passwordCheck = (EditText) findViewById(R.id.reenterPasswordField);

        populateSpinners();
        addButtonListener();
//        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

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

    private void addButtonListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accounts.put(username.getText().toString(), password.getText().toString());
                Intent successIntent = new Intent(v.getContext(), WelcomeScreen.class);
                startActivityForResult(successIntent, 0);
                Snackbar confirmationSnackbar = Snackbar.make(v, "TEST", Snackbar.LENGTH_LONG);
                confirmationSnackbar.show();
            }
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
