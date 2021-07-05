package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CandidateGenInfo extends AppCompatActivity {
ImageView back_btn_genInfo;
Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_gen_info);
        btn_submit = findViewById(R.id.buttonsubmit);
        back_btn_genInfo = findViewById(R.id.back_arrow_genInfo);
        ImageView back_btn_review= findViewById(R.id.back_arrow_review);


        //setting backbutton listener
        back_btn_genInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(getApplicationContext(), JobDetail.class);
                //startActivity(intent);
                finish();
            }
        });

        //setting submit button listener
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CandidateCertificates.class);
                startActivity(intent);
            }
        });

    }
}