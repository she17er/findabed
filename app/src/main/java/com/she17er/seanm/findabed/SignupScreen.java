package com.she17er.seanm.findabed;

import android.R.id;
import android.R.layout;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Activity containing a signup form for users
 */

public class SignupScreen extends AppCompatActivity {

    //UI references
    protected EditText username;
    protected EditText email;
    protected EditText phone;
    protected EditText password;
    protected EditText passwordCheck;
    protected EditText age;
    protected Spinner genderSpinner;
    protected Spinner vetSpinner;
    protected Spinner roleSpinner;
    protected Spinner accountSpinner;
    protected Button submit;
    protected String currUsernames;
    protected List<String> allNames;

    //URL for the Heroku backend
    private final String backendURL = "https://she17er.herokuapp.com/api/users/newUsers";
    private final String userNameURL = "https://she17er.herokuapp.com/api/users/getUserNames";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_signup_screen);
        final Toolbar toolbar = this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        //Initializes all UI components
        this.submit = this.findViewById(R.id.submitButton);
        this.username = this.findViewById(R.id.usernameField);
        this.email = this.findViewById(R.id.emailField);
        this.phone = this.findViewById(R.id.phoneField);
        this.password = this.findViewById(R.id.passwordField);
        this.passwordCheck = this.findViewById(R.id.reenterPasswordField);
        this.age = this.findViewById(R.id.ageField);

        this.populateSpinners();
        this.addButtonListener();


        final SignupScreen.AsyncTaskRunnerGetUsername AsyncGetUsername = new SignupScreen.AsyncTaskRunnerGetUsername();
        AsyncGetUsername.execute("start");

        try {
            this.currUsernames = AsyncGetUsername.get();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        this.allNames = new ArrayList<>();
        this.currUsernames = this.currUsernames.substring(1, this.currUsernames.length() - 1);
        while (this.currUsernames.contains("username")) {
            final int index = this.currUsernames.indexOf("username");
            final int curIndex = this.currUsernames.indexOf('}', index);
            this.allNames.add(this.currUsernames.substring(index + 11, curIndex - 1));
            this.currUsernames = this.currUsernames.substring(curIndex);
        }

        //Generates back button on action bar
        if (getActionBar() != null) {
            this.getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Adds all predefined options to signup spinners
     * These spinners include genderSpinner, vetSpinner, roleSpinner, and accountSpinner
     */
    private void populateSpinners() {
        this.genderSpinner = this.findViewById(R.id.genderSpinner);
        final List<String> genderEntries = new ArrayList<>();
        genderEntries.add("Male");
        genderEntries.add("Female");
        genderEntries.add("Other");
        final ArrayAdapter<String> dataAdapterGender = new ArrayAdapter<>(this,
                layout.simple_spinner_item, genderEntries);
        dataAdapterGender.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        this.genderSpinner.setAdapter(dataAdapterGender);

        this.vetSpinner = this.findViewById(R.id.vetStatusSpinner);
        final List<String> vetStatEntries = new ArrayList<>();
        vetStatEntries.add("Non-Veteran");
        vetStatEntries.add("Veteran");
        final ArrayAdapter<String> dataAdapterVet = new ArrayAdapter<>(this,
                layout.simple_spinner_item, vetStatEntries);
        dataAdapterGender.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        this.vetSpinner.setAdapter(dataAdapterVet);

        this.accountSpinner = this.findViewById(R.id.accountStateSpinner);
        final List<String> accountEntries = new ArrayList<>();
        accountEntries.add("User");
        accountEntries.add("Admin");
        final ArrayAdapter<String> dataAdapterAccount = new ArrayAdapter<>(this,
                layout.simple_spinner_item, accountEntries);
        dataAdapterAccount.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        this.accountSpinner.setAdapter(dataAdapterAccount);

        this.roleSpinner = this.findViewById(R.id.roleDescriptionSpinner);
        final List<String> roleEntries = new ArrayList<>();
        roleEntries.add("Shelter Seeker");
        roleEntries.add("Shelter Worker");
        final ArrayAdapter<String> dataAdapterRole = new ArrayAdapter<>(this,
                layout.simple_spinner_item, roleEntries);
        dataAdapterRole.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        this.roleSpinner.setAdapter(dataAdapterRole);
    }
    /**
     * Creates a button listener that adds the new user to the database
     * if all input is valid
     */
    private void addButtonListener() {
        this.submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (SignupScreen.this.checkValid()) {
                    final SignupScreen.AsyncTaskRunner postReq = new SignupScreen.AsyncTaskRunner();
                    postReq.execute("start");
                    final Intent successIntent = new Intent(view.getContext(), WelcomeScreen.class);
                    successIntent.putExtra("FromSignUp", "Account successfully created!");
                    SignupScreen.this.startActivityForResult(successIntent, 0);
                }
            }
        });
    }

    /**
     * Sets up test cases for signup screen
     */
    public void setTestCases() {

    }

    @Override
    public String toString() {
        return "SignupScreen{" +
                "username=" + this.username +
                ", email=" + this.email +
                ", phone=" + this.phone +
                ", password=" + this.password +
                ", passwordCheck=" + this.passwordCheck +
                ", age=" + this.age +
                ", genderSpinner=" + this.genderSpinner +
                ", vetSpinner=" + this.vetSpinner +
                ", roleSpinner=" + this.roleSpinner +
                ", accountSpinner=" + this.accountSpinner +
                ", submit=" + this.submit +
                ", currUsernames='" + this.currUsernames + '\'' +
                ", allNames=" + this.allNames +
                ", backendURL='" + this.backendURL + '\'' +
                ", userNameURL='" + this.userNameURL + '\'' +
                '}';
    }

    /**
     * Checks if all entered sign-up data is valid
     * Throws error messages on sign-up text-boxes that are invalid
     * @return Whether or not the sign-up data is valid
     */
    protected boolean checkValid() {
        boolean validLogin = true;
        if (TextUtils.isEmpty(this.username.getText())) {
            this.username.setError("Username is required");
            validLogin = false;
        }
        for (final Iterator<String> iterator = this.allNames.iterator(); iterator.hasNext(); ) {
            final String s = iterator.next();
            if (this.username.getText().toString().equals(s)) {
                this.username.setError("this username has been registered");
                validLogin = false;
                break;
            }
        }
        if (TextUtils.isEmpty(this.email.getText())) {
            this.email.setError("Email is required");
            validLogin = false;
        } else if (!this.email.getText().toString().contains("@")) {
            this.email.setError("Not a valid email");
            validLogin = false;
        }
        if (TextUtils.isEmpty(this.phone.getText())) {
            this.phone.setError("Phone number is required");
            validLogin = false;
        }
        if (TextUtils.isEmpty(this.password.getText())) {
            this.password.setError("Password is required");
            validLogin = false;
        } else if (password.getText().toString().length() < 6) {
            this.password.setError("Passwords must contain more than 5 characters");
            validLogin = false;
        }
        if (TextUtils.isEmpty(this.passwordCheck.getText())) {
            this.passwordCheck.setError("Please re-enter your password");
            validLogin = false;
        } else if (!this.password.getText().toString().equals(this.passwordCheck.getText().toString())) {
            this.passwordCheck.setError("Passwords must match");
            validLogin = false;
        }
        if (TextUtils.isEmpty(this.age.getText())) {
            this.age.setError("Age is required");
            validLogin = false;
        } else if (age.getText().length() > 3) {
            this.age.setError("Please enter a valid age");
            validLogin = false;
        }
        return validLogin;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case id.home:
                finish();
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
        protected String doInBackground(final String... params) {
            try {
                final URL url1 = new URL(SignupScreen.this.userNameURL);
                final HttpURLConnection unConnection = (HttpURLConnection) url1.openConnection();
                unConnection.setRequestProperty("Content-Type", "application/json");
                unConnection.setRequestMethod("GET");
                unConnection.connect();

                final BufferedReader in = new BufferedReader(new InputStreamReader(unConnection
                        .getInputStream()));
                String inputLine = "";
                final StringBuilder userNameContent = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    userNameContent.append(inputLine);
                    return inputLine;
                }
                in.close();
            } catch (final MalformedURLException e) {
                e.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }
            return "";
        }
        @Override
        protected void onPostExecute(final String result) {
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(final String... values) {
            super.onProgressUpdate(values);
        }
    }

    /**
     * This class executes the process of registering a new user in the database.
     * It will give a status 200 if the process is successful.
     */
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(final String... params) {
            try {

                final URL url = new URL(SignupScreen.this.backendURL);
                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestMethod("POST");
                connection.connect();

                final JSONObject user = new JSONObject();
                user.put("username", SignupScreen.this.username.getText().toString().toLowerCase());
                user.put("age", SignupScreen.this.age.getText());
                user.put("gender", SignupScreen.this.genderSpinner.getSelectedItem().toString());
                user.put("vet_S", SignupScreen.this.vetSpinner.getSelectedItem().toString());
                user.put("contact.phone", SignupScreen.this.phone.getText().toString());
                user.put("contact.email", SignupScreen.this.email.getText().toString());
                user.put("account_State", SignupScreen.this.accountSpinner.getSelectedItem().toString());
                user.put("password", SignupScreen.this.password.getText().toString());
                user.put("role", SignupScreen.this.roleSpinner.getSelectedItem().toString());
                user.put("login", "false");

                final DataOutputStream localDataOutputStream = new DataOutputStream(connection
                        .getOutputStream());
                localDataOutputStream.writeBytes(user.toString());
                localDataOutputStream.flush();
                localDataOutputStream.close();

                final BufferedReader in = new BufferedReader(new InputStreamReader(connection
                        .getInputStream()));
                String inputLine;
                final StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();


            } catch (final MalformedURLException e) {
                e.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(final String result) {
            Log.d("targetString", result);
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(final String... values) {
            super.onProgressUpdate(values);
        }
    }
}
