package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class JSCareerInfo extends AppCompatActivity {
Button career_save;
ImageView career_arrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jscareer_info);

        career_arrow = findViewById(R.id.career_arrow);
        career_save=findViewById(R.id.career_save);

        //listener for back arrow
        career_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent (getApplicationContext(),JSPersonalInfo.class);
              //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
              //  startActivity(intent);
                finish();
            }
        });

        //listener for save button
        career_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),JSPassword.class);
                startActivity(intent);
            }
        });
    }
}