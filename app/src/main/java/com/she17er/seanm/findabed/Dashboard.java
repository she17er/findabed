package com.she17er.seanm.findabed;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.res.AssetManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;


/**
 * Initial dashboard activity that a user sees upon login
 * Parses and displays all shelters from a CSV file as a list
 *
 * @author sean walsh
 * @edited by elissa huang
 * @version 1.3
 */

public class Dashboard extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //UI Components
    RecyclerView shelterView;
    Button logout;
    Button map;
    Button profile;
    Spinner genderSelect, ageSelect;

    //ArrayList that stores data from CSV
    public static ArrayList<Shelter> masterShelters, currentShelters;

    //Current restrictions from the spinners
    String gender, age;

    //Backend URL for populating shelters list
    String getSheltersURL = "https://she17er.herokuapp.com/api/shelter/getShelters";
    public static String jsonData;

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        String shelterInfo = "";

        @Override
        protected String doInBackground(String... params) {

            try {

                URL url = new URL(getSheltersURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    shelterInfo += line + "\n";
                    //Log.d("readLine", line);
                }
                br.close();

                Log.d("shelterInfoString", shelterInfo);
                return shelterInfo;
            } catch (Exception e) {
                Log.d("GETError", e.toString());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        //Gets the shelter info json data
        public String getShelterInfo() {
            return shelterInfo;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        shelterView = (RecyclerView) findViewById(R.id.shelterRecyclerView);
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
        map = (Button) findViewById(R.id.mapButton);
        map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapScreen.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("data", currentShelters);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });

        //Removes actionbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Populates shelter list from JSON data

        AsyncTaskRunner getShelters = new AsyncTaskRunner();
        getShelters.execute("start");
        String allInfo = "";
        try {
            allInfo = getShelters.get();
            Log.d("Async Result", getShelters.get());
        } catch (Exception e) {
            Log.d("Async Exception", e.toString());
        }
        Log.d("Async Result1", allInfo);


        // Adds shelters to master list (also has legacy csv code)
        masterShelters = jsonParser(allInfo);
        currentShelters = new ArrayList<>();
//        addCSVShelters(R.raw.data, masterShelters);
        for (Shelter shelter: masterShelters) {
            currentShelters.add(shelter);
        }

        populateShelterList(currentShelters);

        //Initialize Spinners
        gender = "";
        age = "";
        populateSpinners();
        genderSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    gender = "women";
                } else if (position == 2) {
                    gender = "men ";
                } else {
                    gender = "";
                }
                spinnerSearch();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}

        });

        ageSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    age = "";
                } else if (position == 1) {
                    age = "children";
                } else if (position == 2) {
                    age = "young adults";
                } else {
                    age = "newborn";
                }
                spinnerSearch();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    /**
     * @param ShelterInfo the information got from the database
     * @return an ArrayList containing various shelters with their information
     */
    public ArrayList<Shelter> jsonParser (String ShelterInfo) {
        ArrayList<Shelter> allShelters = new ArrayList<Shelter>();
        String tempInfo = ShelterInfo.substring(3);
        ArrayList<String> Info = new ArrayList<>();
        try {
            while (tempInfo.indexOf("},{") != -1) {
                int rightIndex = tempInfo.indexOf("},{");
                Info.add(tempInfo.substring(0, rightIndex));
                tempInfo = tempInfo.substring(rightIndex + 3);
            }
            for (String s: Info) {
                Log.d("each Info", s);
                ArrayList<String> arr = new ArrayList<>();
                int i = s.indexOf("name");
                int j = s.indexOf(',', i);
                arr.add(s.substring(i + 7, j - 1));
                i = s.indexOf("maxCapacity");
                j = s.indexOf(',', i);
                arr.add(s.substring(i + 13, j));
                i = s.indexOf("acceptedTypes");
                j = s.indexOf(']', i);
                arr.add(s.substring(i + 18, j));
                i = s.indexOf("coOrdinates");
                j = s.indexOf(',', i);
                arr.add(s.substring(i + 14, j));
                i = j + 1;
                j = s.indexOf(',', i);
                arr.add(s.substring(i, j - 1));
                i = s.indexOf("location");
                j = s.indexOf("currCapacity");
                arr.add(s.substring(i + 11, j - 3));
                i = s.indexOf("phoneNumber");
                j = s.indexOf(',', i);
                arr.add(s.substring(i + 13, j));
                i = s.indexOf("currCapacity");
                j = s.indexOf(',', i);
                arr.add(s.substring(i + 14, j));
                i = s.indexOf("_id");
                j = s.indexOf(',', i);
                arr.add(s.substring(i + 6, j - 1));
                Log.d("allInfo", arr.toString() + arr.size());
                Shelter newShelter = new Shelter();
                newShelter.setBackendID(arr.get(8));
                newShelter.setAddress(arr.get(5));
                newShelter.setCapacity(arr.get(1));
                newShelter.setLatitude(arr.get(4));
                newShelter.setName(arr.get(0));
                newShelter.setGenderandAge(arr.get(2));
                newShelter.setLongitude(arr.get(3));
                newShelter.setPhoneNumber(arr.get(6));
                newShelter.setCurrentCapacity(arr.get(7));
                allShelters.add(newShelter);
            }
        } catch (Exception e) {
            Log.d("jsonParser", "Parser failed");
            e.printStackTrace();
        }
        return allShelters;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        // Here is where we are going to implement the filter logic
        final ArrayList<Shelter> updatedShelters = new ArrayList<>();
        for (Shelter aShelter: currentShelters) {
            if (aShelter.getName().toLowerCase().contains(query)) {
                updatedShelters.add(aShelter);
            }
        }
        populateShelterList(updatedShelters);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * Narrows down displayed/ searchable shelters based on spinner selection
     */
    private void spinnerSearch() {
        currentShelters = new ArrayList<>();
        String wrongGender = "";
        if (gender.equals("women")) {
            wrongGender = "men ";
        } else if (gender.equals("men ")) {
            wrongGender = "women";
        }
        for (Shelter shelter: masterShelters) {
            if (!shelter.getGender().equals(wrongGender) && shelter.getAgeRange().contains(age)) {
                currentShelters.add(shelter);
            }
        }
        populateShelterList(currentShelters);
    }

    /**
     * Populates the RecyclerView with appropriate shelters
     * @param mShelters The list of shelters to be added, filtered based on criteria
     */
    private void populateShelterList(final ArrayList<Shelter> mShelters) {
        // Create adapter passing in the sample user data
        ShelterAdapter adapter = new ShelterAdapter(this, mShelters);
        adapter.setOnItemClickListener(new ShelterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(itemView.getContext(), ShelterInspectScreen.class);
                intent.putExtra("shelterID", "" + position);
                intent.putExtra("backendID", masterShelters.get(position).getBackendID());
                startActivityForResult(intent, 0);
            }
        });
        // Attach the adapter to the recyclerview to populate items
        shelterView.setAdapter(adapter);
        // Set layout manager to position the items
        shelterView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Populates the genderSelect and ageSelect spinners with all appropriate entry choices
     */
    private void populateSpinners() {
        genderSelect = (Spinner) findViewById(R.id.genderFilterSpinner);
        List<String> genderEntries = new ArrayList<>();
        genderEntries.add("Any Gender");
        genderEntries.add("Female");
        genderEntries.add("Male");
        ArrayAdapter<String> dataAdapterGender = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, genderEntries);
        dataAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSelect.setAdapter(dataAdapterGender);

        ageSelect = (Spinner) findViewById(R.id.ageFilterSpinner);
        List<String> ageEntries = new ArrayList<>();
        ageEntries.add("Any Age");
        ageEntries.add("Children");
        ageEntries.add("Young Adults");
        ageEntries.add("Families with Newborns");
        ArrayAdapter<String> dataAdapterRole = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ageEntries);
        dataAdapterRole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSelect.setAdapter(dataAdapterRole);
    }

    /**
     * Reads all shelter data from csv in the modal and adds them to an arraylist
     */
    public void addCSVShelters(int id, ArrayList<Shelter> dataStore) {
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
                dataStore.add(new Shelter(tokens));
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
