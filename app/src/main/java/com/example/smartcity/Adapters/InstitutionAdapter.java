package com.example.smartcity.Adapters;

import com.example.smartcity.AdminViews.AdminAdd;

import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.smartcity.Utils.Database.InstitutionDatabaseManager;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import com.example.smartcity.Utils.FirebaseResponseListener;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.Globals;
import com.example.smartcity.Models.InstitutionModel;
import com.example.smartcity.UserViews.InstitutionActivity;
import com.example.smartcity.R;

import com.example.smartcity.Utils.FirebaseResponseListener;

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
                i.putExtra("category", "institutions");
                i.putExtra("title", item.getInstitutionName());
                i.putExtra("description", item.getInstitutionDescription());
                i.putExtra("id", item.getId());
                institutionActivity.startActivity(i);
                institutionActivity.finish();
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
                                InstitutionDatabaseManager institutionDatabaseManager = new InstitutionDatabaseManager();
                                institutionDatabaseManager.deleteData("institutions", item.getId());
                                institutionList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return institutionList == null ? 0 : institutionList.size();
    }

    public void setInstitutionList(List<InstitutionModel> institutionList) {
        this.institutionList = institutionList;
        notifyDataSetChanged();
    }

    public void getFromDatabase() {
        List<InstitutionModel> institutionModelList = new ArrayList<>();

        InstitutionDatabaseManager institutionDatabaseManager = new InstitutionDatabaseManager();

        institutionDatabaseManager.fetchData("institutions", new FirebaseResponseListener<List<DocumentSnapshot>>() {
            @Override
            public void onCallback(List<DocumentSnapshot> response) {
                for (DocumentSnapshot d : response) {
                    InstitutionModel institutionModel = new InstitutionModel();
                    institutionModel.setInstitutionName(d.get("title").toString());
                    institutionModel.setInstitutionDescription(d.get("description").toString());
                    institutionModel.setId(d.getId());
                    Log.d("firebase", institutionModel.getId());
                    institutionModelList.add(institutionModel);
                }
                setInstitutionList(institutionModelList);
            }
        });
    }


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

