package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class dashboard extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<SpecificJob>jobList;
    List<JSJobModel> jobModels;
    SpecificJobAdoptor adaptor;
    private RecyclerView suggestRecyclerViews;
    private RecyclerView specific_recycle;
    private TextView logout;
    String getJob = "https://demotic-recruit.000webhostapp.com/job_fetch.php";
    JobsuggestRecycleAdoptar jobRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ImageView navbutton= (ImageView)findViewById(R.id.navbotton);
        TextView showall= (TextView)findViewById(R.id.showall);
        jobModels = new ArrayList<>();
        suggestRecyclerViews=findViewById(R.id.suggestrecycle);

        fetchJob();
        suggestRecyclerViews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));


        specific_recycle=findViewById(R.id.recyclejob);
        specific_recycle.setLayoutManager(new LinearLayoutManager(this));
        specific_recycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        List<SpecificJob> job_name= new ArrayList<SpecificJob>();
        job_name.add(new SpecificJob(R.drawable.data_management,"48 Active Recruiters","Data Analyst","Hydrabad"));
        job_name.add(new SpecificJob((R.drawable.data_management),"52 Active Recruiters","Python Developer","Mumbai"));
        specific_recycle.setAdapter(new SpecificJobAdoptor(job_name));



        AppCompatButton buttonschedule=(AppCompatButton)findViewById(R.id.buttonschedule);
        navbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent navid = new Intent(dashboard.this,navigation.class);
                startActivity(navid);


            }

        });
        showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent suggestjob = new Intent(dashboard.this,jobSuggestion.class);
                startActivity(suggestjob);



            }
        });
        buttonschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent schedule = new Intent(dashboard.this,mySchedule.class);
                startActivity(schedule);



            }
        });
        iniData();
        initRecylerView();

        //setting listener for logout button
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating shared preferences object
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor =preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                Toast.makeText(getApplicationContext(), "Logout Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),JobLoginActivity.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

    }

    private void fetchJob() {
        StringRequest creatorRequest = new StringRequest(Request.Method.GET, getJob , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);
                        String id = countryObject.getString("id");
                        String positionId = countryObject.getString("position_id");
                        String industryId = countryObject.getString("industry_id");
                        String jobType = countryObject.getString("job_type");
                        String companyId = countryObject.getString("company_id");
                        String companyName = countryObject.getString("company_name");
                        String contactId = countryObject.getString("contact_id");
                        String status = countryObject.getString("status");
                        String priority = countryObject.getString("priority");
                        String referenceNumber = countryObject.getString("reference_number");
                        String designation = countryObject.getString("designation");
                        String recruiterId = countryObject.getString("recruiter_id");
                        String openings = countryObject.getString("openings");
                        String startDate = countryObject.getString("start_date");
                        String endDate = countryObject.getString("end_date");
                        String countryId = countryObject.getString("country_id");
                        String stateId = countryObject.getString("state_id");
                        String cityId = countryObject.getString("city_id");
                        String skill = countryObject.getString("skills");
                        String qualification = countryObject.getString("qualifications");
                        String eligibilityCriteria = countryObject.getString("eligibility_criteria");
                        String experienceRequirement = countryObject.getString("experience_requirement");
                        String relevantExperience = countryObject.getString("relevant_experience");
                        String irrelevantExperience = countryObject.getString("irrelevant_experience");
                        String rolesResponsibilities = countryObject.getString("roles_responsibilities");
                        String growthOppurtunities = countryObject.getString("growth_opportunities");
                        String learningOpportunities = countryObject.getString("learning_opportunities");
                        String employeeEndorsements = countryObject.getString("employee_endorsements");
                        String employeeBenefits = countryObject.getString("employee_benefits");
                        String companyReputation = countryObject.getString("company_reputation");
                        String packageCurrency = countryObject.getString("package_currency");
                        String packageType = countryObject.getString("package_type");
                        String packages = countryObject.getString("package");
                        String billRate = countryObject.getString("bill_rate");
                        String markupPercentage = countryObject.getString("markup_percentage");
                        String clientMargin = countryObject.getString("client_margin");
                        String daysOn = countryObject.getString("days_on");
                        String daysOff = countryObject.getString("days_off");
                        String shiftPattern = countryObject.getString("shift_pattern");
                        String createdDate = countryObject.getString("created_date");


                        JSJobModel creator = new JSJobModel();
                        creator.setId(id);
                        creator.setPosition_id(positionId);
                        creator.setIndustry_id(industryId);
                        creator.setJob_type(jobType);
                        creator.setCompany_id(companyId);
                        creator.setCompany_name(companyName);
                        creator.setContact_id(contactId);
                        creator.setStatus(status);
                        creator.setPriority(priority);
                        creator.setReference_number(referenceNumber);
                        creator.setDesignation(designation);
                        creator.setRecruiter_id(recruiterId);
                        creator.setOpenings(openings);
                        creator.setStart_date(startDate);
                        creator.setEnd_date(endDate);
                        creator.setCountry_id(countryId);
                        creator.setState_id(stateId);
                        creator.setCity_id(cityId);
                        creator.setSkills(skill);
                        creator.setQualifications(qualification);
                        creator.setEligibility_criteria(eligibilityCriteria);
                        creator.setExperience_requirement(experienceRequirement);
                        creator.setRelevant_experience(relevantExperience);
                        creator.setIrrelevant_experience(irrelevantExperience);
                        creator.setRoles_reponsibilities(rolesResponsibilities);
                        creator.setGrowth_opportunities(growthOppurtunities);
                        creator.setLearning_opportunities(learningOpportunities);
                        creator.setEmployee_endorsements(employeeEndorsements);
                        creator.setEmployee_benefits(employeeBenefits);
                        creator.setCompany_reputation(companyReputation);
                        creator.setPackage_currency(packageCurrency);
                        creator.setPackage_type(packageType);
                        creator.setPackages(packages);
                        creator.setBill_rate(billRate);
                        creator.setMarkup_percentage(markupPercentage);
                        creator.setClient_margin(clientMargin);
                        creator.setDays_on(daysOn);
                        creator.setDays_off(daysOff);
                        creator.setShift_pattern(shiftPattern);
                        creator.setCreated_date(createdDate);

                        jobModels.add(creator);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jobRecyclerAdapter = new JobsuggestRecycleAdoptar(jobModels, dashboard.this);
                suggestRecyclerViews.setAdapter(jobRecyclerAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(creatorRequest);
    }

    private void iniData() {
        jobList = new ArrayList<>();
        jobList.add(new SpecificJob(R.drawable.data_management,"48 Active Recruiters","Data Analysis","Hyderabad"));
        jobList.add(new SpecificJob(R.drawable.coder,"32 Active Recruiters","Python Developer","Hyderabad"));
        jobList.add(new SpecificJob(R.drawable.data_management,"48 Active Recruiters","Data Analysis","Hyderabad"));
    }

    private void initRecylerView() {
        recyclerView=findViewById(R.id.recyclejob);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adaptor=new SpecificJobAdoptor(jobList);
        adaptor.notifyDataSetChanged();
    }
}