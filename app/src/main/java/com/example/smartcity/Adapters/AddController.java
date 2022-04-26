package com.example.smartcity.Adapters;

import com.example.smartcity.Models.GenericModel;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.Utils.Database.DatabaseManager;

public class AddController {
    private String category;
    private GenericModel model;

    private DatabaseManager databaseManager = new DatabaseManager();

    public AddController(String category) {
        this.category = category;
    }

    public void addOrUpdateItemInDatabase(boolean update, GenericModel model) {
        if (!update) {
            databaseManager.insertData(category, model);
        } else {
            databaseManager.updateData(category, model);
        }
    }

}
