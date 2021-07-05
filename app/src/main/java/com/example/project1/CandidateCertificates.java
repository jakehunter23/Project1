package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CandidateCertificates extends AppCompatActivity {
    ImageView back_btn_cert;
    Button btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_certificates);

        back_btn_cert = findViewById(R.id.back_arrow_certificate);
        btn_next = findViewById(R.id.cert_next);

        // setting listener for next
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CandidateEducation.class);
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