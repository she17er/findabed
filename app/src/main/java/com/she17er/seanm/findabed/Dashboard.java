package com.she17er.seanm.findabed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Initial dashboard activity that a user sees upon login
 * Parses and displays all shelters from a CSV file as a list
 *
 * @edited by elissa huang
 * @version 1.1
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
        String csvFile = "C:/Users/eliss/Documents/GitHub/findabed/app/src/main/java/data/data.csv";
        addCSVShelters(csvFile);
        // Create adapter passing in the sample user data
        ShelterAdapter adapter = new ShelterAdapter(this, shelters);
        // Attach the adapter to the recyclerview to populate items
        shelterView.setAdapter(adapter);
        // Set layout manager to position the items
        shelterView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addCSVShelters(String filePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String s;
            while ((s = br.readLine()) != null) {
                String[] tokens = s.split(",");
                shelters.add(new Shelter(tokens));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
