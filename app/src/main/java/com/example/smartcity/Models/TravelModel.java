package com.example.smartcity.Models;

public class TravelModel {
    private String travelTitle;
    private String travelDescription;
    private String id;
    private String location;

    public String getTravelTitle() {
        return travelTitle;
    }

    public void setTravelTitle(String title) {
        this.travelTitle = title;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
