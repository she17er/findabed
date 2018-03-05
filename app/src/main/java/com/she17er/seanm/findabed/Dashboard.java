package com.she17er.seanm.findabed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

/**
 * Initial dashboard activity that a user sees upon login
 * Parses and displays all shelters from a CSV file as a list
 */

public class Dashboard extends AppCompatActivity {

    ArrayList<Shelter> shelters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        RecyclerView shelterView = (RecyclerView) findViewById(R.id.shelterRecyclerView);

        // Initialize shelters
        shelters = new ArrayList<>();
        addDummyShelters();
        // Create adapter passing in the sample user data
        ShelterAdapter adapter = new ShelterAdapter(this, shelters);
        // Attach the adapter to the recyclerview to populate items
        shelterView.setAdapter(adapter);
        // Set layout manager to position the items
        shelterView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addDummyShelters() {
        shelters.add(new Shelter("Test Shelter 1"));
        shelters.add(new Shelter("Test Shelter 2"));
        shelters.add(new Shelter("Test Shelter 3"));
    }
}
