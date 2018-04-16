package com.she17er.seanm.findabed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Screen that shows the shelter data for a given shelter
 */

public class ShelterInspectScreen extends AppCompatActivity {

    //UI Setup
    TextView name;
    TextView address;
    TextView number;
    TextView capacity;
    TextView gender;
    TextView latitude;
    TextView longitude;
    TextView age;
    TextView restrictions;
    TextView currCapacity;
    TextView nameF;
    TextView addressF;
    TextView numberF;
    TextView capacityF;
    TextView genderF;
    TextView latitudeF;
    TextView longitudeF;
    TextView ageF;
    TextView restrictionsF;
    TextView currCapacityF;
    Button bookButton;

    //Data variables
    int shelterPosition;
    Shelter shelter;

    @Override
    public String toString() {
        return "Shelter";
    }
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_inspect_screen);

        //Generates back button on action bar
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Removes actionbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        View parentLayout = getWindow().getDecorView().findViewById(android.R.id.content);
        Intent intent = this.getIntent();
        if (intent.getExtras() != null) {
            shelterPosition = Integer.parseInt(intent.getExtras().getString("shelterID"));
            shelter = Dashboard.masterShelters.get(shelterPosition);
        }

        bookButton = findViewById(R.id.bookButton);
        addButtonListener();

        name = findViewById(R.id.shelterName);
        address = findViewById(R.id.shelterAddress);
        number = findViewById(R.id.shelterPhoneNumber);
        capacity = findViewById(R.id.shelterCapacity);
        gender = findViewById(R.id.shelterGender);
        latitude = findViewById(R.id.shelterLatitude);
        longitude = findViewById(R.id.shelterLongitude);
        age = findViewById(R.id.shelterAges);
        restrictions = findViewById(R.id.shelterRestrictions);
        currCapacity = findViewById(R.id.currCapacity);

        String capacityText = "" + shelter.getCapacity();
        String latitudeText = "" + shelter.getLatitude();
        String longitudeText = "" + shelter.getLongitude();
        String currCapacityText = "" + (shelter.getCapacity() - shelter.getCurrentCapacity());

        nameF = findViewById(R.id.shelterNameField);
        nameF.setText(shelter.getName());
        addressF = findViewById(R.id.shelterAddressField);
        addressF.setText(shelter.getAddress());
        numberF = findViewById(R.id.shelterPhoneNumberField);
        numberF.setText(shelter.getPhoneNumber());
        capacityF = findViewById(R.id.shelterCapacityField);
        capacityF.setText(capacityText);
        genderF = findViewById(R.id.shelterGenderField);
        genderF.setText(shelter.getGender());
        latitudeF = findViewById(R.id.shelterLatitudeField);
        latitudeF.setText(latitudeText);
        longitudeF = findViewById(R.id.shelterLongitudeField);
        longitudeF.setText(longitudeText);
        ageF = findViewById(R.id.shelterAgesField);
        ageF.setText(shelter.getAgeRange());
        currCapacityF = findViewById(R.id.currCapacityField);
        currCapacityF.setText(currCapacityText);
        restrictionsF = findViewById(R.id.shelterRestrictionsField);
        restrictionsF.setText(shelter.getRestrictions());
    }

    /**
     * Adds a button listener
     */
    private void addButtonListener() {
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookingScreen.class);
                intent.putExtra("shelterID", "" + shelterPosition);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
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
