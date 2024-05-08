package com.mad.tripon;// SearchActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchActivity extends AppCompatActivity {

    private EditText searchEditText;
    private RecyclerView recyclerView;
    List<PlaceResponseModel.Result> items = new ArrayList<>();

    private RequestQueue requestQueue;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = findViewById(R.id.searchbar1); // Make sure the ID matches the new EditText ID
//        List<item> items = new ArrayList<>();
//        items.add(new item("1", "Place 1", "Address 1"));
//        items.add(new item("2", "Place 2", "Address 2"));
//        items.add(new item("3", "Place 3", "Address 3"));
//        items.add(new item("4", "Place 4", "Address 4"));
//        items.add(new item("5", "Place 5", "Address 5"));
         recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new MyItemAdapter(getApplicationContext(), items));
        back = findViewById(R.id.back1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, Home.class);
                startActivity(intent);
            }
        });


        // Retrieve the text passed from Home activity
        String searchText = getIntent().getStringExtra("SEARCH_TEXT");

        // Set the retrieved text to the EditText
        if (searchText != null) {
            searchEditText.setText(searchText);
        }
        getData(searchText);

    }


    public void getData(String searchText) {

        ApiCall apiCall = RetrofitClient.createService(ApiCall.class);
        Call<PlaceResponseModel> call = apiCall.getAllPlaces(searchText );
        call.enqueue(new Callback<PlaceResponseModel>() {
            @Override
            public void onResponse(Call<PlaceResponseModel> call, retrofit2.Response<PlaceResponseModel> response) {
                if (response.code() == 200) {
                    items.addAll(response.body().results);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(new MyItemAdapter(getApplicationContext(), items));

                }
            }

            @Override
            public void onFailure(@NonNull Call<PlaceResponseModel> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "R.string.error_msg_something", Toast.LENGTH_LONG).show();
            }
        });
    }
}

