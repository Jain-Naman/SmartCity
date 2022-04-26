package com.example.smartcity.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.smartcity.Utils.Database.DatabaseManager;
//
//import com.example.smartcity.Utils.FirebaseResponseListener;

import com.google.firebase.firestore.DocumentSnapshot;

import android.content.Intent;

import com.example.smartcity.AdminViews.AdminAdd;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.Globals;
import com.example.smartcity.Models.InstitutionModel;
import com.example.smartcity.UserViews.InstitutionActivity;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.R;
import com.example.smartcity.UserViews.TravelActivity;

import java.util.List;

public class InstitutionAdapter extends RecyclerView.Adapter<InstitutionAdapter.ViewHolder> {
    List<InstitutionModel> institutionList;
    private InstitutionActivity institutionActivity;

    public InstitutionAdapter(InstitutionActivity institutionActivity) {
        this.institutionActivity = institutionActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.institution_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InstitutionAdapter.ViewHolder holder, int position) {
        InstitutionModel item = institutionList.get(position);
        holder.institutionName.setText(item.getInstitutionName());
        holder.institutionDescription.setText(item.getInstitutionDescription());
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(institutionActivity.getApplicationContext(), "Editing mode", Toast.LENGTH_SHORT).show();

                final Intent i = new Intent(institutionActivity.getApplication(), AdminAdd.class);
                i.putExtra("category", "institute");
                i.putExtra("title", item.getInstitutionName());
                i.putExtra("description", item.getInstitutionDescription());
                institutionActivity.startActivity(i);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(institutionActivity.getApplicationContext(), "Delete mode", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                Toast.makeText(institutionActivity.getApplicationContext(), "Delete Confirmed!", Toast.LENGTH_SHORT).show();
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
        return institutionList.size();
    }

    public void setInstitutionList(List<InstitutionModel> institutionList) {
        this.institutionList = institutionList;
        notifyDataSetChanged();
    }

    /*public void getFromDatabase(){
        List<InstitutionModel> institutionModelList = new ArrayList<>();

        DatabaseManager databaseManager = new DatabaseManager();

        databaseManager.fetchData("travel", new FirebaseResponseListener<List<DocumentSnapshot>>() {
            @Override
            public void onCallback(List<DocumentSnapshot> response) {
                for(DocumentSnapshot d: response){
                    TravelModel travelModel = new TravelModel();
                    travelModel.setTravelTitle(d.get("title").toString());
                    travelModel.setTravelDescription(d.get("description").toString());
                    travelModel.setId(d.getId());
                    Log.d("firebase", travelModel.getId());
                    travelModelList.add(travelModel);
                }
                setTravelList(travelModelList);
            }
        });
    }*/





    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView institutionName;
        TextView institutionDescription;
        Button editButton;
        Button deleteButton;

        ViewHolder(View view) {
            super(view);
            institutionName = view.findViewById(R.id.institutionNameText);
            institutionDescription = view.findViewById(R.id.institutionDescription);
            editButton = view.findViewById(R.id.institutionEdit);
            deleteButton = view.findViewById(R.id.institutionDelete);
            if (!Globals.currentUser.equals("admin")) {
                editButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.INVISIBLE);
            }
        }
    }
}

