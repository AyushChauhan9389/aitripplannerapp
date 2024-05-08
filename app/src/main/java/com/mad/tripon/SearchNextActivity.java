package com.mad.tripon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchNextActivity extends AppCompatActivity implements OnMapReadyCallback {
    EditText txt;
    TextView txt1, txt2;
    Button btn;
    private GoogleMap myMap;

    // Firebase references
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth; // Firebase Auth instance
    Button restaurant, hotel, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_next);

        // Initialize Firebase Auth and Database
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Locations");

        txt = findViewById(R.id.searchbar1);
        txt1 = findViewById(R.id.lat);
        txt2 = findViewById(R.id.lng);
        btn = findViewById(R.id.save_btn);

         restaurant = findViewById(R.id.restaurant);
         hotel = findViewById(R.id.hotel);
         back = findViewById(R.id.back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchNextActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchNextActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchNextActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });
        // Retrieve the text passed from previous Activity
        String place_id = getIntent().getStringExtra("place_id");
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        double lat = getIntent().getDoubleExtra("lat", 0);
        double lng = getIntent().getDoubleExtra("lng", 0);

        txt1.setText(String.valueOf(lat));
        txt2.setText(String.valueOf(lng));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn.setOnClickListener(v -> {
            FirebaseUser currentUser = mAuth.getCurrentUser(); // Get current user
            if (currentUser != null) {
                String userId = currentUser.getUid(); // Get user ID
                saveLocation(userId, place_id, name, address, lat, lng);
            } else {
                Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        LatLng data = new LatLng(Double.parseDouble(txt1.getText().toString()), Double.parseDouble(txt2.getText().toString()));
        myMap.addMarker(new MarkerOptions().position(data).title("Ready to Visit"));
        myMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(data, 15.0f));
    }

    private void saveLocation(String userId, String placeId, String name, String address, double lat, double lng) {
        // Creating an object for storing to Firebase
        LocationData locationData = new LocationData(userId, placeId, name, address, lat, lng);
        // Using push() to create a unique ID for each entry
        databaseReference.push().setValue(locationData)
                .addOnSuccessListener(aVoid -> {
                    // Handle success
                    Toast.makeText(SearchNextActivity.this, "Location saved successfully!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    Toast.makeText(SearchNextActivity.this, "Failed to save location", Toast.LENGTH_SHORT).show();
                });
    }

    // Inner class to model the location data
    static class LocationData {
        public String userId, placeId, name, address;
        public double lat, lng;

        public LocationData(String userId, String placeId, String name, String address, double lat, double lng) {
            this.userId = userId;
            this.placeId = placeId;
            this.name = name;
            this.address = address;
            this.lat = lat;
            this.lng = lng;
        }
    }
}
