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

    String firstname, lastname, email, contact, address, city, zip,state, country,id, token, qualification, yop, exp, skill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsdetails);

        detail_save = findViewById(R.id.detail_save);
        detail_arrow = findViewById(R.id.details_arrow);

        firstname = getIntent().getExtras().getString("first_name");
        lastname = getIntent().getExtras().getString("last_name");
        email = getIntent().getExtras().getString("email");
        contact = getIntent().getExtras().getString("contact");
        address = getIntent().getExtras().getString("address");
        city = getIntent().getExtras().getString("city");
        zip = getIntent().getExtras().getString("zipcode");
        state = getIntent().getExtras().getString("state");
        country = getIntent().getExtras().getString("country");
        id = getIntent().getExtras().getString("signup_id");
        token = getIntent().getExtras().getString("token");
        qualification = getIntent().getExtras().getString("qualification");
        yop = getIntent().getExtras().getString("year_passing");
        exp = getIntent().getExtras().getString("experience");
        skill = getIntent().getExtras().getString("skill");

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