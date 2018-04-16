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
    private EditText username;
    private EditText email;
    private EditText phone;
    private EditText password;
    private EditText passwordCheck;
    private EditText age;
    private Spinner genderSpinner;
    private Spinner vetSpinner;
    private Spinner roleSpinner;
    private Spinner accountSpinner;
    private Button submit;
    private String currUsernames;
    private List<String> allNames;

    //URL for the Heroku backend
    String backendURL = "https://she17er.herokuapp.com/api/users/newUsers";
    String userNameURL = "https://she17er.herokuapp.com/api/users/getUserNames";

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializes all UI components
        submit = findViewById(R.id.submitButton);
        username = findViewById(R.id.usernameField);
        email = findViewById(R.id.emailField);
        phone = findViewById(R.id.phoneField);
        password = findViewById(R.id.passwordField);
        passwordCheck = findViewById(R.id.reenterPasswordField);
        age = findViewById(R.id.ageField);

        populateSpinners();
        addButtonListener();


        AsyncTaskRunnerGetUsername AsyncGetUsername = new AsyncTaskRunnerGetUsername();
        AsyncGetUsername.execute("start");

        try {
            currUsernames = AsyncGetUsername.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        allNames = new ArrayList<>();
        currUsernames = currUsernames.substring(1, currUsernames.length() - 1);
        while (currUsernames.contains("username")) {
            int index = currUsernames.indexOf("username");
            int curIndex = currUsernames.indexOf('}', index);
            allNames.add(currUsernames.substring(index + 11, curIndex - 1));
            currUsernames = currUsernames.substring(curIndex);
        }

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
        genderSpinner = findViewById(R.id.genderSpinner);
        List<String> genderEntries = new ArrayList<>();
        genderEntries.add("Male");
        genderEntries.add("Female");
        genderEntries.add("Other");
        ArrayAdapter<String> dataAdapterGender = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, genderEntries);
        dataAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(dataAdapterGender);

        vetSpinner = findViewById(R.id.vetStatusSpinner);
        List<String> vetStatEntries = new ArrayList<>();
        vetStatEntries.add("Non-Veteran");
        vetStatEntries.add("Veteran");
        ArrayAdapter<String> dataAdapterVet = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, vetStatEntries);
        dataAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vetSpinner.setAdapter(dataAdapterVet);

        accountSpinner = findViewById(R.id.accountStateSpinner);
        List<String> accountEntries = new ArrayList<>();
        accountEntries.add("User");
        accountEntries.add("Admin");
        ArrayAdapter<String> dataAdapterAccount = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, accountEntries);
        dataAdapterAccount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(dataAdapterAccount);

        roleSpinner = findViewById(R.id.roleDescriptionSpinner);
        List<String> roleEntries = new ArrayList<>();
        roleEntries.add("Shelter Seeker");
        roleEntries.add("Shelter Worker");
        ArrayAdapter<String> dataAdapterRole = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, roleEntries);
        dataAdapterRole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(dataAdapterRole);
    }
    /**
     * Creates a button listener that adds the new user to the database
     * if all input is valid
     */
    private void addButtonListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid()) {
                    AsyncTaskRunner postReq = new AsyncTaskRunner();
                    postReq.execute("start");
                    Intent successIntent = new Intent(v.getContext(), WelcomeScreen.class);
                    successIntent.putExtra("FromSignUp", "Account successfully created!");
                    startActivityForResult(successIntent, 0);
                }
            }
        });
    }

    /**
     * Checks if all entered sign-up data is valid
     * Throws error messages on sign-up text-boxes that are invalid
     * @return Whether or not the sign-up data is valid
     */
    private boolean checkValid() {
        boolean validLogin = true;
        if (TextUtils.isEmpty(username.getText())) {
            username.setError("Username is required");
            validLogin = false;
        }
        for (String s: allNames) {
            if (username.getText().toString().equals(s)) {
                username.setError("this username has been registered");
                validLogin = false;
                break;
            }
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
        //@todo Fix password confirmation, I have no clue why this always fails :(
         else if (!password.getText().toString().equals(passwordCheck.getText().toString())) {
            passwordCheck.setError("Passwords must match");
            validLogin = false;
        }
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
    public final boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This class executes the process of getting existing usernames from backend.
     * Getting all existing usernames prevents users from signing up with the same usernames.
     */
    private class AsyncTaskRunnerGetUsername extends AsyncTask<String, String, String> {

        @Override
        protected final String doInBackground(String... params) {
            try {
                URL url1 = new URL(userNameURL);
                HttpURLConnection unConnection = (HttpURLConnection) url1.openConnection();
                unConnection.setRequestProperty("Content-Type", "application/json");
                unConnection.setRequestMethod("GET");
                unConnection.connect();

                BufferedReader in = new BufferedReader(new InputStreamReader(unConnection.getInputStream()));
                String inputLine = "";
                StringBuilder userNameContent = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    userNameContent.append(inputLine);
                    return inputLine;
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
        @Override
        protected final void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected final void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected final void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }

    /**
     * This class executes the process of registering a new user in the database.
     * It will give a status 200 if the process is successful.
     */
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected final String doInBackground(String... params) {
            try {

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

                DataOutputStream localDataOutputStream = new DataOutputStream(connection.getOutputStream());
                localDataOutputStream.writeBytes(user.toString());
                localDataOutputStream.flush();
                localDataOutputStream.close();

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected final void onPostExecute(String s) {
            Log.d("targetString", s);
            super.onPostExecute(s);
        }

        @Override
        protected final void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected final void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}
