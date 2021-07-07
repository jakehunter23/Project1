package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JobLoginActivity extends AppCompatActivity {
TextView tv_signup;
ImageView back_login;
Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_submit = findViewById(R.id.buttonsubmit);
        //setting listener for submit button
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),dashboard.class);
                startActivity(intent);
            }
        });

        back_login = findViewById(R.id.back_arrow_login);
        //setting listener for back button

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(getApplicationContext(),MainActivity.class);
              //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
              //  startActivity(intent);
                finish();
            }
        });


        //setting colors
        tv_signup = findViewById(R.id.signup);
        String text = "<font color=#00000033> Don't have an account yet?</font><font color=#3CA6DA> Sign Up.</font>";
        tv_signup.setText(Html.fromHtml(text));

        //setting listener for signup
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),JSPersonalInfo.class);
                startActivity(intent);
            }
        });
    }
}