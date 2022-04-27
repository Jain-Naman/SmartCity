package com.example.smartcity.UserViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.smartcity.Adapters.MovieAdapter;
import com.example.smartcity.Models.MovieModel;

import com.example.smartcity.R;
import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {

    private RecyclerView movieRecyclerView;
    private MovieAdapter movieAdapter;
    private List<MovieModel> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


        movieList = new ArrayList<>();

        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieAdapter = new MovieAdapter(this);

        movieRecyclerView.setAdapter(movieAdapter);

        movieAdapter.getFromDatabase();

        // travelAdapter.setTravelList(travelList);
        movieAdapter.notifyDataSetChanged();

    }
}