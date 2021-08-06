package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CandidateSplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 2000;
    String firstName, lastName, email, createdDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.candidate_splash_screen);

        firstName = getIntent().getExtras().getString("first_name");
        lastName = getIntent().getExtras().getString("last_name");
        email = getIntent().getExtras().getString("email");
        createdDate = getIntent().getExtras().getString("created_date");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(CandidateSplashScreen.this,
                        CandidateActivity.class);
                //Intent is used to switch from one activity to another.
                Bundle bundle =  new Bundle();
                bundle.putString("first_name", firstName);
                bundle.putString("last_name", lastName);
                bundle.putString("email", email);
                bundle.putString("created_date", createdDate);
                i.putExtras(bundle);

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
