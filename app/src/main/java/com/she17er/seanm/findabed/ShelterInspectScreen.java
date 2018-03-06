package com.she17er.seanm.findabed;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShelterInspectScreen extends AppCompatActivity {

    //UI Setup
    TextView name, address, number, capacity, gender, latitude, longitude;
    TextView nameF, addressF, numberF, capacityF, genderF, latitudeF, longitudeF;

    //Data variables
    String shelterID;
    Shelter shelter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_inspect_screen);

        //Generates back button on action bar
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        View parentLayout = getWindow().getDecorView().findViewById(android.R.id.content);
        Intent intent = this.getIntent();
        if (intent.getExtras() != null) {
            shelterID = intent.getExtras().getString("shelterID");
            Log.d("intentExtra", intent.getExtras().toString());
            Log.d("ShelterExistsInList", "" + Dashboard.shelters.contains(shelterID));
            for (Shelter mShelter: Dashboard.shelters) {
                if (mShelter.getName().equals(shelterID)) {
                    this.shelter = mShelter;
                }
            }
        }

        name = (TextView) findViewById(R.id.shelterName);
        address = (TextView) findViewById(R.id.shelterAddress);
        number = (TextView) findViewById(R.id.shelterPhoneNumber);
        capacity = (TextView) findViewById(R.id.shelterCapacity);
        gender = (TextView) findViewById(R.id.shelterGender);
        latitude = (TextView) findViewById(R.id.shelterLatitude);
        longitude = (TextView) findViewById(R.id.shelterLongitude);

        nameF = (TextView) findViewById(R.id.shelterNameField);
        nameF.setText(shelter.getName());
        addressF = (TextView) findViewById(R.id.shelterAddressField);
        addressF.setText(shelter.getAddress());
        numberF = (TextView) findViewById(R.id.shelterPhoneNumberField);
        numberF.setText(shelter.getPhoneNumber());
        capacityF = (TextView) findViewById(R.id.shelterCapacityField);
        capacityF.setText(shelter.getCapacity());
        genderF = (TextView) findViewById(R.id.shelterGenderField);
        genderF.setText(shelter.getGender());
        latitudeF = (TextView) findViewById(R.id.shelterLatitudeField);
        latitudeF.setText("" + shelter.getLatitude());
        longitudeF = (TextView) findViewById(R.id.shelterLongitudeField);
        longitudeF.setText("" + shelter.getLongitude());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
