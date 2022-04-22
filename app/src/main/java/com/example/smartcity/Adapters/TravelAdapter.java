package com.example.smartcity.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.Globals;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.R;
import com.example.smartcity.UserViews.TravelActivity;

import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ViewHolder> {
    List<TravelModel> travelList;
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
        return travelList.size();
    }

    public void setTravelList(List<TravelModel> travelList) {
        this.travelList = travelList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView travelTitle;
        TextView travelDescription;
        Button editButton;
        Button deleteButton;

        ViewHolder(View view) {
            super(view);
            travelTitle = view.findViewById(R.id.travelTitleText);
            travelDescription = view.findViewById(R.id.travelDescription);
            editButton = view.findViewById(R.id.travelEdit);
            deleteButton = view.findViewById(R.id.travelDelete);
            if (!Globals.currentUser.equals("admin")) {
                editButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.INVISIBLE);
            }
        }
    }
}
