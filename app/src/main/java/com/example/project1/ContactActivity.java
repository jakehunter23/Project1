package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ContactActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tab1, tab2, tab3, tab4, tab5, tab6;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Fragment fragment =null;
    FragmentTransaction fragmentTransaction;
    String firstName, lastName, middleName, phone, specEmail, SpecAddress, specDate;
    String specificContact = "https://demotic-recruit.000webhostapp.com/specific_contact_fetch.php";
    String id, first_name, last_name, middle_name, status, email, contact_number, address, city, zipcode, stateId, countryId;
    String title, companyName, contactTypeId, division, sourceId, reportToId, industryId, lastContactDate, lastVisitDate, visibility, validity, created_date, image_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_glance);

        firstName = getIntent().getExtras().getString("first_name");
        lastName = getIntent().getExtras().getString("last_name");
        middleName = getIntent().getExtras().getString("middle_name");
        phone = getIntent().getExtras().getString("contact_number");
        specEmail = getIntent().getExtras().getString("email");
        SpecAddress = getIntent().getExtras().getString("address");
        specDate = getIntent().getExtras().getString("last_contact_date");

        SpecContact();


        tabLayout=findViewById(R.id.contact_glance_tablay);
        tab1 = findViewById(R.id.con_glance);
        tab2 = findViewById(R.id.con_app);
        tab3 =findViewById(R.id.con_act);
        tab4 = findViewById(R.id.con_job);
        tab5 = findViewById(R.id.con_files_tab);
        tab6 = findViewById(R.id.con_note);
        frameLayout = findViewById(R.id.con_main_frag);
        fragment = new ContactGlanceFragment();
        fragmentManager =getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.con_main_frag,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        Button editContact = findViewById(R.id.cand_edit_button);
        editContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddContactActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("first_name", first_name);
                bundle.putString("last_name", last_name);
                bundle.putString("middle_name", middle_name);
                bundle.putString("status", status);
                bundle.putString("email", email);
                bundle.putString("contact_number", contact_number);
                bundle.putString("address", address);
                bundle.putString("city", city);
                bundle.putString("zipcode", zipcode);
                bundle.putString("state_id", stateId);
                bundle.putString("country_id", countryId);
                bundle.putString("current_title", title);
                bundle.putString("company_name", companyName);
                bundle.putString("contact_type_id", contactTypeId);
                bundle.putString("division", division);
                bundle.putString("source_id", sourceId);
                bundle.putString("report_to_id", reportToId);
                bundle.putString("industry_id", industryId);
                bundle.putString("last_contact_date", lastContactDate);
                bundle.putString("last_visit_date", lastVisitDate);
                bundle.putString("visibility", visibility);
                bundle.putString("validity", validity);
                bundle.putString("created_date", created_date);
                bundle.putString("image_data", image_data);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ContactGlanceFragment();
                        break;
                    case 1:
                        fragment = new ContactAppointment();
                        break;
                    case 2:
                        fragment = new ContactActivityFragment();
                        break;
                    case 3:
                        fragment = new ContactJobsFragment();
                        break;
                    case 4:
                        fragment = new ContactFilesFragment();
                        break;
                    case 5:
                        fragment = new ContactNotesFragment();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.con_main_frag, fragment);
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

    private void SpecContact() {
        StringRequest creatorRequest = new StringRequest(Request.Method.POST, specificContact , new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);
                    int j= contactArray.length();
                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        id = countryObject.getString("id");
                        first_name = countryObject.getString("first_name");
                        last_name = countryObject.getString("last_name");
                        middle_name = countryObject.getString("middle_name");
                        status = countryObject.getString("status");
                        email = countryObject.getString("email");
                        contact_number = countryObject.getString("contact_number");
                        address = countryObject.getString("address");
                        city = countryObject.getString("city");
                        zipcode = countryObject.getString("zipcode");
                        stateId = countryObject.getString("state_id");
                        countryId = countryObject.getString("country_id");
                        title = countryObject.getString("current_title");
                        companyName = countryObject.getString("company_name");
                        contactTypeId = countryObject.getString("contact_type_id");
                        division = countryObject.getString("division");
                        sourceId = countryObject.getString("source_id");
                        reportToId = countryObject.getString("report_to_id");
                        industryId = countryObject.getString("industry_id");
                        lastContactDate = countryObject.getString("last_contact_date");
                        lastVisitDate = countryObject.getString("last_visit_date");
                        visibility = countryObject.getString("visibility");
                        validity = countryObject.getString("validity");
                        created_date=countryObject.getString("created_date");
                        image_data=countryObject.getString("image_data");



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
                param.put("first_name",firstName);
                param.put("last_name",lastName);
                param.put("middle_name",middleName);
                param.put("email",specEmail);
                param.put("address",SpecAddress);
                param.put("last_contact_date",specDate);
                param.put("contact_number",phone);
                return  param;
            }
        };
        Volley.newRequestQueue(this).add(creatorRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ContactActivity.this,
                MyContact.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
