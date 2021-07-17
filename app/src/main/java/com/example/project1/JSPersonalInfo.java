package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class JSPersonalInfo extends AppCompatActivity {
Button info_save;
ImageView info_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jspersonal_info);

        info_arrow = findViewById(R.id.info_arrow);
        info_save = findViewById(R.id.info_save);

        //listener for save button
        info_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),JSCareerInfo.class);
                startActivity(intent);

            }
        });

        //listener for back arrow
        info_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

}