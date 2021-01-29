package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class HomePage extends AppCompatActivity {

    String s1[], s2[];
    ListView listview;
    ImageView addloc;
    LocationManager locationManager;
    LocationListener locationListener;
    Button mark;
    TextView t1,t2;
    FusedLocationProviderClient fusedLocationProviderClient;

    /*
    * getIntent().getExtras().getString("firstName");
    * getIntent().getExtras().getString("lastName");
    * getIntent().getExtras().getString("Status");
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        mark = (Button) findViewById(R.id.mark);
        t1 = (TextView) findViewById(R.id.status);

        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setText("Positive");
                mark.setText("Mark as Negative");
                mark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        t1.setText("Negative");
                        mark.setText("Mark as Positive");
                    }
                });
            }
        });

        ListView listView = (ListView) findViewById(R.id.itemlist);
        String[] sports = getResources().getStringArray(R.array.places);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sports));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        t2 = findViewById(R.id.loc);
        addloc = (ImageView) findViewById(R.id.addloc);
        if (ContextCompat.checkSelfPermission(HomePage.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HomePage.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        addloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    Double lat = location.getLatitude();
                                    Double lon = location.getLongitude();
                                    t2.setText(lat + " , " + lon + "");
                                    Toast.makeText(HomePage.this, "Success", Toast.LENGTH_SHORT);

                                }
                            }
                        });
                    } else {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    }
                }
            }
        });
    }
}