package com.she17er.seanm.findabed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.opencsv.CSVWriter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The booking screen for booking a new shelter
 */

public class BookingScreen extends AppCompatActivity {

    //UI Components
    EditText numberText;
    Button confirmButton;
    Button cancelBookingButton;
    Button cancelButton;

    //Needs to add id to use it
    String bookingURL = "https://she17er.herokuapp.com/api/shelter/updateCapacity/";

    //Shelter Data
    int shelterPosition;
    String shelterID;
    Shelter shelter;
    int bookingNumber;

    SharedPreferences sharedPreferences;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_screen);

        //Removes actionbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Adds UI components
        numberText = findViewById(R.id.numberText);
        confirmButton = findViewById(R.id.confirmBookingButton);
        cancelBookingButton = findViewById(R.id.cancelBookingButton);
        cancelButton = findViewById(R.id.cancelButton);
        addButtonListener();

        //Initializes shelter data
        Intent intent = this.getIntent();
        if (intent.getExtras() != null) {
            shelterPosition = Integer.parseInt(intent.getExtras().getString("shelterID"));
            shelter = Dashboard.masterShelters.get(shelterPosition);
            shelterID = shelter.getBackendID();
        }

        //Hides cancelBookingButton if there is no booking for a user to cancel
        if (shelter.getCurrentCapacity() == 0) {
            cancelBookingButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * toString for BookingScreen
     * @return BookingScreen as a String
     */
    public String toString() {
        return "Booking screen for " + shelter.getName() + ", ID: " + shelterID;
    }

    /**
     * Adds all button listeners
     */
    private void addButtonListener() {
        //Button listener that beings a booking
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberText.getText().toString().equals("")) {
                    numberText.setError("Please enter a number");
                } else {
                    bookingNumber = Integer.parseInt(numberText.getText().toString());
                    if ((bookingNumber + shelter.getCurrentCapacity()) > shelter.getCapacity()) {
                        numberText.setError("Not enough space in the shelter");
                    } else {
                        //Writes the name of the shelter booked to a local variable
//                        SharedPreferences.Editor userData = sharedPreferences.edit();
//                        userData.putString("booking", shelter.getName()).apply();

                        AsyncTaskRunner makeBooking = new AsyncTaskRunner();
                        makeBooking.execute("start");
                        Intent intent = new Intent(v.getContext(), Dashboard.class);
                        startActivityForResult(intent, 0);
                    }
                }
            }
        });

        //Button to cancel a booking and return to the Dashboard
        cancelBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences userData = getSharedPreferences("booking", 0);
//                if (userData.) {
//
//
//                    Intent intent = new Intent(v.getContext(), Dashboard.class);
//                    startActivityForResult(intent, 0);
//                } else {
//
//                }
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
     * This class executes the process of booking a shelter and update the backend database.
     */
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected final String doInBackground(String... params) {
            try {

                URL url = new URL(bookingURL + shelterID);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestMethod("POST");
                connection.connect();

                JSONObject booking = new JSONObject();
                booking.put("currCapacity", shelter.getCurrentCapacity() + bookingNumber);

                DataOutputStream localDataOutputStream = new DataOutputStream(connection.getOutputStream());
                localDataOutputStream.writeBytes(booking.toString());
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
