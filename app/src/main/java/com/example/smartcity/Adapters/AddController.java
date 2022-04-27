package com.example.smartcity.Adapters;

import android.util.Log;

import com.example.smartcity.Models.GenericModel;
import com.example.smartcity.Models.InstitutionModel;
import com.example.smartcity.Models.JobseekerModel;
import com.example.smartcity.Models.MovieModel;
import com.example.smartcity.Models.NewsModel;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.Utils.Database.InstitutionDatabaseManager;
import com.example.smartcity.Utils.Database.JobDatabaseManager;
import com.example.smartcity.Utils.Database.MovieDatabaseManager;
import com.example.smartcity.Utils.Database.NewsDatabaseManager;
import com.example.smartcity.Utils.Database.TravelDatabaseManager;

import java.util.Map;

public class AddController {

    public AddController() {
    }

    public void addOrUpdateItemInDatabase(boolean toUpdate, String category, Map<String, String> data) {
        Log.d("firebase", "ADD CONTROL " + category);
        switch (category) {
            case "travel":
                TravelDatabaseManager travelDatabaseManager = new TravelDatabaseManager();
                TravelModel travelModel = new TravelModel();
                travelModel.setTravelTitle(data.get("title"));
                travelModel.setTravelDescription(data.get("description"));
                travelModel.setLocation(data.get("location"));
                travelModel.setId(data.get("id"));
                if (toUpdate)
                    travelDatabaseManager.updateData(category, travelModel);
                else
                    travelDatabaseManager.insertData(category, travelModel);
                break;
            case "news":
                NewsDatabaseManager newsDatabaseManager = new NewsDatabaseManager();
                NewsModel newsModel = new NewsModel();
                newsModel.setNewsHeadline(data.get("title"));
                newsModel.setDetailedNews(data.get("description"));
                newsModel.setId(data.get("id"));
                if (toUpdate)
                    newsDatabaseManager.updateData(category, newsModel);
                else
                    newsDatabaseManager.insertData(category, newsModel);
                break;
            case "job post":
                JobDatabaseManager jobDatabaseManager = new JobDatabaseManager();
                JobseekerModel jobseekerModel = new JobseekerModel();
                jobseekerModel.setJobseekerName(data.get("title"));
                jobseekerModel.setJobseekerDescription(data.get("description"));
                jobseekerModel.setNumberOfVacancies(data.get("vacancies"));
                jobseekerModel.setId(data.get("id"));
                if (toUpdate)
                    jobDatabaseManager.updateData(category, jobseekerModel);
                else
                    jobDatabaseManager.insertData(category, jobseekerModel);
                break;
            case "institutions":
                InstitutionDatabaseManager institutionDatabaseManager = new InstitutionDatabaseManager();
                InstitutionModel institutionModel = new InstitutionModel();
                institutionModel.setInstitutionName(data.get("title"));
                institutionModel.setInstitutionDescription(data.get("description"));
                institutionModel.setId(data.get("id"));
                if (toUpdate)
                    institutionDatabaseManager.updateData(category, institutionModel);
                else
                    institutionDatabaseManager.insertData(category, institutionModel);
                break;
            case "movies":
                MovieDatabaseManager movieDatabaseManager = new MovieDatabaseManager();
                MovieModel movieModel = new MovieModel();
                movieModel.setMovieTitle(data.get("title"));
                movieModel.setMovieDescription(data.get("description"));
                movieModel.setMovieSeats(data.get("vacancies"));
                movieModel.setId(data.get("id"));
                if (toUpdate)
                    movieDatabaseManager.updateData(category, movieModel);
                else
                    movieDatabaseManager.insertData(category, movieModel);
                break;
        }
    }
}
