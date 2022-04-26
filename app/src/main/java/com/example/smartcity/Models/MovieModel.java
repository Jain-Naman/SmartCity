package com.example.smartcity.Models;

//import com.example.smartcity.Utils.TravelType;

import java.util.ArrayList;
import java.util.List;

public class MovieModel {
    private String movieTitle;
//    private TravelType instituionType;
    private String movieDescription;
    private String id;
    private String movieSeats;


    // TODO - location

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String title) {
        this.movieTitle = title;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String description) {
        this.movieDescription = description;
    }

    public String getMovieSeats() {
        return movieSeats;
    }

    public void setMovieSeats(String seats) {
        this.movieSeats = seats;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}
