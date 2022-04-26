package com.example.smartcity.UserViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartcity.Adapters.JobseekerAdapter;
import com.example.smartcity.Adapters.TravelAdapter;
import com.example.smartcity.Models.JobseekerModel;
import com.example.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class JobseekerActivity extends AppCompatActivity {

    private RecyclerView jobseekerRecyclerView;
    private JobseekerAdapter jobseekerAdapter;
    private List<JobseekerModel> jobseekerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobseeker);

        getSupportActionBar().hide();

        jobseekerList = new ArrayList<>();

        jobseekerRecyclerView = findViewById(R.id.jobseekerRecyclerView);
        jobseekerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        jobseekerAdapter = new JobseekerAdapter(this);

        jobseekerRecyclerView.setAdapter(jobseekerAdapter);

        JobseekerModel jobseeker1 = new JobseekerModel();
        jobseeker1.setJobseekerName("Title 1");
        jobseeker1.setJobseekerDescription("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        JobseekerModel jobseeker2 = new JobseekerModel();
        jobseeker2.setJobseekerName("Title 2");
        jobseeker2.setJobseekerDescription("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        JobseekerModel jobseeker3 = new JobseekerModel();
        jobseeker3.setJobseekerName("Title 3");
        jobseeker3.setJobseekerDescription("Lorem ipsum dolor sit amet. 33 consequatur quas in galisum asperiores " +
                "non beatae inventore? Id deleniti eveniet vel aliquid assumenda in explicabo " +
                "doloremque cum corrupti nostrum. Vel laborum tempora qui galisum blanditiis nam " +
                "temporibus doloremque ut atque facilis sed obcaecati quasi non culpa quisquam aut tempore voluptatem.");

        jobseekerList.add(jobseeker1);
        jobseekerList.add(jobseeker2);
        jobseekerList.add(jobseeker3);

        jobseekerAdapter.setJobseekerList(jobseekerList);
        jobseekerAdapter.notifyDataSetChanged();

    }
}