package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.android.volley.toolbox.StringRequest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactSplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 2000;
    String firstName, lastName, email, middleName, lastContactDate, phoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firstName = getIntent().getExtras().getString("first_name");
        lastName = getIntent().getExtras().getString("last_name");
        middleName = getIntent().getExtras().getString("middle_name");
        phoneNumber = getIntent().getExtras().getString("contact_number");
        email = getIntent().getExtras().getString("email");
        lastContactDate = getIntent().getExtras().getString("last_contact_date");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen_contact);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(ContactSplashScreenActivity.this,
                        ContactActivity.class);
                //Intent is used to switch from one activity to another.
                Bundle bundle = new Bundle();
                bundle.putString("first_name", firstName);
                bundle.putString("last_name", lastName);
                bundle.putString("middle_name", middleName);
                bundle.putString("email", email);
                bundle.putString("contact_number", phoneNumber);
                bundle.putString("last_contact_date", lastContactDate);
                i.putExtras(bundle);
                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
