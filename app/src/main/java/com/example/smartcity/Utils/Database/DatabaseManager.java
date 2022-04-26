package com.example.smartcity.Utils.Database;

import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.smartcity.Globals;
import com.example.smartcity.Models.GenericModel;
import com.example.smartcity.Models.InstitutionModel;
import com.example.smartcity.Models.JobseekerModel;
import com.example.smartcity.Models.MovieModel;
import com.example.smartcity.Models.NewsModel;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.UserViews.InstitutionActivity;
import com.example.smartcity.Utils.FirebaseResponseListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {

    public DatabaseManager() {
    }

    private DatabaseHandler databaseHandler = new DatabaseHandler();

    // TODO - create a generic model
    public void insertData(String category, GenericModel genericModel) {
        Map<String, Object> dataMap = new HashMap<>();

        switch (category) {
            case "travel": {
                TravelModel model = (TravelModel) genericModel.getObject();
                dataMap.put("title", model.getTravelTitle());
                dataMap.put("description", model.getTravelDescription());
                dataMap.put("location", model.getLocation());
                break;
            }
            case "job post": {
                List<String> appliedList = new ArrayList<>();
                appliedList.add(" ");
                JobseekerModel model = (JobseekerModel) genericModel.getObject();
                dataMap.put("title", model.getJobseekerName());
                dataMap.put("description", model.getJobseekerDescription());
                dataMap.put("vacancies", model.getNumberOfVacancies());
                dataMap.put("applied", appliedList);
                break;
            }
            case "institutions": {
                InstitutionModel model = (InstitutionModel) genericModel.getObject();
                dataMap.put("title", model.getInstitutionName());
                dataMap.put("description", model.getInstitutionDescription());
                break;
            }
            case "news": {
                NewsModel model = (NewsModel) genericModel.getObject();
                dataMap.put("title", model.getNewsHeadline());
                dataMap.put("description", model.getDetailedNews());
                break;
            }
            case "movies": {
                List<String> bookedList = new ArrayList<>();
                bookedList.add(" ");
                MovieModel model = (MovieModel) genericModel.getObject();
                dataMap.put("title", model.getMovieTitle());
                dataMap.put("description", model.getMovieDescription());
                dataMap.put("vacancies", model.getMovieSeats());
                dataMap.put("booked", bookedList);
                break;
            }
        }
        databaseHandler.putData(dataMap, category);
    }

    public void fetchData(String category, FirebaseResponseListener<List<DocumentSnapshot>> firebaseResponseListener) {
        databaseHandler.getData(category, firebaseResponseListener);
    }

    public void updateData(String category, GenericModel genericModel) {

        Map<String, Object> dataMap = new HashMap<>();
        String id;

        switch (category) {
            case "travel": {
                TravelModel model = (TravelModel) genericModel.getObject();
                dataMap.put("title", model.getTravelTitle());
                dataMap.put("description", model.getTravelDescription());
                dataMap.put("location", model.getLocation());
                id = model.getId();
                break;
            }
            case "job post": {
                JobseekerModel model = (JobseekerModel) genericModel.getObject();
                dataMap.put("title", model.getJobseekerName());
                dataMap.put("description", model.getJobseekerDescription());
                dataMap.put("vacancies", model.getNumberOfVacancies());
                id = model.getId();
                break;
            }
            case "institutions": {
                InstitutionModel model = (InstitutionModel) genericModel.getObject();
                dataMap.put("title", model.getInstitutionName());
                dataMap.put("description", model.getInstitutionDescription());
                id = model.getId();
                break;
            }
            case "news": {
                NewsModel model = (NewsModel) genericModel.getObject();
                dataMap.put("title", model.getNewsHeadline());
                dataMap.put("description", model.getDetailedNews());
                id = model.getId();
                break;
            }
            case "movies": {
                MovieModel model = (MovieModel) genericModel.getObject();
                dataMap.put("title", model.getMovieTitle());
                dataMap.put("description", model.getMovieDescription());
                dataMap.put("vacancies", model.getMovieSeats());
                id = model.getId();
                break;
            }
            default:
                id = null;
        }
        databaseHandler.modifyData(dataMap, category, id);
    }

    public void deleteData(String category, String id) {
        databaseHandler.removeData(category, id);
        Log.d("firebase", "Deleted document with id " + id);
    }

    public void editMovieBooking(String email, String docId, FirebaseResponseListener<Boolean> firebaseResponseListener) {
        databaseHandler.editMovieData(email, docId, firebaseResponseListener);
    }

    public void editJobBooking(String email, String docId, FirebaseResponseListener<Boolean> firebaseResponseListener) {
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
            CollectionReference collectionReference = firestore.collection(category.toLowerCase());
            DocumentReference documentReference = collectionReference.document(id);

            Log.d("firebase", documentReference.getId());

            documentReference.update("title", data.get("title"));
            documentReference.update("description", data.get("description"));

            if (category.equals("travel")) {
                documentReference.update("location", data.get("location"));
            } else if (category.equals("movies") || category.equals("job post")) {
                documentReference.update("vacancies", data.get("vacancies"));
            }
        }

        public void removeData(String category, String id) {
            firestore.collection(category.toLowerCase()).document(id).delete();
        }
    }
}
