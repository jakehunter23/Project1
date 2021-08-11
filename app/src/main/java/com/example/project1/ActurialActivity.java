package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActurialActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tab1, tab2, tab3, tab4, tab5, tab6,tab7;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Fragment fragment =null;
    FragmentTransaction fragmentTransaction;

    String specCid, specOpen, specDate;
    public static String id;
    String position_id, industry_id, job_type, company_id, contact_id, status, priority, reference_number;
    String designation, recruiter_id, openings, start_date, end_date, country_id, state_id, city_id, skills;
    String qualifications,eligibility_criteria, experience_requirement, relevant_experience, irrelevant_experience;
    String roles_responsibilities, growth_opportunities, learning_opportunities, employee_endorsements, employee_benefits;
    String company_reputation, package_currency, package_type, packages, bill_rate, markup_percentage;
    String client_margin, days_on, days_off, shift_pattern, created_date;
    String fetchSpecJob = "https://demotic-recruit.000webhostapp.com/specific_job_fetch.php";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acturial_glance);

        specCid = getIntent().getExtras().getString("company_id");
        specOpen = getIntent().getExtras().getString("openings");
        specDate = getIntent().getExtras().getString("created_date");

        fetchJob();

        tabLayout=findViewById(R.id.act_glance_tabView);
        tab1 = findViewById(R.id.act_glance);
        tab2 = findViewById(R.id.act_appt);
        tab3 =findViewById(R.id.act2);
        tab4 = findViewById(R.id.act_file);
        tab5 = findViewById(R.id.act_note);
        tab6 = findViewById(R.id.act_publish);
        tab7=findViewById(R.id.act_cmt_tab);
        frameLayout = findViewById(R.id.act_host_fragment);
        fragment = new ActurialGlanceFragment();
        fragmentManager =getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.act_host_fragment,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ActurialGlanceFragment();
                        break;
                    case 1:
                        fragment = new ActurialApplicantsFragment();
                        break;
                    case 2:
                        fragment = new ActurialActivityFragment();
                        break;
                    case 3:
                        fragment = new ActurialFilesFragment();
                        break;
                    case 4:
                        fragment = new ActurialNotesFragment();
                        break;
                    case 5:
                        fragment = new ActurialPublishFragment();
                        break;
                    case 6:
                        fragment= new Acturial_Comment_Fragment();

                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.act_host_fragment, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void fetchJob() {
        StringRequest creatorRequest = new StringRequest(Request.Method.POST, fetchSpecJob , new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);
                    int j= contactArray.length();
                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        id = countryObject.getString("id");
                        position_id = countryObject.getString("position_id");
                        industry_id = countryObject.getString("industry_id");
                        job_type = countryObject.getString("job_type");
                        company_id = countryObject.getString("company_id");
                        contact_id = countryObject.getString("contact_id");
                        priority = countryObject.getString("priority");
                        status = countryObject.getString("status");
                        reference_number = countryObject.getString("reference_number");
                        designation = countryObject.getString("designation");
                        recruiter_id = countryObject.getString("recruiter_id");
                        openings = countryObject.getString("openings");
                        start_date = countryObject.getString("start_date");
                        end_date = countryObject.getString("end_date");
                        country_id = countryObject.getString("country_id");
                        state_id = countryObject.getString("state_id");
                        city_id = countryObject.getString("city_id");
                        skills = countryObject.getString("skills");
                        qualifications = countryObject.getString("qualifications");
                        eligibility_criteria = countryObject.getString("eligibility_criteria");
                        experience_requirement = countryObject.getString("experience_requirement");
                        relevant_experience = countryObject.getString("relevant_experience");
                        irrelevant_experience = countryObject.getString("irrelevant_experience");
                        roles_responsibilities=countryObject.getString("roles_responsibilities");
                        growth_opportunities = countryObject.getString("growth_opportunities");
                        learning_opportunities = countryObject.getString("learning_opportunities");
                        employee_endorsements = countryObject.getString("employee_endorsements");
                        employee_benefits = countryObject.getString("employee_benefits");
                        company_reputation = countryObject.getString("company_reputation");
                        package_currency = countryObject.getString("package_currency");
                        package_type = countryObject.getString("package_type");
                        packages = countryObject.getString("package");
                        bill_rate = countryObject.getString("bill_rate");
                        markup_percentage = countryObject.getString("markup_percentage");
                        client_margin = countryObject.getString("client_margin");
                        days_on = countryObject.getString("days_on");
                        days_off = countryObject.getString("days_off");
                        shift_pattern = countryObject.getString("shift_pattern");
                        created_date = countryObject.getString("created_date");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("company_id",specCid);
                param.put("openings", specOpen);
                param.put("created_date", specDate);
                return  param;
            }
        };
        Volley.newRequestQueue(this).add(creatorRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActurialActivity.this,
                JobListings.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
