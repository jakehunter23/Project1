package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class JSDetails extends AppCompatActivity {
    Button detail_save;
    ImageView detail_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsdetails);

        detail_save = findViewById(R.id.detail_save);
        detail_arrow = findViewById(R.id.details_arrow);

        //listener for save button
        detail_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),dashboard.class);
                startActivity(intent);
            }
        });

        //listener for back arrow
        detail_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(JSDetails.this, JSPassword.class);
                // startActivity(intent);
                finish();

            }
        });
    }

}