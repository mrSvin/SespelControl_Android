package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.myapplication.ui_zapros.speed;
import static com.example.myapplication.ui_zapros.x_pos;
import static com.example.myapplication.ui_zapros.y_pos;

public class tab2 extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    public GoogleMap mMap;
    //MapView mMap;
    View mView;
    double X;
    double Y;
    public static Button button_refresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.tab2, container, false);

        //Обновляем фрагмент местоположения на карте
        button_refresh = mView.findViewById(R.id.button_refresh);
        button_refresh.setOnClickListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment == null){
            FragmentManager fm= getFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            mapFragment=SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return mView;
    }

    @Override
    public void onMapReady (GoogleMap googleMap) {
        mMap = googleMap;
        X = Double.parseDouble(x_pos);
        Y = Double.parseDouble(y_pos);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Y, X);
        mMap.addMarker(new MarkerOptions().position(sydney)
                .snippet("координаты: " + X + ", " + Y)
                .title("скорость: " + speed  + " км/ч"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.metka));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10.0f));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_refresh:
                new tab2();
                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map);
                if (mapFragment == null){
                    FragmentManager fm= getFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();
                    mapFragment=SupportMapFragment.newInstance();
                    ft.replace(R.id.map, mapFragment).commit();
                }
                mapFragment.getMapAsync(this);

        }
    }

}