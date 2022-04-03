package com.example.smartcity.Models;

import com.example.smartcity.Utils.TravelType;

public class TravelModel {
    private String travelTitle;
    private TravelType travelType;
    private String travelDescription;
    private String id;

    public String getTravelTitle() {
        return travelTitle;
    }

    public void setTravelTitle(String travelTitle) {
        this.travelTitle = travelTitle;
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

    public void setTravelDescription(String travelDescription) {
        this.travelDescription = travelDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
