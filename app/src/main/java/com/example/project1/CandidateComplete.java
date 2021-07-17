package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CandidateComplete extends AppCompatActivity {
 ImageView back_btn;
 Button btn_comp_done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_complete);

        btn_comp_done = findViewById(R.id.Comp_done);
        back_btn = findViewById(R.id.back_arrow_complete);

        //setting listener for back button
     back_btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           //  Intent intent = new Intent(getApplicationContext(), CandidateReview.class);
           //  startActivity(intent);
             finish();
         }
     });

     //setting listener for done button
      btn_comp_done.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(CandidateComplete.this, dashboard.class);
              startActivity(intent);
          }
      });

    }
}