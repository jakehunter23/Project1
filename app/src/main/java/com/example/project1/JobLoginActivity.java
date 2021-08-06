package com.example.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
                Intent intent = new Intent(getBaseContext(),dashboard.class);
                startActivity(intent);
            }
        });

        back_login = findViewById(R.id.back_arrow_login);
        //setting listener for back button

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SplashScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
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

        CheckBox JS_rememberMe = findViewById(R.id.JS_remember_me);

        //creating shared preference object
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true"))
        {//calling login method
            Intent i = new Intent(getApplicationContext(),dashboard.class);
            startActivity(i);
        }else if (checkbox.equals("false"))
        {
            Toast.makeText(getApplicationContext(), "Please Login", Toast.LENGTH_SHORT).show();
        }

        //listener for remember me
        JS_rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    //creating shared preferences object
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor =preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();


                }else if(!buttonView.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor =preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();

                }
            }
        });

    }
}