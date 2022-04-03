package com.example.smartcity.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.R;
import com.example.smartcity.UserViews.TravelActivity;

import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ViewHolder> {
    List<TravelModel> travelList;
    private TravelActivity travelActivity;

    // TODO: 02-04-2022 ADD Database Functionality
    // private DatabaseHandler db;

    public TravelAdapter(TravelActivity travelActivity){
        this.travelActivity = travelActivity;
    }

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
                Toast.makeText(travelActivity.getApplicationContext(),"Delete mode", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return travelList.size();
    }

    public void setTravelList(List<TravelModel> travelList){
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
        }
    }
}
