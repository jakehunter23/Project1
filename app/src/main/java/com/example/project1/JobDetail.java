package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class JobDetail extends AppCompatActivity {
ImageView back_btn_job;
Button btn_apply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        btn_apply = findViewById(R.id.apply);
        back_btn_job = findViewById(R.id.back_arrow_job);

        //setting back button Listener
        back_btn_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            onBackPressed();
            }
        });

        //setting listener for apply listener
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CandidateGenInfo.class);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed(){
      //  Intent intent = new Intent(JobDetail.this,DashBoardActivity.class);
       // startActivity(intent);
        finish();
    }
}