package com.example.smartcity.UserViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartcity.Adapters.NewsAdapter;
import com.example.smartcity.Models.NewsModel;
import com.example.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;
    private List<NewsModel> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().hide();

        newsList = new ArrayList<>();

        newsRecyclerView = findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsAdapter = new NewsAdapter(this);

        newsRecyclerView.setAdapter(newsAdapter);

        newsAdapter.getFromDatabase();

    }
}