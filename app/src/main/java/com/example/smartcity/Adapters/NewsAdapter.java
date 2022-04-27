package com.example.smartcity.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.smartcity.AdminViews.AdminAdd;
import com.example.smartcity.Globals;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.AdminViews.AdminAdd;
import com.example.smartcity.Models.InstitutionModel;
import com.example.smartcity.Utils.Database.NewsDatabaseManager;
import com.example.smartcity.Utils.FirebaseResponseListener;
import com.google.firebase.firestore.DocumentSnapshot;

import com.example.smartcity.Globals;
import com.example.smartcity.Models.NewsModel;
import com.example.smartcity.UserViews.NewsActivity;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.R;
import com.example.smartcity.UserViews.TravelActivity;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<NewsModel> newsList;
    private NewsActivity newsActivity;

    public NewsAdapter(NewsActivity newsActivity) {
        this.newsActivity = newsActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        NewsModel item = newsList.get(position);
        holder.newsHeadline.setText(item.getNewsHeadline());
        holder.detailedNews.setText(item.getDetailedNews());
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(newsActivity.getApplicationContext(), "Editing mode", Toast.LENGTH_SHORT).show();

                // go to AdminAdd with the existing information
                final Intent i = new Intent(newsActivity.getApplication(), AdminAdd.class);
                i.putExtra("category", "news");
                i.putExtra("title", item.getNewsHeadline());
                i.putExtra("description", item.getDetailedNews());
                i.putExtra("id", item.getId());
                newsActivity.startActivity(i);
                newsActivity.finish();
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(newsActivity.getApplicationContext(), "Delete mode", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                Toast.makeText(newsActivity.getApplicationContext(), "Delete Confirmed!", Toast.LENGTH_SHORT).show();
                                NewsDatabaseManager newsDatabaseManager = new NewsDatabaseManager();
                                newsDatabaseManager.deleteData("news", item.getId());
                                newsList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    public void setNewsList(List<NewsModel> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    public void getFromDatabase() {
        List<NewsModel> newsModelList = new ArrayList<>();

        NewsDatabaseManager newsDatabaseManager = new NewsDatabaseManager();

        newsDatabaseManager.fetchData("News", new FirebaseResponseListener<List<DocumentSnapshot>>() {
            @Override

            public void onCallback(List<DocumentSnapshot> response) {
                for (DocumentSnapshot d : response) {
                    NewsModel newsModel = new NewsModel();
                    newsModel.setNewsHeadline(d.get("title").toString());
                    newsModel.setDetailedNews(d.get("description").toString());
                    newsModel.setId(d.getId());
                    Log.d("firebase", newsModel.getId());
                    newsModelList.add(newsModel);
                }
                setNewsList(newsModelList);
            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView newsHeadline;
        TextView detailedNews;
        Button editButton;
        Button deleteButton;

        ViewHolder(View view) {
            super(view);
            newsHeadline = view.findViewById(R.id.newsHeadlineText);
            detailedNews = view.findViewById(R.id.detailedNews);
            editButton = view.findViewById(R.id.newsEdit);
            deleteButton = view.findViewById(R.id.newsDelete);
            if (!Globals.currentUser.equals("admin")) {
                editButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.INVISIBLE);
            }
        }
    }
}

