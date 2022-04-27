package com.example.smartcity.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.AdminViews.AdminAdd;
import com.example.smartcity.Globals;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.R;
import com.example.smartcity.UserViews.TravelActivity;
import com.example.smartcity.Utils.Database.TravelDatabaseManager;
import com.example.smartcity.Utils.FirebaseResponseListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ViewHolder>{
    List<TravelModel> travelList = new ArrayList<>();
    private TravelActivity travelActivity;

    public TravelAdapter(TravelActivity travelActivity) {
        this.travelActivity = travelActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.travel_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TravelAdapter.ViewHolder holder, int position) {
        TravelModel item = travelList.get(position);
        holder.travelTitle.setText(item.getTravelTitle());
        holder.travelDescription.setText(item.getTravelDescription());

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(travelActivity.getApplicationContext(), "Editing mode", Toast.LENGTH_SHORT).show();

                // go to AdminAdd with the existing information
                final Intent i = new Intent(travelActivity.getApplication(), AdminAdd.class);
                i.putExtra("category", "travel");
                i.putExtra("title", item.getTravelTitle());
                i.putExtra("description", item.getTravelDescription());
                i.putExtra("id", item.getId());
                travelActivity.startActivity(i);
                travelActivity.finish();
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(travelActivity.getApplicationContext(), "Delete mode", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                Toast.makeText(travelActivity.getApplicationContext(), "Delete Confirmed!", Toast.LENGTH_SHORT).show();
                                TravelDatabaseManager travelDatabaseManager = new TravelDatabaseManager();
                                travelDatabaseManager.deleteData("travel", item.getId());
                                travelList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
            }
        });

        holder.travelLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLocation()));
                intent.setPackage("com.google.android.apps.maps");
                travelActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return travelList == null ? 0 : travelList.size();
    }

    public void setTravelList(List<TravelModel> travelList) {
        this.travelList = travelList;
        notifyDataSetChanged();
    }


    public void getFromDatabase(){
        List<TravelModel> travelModelList = new ArrayList<>();

        TravelDatabaseManager travelDatabaseManager = new TravelDatabaseManager();

        travelDatabaseManager.fetchData("travel", new FirebaseResponseListener<List<DocumentSnapshot>>() {
            @Override
            public void onCallback(List<DocumentSnapshot> response) {
                for(DocumentSnapshot d: response){
                    TravelModel travelModel = new TravelModel();
                    travelModel.setTravelTitle(d.get("title").toString());
                    travelModel.setTravelDescription(d.get("description").toString());
                    travelModel.setLocation(d.get("location").toString());
                    travelModel.setId(d.getId());

                    Log.d("firebase", travelModel.getId());
                    travelModelList.add(travelModel);
                }
                setTravelList(travelModelList);
            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView travelTitle;
        TextView travelDescription;
        Button editButton;
        Button deleteButton;
        Button travelLocation;

        ViewHolder(View view) {
            super(view);
            travelTitle = view.findViewById(R.id.travelTitleText);
            travelDescription = view.findViewById(R.id.travelDescription);
            editButton = view.findViewById(R.id.travelEdit);
            deleteButton = view.findViewById(R.id.travelDelete);
            travelLocation = view.findViewById(R.id.travelLocation);

            if (!Globals.currentUser.equals("admin")) {
                editButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.INVISIBLE);
            }
        }
    }
}
