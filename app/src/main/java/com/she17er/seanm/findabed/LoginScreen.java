package com.she17er.seanm.findabed;

import android.R.integer;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.she17er.seanm.findabed.R.id;
import com.she17er.seanm.findabed.R.layout;
import com.she17er.seanm.findabed.R.string;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Login screen that offers login via username/password.
 */
public class LoginScreen extends AppCompatActivity {

    //Keep track of the login task to ensure we can cancel it if requested
    private LoginScreen.UserLoginTask mAuthTask;

    // UI references.
    private AutoCompleteTextView mUserView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mUserSignInButton;
    private User currUser;

    //URL for the login route on the backend
    private final String backendURL = "https://she17er.herokuapp.com/api/users/login";

    //Strings for encryption
    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_login_screen);
        this.currUser = new User();

        // Set up the login form.
        this.mUserView = this.findViewById(id.username);
        this.mPasswordView = this.findViewById(id.password);
        this.mPasswordView.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView textView, final int i, final KeyEvent keyEvent) {
                if ((i == EditorInfo.IME_ACTION_DONE) || (i == EditorInfo.IME_NULL)) {
                    LoginScreen.this.attemptLogin();
                    return true;
                }
                return false;
            }
        });

        //Sign in button functionality
        this.mUserSignInButton = this.findViewById(id.username_sign_in_button);
        this.mUserSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                LoginScreen.this.attemptLogin();
            }
        });

        //Login form & loader setup
        this.mLoginFormView = this.findViewById(id.login_form);
        this.mProgressView = this.findViewById(id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid username, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (currUser.getLoginTimes() >= 3) {
            this.mPasswordView.setError(null);
            this.mUserView.setError("Too many trial times");
            return;
        }
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        this.mUserView.setError(null);
        this.mPasswordView.setError(null);

        // Store values at the time of the login attempt
        final String username = this.mUserView.getText().toString().toLowerCase();
        final String password = this.mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one
        if (TextUtils.isEmpty(password)) {
            this.mPasswordView.setError(this.getString(string.error_field_required));
            focusView = this.mPasswordView;
            cancel = true;
        }
        else if (!TextUtils.isEmpty(password) && !this.isPasswordValid(password)) {
            this.mPasswordView.setError(this.getString(string.error_invalid_password));
            focusView = this.mPasswordView;
            cancel = true;
        }

        // Check for a valid username
        if (TextUtils.isEmpty(username)) {
            this.mUserView.setError(this.getString(string.error_field_required));
            focusView = this.mUserView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            this.showProgress(true);
            this.mAuthTask = new LoginScreen.UserLoginTask(username, password);
            this.mAuthTask.execute((Void) null);
            this.currUser.loginTrial();
        }
    }

    /**
     * Checks if a password passes criteria before it is checked against list of users
     */
    private boolean isPasswordValid(final String password) {
        return password.length() > 5;
    }

    /**
     * Shows the progress UI and hides the login form
     */
    @TargetApi(VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        final int shortAnimTime = this.getResources().getInteger(integer.config_shortAnimTime);

        this.mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        this.mLoginFormView.animate().setDuration((long) shortAnimTime).alpha(
                (float) (show ? 0 : 1)).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                LoginScreen.this.mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        this.mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        this.mProgressView.animate().setDuration((long) shortAnimTime).alpha(
                (float) (show ? 1 : 0)).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                LoginScreen.this.mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * Generates a new encryption key
     * @return The encryption key
     * @throws Exception Any exception
     */
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        return key;
    }

    /**
     * Encryptes a given String
     * @param value The String to be encrypted
     * @return The newly encrypted String
     * @throws Exception Any exception
     */
    public static String encrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        String encryptedValue64 = Base64.encodeToString(encryptedByteValue, Base64.DEFAULT);
        return encryptedValue64;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mUser;
        private final String mPassword;

        UserLoginTask(final String username, final String password) {
            super();
            this.mUser = username;
            this.mPassword = password;
        }

        @Override
        public String toString() {
            return "UserLoginTask{" +
                    "mUser='" + this.mUser + '\'' +
                    ", mPassword='" + this.mPassword + '\'' +
                    '}';
        }

        @Override
        protected Boolean doInBackground(final Void... params) {

            try {
                // Simulate network access.
                final URL url = new URL(LoginScreen.this.backendURL);
                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestMethod("POST");
                connection.connect();

                String encryptedPassword = "";
                try {
                    encryptedPassword = encrypt(mPassword);
                } catch (Exception e) {
                    e.printStackTrace();
                    //Crash the app if this fails for security purposes
                    throw new NullPointerException("The login password encryption failed!");
                }

                final JSONObject user = new JSONObject();
                user.put("username", this.mUser);
                user.put("password", encryptedPassword);

                final DataOutputStream localDataOutputStream = new DataOutputStream(connection
                        .getOutputStream());
                localDataOutputStream.writeBytes(user.toString());
                localDataOutputStream.flush();
                localDataOutputStream.close();

                if (connection.getResponseCode() != 200) {
                    return Boolean.FALSE;
                }

                final BufferedReader in = new BufferedReader(new InputStreamReader(connection
                        .getInputStream()));
                final String inputLine;
                inputLine = in.readLine();
                Log.d("LoginRes", inputLine);
                in.close();

            } catch (final MalformedURLException e) {
                e.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }

//            if (SignupScreen.accounts.containsKey(mUser)) {
//                currentUser = mUser.toLowerCase();
//                accountState = SignupScreen.accounts.get(mUser)[1];
//                return SignupScreen.accounts.get(mUser)[0].equals(mPassword);
//            } else {
//                return false;
//            }
            return Boolean.TRUE;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            LoginScreen.this.mAuthTask = null;
            LoginScreen.this.showProgress(false);

            // Performs the actual login if username/ password are valid
            if (result.booleanValue() && (currUser.getLoginTimes() <= 3)) {
                final Intent intent = new Intent(LoginScreen.this.getApplicationContext(), Dashboard.class);
                LoginScreen.this.startActivity(intent);
                LoginScreen.this.finish();
            } else {
                LoginScreen.this.mPasswordView.setError(LoginScreen.this.getString(string.error_incorrect_password));
                LoginScreen.this.mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            LoginScreen.this.mAuthTask = null;
            LoginScreen.this.showProgress(false);
        }
    }
}

