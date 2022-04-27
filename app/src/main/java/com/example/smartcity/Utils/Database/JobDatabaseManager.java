package com.example.smartcity.Utils.Database;

import android.util.Log;

import com.example.smartcity.Models.JobseekerModel;
import com.example.smartcity.Models.NewsModel;
import com.example.smartcity.Utils.FirebaseResponseListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.AbstractPreferences;

public class JobDatabaseManager {
    private DatabaseHandler databaseHandler = new DatabaseHandler();

    public void insertData(String category, JobseekerModel jobseekerModel) {
        Map<String, Object> dataMap = new HashMap<>();
        List<String> appliedList = new ArrayList<>();
        appliedList.add("");

        dataMap.put("title", jobseekerModel.getJobseekerName());
        dataMap.put("description", jobseekerModel.getJobseekerDescription());
        dataMap.put("vacancies", jobseekerModel.getNumberOfVacancies());
        dataMap.put("applied", appliedList);

        databaseHandler.putData(dataMap, category);
    }

    public void fetchData(String category, FirebaseResponseListener<List<DocumentSnapshot>> firebaseResponseListener) {
        databaseHandler.getData(category, firebaseResponseListener);
    }

    public void updateData(String category, JobseekerModel jobseekerModel) {

        Map<String, Object> dataMap = new HashMap<>();
        String id;
        dataMap.put("title", jobseekerModel.getJobseekerName());
        dataMap.put("description", jobseekerModel.getJobseekerDescription());
        dataMap.put("vacancies", jobseekerModel.getNumberOfVacancies());
        id = jobseekerModel.getId();
        databaseHandler.modifyData(dataMap, category, id);
    }

    public void deleteData(String category, String id) {
        databaseHandler.removeData(category, id);
        Log.d("firebase", "Deleted document with id " + id);
    }

    public void editJobBooking(String email, String docId, FirebaseResponseListener<Boolean> firebaseResponseListener) {
        databaseHandler.editJobData(email, docId, firebaseResponseListener);
    }
}
