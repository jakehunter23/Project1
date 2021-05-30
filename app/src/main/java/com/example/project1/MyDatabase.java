package com.example.project1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyDatabase extends AppCompatActivity {

    RecyclerView DataRec;
    ImageView menu;
    Button add;
    MyDataRecAdapter myDataRecAdapter;
    List<CandidateModel> candidateModels;

    String fetchCandidate = "https://demotic-recruit.000webhostapp.com/candidate_fetch.php";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_database);

        DataRec = findViewById(R.id.my_database_recycler);
        DataRec.setLayoutManager(new LinearLayoutManager(this));
        candidateModels = new ArrayList<>();

        loadCandidate();


        menu=findViewById(R.id.menu_icon_database);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NavActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i,2);
            }
        });

        add = findViewById(R.id.candidate_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyDatabase.this,AddCandidateActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadCandidate() {
        StringRequest candidateRequest = new StringRequest(Request.Method.GET, fetchCandidate , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("first_name");
                        String lastName = countryObject.getString("last_name");
                        String email= countryObject.getString("email");
                        String company_name = countryObject.getString("company_name");
                        String title = countryObject.getString("title");
                        String status = countryObject.getString("status");
                        String createdDate = countryObject.getString("created_date");
                        String degree = countryObject.getString("degree");

                        CandidateModel candidate = new CandidateModel();
                        candidate.setFirst_name(countryName);
                        candidate.setLast_name(lastName);
                        candidate.setEmail(email);
                        candidate.setCompany_name(company_name);
                        candidate.setTitle(title);
                        candidate.setStatus(status);
                        candidate.setCreated_date(createdDate);
                        candidate.setDegree(degree);

                        candidateModels.add(candidate);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                myDataRecAdapter = new MyDataRecAdapter(MyDatabase.this,candidateModels);
                DataRec.setAdapter(myDataRecAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(candidateRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyDatabase.this,
                DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
