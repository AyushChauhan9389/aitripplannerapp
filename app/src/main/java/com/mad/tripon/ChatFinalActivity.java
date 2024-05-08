package com.mad.tripon;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatFinalActivity extends AppCompatActivity {
    private TextView tvLocationName, tvLocationValue;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.onemoreview);

        // Initialize TextViews
        tvLocationName = findViewById(R.id.nameView);
        tvLocationValue = findViewById(R.id.location);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Locations");

        // Fetch data from Firebase
        loadDataFromFirebase();
    }

    private void loadDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String locationName = snapshot.child("name").getValue(String.class);
                    String locationValue = snapshot.child("value").getValue(String.class);

                    // Set the values to TextViews
                    tvLocationName.setText(locationName);
                    tvLocationValue.setText(locationValue);
                    break; // Assuming you only need one location's data; remove if multiple
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
