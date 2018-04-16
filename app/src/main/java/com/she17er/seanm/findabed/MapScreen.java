package com.she17er.seanm.findabed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Screen that shows the map of all shelters
 */

public class MapScreen extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    public String toString() {
        return "MapScreen{" +
                "mMap=" + mMap +
                '}';
    }

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
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
    public final void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Bundle bundle = getIntent().getExtras();
        List<Shelter> shelterList = new ArrayList<>();
        shelterList = bundle.getParcelableArrayList("data");
        LatLng temp = new LatLng(shelterList.get(0).getLatitude(), shelterList.get(0)
                .getLongitude());
        for (Iterator<Shelter> iterator = shelterList.iterator(); iterator.hasNext(); ) {
            Shelter s = iterator.next();
            LatLng mark = new LatLng(s.getLatitude(), s.getLongitude());
            mMap.addMarker(new MarkerOptions().position(mark).title(s.getName()).snippet("" + s
                    .get_id()));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 11.0f ) );
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //The snippet is the same as the position in the arrayList
                String name = marker.getSnippet();
//                name = "0";
                //Using position get Value from arraylist
                Intent intent = new Intent(MapScreen.this, ShelterInspectScreen.class);
                intent.putExtra("shelterID", "" + name);
                startActivityForResult(intent, 0);
                return false;
            }
        });
    }
}
