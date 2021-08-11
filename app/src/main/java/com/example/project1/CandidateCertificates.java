package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;

public class CandidateCertificates extends AppCompatActivity {
    ImageView back_btn_cert;
    Button btn_next;
    String firstname, lastname, email, phone, city, address, country, zipcode, company_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_certificates);

        firstname = getIntent().getExtras().getString("first_name");
        lastname = getIntent().getExtras().getString("last_name");
        email = getIntent().getExtras().getString("email");
        phone = getIntent().getExtras().getString("phone_number");
        address = getIntent().getExtras().getString("address");
        city = getIntent().getExtras().getString("city");
        country = getIntent().getExtras().getString("country");
        zipcode = getIntent().getExtras().getString("zipcode");
        company_id = getIntent().getExtras().getString("company_id");

        back_btn_cert = findViewById(R.id.back_arrow_certificate);
        btn_next = findViewById(R.id.cert_next);

        // setting listener for next
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CandidateEducation.class);
                Bundle bundle = new Bundle();
                bundle.putString("first_name", firstname);
                bundle.putString("last_name", lastname);
                bundle.putString("email",email);
                bundle.putString("phone_number", phone);
                bundle.putString("address", address);
                bundle.putString("city", city);
                bundle.putString("country", country);
                bundle.putString("zipcode", zipcode);
                bundle.putString("company_id", company_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //setting listener for back button
        ImageView back_btn_review= findViewById(R.id.back_arrow_review);
        back_btn_cert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(getApplicationContext(), CandidateGenInfo.class);
               // startActivity(intent);
                finish();
            }
        });
    }
}