package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ClientActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tab1, tab2, tab3, tab4, tab5, tab6, tab7;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Fragment fragment =null;
    FragmentTransaction fragmentTransaction;
    TextView companyName;
    TextView companyAddress;

    String specificClient = "https://demotic-recruit.000webhostapp.com/specific_client_fetch.php";


    String id, name,  phoneNumber, address,  email, createdDate, parentId, creatorId, activeContactId, sourceId, ownershipId, industryId, status, url, description, stateId, countryId, city, zipcode, bankName;
    private String bankId, bankAccountNumber, IBAN, VAT;
    String specName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_glance);
        specName = getIntent().getExtras().getString("name");


        fetchClient();

        companyName.setText(name);
        companyAddress.setText(address);

        tabLayout = findViewById(R.id.client_activity_tab);
        tab1 = findViewById(R.id.client_at_a_glance);
        tab2 = findViewById(R.id.client_hires);
        tab3 =findViewById(R.id.client_contacts);
        tab4 = findViewById(R.id.client_apps);
        tab5 = findViewById(R.id.client_act);
        tab6 =findViewById(R.id.client_files);
        tab7 = findViewById(R.id.client_notes);

        frameLayout = findViewById(R.id.tab_host_fragment);
        fragment = new ClientGlanceFragment();
        fragmentManager =getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.tab_host_fragment,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();


        companyName = findViewById(R.id.textView);
        companyAddress = findViewById(R.id.textView4);

        Button editClient = findViewById(R.id.button);
        editClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Add_client_activity.class);
                Bundle send = new Bundle();
                send.putString("id", id);
                send.putString("parent_id", parentId);
                send.putString("creator_id", creatorId);
                send.putString("active_contact_id", activeContactId);
                send.putString("source_id", sourceId);
                send.putString("ownership_id", ownershipId);
                send.putString("industry_id", industryId);
                send.putString("status", status);
                send.putString("phone_number", phoneNumber);
                send.putString("created_date", createdDate);
                send.putString("url", url);
                send.putString("description", description);
                send.putString("state_id", stateId);
                send.putString("country_id", countryId);
                send.putString("city", city);
                send.putString("zipcode", zipcode);
                send.putString("bank_name", bankName);
                send.putString("bank_id", bankId);
                send.putString("bank_account_number", bankAccountNumber);
                send.putString("IBAN", IBAN);
                send.putString("VAT", VAT);
                send.putString("name", name);
                send.putString("email", email);
                send.putString("address", address);
                intent.putExtras(send);
                startActivity(intent);
            }
        });




        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ClientGlanceFragment();
                        break;
                    case 1:
                        fragment = new ClientHiresFragment();
                        break;
                    case 2:
                        fragment = new ClientContactsFragment();
                        break;
                    case 3:
                        fragment = new ClientAppointmentFragment();
                        break;
                    case 4:
                        fragment = new ClientActivityFragment();
                        break;
                    case 5:
                        fragment = new ClientFilesFragment();
                        break;
                    case 6:
                        fragment = new ClientNotesFragment();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.tab_host_fragment, fragment);
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

    private void fetchClient() {
        StringRequest creatorRequest = new StringRequest(Request.Method.POST, specificClient , new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);
                    int j= contactArray.length();
                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        id = countryObject.getString("id");
                        parentId = countryObject.getString("parent_id");
                        creatorId = countryObject.getString("creator_id");
                        activeContactId = countryObject.getString("active_contact_id");
                        sourceId = countryObject.getString("source_id");
                        ownershipId = countryObject.getString("ownership_id");
                        industryId = countryObject.getString("industry_id");
                        status = countryObject.getString("status");
                        phoneNumber = countryObject.getString("phone_number");
                        createdDate = countryObject.getString("created_date");
                        url = countryObject.getString("url");
                        description = countryObject.getString("description");
                        stateId = countryObject.getString("state_id");
                        countryId = countryObject.getString("country_id");
                        city = countryObject.getString("city");
                        zipcode = countryObject.getString("zipcode");
                        bankName = countryObject.getString("bank_name");
                        bankId = countryObject.getString("bank_id");
                        bankAccountNumber = countryObject.getString("bank_account_number");
                        IBAN = countryObject.getString("IBAN");
                        VAT = countryObject.getString("VAT");
                        name = countryObject.getString("name");
                        email = countryObject.getString("email");
                        address=countryObject.getString("address");


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
                param.put("name",specName);
                return  param;
            }
        };
        Volley.newRequestQueue(this).add(creatorRequest);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ClientActivity.this,
               MyClients.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
