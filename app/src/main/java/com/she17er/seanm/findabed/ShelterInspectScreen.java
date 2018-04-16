package com.she17er.seanm.findabed;

import android.R.id;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.she17er.seanm.findabed.R.layout;

/**
 * Screen that shows the shelter data for a given shelter
 */

public class ShelterInspectScreen extends AppCompatActivity {

    //UI Setup
    private TextView name;
    private TextView address;
    private TextView number;
    private TextView capacity;
    private TextView gender;
    private TextView latitude;
    private TextView longitude;
    private TextView age;
    private TextView restrictions;
    private TextView currCapacity;
    private TextView nameF;
    private TextView addressF;
    private TextView numberF;
    private TextView capacityF;
    private TextView genderF;
    private TextView latitudeF;
    private TextView longitudeF;
    private TextView ageF;
    private TextView restrictionsF;
    private TextView currCapacityF;
    private Button bookButton;

    //Data variables
    private int shelterPosition;
    private Shelter shelter;

    @Override
    public String toString() {
        return "ShelterInspectScreen{" +
                "name=" + this.name +
                ", address=" + this.address +
                ", number=" + this.number +
                ", capacity=" + this.capacity +
                ", gender=" + this.gender +
                ", latitude=" + this.latitude +
                ", longitude=" + this.longitude +
                ", age=" + this.age +
                ", restrictions=" + this.restrictions +
                ", currCapacity=" + this.currCapacity +
                ", nameF=" + this.nameF +
                ", addressF=" + this.addressF +
                ", numberF=" + this.numberF +
                ", capacityF=" + this.capacityF +
                ", genderF=" + this.genderF +
                ", latitudeF=" + this.latitudeF +
                ", longitudeF=" + this.longitudeF +
                ", ageF=" + this.ageF +
                ", restrictionsF=" + this.restrictionsF +
                ", currCapacityF=" + this.currCapacityF +
                ", bookButton=" + this.bookButton +
                ", shelterPosition=" + this.shelterPosition +
                ", shelter=" + this.shelter +
                '}';
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_shelter_inspect_screen);

        //Generates back button on action bar
        if (getActionBar() != null) {
            this.getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Removes actionbar title
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);

        final View parentLayout = this.getWindow().getDecorView().findViewById(id.content);
        final Intent intent = getIntent();
        if (intent.getExtras() != null) {
            this.shelterPosition = Integer.parseInt(intent.getExtras().getString("shelterID"));
            this.shelter = Dashboard.masterShelters.get(this.shelterPosition);
        }

        this.bookButton = this.findViewById(R.id.bookButton);
        this.addButtonListener();

        this.name = this.findViewById(R.id.shelterName);
        this.address = this.findViewById(R.id.shelterAddress);
        this.number = this.findViewById(R.id.shelterPhoneNumber);
        this.capacity = this.findViewById(R.id.shelterCapacity);
        this.gender = this.findViewById(R.id.shelterGender);
        this.latitude = this.findViewById(R.id.shelterLatitude);
        this.longitude = this.findViewById(R.id.shelterLongitude);
        this.age = this.findViewById(R.id.shelterAges);
        this.restrictions = this.findViewById(R.id.shelterRestrictions);
        this.currCapacity = this.findViewById(R.id.currCapacity);

        final String capacityText = "" + this.shelter.getCapacity();
        final String latitudeText = "" + this.shelter.getLatitude();
        final String longitudeText = "" + this.shelter.getLongitude();
        final String currCapacityText = "" + (this.shelter.getCapacity() - this.shelter.getCurrentCapacity());

        this.nameF = this.findViewById(R.id.shelterNameField);
        this.nameF.setText(this.shelter.getName());
        this.addressF = this.findViewById(R.id.shelterAddressField);
        this.addressF.setText(this.shelter.getAddress());
        this.numberF = this.findViewById(R.id.shelterPhoneNumberField);
        this.numberF.setText(this.shelter.getPhoneNumber());
        this.capacityF = this.findViewById(R.id.shelterCapacityField);
        this.capacityF.setText(capacityText);
        this.genderF = this.findViewById(R.id.shelterGenderField);
        this.genderF.setText(this.shelter.getGender());
        this.latitudeF = this.findViewById(R.id.shelterLatitudeField);
        this.latitudeF.setText(latitudeText);
        this.longitudeF = this.findViewById(R.id.shelterLongitudeField);
        this.longitudeF.setText(longitudeText);
        this.ageF = this.findViewById(R.id.shelterAgesField);
        this.ageF.setText(this.shelter.getAgeRange());
        this.currCapacityF = this.findViewById(R.id.currCapacityField);
        this.currCapacityF.setText(currCapacityText);
        this.restrictionsF = this.findViewById(R.id.shelterRestrictionsField);
        this.restrictionsF.setText(this.shelter.getRestrictions());
    }

    /**
     * Adds a button listener
     */
    private void addButtonListener() {
        this.bookButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent intent = new Intent(view.getContext(), BookingScreen.class);
                intent.putExtra("shelterID", "" + ShelterInspectScreen.this.shelterPosition);
                ShelterInspectScreen.this.startActivityForResult(intent, 0);
            }
        });
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
}
