package com.example.smartcity.UserViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smartcity.R;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().hide();

        // TODO: 02-04-2022 ADD Logout button 

        CardView travelCard = findViewById(R.id.travelCard);
        travelCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "On click card demo", Toast.LENGTH_SHORT).show();
                final Intent i = new Intent(Menu.this, TravelActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Returned from the activity", Toast.LENGTH_SHORT).show();
                
            }
        });
    }
}