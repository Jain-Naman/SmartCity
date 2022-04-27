package com.example.smartcity.Utils.Database;

import android.util.Log;

import com.example.smartcity.Models.MovieModel;
import com.example.smartcity.Models.NewsModel;
import com.example.smartcity.Utils.FirebaseResponseListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDatabaseManager {
    private DatabaseHandler databaseHandler = new DatabaseHandler();

    public void insertData(String category, MovieModel movieModel) {
        Map<String, Object> dataMap = new HashMap<>();
        List<String> bookedList = new ArrayList<>();
        bookedList.add("");

        dataMap.put("title", movieModel.getMovieTitle());
        dataMap.put("description", movieModel.getMovieDescription());
        dataMap.put("vacancies", movieModel.getMovieSeats());
        dataMap.put("booked", bookedList);

        databaseHandler.putData(dataMap, category);
    }

    public void fetchData(String category, FirebaseResponseListener<List<DocumentSnapshot>> firebaseResponseListener) {
        databaseHandler.getData(category, firebaseResponseListener);
    }

    public void updateData(String category, MovieModel movieModel) {

        Map<String, Object> dataMap = new HashMap<>();
        String id;
        dataMap.put("title", movieModel.getMovieTitle());
        dataMap.put("description", movieModel.getMovieDescription());
        dataMap.put("vacancies", movieModel.getMovieSeats());
        id = movieModel.getId();
        databaseHandler.modifyData(dataMap, category, id);
    }

    public void deleteData(String category, String id) {
        databaseHandler.removeData(category, id);
        Log.d("firebase", "Deleted document with id " + id);
    }

    public void editMovieBooking(String email, String docId, FirebaseResponseListener<Boolean> firebaseResponseListener) {
        databaseHandler.editMovieData(email, docId, firebaseResponseListener);
    }
}
