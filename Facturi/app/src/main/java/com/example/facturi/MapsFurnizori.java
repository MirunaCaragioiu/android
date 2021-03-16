package com.example.facturi;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFurnizori extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_furnizori);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        LatLng f1 = new LatLng(44.4570458,26.1045432);
        LatLng f2 = new LatLng(44.3990089,26.2229022);
        LatLng f3 = new LatLng(44.4190171,26.096401);
        LatLng f4 = new LatLng(44.4186947,26.0962763);
        LatLng f5 = new LatLng(44.451267,26.0879186);
        LatLng f6 = new LatLng(44.4268719,26.1110104);
        LatLng f7 = new LatLng(44.43269,26.1035397);

        mMap.addMarker(new MarkerOptions().position(f1).title(getString(R.string.apaNova)));
        mMap.addMarker(new MarkerOptions().position(f2).title(getString(R.string.iqApa)));
        mMap.addMarker(new MarkerOptions().position(f3).title(getString(R.string.engieOne)));
        mMap.addMarker(new MarkerOptions().position(f4).title(getString(R.string.distrigazSud)));
        mMap.addMarker(new MarkerOptions().position(f5).title(getString(R.string.electrica)));
        mMap.addMarker(new MarkerOptions().position(f6).title(getString(R.string.enel)));
        mMap.addMarker(new MarkerOptions().position(f7).title(getString(R.string.radet)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(f7,10));
    }
}
