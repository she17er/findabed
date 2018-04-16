package com.she17er.seanm.findabed;

import android.R.id;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.she17er.seanm.findabed.R.layout;

/**
 * Screen that shows user's profile data
 */

public class ProfileScreen extends AppCompatActivity {

    private TextView profileText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_profile_screen);

        this.profileText = this.findViewById(R.id.profileText);
        //profileText.setText("Welcome " + LoginScreen.currentUser + ", you are a
        // " + LoginScreen.accountState);

        //Generates back button on action bar
        if (getActionBar() != null) {
            this.getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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

    @Override
    public String toString() {
        return "ProfileScreen{" +
                "profileText=" + this.profileText +
                '}';
    }
}
