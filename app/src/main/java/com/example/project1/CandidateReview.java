package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CandidateReview extends AppCompatActivity {
    ImageView back_btn_review;
    Button btn_review_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_review);

        btn_review_submit = findViewById(R.id.review_submit);
      back_btn_review= findViewById(R.id.back_arrow_review);

        //setting listener for back button
        back_btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(getApplicationContext(), CandidateEducation.class);
              //  startActivity(intent);
                finish();
            }
        });

        //setting listener for submit button
        btn_review_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CandidateComplete.class);
                startActivity(intent);
            }
        });
    }
}