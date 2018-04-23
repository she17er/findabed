package com.she17er.seanm.findabed;

import android.R.layout;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Spinner;

import com.she17er.seanm.findabed.R.id;
import com.she17er.seanm.findabed.ShelterAdapter.OnItemClickListener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Initial dashboard activity that a user sees upon login
 * Parses and displays all shelters from a CSV file as a list
 */

public class Dashboard extends AppCompatActivity implements OnQueryTextListener {

    //UI Components
    protected RecyclerView shelterView;
    private Button logout;
    private Button map;
    private Button profile;
    private Spinner genderSelect;
    private Spinner ageSelect;

    //ArrayList that stores data from CSV
    protected static List<Shelter> masterShelters;
    protected static ArrayList<Shelter> currentShelters;

    //Current restrictions from the spinners
    private String gender;
    private String age;

    //Backend URL for populating shelters list
    private final String getSheltersURL = "https://she17er.herokuapp.com/api/shelter/getShelters";
    private static String jsonData;

    /**
     * This class gets all the shelter information from the backend.
     */
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        String shelterInfo = "";

        @Override
        public String toString() {
            return "AsyncTaskRunner{" +
                    "shelterInfo='" + this.shelterInfo + '\'' +
                    '}';
        }

        @Override
        protected String doInBackground(final String... params) {

            try {

                final URL url = new URL(Dashboard.this.getSheltersURL);
                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.connect();

                final BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                final StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    this.shelterInfo += line + "\n";
                    //Log.d("readLine", line);
                }
                br.close();

                return this.shelterInfo;
            } catch (final MalformedURLException e) {
                e.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(final String result) {
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

        //Gets the shelter info json data
        public String getShelterInfo() {
            return this.shelterInfo;
        }

    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_dashboard);
        this.shelterView = this.findViewById(id.shelterRecyclerView);
        this.logout = this.findViewById(id.logoutButton);
        this.logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent intent = new Intent(view.getContext(), WelcomeScreen.class);
                Dashboard.this.startActivityForResult(intent, 0);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        this.profile = this.findViewById(id.profileButton);
        this.profile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent intent = new Intent(view.getContext(), ProfileScreen.class);
                Dashboard.this.startActivityForResult(intent, 0);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        this.map = this.findViewById(id.mapButton);
        this.map.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent intent = new Intent(view.getContext(), MapScreen.class);
                final Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("data", Dashboard.currentShelters);
                intent.putExtras(bundle);
                Dashboard.this.startActivityForResult(intent, 0);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        //Removes actionbar title
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Populates shelter list from JSON data

        final Dashboard.AsyncTaskRunner getShelters = new Dashboard.AsyncTaskRunner();
        getShelters.execute("start");
        String allInfo = "";
        try {
            allInfo = getShelters.get();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        Dashboard.jsonData = allInfo;

        // Adds shelters to master list (also has legacy csv code)
        Dashboard.masterShelters = this.jsonParser(allInfo);
        Dashboard.currentShelters = new ArrayList<>();
//        addCSVShelters(R.raw.data, masterShelters);
        for (final Iterator<Shelter> iterator = Dashboard.masterShelters.iterator(); iterator.hasNext(); ) {
            final Shelter shelter = iterator.next();
            Dashboard.currentShelters.add(shelter);
        }

        this.populateShelterList(Dashboard.currentShelters);

        //Initialize Spinners
        this.gender = "";
        this.age = "";
        this.populateSpinners();
        this.genderSelect.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, final View view,
                                       final int i, final long l) {
                switch (i) {
                    case 1:
                        Dashboard.this.gender = "women";
                        break;
                    case 2:
                        Dashboard.this.gender = "men";
                        break;
                    default:
                        Dashboard.this.gender = "";
                        break;
                }
                Dashboard.this.spinnerSearch();
            }

            @Override
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }

        });

        this.ageSelect.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, final View view,
                                       final int i, final long l) {
                switch (i) {
                    case 0:
                        Dashboard.this.age = "";
                        break;
                    case 1:
                        Dashboard.this.age = "children";
                        break;
                    case 2:
                        Dashboard.this.age = "young adults";
                        break;
                    default:
                        Dashboard.this.age = "newborn";
                        break;
                }
                Dashboard.this.spinnerSearch();
            }

            @Override
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public String toString() {
        return "Dashboard{" +
                "shelterView=" + this.shelterView +
                ", logout=" + this.logout +
                ", map=" + this.map +
                ", profile=" + this.profile +
                ", genderSelect=" + this.genderSelect +
                ", ageSelect=" + this.ageSelect +
                ", gender='" + this.gender + '\'' +
                ", age='" + this.age + '\'' +
                ", getSheltersURL='" + this.getSheltersURL + '\'' +
                '}';
    }

    /**
     * @param ShelterInfo the information got from the database
     * @return an ArrayList containing various shelters with their information
     */
    public ArrayList<Shelter> jsonParser(final String ShelterInfo) {
        final ArrayList<Shelter> allShelters = new ArrayList<>();
        String tempInfo = ShelterInfo.substring(3);
        final List<String> Info = new ArrayList<>();
        int count = 0;
        try {
            while (tempInfo.contains("},{")) {
                final int rightIndex = tempInfo.indexOf("},{");
                Info.add(tempInfo.substring(0, rightIndex));
                tempInfo = tempInfo.substring(rightIndex + 3);
            }
            Info.add(tempInfo);
            for (final Iterator<String> iterator = Info.iterator(); iterator.hasNext(); ) {
                final String s = iterator.next();
                final List<String> arr = new ArrayList<>();
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
                final Shelter newShelter = new Shelter();
                newShelter.setBackendID(arr.get(8));
                newShelter.setAddress(arr.get(5));
                newShelter.setCapacity(arr.get(1));
                newShelter.setLatitude(arr.get(4));
                newShelter.setName(arr.get(0));
                newShelter.setGenderAndAge(arr.get(2));
                newShelter.setLongitude(arr.get(3));
                newShelter.setPhoneNumber(arr.get(6));
                newShelter.setCurrentCapacity(arr.get(7));
                newShelter.set_id("" + count);
                count++;
                allShelters.add(newShelter);
            }
        } catch (final RuntimeException e) {
            Log.d("jsonParser", "Parser failed");
            e.printStackTrace();
            return null;
        }
        return allShelters;
    }

    @Override
    public boolean onQueryTextChange(final String s) {
        // Here is where we are going to implement the filter logic
        ArrayList<Shelter> updatedShelters = new ArrayList<>();
        for (final Iterator<Shelter> iterator = Dashboard.currentShelters.iterator(); iterator.hasNext(); ) {
            final Shelter aShelter = iterator.next();
            if (aShelter.getName().toLowerCase().contains(s)) {
                updatedShelters.add(aShelter);
            }
        }
        this.populateShelterList(updatedShelters);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(final String s) {
        return false;
    }

    /**
     * Narrows down displayed/ searchable shelters based on spinner selection
     */
    private void spinnerSearch() {
        Dashboard.currentShelters = new ArrayList<>();
        String wrongGender = "";
        if ("women".equals(this.gender)) {
            wrongGender = "men ";
        } else if ("men ".equals(this.gender)) {
            wrongGender = "women";
        }
        for (final Iterator<Shelter> iterator = Dashboard.masterShelters.iterator(); iterator.hasNext(); ) {
            final Shelter shelter = iterator.next();
            if (!shelter.getGender().equals(wrongGender) && shelter.getAgeRange().contains(this.age)) {
                Dashboard.currentShelters.add(shelter);
            }
        }
        this.populateShelterList(Dashboard.currentShelters);
    }

    /**
     * Populates the RecyclerView with appropriate shelters
     *
     * @param mShelters The list of shelters to be added, filtered based on criteria
     */
    protected void populateShelterList(ArrayList<Shelter> mShelters) {
        // Create adapter passing in the sample user data
        final ShelterAdapter adapter = new ShelterAdapter(this, mShelters);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final View itemView, final int position) {
                final Intent intent = new Intent(itemView.getContext(), ShelterInspectScreen.class);
                intent.putExtra("shelterID", "" + Dashboard.masterShelters.get(position).get_id());
                intent.putExtra("backendID", Dashboard.masterShelters.get(position).getBackendID());
                Dashboard.this.startActivityForResult(intent, 0);
            }
        });
        // Attach the adapter to the recyclerview to populate items
        this.shelterView.setAdapter(adapter);
        // Set layout manager to position the items
        this.shelterView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Populates the genderSelect and ageSelect spinners with all appropriate entry choices
     */
    private void populateSpinners() {
        this.genderSelect = this.findViewById(id.genderFilterSpinner);
        final List<String> genderEntries = new ArrayList<>();
        genderEntries.add("Any Gender");
        genderEntries.add("Female");
        genderEntries.add("Male");
        final ArrayAdapter<String> dataAdapterGender = new ArrayAdapter<>(this,
                layout.simple_spinner_item, genderEntries);
        dataAdapterGender.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        this.genderSelect.setAdapter(dataAdapterGender);

        this.ageSelect = this.findViewById(id.ageFilterSpinner);
        final List<String> ageEntries = new ArrayList<>();
        ageEntries.add("Any Age");
        ageEntries.add("Children");
        ageEntries.add("Young Adults");
        ageEntries.add("Families with Newborns");
        final ArrayAdapter<String> dataAdapterRole = new ArrayAdapter<>(this,
                layout.simple_spinner_item, ageEntries);
        dataAdapterRole.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        this.ageSelect.setAdapter(dataAdapterRole);
    }

    /**
     * Reads all shelter data from csv in the modal and adds them to an ArrayList
     * @param id The shelter's position
     * @param dataStore The array of all shelters to get positions from
     */
    public void addCSVShelters(final int id, final List<Shelter> dataStore) {
        final InputStream inputStream = this.getResources().openRawResource(id);
        final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,
                Charset.forName("UTF-8")));
        try {
            String s = br.readLine();
            while ((s = br.readLine()) != null) {
                final StringBuilder builder = new StringBuilder(s);
                boolean inQuotes = false;
                for (int currentIndex = 0; currentIndex < builder.length(); currentIndex++) {
                    final char currentChar = builder.charAt(currentIndex);
                    if ((int) currentChar == (int) '\"') {
                        inQuotes = !inQuotes; // toggle state
                    }
                    if (((int) currentChar == (int) ',') && inQuotes) {
                        builder.setCharAt(currentIndex, ';'); // sets the comma in the
                        // quotes to semi-colon
                    }
                }
                final ArrayList<String> tokens = new ArrayList<> (Arrays.asList(builder.toString()
                        .split(",")));
                dataStore.add(new Shelter(tokens));
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

