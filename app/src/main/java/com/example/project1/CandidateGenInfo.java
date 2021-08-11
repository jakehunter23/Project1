package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CandidateGenInfo extends AppCompatActivity {
ImageView back_btn_genInfo;
Button btn_submit;
EditText Firstname, Lastname, Email, Phone, City, Address, Country, Zipcode;
String firstname, lastname, email, phone, city, address, country, zipcode, company_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_gen_info);
        btn_submit = findViewById(R.id.buttonsubmit);
        back_btn_genInfo = findViewById(R.id.back_arrow_genInfo);
        ImageView back_btn_review= findViewById(R.id.back_arrow_review);
        Firstname = findViewById(R.id.firstname_jr);
        Lastname = findViewById(R.id.lastName_jr);
        Email = findViewById(R.id.email_jr);
        Phone = findViewById(R.id.phone_jr);
         City = findViewById(R.id.city_jr);
         Address = findViewById(R.id.address_jr);
         Country = findViewById(R.id.country_jr);
         Zipcode = findViewById(R.id.zipcode_jr);

         company_id = getIntent().getExtras().getString("company_id");


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
                Bundle bundle = new Bundle();
                bundle.putString("first_name",Firstname.getText().toString().trim());
                bundle.putString("last_name", Lastname.getText().toString().trim());
                bundle.putString("email", Email.getText().toString().trim());
                bundle.putString("phone_number", Phone.getText().toString().trim());
                bundle.putString("address", Address.getText().toString().trim());
                bundle.putString("city", City.getText().toString().trim());
                bundle.putString("country", Country.getText().toString().trim());
                bundle.putString("zipcode", Zipcode.getText().toString().trim());
                bundle.putString("company_id", company_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}