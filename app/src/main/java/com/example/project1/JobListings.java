package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

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

import static java.security.AccessController.getContext;

public class JobListings extends AppCompatActivity {

    private ImageView menu;

     RecyclerView jobRec;
    String [] name ={"Philip Martin","Philip Martin","Philip Martin","Philip Martin"};
    String [] company = {"XYZ Pharmaceuticals","XYZ Pharmaceuticals","XYZ Pharmaceuticals","XYZ Pharmaceuticals"};
    String fetchJob = "https://demotic-recruit.000webhostapp.com/job_fetch.php";
    String fetchClient = "https://demotic-recruit.000webhostapp.com/client_fetch.php";
    String fetchCreator = "https://demotic-recruit.000webhostapp.com/user_fetch.php";
    List<JobModel> jobModelList;
    List<ClientModel> clientModelList;
    List<CreatorModel> creatorModelList;
    JobListRecAdapter jobListRecAdapter;
    Spinner sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_listing);

        jobRec =findViewById(R.id.job_list_recycler);
        jobRec.setLayoutManager(new LinearLayoutManager(this));
        jobModelList = new ArrayList<>();
        clientModelList = new ArrayList<>();
        creatorModelList = new ArrayList<>();
        sp=findViewById(R.id.spinnerAll);

        ArrayList<String> sp_List=new ArrayList<>();
        sp_List.add("All");
        sp_List.add("Software Engineer");
        sp_List.add("Android Developer");
        sp_List.add("Web Developer");
        sp_List.add("Data Scientist");


        ArrayAdapter SPAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sp_List);
        SPAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(SPAdapter);

        loadJob();
        loadClient();
        loadCrestor();

        menu=findViewById(R.id.menu_icon_jobl);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NavActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i,2);
            }
        });


    }

    private void loadCrestor() {
        StringRequest creatorRequest = new StringRequest(Request.Method.GET, fetchCreator , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("first_name");
                        String lastName = countryObject.getString("last_name");
                        String email = countryObject.getString("email");

                        CreatorModel creator = new CreatorModel();
                        creator.setFirst_name(countryName);
                        creator.setLast_name(lastName);
                        creator.setEmail(email);

                        creatorModelList.add(creator);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jobListRecAdapter =  new JobListRecAdapter(JobListings.this, jobModelList, clientModelList, creatorModelList);
                jobRec.setAdapter(jobListRecAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(creatorRequest);
    }

    private void loadClient() {
        StringRequest clientRequest = new StringRequest(Request.Method.GET, fetchClient, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        String address = countryObject.getString("address");
                        ClientModel client = new ClientModel();
                        client.setName(countryName);
                        client.setAddress(address);

                        clientModelList.add(client);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(clientRequest);
    }

    private void loadJob() {
        StringRequest jobRequest = new StringRequest(Request.Method.GET, fetchJob, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        int openings = countryObject.getInt("openings");
                        String createdDate = countryObject.getString("created_date");
                        JobModel job = new JobModel();
                        job.setOpenings(openings);
                        job.setCreatedDate(createdDate);
                        jobModelList.add(job);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(jobRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
