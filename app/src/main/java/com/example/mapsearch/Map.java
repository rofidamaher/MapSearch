package com.example.mapsearch;

import android.location.Address;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    SupportMapFragment mMapFragment;
    SearchView searchView;
    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();
    LatLng sydney = new LatLng(-34,151);
    LatLng TamWorth = new LatLng(-31.0833,150.916672);
    LatLng NewCast = new LatLng(-27.470125,153.02102);
    LatLng Brisbane = new LatLng(-32.256943,148.601105);
    LatLng Dubbo = new LatLng(-32.916668,151.750000);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        searchView = findViewById(R.id.sv_location);
        mMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();

                arrayList.add(sydney);
                arrayList.add(TamWorth);
                arrayList.add(NewCast);
                arrayList.add(Brisbane);
                arrayList.add(Dubbo);

                List <Address> addressList = null;
                if(location != null || !location.equals("")){
//                    Geocoder geocoder = new Geocoder(Map.this);
//                    try{
//                        addressList = geocoder.getFromLocationName(location,1);
//                    } catch (IOException e){
//                        e.printStackTrace();
//                    }
//                    Address address = addressList.get(0);
//                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
//                    map.addMarker(new MarkerOptions().position(latLng).title(location));
//                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                    for (int i= 0;i<arrayList.size();i++)
                    {
                        map.addMarker(new MarkerOptions().position(arrayList.get(i)).title(location));
                        map.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                        map.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
                    }
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {

        map = googleMap;
    }
}
