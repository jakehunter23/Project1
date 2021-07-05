package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class jobSuggestion extends AppCompatActivity {
    private RecyclerView ReferRecyclerViews;
    CardView filter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_suggestion);
        ReferRecyclerViews=findViewById(R.id.RecyclerViews);
        ImageView bakbutton=findViewById(R.id.backbutton);

        ReferRecyclerViews.setLayoutManager(new LinearLayoutManager(this));
        String [] name={"AirBnB","Netflix Inc","AirBnB"};
        ReferRecyclerViews.setAdapter(new JobsuggestRecycleAdoptar(name,this));
        filter=(CardView)findViewById(R.id.fliter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insss = new Intent(jobSuggestion.this,jobSuggestion_1.class);
                startActivity(insss);
                finish();
            }
        });
        bakbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(jobSuggestion.this,dashboard.class);
                startActivity(back);
                finish();
            }
        });


    }



}