package com.she17er.seanm.findabed;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Welcome screen that the application starts with
 */
public class WelcomeScreen extends AppCompatActivity {

    //UI references
    Button login;
    Button signup;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //Login button functionality, switches to login activity screen
        login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent loginIntent = new Intent(view.getContext(), LoginScreen.class);
               startActivityForResult(loginIntent, 0);
           }
        });

        //Signup button functionality, switches to signup activity screen
        signup = findViewById(R.id.signupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(view.getContext(), SignupScreen.class);
                startActivityForResult(signupIntent, 0);
            }
        });

        //Snackbar that launches when an account is successfully created
        View parentLayout = getWindow().getDecorView().findViewById(android.R.id.content);
        Intent intent = this.getIntent();
        if (intent.getExtras() != null) {
            Snackbar confirmationSnackBar = Snackbar.make(parentLayout,
                    intent.getExtras().getString("FromSignup"), Snackbar.LENGTH_LONG);
            confirmationSnackBar.show();
        }
    }
}
