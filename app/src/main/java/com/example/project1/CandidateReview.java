package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class CandidateReview extends AppCompatActivity {
    ImageView back_btn_review;
    String insertJr = "https://demotic-recruit.000webhostapp.com/job_request_insert.php";
    Button btn_review_submit;
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);
    String firstname, lastname, email, phone, city, address, country, zipcode, candidateId, company_id, token, resume, certificate, attach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_review);

        firstname = getIntent().getExtras().getString("first_name");
        lastname = getIntent().getExtras().getString("last_name");
        email = getIntent().getExtras().getString("email");
        phone = getIntent().getExtras().getString("phone_number");
        address = getIntent().getExtras().getString("address");
        city = getIntent().getExtras().getString("city");
        country = getIntent().getExtras().getString("country");
        zipcode = getIntent().getExtras().getString("zipcode");
        company_id = getIntent().getExtras().getString("company_id");
        resume = getIntent().getExtras().getString("resume");
        certificate = getIntent().getExtras().getString("certificates");
        candidateId = Constants.JS_ID;
        token = Constants.JS_TOKEN;


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
                insert_jr();

            }
        });
    }

    private void insert_jr() {


        StringRequest insertRequest = new StringRequest(Request.Method.POST, insertJr, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(getApplicationContext(), CandidateComplete.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> param = new HashMap<String, String>();


                param.put("first_name", firstname);
                param.put("last_name", lastname);
                param.put("email", email);
                param.put("phone_number", phone);
                param.put("address", address);
                param.put("city", city);
                param.put("zipcode", zipcode);
                param.put("country", country);
                param.put("company_id", company_id);
                param.put("candidate_id", candidateId);
                param.put("created_date", strDate);
                param.put("token", token);
                param.put("resume", resume);
                param.put("certificates", certificate);



                return param;
            }
        };




        Volley.newRequestQueue(getBaseContext()).add(insertRequest);

    }
}