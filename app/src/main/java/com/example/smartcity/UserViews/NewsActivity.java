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

        NewsModel news1 = new NewsModel();
        news1.setNewsHeadline("Title 1");
        news1.setDetailedNews("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        NewsModel news2 = new NewsModel();
        news2.setNewsHeadline("Title 2");
        news2.setDetailedNews("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        NewsModel news3 = new NewsModel();
        news3.setNewsHeadline("Title 3");
        news3.setDetailedNews("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        newsList.add(news1);
        newsList.add(news2);
        newsList.add(news3);

        newsAdapter.setNewsList(newsList);
        newsAdapter.notifyDataSetChanged();

    }
}