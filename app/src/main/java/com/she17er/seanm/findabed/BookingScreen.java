package com.she17er.seanm.findabed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStream;
import java.util.ArrayList;

public class BookingScreen extends AppCompatActivity {

    //UI Components
    EditText numberText;
    Button confirmButton;

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
        confirmButton = (Button) findViewById(R.id.confirmButton);
        addButtonListener();

        //Initializes shelter data
        Intent intent = this.getIntent();
        if (intent.getExtras() != null) {
            shelterPosition = Integer.parseInt(intent.getExtras().getString("shelterID"));
            shelter = Dashboard.masterShelters.get(shelterPosition);
        }

        Button cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShelterInspectScreen.class);
                intent.putExtra("shelterID", "" + shelterPosition);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * Adds button that confirms a booking
     */
    private void addButtonListener() {
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maxCapacity = Integer.parseInt(shelter.getCapacity());
                int currCapacity = shelter.getCurrentCapacity();
                int bookingNumber = Integer.parseInt(numberText.getText().toString());
                if (bookingNumber + currCapacity > maxCapacity) {
                    numberText.setError("Not enough space in the shelter");
                } else {
                    shelter.setCurrentCapacity(Integer.toString(shelter.getCurrentCapacity() + bookingNumber));
                    Intent intent = new Intent(v.getContext(), ShelterInspectScreen.class);
                    intent.putExtra("shelterID", "" + shelterPosition);
                    startActivityForResult(intent, 0);
                }
            }
        });
    }

    /**
     * Reads csv data from the data.csv file for booking purposes
     *
     * @param id The file to read csv data from
     */
    public void writeToCSV(int id){
        InputStream inputStream = getResources().openRawResource(id);
    }
}
