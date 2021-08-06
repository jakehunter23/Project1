package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class CandidateActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tab1, tab2, tab3, tab4, tab5;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Fragment fragment =null;
    FragmentTransaction fragmentTransaction;
    RelativeLayout contact,info;
    ImageView arrow;
    TextView moreOrLess;
    String specificCandidate = "https://demotic-recruit.000webhostapp.com/specific_candidate_fetch.php";
    String specFirst, specLast, specEmail, specDate;
    String id, firstName, lastName, status, statusId, email, phoneNumber, address, city, zipcode, type, preference, sourceId, ownerId, currentSalary, desiredSalary, stateId, countryId, title, companyName, hourlyRateLow, hourlyRateHigh, talent, skill, degree, comments, availabilityDate, job, accessibility, createdDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidate_glance);
        contact=findViewById(R.id.contactGlance);
        contact.setVisibility(View.GONE);
        info=findViewById(R.id.item_description_layout);
        arrow=findViewById(R.id.item_description_img);
        moreOrLess=findViewById(R.id.item_description_title);

        specFirst = getIntent().getExtras().getString("first_name");
        specLast = getIntent().getExtras().getString("last_name");
        specEmail = getIntent().getExtras().getString("email");
        specDate = getIntent().getExtras().getString("created_date");

        specCand();

        tabLayout=findViewById(R.id.cand_glance_tabView);
        tab1 = findViewById(R.id.cand_glance);
        tab2 = findViewById(R.id.cand_status);
        tab3 =findViewById(R.id.cand_act);
        tab4 = findViewById(R.id.cand_files);
        tab5 = findViewById(R.id.cand_notes);
        frameLayout = findViewById(R.id.cand_fragment_host);
        fragment = new CandidateGlanceFragment();
        fragmentManager =getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.cand_fragment_host,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();



        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contact.getVisibility() == View.VISIBLE) {


                    TransitionManager.beginDelayedTransition(contact,
                            new AutoTransition());
                  contact.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.expand_more);
                    moreOrLess.setText("More information");

                }  else {
                    TransitionManager.beginDelayedTransition(contact,
                            new AutoTransition());
                    contact.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.expand_less);
                    moreOrLess.setText("Less information");
                }


            }

        });

        Button editCand = findViewById(R.id.cand_edit_button);
        editCand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddCandidateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("first_name", firstName);
                bundle.putString("last_name", lastName);
                bundle.putString("status_id", statusId);
                bundle.putString("status", status);
                bundle.putString("email", email);
                bundle.putString("contact_number", phoneNumber);
                bundle.putString("address", address);
                bundle.putString("city", city);
                bundle.putString("zipcode", zipcode);
                bundle.putString("state_id", stateId);
                bundle.putString("country_id", countryId);
                bundle.putString("current_title", title);
                bundle.putString("company_name", companyName);
                bundle.putString("type", type);
                bundle.putString("preference", preference);
                bundle.putString("owner_id", ownerId);
                bundle.putString("source_id", sourceId);
                bundle.putString("current_salary", currentSalary);
                bundle.putString("desired_salary", desiredSalary);
                bundle.putString("hourly_rate_high", hourlyRateHigh);
                bundle.putString("hourly_rate_low", hourlyRateLow);
                bundle.putString("talent", talent);
                bundle.putString("skill", skill);
                bundle.putString("degree", degree);
                bundle.putString("comments", comments);
                bundle.putString("availability_date", availabilityDate);
                bundle.putString("job", job);
                bundle.putString("accessibility", accessibility);
                bundle.putString("created_date", createdDate);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new CandidateGlanceFragment();
                        break;
                    case 1:
                        fragment = new CandidateStatusFragment();
                        break;
                    case 2:
                        fragment = new CandidateActivityFragment();
                        break;
                    case 3:
                        fragment = new CandidateFilesFragment();
                        break;
                    case 4:
                        fragment = new CandidateNotesFragment();
                        break;

                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.cand_fragment_host, fragment);
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

    private void specCand() {
        StringRequest creatorRequest = new StringRequest(Request.Method.POST, specificCandidate , new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);
                    int j= contactArray.length();
                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        id = countryObject.getString("id");
                        firstName = countryObject.getString("first_name");
                        lastName = countryObject.getString("last_name");
                        statusId = countryObject.getString("status_id");
                        status = countryObject.getString("status");
                        email = countryObject.getString("email");
                        phoneNumber = countryObject.getString("contact_number");
                        address = countryObject.getString("address");
                        city = countryObject.getString("city");
                        zipcode = countryObject.getString("zipcode");
                        stateId = countryObject.getString("state_id");
                        countryId = countryObject.getString("country_id");
                        title = countryObject.getString("title");
                        companyName = countryObject.getString("company_name");
                        type = countryObject.getString("type");
                        preference = countryObject.getString("preference");
                        ownerId = countryObject.getString("owner_id");
                        sourceId = countryObject.getString("source_id");
                        currentSalary = countryObject.getString("current_salary");
                        desiredSalary = countryObject.getString("desired_salary");
                        hourlyRateHigh = countryObject.getString("hourly_rate_high");
                        hourlyRateLow = countryObject.getString("hourly_rate_low");
                        talent = countryObject.getString("talent");
                        skill = countryObject.getString("skill");
                        degree = countryObject.getString("degree");
                        comments = countryObject.getString("comments");
                        availabilityDate = countryObject.getString("availability_date");
                        job = countryObject.getString("job");
                        accessibility = countryObject.getString("accessibility");
                        createdDate = countryObject.getString("created_date");



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
                param.put("first_name",specFirst);
                param.put("last_name",specLast);
                param.put("email",specEmail);
                param.put("created_date",specDate);
                return  param;
            }
        };
        Volley.newRequestQueue(this).add(creatorRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CandidateActivity.this,
                MyDatabase.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
