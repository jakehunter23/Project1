package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecruitmentSplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT = 2000;
    String designation, createdDate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.splash_screen_recruitment);
        designation = getIntent().getExtras().getString("designation");
        createdDate = getIntent().getExtras().getString("createdDate");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(RecruitmentSplashScreenActivity.this,
                       CreateTask.class);
                Bundle specificBundle = new Bundle();
                specificBundle.putString("designation", designation);
                specificBundle.putString("createdDate", createdDate);                //Intent is used to switch from one activity to another.
                i.putExtras(specificBundle);
                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
