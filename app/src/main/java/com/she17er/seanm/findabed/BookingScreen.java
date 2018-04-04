package com.she17er.seanm.findabed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class BookingScreen extends AppCompatActivity {

    //UI Components
    EditText numberText;
    Button confirmButton, cancelBookingButton, cancelButton;

    //Needs to add id to use it
    String bookingURL = "https://she17er.herokuapp.com/api/shelter/updateCapacity/";

    //Shelter Data
    int shelterPosition;
    Shelter shelter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_screen);

        //Removes actionbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Adds UI components
        numberText = (EditText) findViewById(R.id.numberText);
        confirmButton = (Button) findViewById(R.id.confirmBookingButton);
        cancelBookingButton = (Button) findViewById(R.id.cancelBookingButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
        addButtonListener();

        //Initializes shelter data
        Intent intent = this.getIntent();
        if (intent.getExtras() != null) {
            shelterPosition = Integer.parseInt(intent.getExtras().getString("shelterID"));
            shelter = Dashboard.masterShelters.get(shelterPosition);
        }

        //Hides cancelBookingButton if there is no booking for a user to cancel
        if (shelter.getCurrentCapacity() == 0) {
            cancelBookingButton.setVisibility(View.GONE);
        }
    }

    /**
     * Adds all button listeners
     */
    private void addButtonListener() {
        //Button listener that beings a booking
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("thisisthepath", "" + System.getProperty("user.dir"));
                int bookingNumber = Integer.parseInt(numberText.getText().toString());
                if (bookingNumber + shelter.getCurrentCapacity() > shelter.getCapacity()) {
                    numberText.setError("Not enough space in the shelter");
                } else {
                    writeToCSV(R.raw.data);
                    Intent intent = new Intent(v.getContext(), Dashboard.class);
                    startActivityForResult(intent, 0);
                }
            }
        });

        //Button to return to dashboard
        cancelBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Dashboard.class);
                startActivityForResult(intent, 0);
            }
        });

        //Button to return to dashboard
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Dashboard.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * Reads csv data from the data.csv file for booking purposes
     */
    public void writeToCSV(int id) {
        String csvData = "";
        InputStream inputStream = getResources().openRawResource(id);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        try {
            String s = br.readLine();
            while ((s = br.readLine()) != null) {
                StringBuilder builder = new StringBuilder(s);
                boolean inQuotes = false;
                for (int currentIndex = 0; currentIndex < builder.length(); currentIndex++) {
                    char currentChar = builder.charAt(currentIndex);
                    if (currentChar == '\"') {
                        inQuotes = !inQuotes; // toggle state
                    }
                    if (currentChar == ',' && inQuotes) {
                        builder.setCharAt(currentIndex, ';'); // sets the comma in the quotes to semi-colon
                    }
                }
                csvData += builder.toString();
                csvData += "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("csvdata", csvData);
    }
}
