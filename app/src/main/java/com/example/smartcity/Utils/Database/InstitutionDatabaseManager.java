package com.example.smartcity.Utils.Database;

import android.util.Log;

import com.example.smartcity.Models.InstitutionModel;
import com.example.smartcity.Models.NewsModel;
import com.example.smartcity.Utils.FirebaseResponseListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstitutionDatabaseManager {
    private DatabaseHandler databaseHandler = new DatabaseHandler();

    public void insertData(String category, InstitutionModel institutionModel) {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("title", institutionModel.getInstitutionName());
        dataMap.put("description", institutionModel.getInstitutionDescription());

        databaseHandler.putData(dataMap, category);
    }

    public void fetchData(String category, FirebaseResponseListener<List<DocumentSnapshot>> firebaseResponseListener) {
        databaseHandler.getData(category, firebaseResponseListener);
    }

    public void updateData(String category, InstitutionModel institutionModel) {

        Map<String, Object> dataMap = new HashMap<>();
        String id;
        dataMap.put("title", institutionModel.getInstitutionName());
        dataMap.put("description", institutionModel.getInstitutionDescription());
        id = institutionModel.getId();
        databaseHandler.modifyData(dataMap, category, id);
    }

    public void deleteData(String category, String id) {
        databaseHandler.removeData(category, id);
        Log.d("firebase", "Deleted document with id " + id);
    }
}
