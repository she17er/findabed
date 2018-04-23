package com.she17er.seanm.findabed;

import android.R.id;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.she17er.seanm.findabed.R.layout;

/**
 * Welcome screen that the application starts with
 */
@TargetApi(21)
public class WelcomeScreen extends AppCompatActivity {

    @Override
    public String toString() {
        return "WelcomeScreen{" +
                "login=" + this.login +
                ", signup=" + this.signup +
                '}';
    }

    //UI references
    private Button login;
    private Button signup;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_welcome_screen);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        //Login button functionality, switches to login activity screen
        this.login = this.findViewById(R.id.loginButton);
        this.login.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(final View view) {
               final Intent loginIntent = new Intent(view.getContext(), LoginScreen.class);
               WelcomeScreen.this.startActivityForResult(loginIntent, 0);
               overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
           }
        });

        //Signup button functionality, switches to signup activity screen
        this.signup = this.findViewById(R.id.signupButton);
        this.signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent signupIntent = new Intent(view.getContext(), SignupScreen.class);
                WelcomeScreen.this.startActivityForResult(signupIntent, 0);
            }
        });

        //Snackbar that launches when an account is successfully created
        final View parentLayout = this.getWindow().getDecorView().findViewById(id.content);
        final Intent intent = getIntent();
        if (intent.getExtras() != null) {
            final Snackbar confirmationSnackBar = Snackbar.make(parentLayout,
                    intent.getExtras().getString("FromSignup"), Snackbar.LENGTH_LONG);
            confirmationSnackBar.show();
        }
    }
}
