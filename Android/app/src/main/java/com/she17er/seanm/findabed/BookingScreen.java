package com.she17er.seanm.findabed;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.she17er.seanm.findabed.R.id;
import com.she17er.seanm.findabed.R.layout;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The booking screen for booking a new shelter
 */
@TargetApi(21)
public class BookingScreen extends AppCompatActivity {

    //Needs to add id to use it
    private final String bookingURL = "https://she17er.herokuapp.com/api/shelter/updateCapacity/";
    SharedPreferences sharedPreferences;
    //UI Components
    private EditText numberText;
    private Button confirmButton;
    private Button cancelBookingButton;
    private Button cancelButton;
    //Shelter Data
    private int shelterPosition;
    private String shelterID;
    private Shelter shelter;
    private int bookingNumber;

    @Override
    public String toString() {
        return "BookingScreen{" +
                "numberText=" + this.numberText +
                ", confirmButton=" + this.confirmButton +
                ", cancelBookingButton=" + this.cancelBookingButton +
                ", cancelButton=" + this.cancelButton +
                ", bookingURL='" + this.bookingURL + '\'' +
                ", shelterPosition=" + this.shelterPosition +
                ", shelterID='" + this.shelterID + '\'' +
                ", shelter=" + this.shelter +
                ", bookingNumber=" + this.bookingNumber +
                ", sharedPreferences=" + this.sharedPreferences +
                '}';
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_booking_screen);

        //Removes actionbar title
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Adds UI components
        this.numberText = this.findViewById(id.numberText);
        this.confirmButton = this.findViewById(id.confirmBookingButton);
        this.cancelBookingButton = this.findViewById(id.cancelBookingButton);
        this.cancelButton = this.findViewById(id.cancelButton);
        this.addButtonListener();

        //Initializes shelter data
        final Intent intent = getIntent();
        if (intent.getExtras() != null) {
            this.shelterPosition = Integer.parseInt(intent.getExtras().getString("shelterID"));
            this.shelter = Dashboard.masterShelters.get(this.shelterPosition);
            this.shelterID = this.shelter.getBackendID();
        }

        //Hides cancelBookingButton if there is no booking for a user to cancel
        if (shelter.getCurrentCapacity() == 0) {
            this.cancelBookingButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Adds all button listeners
     */
    private void addButtonListener() {
        //Button listener that beings a booking
        this.confirmButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                if ("".equals(BookingScreen.this.numberText.getText().toString())) {
                    BookingScreen.this.numberText.setError("Please enter a number");
                } else {
                    BookingScreen.this.bookingNumber = Integer.parseInt(BookingScreen.this.numberText.getText().toString());
                    if ((BookingScreen.this.bookingNumber + BookingScreen.this.shelter.getCurrentCapacity()) > BookingScreen.this.shelter.getCapacity()) {
                        BookingScreen.this.numberText.setError("Not enough space in the shelter");
                    } else {
                        //Writes the name of the shelter booked to a local variable
//                        SharedPreferences.Editor userData = sharedPreferences.edit();
//                        userData.putString("booking", shelter.getName()).apply();

                        final BookingScreen.AsyncTaskRunner makeBooking = new BookingScreen.AsyncTaskRunner();
                        makeBooking.execute("start");
                        final Intent intent = new Intent(view.getContext(), Dashboard.class);
                        intent.putExtra("FromBooking", "Booking successful!");
                        BookingScreen.this.startActivityForResult(intent, 0);
                    }
                }
            }
        });

        //Button to cancel a booking and return to the Dashboard
        this.cancelBookingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
//                SharedPreferences userData = getSharedPreferences("booking", 0);
//                if (userData.) {
//
//
//                    Intent intent = new Intent(v.getContext(), Dashboard.class);
//                    startActivityForResult(intent, 0);
//                } else {
//
//                }
                final Intent intent = new Intent(view.getContext(), Dashboard.class);
                BookingScreen.this.startActivityForResult(intent, 0);
            }
        });

        //Button to return to dashboard
        this.cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent intent = new Intent(view.getContext(), Dashboard.class);
                BookingScreen.this.startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * This class executes the process of booking a shelter and update the backend database.
     */
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(final String... params) {
            try {

                final URL url = new URL(BookingScreen.this.bookingURL + BookingScreen.this.shelterID);
                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestMethod("POST");
                connection.connect();

                final JSONObject booking = new JSONObject();
                booking.put("currCapacity", BookingScreen.this.shelter
                        .getCurrentCapacity() + BookingScreen.this.bookingNumber);

                final DataOutputStream localDataOutputStream = new DataOutputStream(connection
                        .getOutputStream());
                localDataOutputStream.writeBytes(booking.toString());
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
            } catch (final IOException e) {
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
