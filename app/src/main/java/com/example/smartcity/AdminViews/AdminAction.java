package com.example.smartcity.AdminViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.UserViews.TravelActivity;

public class AdminAction extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final String DEFAULT_CATEGORY = "Travel";
    private final String DEFAULT_ACTION = "Add New";

    private Spinner actionSpinner;
    private Spinner categorySpinner;
    private String nextAction = DEFAULT_ACTION;
    private String nextCategory = DEFAULT_CATEGORY;
    private Button adminActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_action);

        getSupportActionBar().hide();

        actionSpinner = findViewById(R.id.actionDropdown);
        ArrayAdapter<CharSequence> actionAdapter = ArrayAdapter.createFromResource(this, R.array.admin_actions, android.R.layout.simple_dropdown_item_1line);
        actionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        actionSpinner.setAdapter(actionAdapter);
        actionSpinner.setOnItemSelectedListener(this);

        categorySpinner = findViewById(R.id.categoryDropDown);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this, R.array.admin_activities, android.R.layout.simple_dropdown_item_1line);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(this);

        adminActionButton = findViewById(R.id.adminActionButton);

        adminActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Action: " + nextAction + " Category: " + nextCategory, Toast.LENGTH_SHORT).show();
                if(nextAction.equals("Add New")){
                    Log.d("action", "Add new");
                    final Intent i = new Intent(AdminAction.this, AdminAdd.class);
                    i.putExtra("category", nextCategory);
                    startActivity(i);
                }
                else{
                    final Intent i = new Intent(AdminAction.this, TravelActivity.class);
                    i.putExtra("category", nextCategory);
                    startActivity(i);
                    // finish();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = (String) adapterView.getItemAtPosition(i);
        if (((String) adapterView.getItemAtPosition(0)).equals("Add New")){
            nextAction = item;
        }
        else {
            nextCategory = item;
        }
        Log.d("dropdown", nextAction);
        Log.d("dropdown", nextCategory);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}