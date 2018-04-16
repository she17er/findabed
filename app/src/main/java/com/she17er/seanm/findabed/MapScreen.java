package com.she17er.seanm.findabed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.she17er.seanm.findabed.R.id;
import com.she17er.seanm.findabed.R.layout;

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
                "mMap=" + this.mMap +
                '}';
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_map_screen);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager()
                .findFragmentById(id.map);
        mapFragment.getMapAsync(this);
        if (getActionBar() != null) {
            this.getActionBar().setDisplayHomeAsUpEnabled(true);
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
    public void onMapReady(final GoogleMap googleMap) {
        this.mMap = googleMap;
        final Bundle bundle = this.getIntent().getExtras();
        List<Shelter> shelterList = new ArrayList<>();
        shelterList = bundle.getParcelableArrayList("data");
        final LatLng temp = new LatLng(shelterList.get(0).getLatitude(), shelterList.get(0)
                .getLongitude());
        for (final Iterator<Shelter> iterator = shelterList.iterator(); iterator.hasNext(); ) {
            final Shelter s = iterator.next();
            final LatLng mark = new LatLng(s.getLatitude(), s.getLongitude());
            this.mMap.addMarker(new MarkerOptions().position(mark).title(s.getName()).snippet("" + s
                    .get_id()));
        }
        this.mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
        this.mMap.animateCamera( CameraUpdateFactory.zoomTo( 11.0f ) );
        this.mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                //The snippet is the same as the position in the arrayList
                final String name = marker.getSnippet();
//                name = "0";
                //Using position get Value from arraylist
                final Intent intent = new Intent(MapScreen.this, ShelterInspectScreen.class);
                intent.putExtra("shelterID", "" + name);
                MapScreen.this.startActivityForResult(intent, 0);
                return false;
            }
        });
    }
}
