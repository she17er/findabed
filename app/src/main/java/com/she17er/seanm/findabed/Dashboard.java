package com.she17er.seanm.findabed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Initial dashboard activity that a user sees upon login
 * Parses and displays all shelters from a CSV file as a list
 *
 * @author sean walsh
 * @edited by elissa huang
 * @version 1.3
 */

public class Dashboard extends AppCompatActivity {

    //UI Components
    Button logout;
    Button profile;

    //ArrayList that stores data from CSV
    public static ArrayList<Shelter> shelters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        RecyclerView shelterView = (RecyclerView) findViewById(R.id.shelterRecyclerView);
        logout = (Button) findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WelcomeScreen.class);
                startActivityForResult(intent, 0);
            }
        });
        profile = (Button) findViewById(R.id.profileButton);
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProfileScreen.class);
                startActivityForResult(intent, 0);
            }
        });

        // Initialize shelters
        shelters = new ArrayList<>();
        addCSVShelters(R.raw.data);
        // Create adapter passing in the sample user data
        ShelterAdapter adapter = new ShelterAdapter(this, shelters);
        adapter.setOnItemClickListener(new ShelterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(itemView.getContext(), ShelterInspectScreen.class);
                intent.putExtra("shelterID", shelters.get(position).getName());
                startActivityForResult(intent, 0);
            }
        });
        // Attach the adapter to the recyclerview to populate items
        shelterView.setAdapter(adapter);
        // Set layout manager to position the items
        shelterView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Reads all shelter data from csv in the modal and adds them to an arraylist
     */
    public void addCSVShelters(int id) {
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
                ArrayList<String> tokens = new ArrayList<String> (Arrays.asList(builder.toString().split(",")));
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
