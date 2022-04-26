package com.example.smartcity.UserViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartcity.Adapters.InstitutionAdapter;
import com.example.smartcity.Adapters.TravelAdapter;
import com.example.smartcity.Models.InstitutionModel;
import com.example.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class InstitutionActivity extends AppCompatActivity {

    private RecyclerView institutionRecyclerView;
    private InstitutionAdapter institutionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution);

        getSupportActionBar().hide();

        institutionRecyclerView = findViewById(R.id.institutionRecyclerView);
        institutionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        institutionAdapter = new InstitutionAdapter(this);

        institutionRecyclerView.setAdapter(institutionAdapter);
        institutionAdapter.getFromDatabase();
        //institutionRecyclerView.setAdapter(institutionAdapter);

    }
}