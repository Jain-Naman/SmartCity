package com.example.smartcity.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.example.smartcity.AdminViews.AdminAdd;
import com.example.smartcity.Globals;

import com.example.smartcity.Models.MovieModel;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.R;

import com.example.smartcity.UserViews.MovieActivity;
import com.example.smartcity.Utils.Database.MovieDatabaseManager;
import com.example.smartcity.Utils.FirebaseResponseListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    List<MovieModel> movieList = new ArrayList<>();
    private MovieActivity movieActivity;
    private Boolean bookedSeats = false;

    public MovieAdapter(MovieActivity movieActivity) {
        this.movieActivity = movieActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        MovieModel item = movieList.get(position);
        holder.movieTitle.setText(item.getMovieTitle());
        holder.movieDescription.setText(item.getMovieDescription());
        holder.movieSeats.setText(item.getMovieSeats());

        if (Integer.parseInt(holder.movieSeats.getText().toString()) == 0) {
            holder.applyButton.setVisibility(View.INVISIBLE);
        }

        holder.applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieDatabaseManager movieDatabaseManager = new MovieDatabaseManager();
                movieDatabaseManager.editMovieBooking(Globals.email, item.getId(), new FirebaseResponseListener<Boolean>() {
                    @Override
                    public void onCallback(Boolean response) {
                        bookedSeats = response;
                        if (response) {
                            Toast.makeText(movieActivity.getApplicationContext(), "Booked", Toast.LENGTH_SHORT).show();
                            holder.applyButton.setEnabled(false);
                            holder.applyButton.setVisibility(view.INVISIBLE);
                            holder.movieSeats.setText(String.valueOf(Integer.parseInt(item.getMovieSeats()) - 1));
                        } else {
                            Toast.makeText(movieActivity.getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public void setMovieList(List<MovieModel> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }


    public void getFromDatabase() {
        List<MovieModel> movieModelList = new ArrayList<>();

        MovieDatabaseManager movieDatabaseManager = new MovieDatabaseManager();

        movieDatabaseManager.fetchData("movies", new FirebaseResponseListener<List<DocumentSnapshot>>() {
            @Override
            public void onCallback(List<DocumentSnapshot> response) {
                for (DocumentSnapshot d : response) {
                    MovieModel movieModel = new MovieModel();
                    movieModel.setMovieTitle(d.get("title").toString());
                    movieModel.setMovieDescription(d.get("description").toString());
                    movieModel.setId(d.getId());
                    int booked = ((List<String>) d.get("booked")).size();
                    int temp = Integer.parseInt(d.get("vacancies").toString());
                    movieModel.setMovieSeats(String.valueOf(temp - booked));
                    Log.d("firebase", movieModel.getId());
                    movieModelList.add(movieModel);
                }
                setMovieList(movieModelList);
            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        TextView movieDescription;
        TextView movieSeats;
        Button applyButton;

        ViewHolder(View view) {
            super(view);
            movieTitle = view.findViewById(R.id.movieTitleText);
            movieDescription = view.findViewById(R.id.movieDescription);
            movieSeats = view.findViewById(R.id.movieSeats);
            applyButton = view.findViewById(R.id.movieApply);
        }
    }
}
