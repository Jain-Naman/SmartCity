package com.example.smartcity.Models;

import com.example.smartcity.Utils.TravelType;

public class TravelModel {
    private String travelTitle;
    private TravelType travelType;
    private String travelDescription;
    private String id;

    // TODO - location

    public String getTravelTitle() {
        return travelTitle;
    }

    public void setTravelTitle(String title) {
        this.travelTitle = title;
    }

    public TravelType getTravelType() {
        return travelType;
    }

    public void setTravelType(TravelType travelType) {
        this.travelType = travelType;
    }

    public String getTravelDescription() {
        return travelDescription;
    }

    public void setTravelDescription(String description) {
        this.travelDescription = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}
