package com.example.smartcity.UserViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartcity.Adapters.TravelAdapter;
import com.example.smartcity.Models.TravelModel;
import com.example.smartcity.R;
import com.example.smartcity.Utils.Database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class TravelActivity extends AppCompatActivity {

    private RecyclerView travelRecyclerView;
    private TravelAdapter travelAdapter;
    private List<TravelModel> travelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        getSupportActionBar().hide();

        travelList = new ArrayList<>();

        travelRecyclerView = findViewById(R.id.travelRecyclerView);
        travelRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        travelAdapter = new TravelAdapter(this);

        travelRecyclerView.setAdapter(travelAdapter);

        TravelModel travel1 = new TravelModel();
        travel1.setTravelTitle("Title 1");
        travel1.setTravelDescription("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        TravelModel travel2 = new TravelModel();
        travel2.setTravelTitle("Title 2");
        travel2.setTravelDescription("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        TravelModel travel3 = new TravelModel();
        travel3.setTravelTitle("Title 3");
        travel3.setTravelDescription("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        travelList.add(travel1);
        travelList.add(travel2);
        travelList.add(travel3);

        travelAdapter.getFromDatabase();

        // travelAdapter.setTravelList(travelList);
        travelAdapter.notifyDataSetChanged();

    }
}