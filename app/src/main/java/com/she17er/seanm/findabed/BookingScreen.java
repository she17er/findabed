package com.she17er.seanm.findabed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class BookingScreen extends AppCompatActivity {

    //UI Components
    EditText numberText;
    Button confirmButton;

    //Shelter Data
    int shelterPosition;
    Shelter shelter;
    ArrayList<Shelter> currStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_screen);

        //Removes actionbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Adds UI components
        numberText = (EditText) findViewById(R.id.numberText);
        confirmButton = (Button) findViewById(R.id.confirmButton);

        //Initializes shelter data
        Intent intent = this.getIntent();
        if (intent.getExtras() != null) {
            shelterPosition = Integer.parseInt(intent.getExtras().getString("shelterID"));
            shelter = Dashboard.masterShelters.get(shelterPosition);
        }
    }
}
