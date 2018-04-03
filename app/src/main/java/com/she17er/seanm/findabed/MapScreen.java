package com.she17er.seanm.findabed;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MapScreen extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private HashMap<String, Shelter> nameHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Bundle bundle = getIntent().getExtras();
        ArrayList<Shelter> shelterList = new ArrayList<Shelter>();
        shelterList = bundle.getParcelableArrayList("data");
        // Add a marker in Sydney and move the camera
        Log.d("Latitude", Double.toString(shelterList.get(0).getLatitude()));
        LatLng sydney = new LatLng(-34, 151);
        LatLng temp = new LatLng(shelterList.get(0).getLatitude(), shelterList.get(0).getLongitude());

        for (Shelter s: shelterList) {
            nameHashMap.put(s.getName(), s);
            LatLng mark = new LatLng(s.getLatitude(), s.getLongitude());
            mMap.addMarker(new MarkerOptions().position(mark).title(s.getName()));
            MarkerOptions m = new MarkerOptions().position(mark).title(s.getName()))
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) );
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int position = (int)(marker.getTag());
                String name = marker.getTitle();
                //Using position get Value from arraylist
                Intent intent = new Intent(MapScreen.this, ShelterInspectScreen.class);
                Bundle bundle1 = new Bundle();
                intent.putExtra("shelter_ID", name);
                startActivityForResult(intent, 0);
                return false;
            }
        });
    }
}
