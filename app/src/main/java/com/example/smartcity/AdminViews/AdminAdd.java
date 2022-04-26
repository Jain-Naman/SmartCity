package com.example.smartcity.AdminViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartcity.Adapters.AddController;

import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.R;
import com.example.smartcity.Utils.TravelType;

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
        String category = extras.getString("category");
        boolean toUpdate = false;

        if(extras.containsKey("title")){
            title.setText(extras.getString("title"));
            description.setText(extras.getString("description"));
            id = extras.getString("id");
            toUpdate = true;
        }

        boolean finalToUpdate = toUpdate;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO - create an abstract class from which other models inherit
                switch (category) {
                    case "Travel":
                        TravelModel model = new TravelModel();
                        model.setTravelTitle(title.getText().toString());
                        model.setTravelDescription(description.getText().toString());
                        model.setTravelType(TravelType.AMUSEMENT_PARKS);
                        model.setId(id);
                        // TODO - dropdown for travel type
                        // send to controller
                        addController = new AddController(category, model);
                        addController.addOrUpdateItemInDatabase(finalToUpdate); // or updateItem
                        Toast.makeText(getApplicationContext(), "Details added/updated successfully!", Toast.LENGTH_SHORT).show(); // or updated
                        finish();
                        break;
                    case "Job Post":
                        // model = new JobModel();
                        break;
                    case "Institutions":
                        // model = new InstituteModel();
                        break;
                    case "News":
                        // model = new NewsModel();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + category);
                }

            }
        });
    }
}