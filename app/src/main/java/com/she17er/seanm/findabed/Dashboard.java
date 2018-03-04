package com.she17er.seanm.findabed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * The screen the user first sees upon successfully signing in
 * Generates a list of shelters from a csv file
 */

public class Dashboard extends AppCompatActivity {

    //UI references
    private TextView welcomeText;
    private TextView accountStateText;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Initializes all welcome text
        welcomeText = (TextView) findViewById(R.id.dashboardWelcomeText);
        welcomeText.setText("Welcome " + LoginScreen.currentUser.toString());
        accountStateText = (TextView) findViewById(R.id.dashboardAccountStateText);
        accountStateText.setText("You are a "
            + LoginScreen.accountState);

        //Sets up logout button functionality
        logout = (Button) findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), WelcomeScreen.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
