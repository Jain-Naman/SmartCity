package com.example.smartcity.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.Globals;
import com.example.smartcity.Models.NewsModel;
import com.example.smartcity.UserViews.NewsActivity;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.R;
import com.example.smartcity.UserViews.TravelActivity;

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
                                // travelList.removeThatItem(), Based on ID.
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void setNewsList(List<NewsModel> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
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

