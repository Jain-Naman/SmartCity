package com.example.smartcity.UserViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartcity.Adapters.InstitutionAdapter;
import com.example.smartcity.Adapters.TravelAdapter;
import com.example.smartcity.Models.InstitutionModel;
import com.example.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class InstitutionActivity extends AppCompatActivity {

    private RecyclerView institutionRecyclerView;
    private InstitutionAdapter institutionAdapter;
    private List<InstitutionModel> institutionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution);

        getSupportActionBar().hide();

        institutionList = new ArrayList<>();

        institutionRecyclerView = findViewById(R.id.institutionRecyclerView);
        institutionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        institutionAdapter = new InstitutionAdapter(this);


        institutionRecyclerView.setAdapter(institutionAdapter);

        InstitutionModel institution1 = new InstitutionModel();
        institution1.setInstitutionName("Title 1");
        institution1.setInstitutionDescription("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        InstitutionModel institution2 = new InstitutionModel();
        institution2.setInstitutionName("Title 2");
        institution2.setInstitutionDescription("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        InstitutionModel institution3 = new InstitutionModel();
        institution3.setInstitutionName("Title 3");
        institution3.setInstitutionDescription("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        institutionList.add(institution1);
        institutionList.add(institution2);
        institutionList.add(institution3);

        institutionAdapter.setInstitutionList(institutionList);
        institutionAdapter.notifyDataSetChanged();

    }
}