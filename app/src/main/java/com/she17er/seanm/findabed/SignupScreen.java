package com.she17er.seanm.findabed;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity containing a signup form for users
 */

public class SignupScreen extends AppCompatActivity {

    //UI references
    private EditText username, email, phone, password, passwordCheck, age;
    private Spinner genderSpinner, vetSpinner, roleSpinner, accountSpinner;
    private Button submit;

    //URL for the Heroku backend
    String backendURL = "https://she17er.herokuapp.com/api/users/newUsers";
    String userNameURL = "https://she17er.herokuapp.com/api/users/getUserName";

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
                    AsyncTaskRunner postReq = new AsyncTaskRunner();
                    postReq.execute("start");
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

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {

//                URL userUrl = new URL(userNameURL);
//                HttpURLConnection connection1 = (HttpURLConnection) userUrl.openConnection();
//                connection1.setDoOutput(true);
//                connection1.setRequestProperty("Content-Type", "application/json");
//                connection1.setRequestMethod("POST");
//                connection1.connect();
//
//                JSONObject check = new JSONObject();
//                check.put("username", username.getText());
//
//                DataOutputStream userNameDataOutputStream = new DataOutputStream(connection1.getOutputStream());
//                userNameDataOutputStream.writeBytes(check.toString());
//                userNameDataOutputStream.flush();
//                userNameDataOutputStream.close();
//
//                Log.d("connection code", "" + connection1.getResponseCode());
//
//                BufferedReader userNamein = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
//                String userNameinputLine;
//                userNameinputLine = userNamein.readLine();
//                Log.d("usernameRes", userNameinputLine);
//                userNamein.close();
//
//                if (userNameinputLine.equals("exists!")) {
//                    return "exists!";
//                }

                URL url = new URL(backendURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestMethod("POST");
                connection.connect();

                JSONObject user = new JSONObject();
                user.put("username", username.getText().toString().toLowerCase());
                user.put("age", age.getText());
                user.put("gender", genderSpinner.getSelectedItem().toString());
                user.put("vet_S", vetSpinner.getSelectedItem().toString());
                user.put("contact.phone", phone.getText().toString());
                user.put("contact.email", email.getText().toString());
                user.put("account_State", accountSpinner.getSelectedItem().toString());
                user.put("password", password.getText().toString());
                user.put("role", roleSpinner.getSelectedItem().toString());
                user.put("login", "false");

                Log.d("json", user.toString());


                DataOutputStream localDataOutputStream = new DataOutputStream(connection.getOutputStream());
                localDataOutputStream.writeBytes(user.toString());
                localDataOutputStream.flush();
                localDataOutputStream.close();

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                Log.d("res", content.toString());
                in.close();


            } catch (Exception e) {
                Log.d("POSTError", e.toString());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("targetString", s);
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}
