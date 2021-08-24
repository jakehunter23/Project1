package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class JSCareerInfo extends AppCompatActivity {
Button career_save;
ImageView career_arrow;
String firstname, lastname, email, contact, address, city, zip,state, country,id;
String insertJS = "https://demotic-recruit.000webhostapp.com/jobseeker_detail_insert.php";
String token="";
private FirebaseAuth mAuth;
FirebaseUser firebaseUser;

EditText Qualification;
EditText Yop;
EditText Experience;
EditText Skill;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jscareer_info);

        career_arrow = findViewById(R.id.career_arrow);
        career_save=findViewById(R.id.career_save);
         Qualification = findViewById(R.id.js_qualification);
         Yop = findViewById(R.id.js_yop);
         Experience = findViewById(R.id.js_experience);
         Skill = findViewById(R.id.js_skills);
        mAuth= FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                token = Objects.requireNonNull(task.getResult());
                Constants.JS_TOKEN=token;

                Log.d("TOKEN", "TOKEN: "+token);
            }
        });

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




        //listener for back arrow
        career_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent (getApplicationContext(),JSPersonalInfo.class);
              //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
              //  startActivity(intent);
                finish();
            }
        });

        //listener for save button
        career_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertJSInfo();
            }
        });
    }

    private void insertJSInfo() {
        StringRequest insertRequest = new StringRequest(Request.Method.POST, insertJS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(getApplicationContext(), JSDetails.class);
                Bundle bundle = new Bundle();
                bundle.putString("first_name", firstname);
                bundle.putString("last_name", lastname);
                bundle.putString("email", email);
                bundle.putString("contact", contact);
                bundle.putString("address", address);
                bundle.putString("city", city);
                bundle.putString("zipcode", zip);
                bundle.putString("state", state);
                bundle.putString("country", country);
                bundle.putString("signup_id", id);
                bundle.putString("token", token);
                bundle.putString("qualification", Qualification.getText().toString().trim());
                bundle.putString("year_passing", Yop.getText().toString().trim());
                bundle.putString("experience", Experience.getText().toString().trim());
                bundle.putString("skill", Skill.getText().toString().trim());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> param = new HashMap<String, String>();
                param.put("first_name", firstname);
                param.put("last_name", lastname);
                param.put("email", email);
                param.put("contact", contact);
                param.put("address", address);
                param.put("city", city);
                param.put("zipcode", zip);
                param.put("state", state);
                param.put("country", country);
                param.put("signup_id", id);
                param.put("token",token);
                param.put("qualification",Qualification.getText().toString().trim());
                param.put("yop",Yop.getText().toString().trim());
                param.put("experience", Experience.getText().toString().trim());
                param.put("skill", Skill.getText().toString().trim());


                return param;
            }
        };


        Volley.newRequestQueue(getBaseContext()).add(insertRequest);
    }


}