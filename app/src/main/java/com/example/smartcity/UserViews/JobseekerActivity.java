package com.example.smartcity.UserViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartcity.Adapters.JobseekerAdapter;
import com.example.smartcity.Adapters.TravelAdapter;
import com.example.smartcity.Models.JobseekerModel;
import com.example.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class JobseekerActivity extends AppCompatActivity {

    private RecyclerView jobseekerRecyclerView;
    private JobseekerAdapter jobseekerAdapter;
    private List<JobseekerModel> jobseekerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobseeker);

        getSupportActionBar().hide();

        jobseekerList = new ArrayList<>();

        jobseekerRecyclerView = findViewById(R.id.jobseekerRecyclerView);
        jobseekerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        jobseekerAdapter = new JobseekerAdapter(this);

        jobseekerRecyclerView.setAdapter(jobseekerAdapter);

        jobseekerAdapter.getFromDatabase();


        jobseekerAdapter.setJobseekerList(jobseekerList);
        jobseekerAdapter.notifyDataSetChanged();

    }
}