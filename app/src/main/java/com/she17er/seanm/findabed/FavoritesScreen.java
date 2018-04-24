package com.she17er.seanm.findabed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.model.Dash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FavoritesScreen extends AppCompatActivity {

    protected RecyclerView shelterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_favorites_screen);
        this.shelterView = this.findViewById(R.id.favoritesRecyclerView);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Set<String> savedShelterNames = preferences.getStringSet("Saved", null);
        ArrayList<Shelter> mShelters = new ArrayList<>();
        for (Shelter shelter: Dashboard.masterShelters) {
            if (savedShelterNames.contains(shelter.getName())) {
                mShelters.add(shelter);
            }
        }
        addFavoriteShelters(mShelters);
    }

    public void addFavoriteShelters(ArrayList<Shelter> mShelters) {
        final ShelterAdapter adapter = new ShelterAdapter(this, mShelters);
        adapter.setOnItemClickListener(new ShelterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View itemView, final int position) {
                final Intent intent = new Intent(itemView.getContext(), ShelterInspectScreen.class);
                intent.putExtra("shelterID", "" + Dashboard.masterShelters.get(position).get_id());
                intent.putExtra("backendID", Dashboard.masterShelters.get(position).getBackendID());
                FavoritesScreen.this.startActivityForResult(intent, 0);
            }
        });
        // Attach the adapter to the recyclerview to populate items
        this.shelterView.setAdapter(adapter);
        // Set layout manager to position the items
        this.shelterView.setLayoutManager(new LinearLayoutManager(this));
    }
}
