package com.she17er.seanm.findabed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //Login button functionality, switches to login activity screen
        Button login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
               Intent loginIntent = new Intent(view.getContext(), LoginScreen.class);
               startActivityForResult(loginIntent, 0);
           }
        });

        //Signup button functionality, switches to signup activity screen
        Button signup = (Button) findViewById(R.id.signupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent signupIntent = new Intent(view.getContext(), SignupScreen.class);
                startActivityForResult(signupIntent, 0);
            }
        });
    }
}
