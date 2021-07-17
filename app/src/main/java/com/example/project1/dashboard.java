package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class dashboard extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<SpecificJob>jobList;
    SpecificJobAdoptor adaptor;
    private RecyclerView suggestRecyclerViews;
    private RecyclerView specific_recycle;
    private TextView logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ImageView navbutton= (ImageView)findViewById(R.id.navbotton);
        TextView showall= (TextView)findViewById(R.id.showall);
        suggestRecyclerViews=findViewById(R.id.suggestrecycle);
        suggestRecyclerViews.setLayoutManager(new LinearLayoutManager(this));
        suggestRecyclerViews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        String [] name={"AirBnB","Netflix Inc","AirBnB"};
        suggestRecyclerViews.setAdapter(new JobsuggestRecycleAdoptar(name ,this));

        specific_recycle=findViewById(R.id.recyclejob);
        specific_recycle.setLayoutManager(new LinearLayoutManager(this));
        specific_recycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        List<SpecificJob> job_name= new ArrayList<SpecificJob>();
        job_name.add(new SpecificJob(R.drawable.data_management,"48 Active Recruiters","Data Analyst","Hydrabad"));
        job_name.add(new SpecificJob((R.drawable.data_management),"52 Active Recruiters","Python Developer","Mumbai"));
        specific_recycle.setAdapter(new SpecificJobAdoptor(job_name));



        AppCompatButton buttonschedule=(AppCompatButton)findViewById(R.id.buttonschedule);
        navbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent navid = new Intent(dashboard.this,navigation.class);
                startActivity(navid);


            }

        });
        showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent suggestjob = new Intent(dashboard.this,jobSuggestion.class);
                startActivity(suggestjob);



            }
        });
        buttonschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent schedule = new Intent(dashboard.this,mySchedule.class);
                startActivity(schedule);



            }
        });
        iniData();
        initRecylerView();

        //setting listener for logout button
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating shared preferences object
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor =preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                Toast.makeText(getApplicationContext(), "Logout Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),JobLoginActivity.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

    }

    private void iniData() {
        jobList = new ArrayList<>();
        jobList.add(new SpecificJob(R.drawable.data_management,"48 Active Recruiters","Data Analysis","Hyderabad"));
        jobList.add(new SpecificJob(R.drawable.coder,"32 Active Recruiters","Python Developer","Hyderabad"));
        jobList.add(new SpecificJob(R.drawable.data_management,"48 Active Recruiters","Data Analysis","Hyderabad"));
    }

    private void initRecylerView() {
        recyclerView=findViewById(R.id.recyclejob);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adaptor=new SpecificJobAdoptor(jobList);
        adaptor.notifyDataSetChanged();
    }
}