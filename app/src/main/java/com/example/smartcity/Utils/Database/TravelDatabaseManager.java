package com.example.smartcity.Utils.Database;

import android.util.Log;

import com.example.smartcity.Models.GenericModel;
import com.example.smartcity.Models.InstitutionModel;
import com.example.smartcity.Models.JobseekerModel;
import com.example.smartcity.Models.MovieModel;
import com.example.smartcity.Models.NewsModel;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.Utils.FirebaseResponseListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravelDatabaseManager {
    private DatabaseHandler databaseHandler = new DatabaseHandler();

    public void insertData(String category, TravelModel travelModel) {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("title", travelModel.getTravelTitle());
        dataMap.put("description", travelModel.getTravelDescription());
        dataMap.put("location", travelModel.getLocation());

        databaseHandler.putData(dataMap, category);
    }

    public void fetchData(String category, FirebaseResponseListener<List<DocumentSnapshot>> firebaseResponseListener) {
        databaseHandler.getData(category, firebaseResponseListener);
    }

    public void updateData(String category, TravelModel travelModel) {

        Map<String, Object> dataMap = new HashMap<>();
        String id;
        dataMap.put("title", travelModel.getTravelTitle());
        dataMap.put("description", travelModel.getTravelDescription());
        dataMap.put("location", travelModel.getLocation());
        id = travelModel.getId();
        databaseHandler.modifyData(dataMap, category, id);
    }

    public void deleteData(String category, String id) {
        databaseHandler.removeData(category, id);
        Log.d("firebase", "Deleted document with id " + id);
    }
}
