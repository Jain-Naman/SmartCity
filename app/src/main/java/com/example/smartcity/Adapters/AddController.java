package com.example.smartcity.Adapters;

import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.Utils.Database.DatabaseManager;

public class AddController {
    private String category;
    private TravelModel travelModel;

    private DatabaseManager databaseManager = new DatabaseManager();

    public AddController(String category, TravelModel travelModel) {
        this.category = category;
        this.travelModel = travelModel;
    }

    public void addOrUpdateItemInDatabase(boolean update) {
        if (!update) {
            databaseManager.insertData(category, travelModel);
        } else {
            databaseManager.updateData(category, travelModel);
        }
    }

    public void deleteFromDatabase(){
        databaseManager.deleteData(category, travelModel.getId());
    }
}
