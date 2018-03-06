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
import android.widget.TextView;


/**
 * Initial dashboard activity that a user sees upon login
 * Parses and displays all shelters from a CSV file as a list
 *
 * @edited by elissa huang
 * @version 1.3
 */

public class Dashboard extends AppCompatActivity {

    //UI Components
    TextView dashWelcomeText;

    //ArrayList that stores data from CSV
    public static ArrayList<Shelter> shelters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        RecyclerView shelterView = (RecyclerView) findViewById(R.id.shelterRecyclerView);
        dashWelcomeText = (TextView) findViewById(R.id.dashWelcomeText);
        dashWelcomeText.setText("Welcome " + LoginScreen.currentUser + ", you are a "
            + LoginScreen.accountState.toLowerCase());

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
