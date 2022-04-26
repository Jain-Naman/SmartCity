package com.example.smartcity.AdminViews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.Adapters.AddController;

import com.example.smartcity.Models.GenericModel;
import com.example.smartcity.Models.InstitutionModel;
import com.example.smartcity.Models.JobseekerModel;
import com.example.smartcity.Models.MovieModel;
import com.example.smartcity.Models.NewsModel;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.R;

public class AdminAdd extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private EditText tag;
    private EditText location;
    private EditText vacancies;
    private String id = null;

    private Button submit;
    private AddController addController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);

        title = findViewById(R.id.addTitle);
        description = findViewById(R.id.addDescription);
        tag = findViewById(R.id.addTag);
        location = findViewById(R.id.addLocation);
        vacancies = findViewById(R.id.addVacancies);

        submit = findViewById(R.id.addSubmitButton);

        Bundle extras = getIntent().getExtras();
        String category = extras.getString("category").toLowerCase();

        TextView addDetailsText = findViewById(R.id.addDetailsText);
        addDetailsText.setText(addDetailsText.getText() + " (" + category + ")");

        boolean toUpdate = false;

        if (extras.containsKey("title")) {
            title.setText(extras.getString("title"));
            description.setText(extras.getString("description"));
            id = extras.getString("id");
            toUpdate = true;
        }
        if (extras.containsKey("vacancies")) {
            vacancies.setText(extras.getString("vacancies"));
        }

        addController = new AddController(category);

        boolean finalToUpdate = toUpdate;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO - create an abstract class from which other models inherit
                Log.d("generic_model", "Clicked submit");
                GenericModel genericModel = null;

                switch (category) {
                    case "travel":
                        TravelModel tModel = new TravelModel();
                        tModel.setTravelTitle(title.getText().toString());
                        tModel.setTravelDescription(description.getText().toString());
                        tModel.setLocation(location.getText().toString().trim());
                        tModel.setId(id);
                        genericModel = new GenericModel<>(tModel);
                        break;
                    case "job post":
                        JobseekerModel jModel = new JobseekerModel();
                        jModel.setJobseekerName(title.getText().toString());
                        jModel.setJobseekerDescription(description.getText().toString());
                        jModel.setNumberOfVacancies(vacancies.getText().toString());
                        jModel.setId(id);
                        genericModel = new GenericModel<>(jModel);
                        break;
                    case "institutions":
                        InstitutionModel iModel = new InstitutionModel();
                        iModel.setInstitutionName(title.getText().toString());
                        iModel.setInstitutionDescription(description.getText().toString());
                        iModel.setId(id);
                        genericModel = new GenericModel<>(iModel);
                        break;
                    case "news":
                        NewsModel nModel = new NewsModel();
                        nModel.setNewsHeadline(title.getText().toString());
                        nModel.setDetailedNews(description.getText().toString());
                        nModel.setId(id);
                        break;
                    case "movies":
                        MovieModel mModel = new MovieModel();
                        mModel.setMovieTitle(title.getText().toString());
                        mModel.setMovieDescription(description.getText().toString());
                        mModel.setMovieSeats(vacancies.getText().toString());
                        mModel.setId(id);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Unknown category", Toast.LENGTH_SHORT).show();
                        throw new IllegalStateException("Unexpected value: " + category);
                }
                addController.addOrUpdateItemInDatabase(finalToUpdate, genericModel);
                Toast.makeText(getApplicationContext(), "Details added/updated successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}