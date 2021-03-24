package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class map_activity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mgoogleMap;
    MapView mMapView;
    View mView;
    double X;
    double Y;

    public map_activity() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mMapView = mView.findViewById(R.id.map);

        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onCreate(null);
            mMapView.onPause();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady (GoogleMap googleMap) {
        //MapsInitializer.initialize(getContext());
        mgoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.689, -74.0445)).title("gg").snippet("ff"));
        CameraPosition Liberty  = CameraPosition.builder().target(new LatLng(40.689, -74.0445)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

    }


}