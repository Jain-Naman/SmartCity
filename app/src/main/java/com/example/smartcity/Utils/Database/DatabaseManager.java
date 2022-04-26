package com.example.smartcity.Utils.Database;

import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.smartcity.Globals;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.Utils.FirebaseResponseListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {

    public DatabaseManager() {
    }

    private DatabaseHandler databaseHandler = new DatabaseHandler();

    // TODO - create a generic model
    public void insertData(String category, TravelModel travelModel) {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("title", travelModel.getTravelTitle());
        dataMap.put("description", travelModel.getTravelDescription());
        /*
        dataMap.put("type", travelModel.getTravelType().toString());
        dataMap.put("location", travelModel.getLocation());
        */
        databaseHandler.putData(dataMap, category);
    }

    public void fetchData(String category, FirebaseResponseListener<List<DocumentSnapshot>> firebaseResponseListener) {
        databaseHandler.getData(category, firebaseResponseListener);
    }

    public void updateData(String category, TravelModel travelModel) {

        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("title", travelModel.getTravelTitle());
        dataMap.put("description", travelModel.getTravelDescription());

        Log.d("firebase", "IN UPDATE DATA");
        databaseHandler.modifyData(dataMap, category, travelModel.getId());
    }

    public void deleteData(String category, String id) {
        databaseHandler.removeData(category, id);
        Log.d("firebase", "Deleted document with id " + id);
    }

    public void editMovieBooking(String email, String docId, FirebaseResponseListener<Boolean> firebaseResponseListener){
        databaseHandler.editMovieData(email, docId, firebaseResponseListener);
    }

    public void editJobBooking(String email, String docId, FirebaseResponseListener<Boolean> firebaseResponseListener){
        databaseHandler.editJobData(email, docId, firebaseResponseListener);
    }


    private class DatabaseHandler {

        DatabaseHandler() {
        }

        private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        public void putData(Map<String, Object> data, String category) {
            firestore.collection(category.toLowerCase()).add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("firebase", "Document written with id " + documentReference.getId());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Log.w("firebase", "Error adding document", e);
                }
            });
        }

        public void editJobData(String email, String docId, FirebaseResponseListener<Boolean> firebaseResponseListener) {
            Log.d("firebase", email);
            DocumentReference documentReference = firestore.collection("job post").document(docId);
            documentReference.update("applied", FieldValue.arrayUnion(email)).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    firebaseResponseListener.onCallback(true);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    firebaseResponseListener.onCallback(false);
                }
            });
        }

        public void editMovieData(String email, String docId, FirebaseResponseListener<Boolean> firebaseResponseListener) {
            Log.d("firebase", email);
            DocumentReference documentReference = firestore.collection("movies").document(docId);
            documentReference.update("booked", FieldValue.arrayUnion(email)).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    firebaseResponseListener.onCallback(true);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    firebaseResponseListener.onCallback(false);
                }
            });
        }

        public void getData(String category, FirebaseResponseListener<List<DocumentSnapshot>> firebaseResponseListener) {
            Task<QuerySnapshot> task = firestore.collection(category.toLowerCase()).get();
            task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (!querySnapshot.isEmpty()) {
                            List<DocumentSnapshot> list = querySnapshot.getDocuments();
                            firebaseResponseListener.onCallback(list);
                        }
                    } else {
                        Log.d("firebase", "Error accessing data/ No data found");
                    }
                }
            });
        }

        public void modifyData(Map<String, Object> data, String category, String id) {
            DocumentReference documentReference = firestore.collection(category.toLowerCase()).document(id);
            Log.d("firebase", documentReference.getId());

            documentReference.update("title", data.get("title"));
            documentReference.update("description", data.get("description"));
        }

        public void removeData(String category, String id){
            firestore.collection(category.toLowerCase()).document(id).delete();
        }
    }
}
