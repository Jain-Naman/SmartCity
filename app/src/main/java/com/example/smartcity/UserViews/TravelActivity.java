package com.example.smartcity.UserViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartcity.Adapters.TravelAdapter;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.R;
import java.util.ArrayList;
import java.util.List;

public class TravelActivity extends AppCompatActivity {

    private RecyclerView travelRecyclerView;
    private TravelAdapter travelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        getSupportActionBar().hide();

        travelRecyclerView = findViewById(R.id.travelRecyclerView);
        travelRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        travelAdapter = new TravelAdapter(this);

        travelRecyclerView.setAdapter(travelAdapter);
        travelAdapter.getFromDatabase();

    }
}