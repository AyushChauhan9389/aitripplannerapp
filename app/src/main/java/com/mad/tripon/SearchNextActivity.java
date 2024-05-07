package com.mad.tripon;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class SearchNextActivity extends AppCompatActivity implements OnMapReadyCallback {
    EditText txt;
    TextView txt1, txt2;

    private GoogleMap myMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_next);
        txt = findViewById(R.id.searchbar1);
        txt1 = findViewById(R.id.lat);
        txt2 = findViewById(R.id.lng);

        // Retrieve the text passed from MyItemAdapter
        String place_id = getIntent().getStringExtra("place_id");
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        double lat = getIntent().getDoubleExtra("lat", 0);
        double lng = getIntent().getDoubleExtra("lng", 0);

        txt.setText(address);
        txt1.setText(String.valueOf(lat));
        txt2.setText(String.valueOf(lng));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;

        LatLng data = new LatLng(Double.parseDouble(txt1.getText().toString()), Double.parseDouble(txt2.getText().toString()));
        myMap.addMarker(new MarkerOptions().position(data).title("Ready to Visit"));
        myMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(data, 15.0f));

    }
}