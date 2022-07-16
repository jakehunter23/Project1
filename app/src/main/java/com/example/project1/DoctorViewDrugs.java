package com.example.project1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorViewDrugs extends AppCompatActivity {
    String fetchDrugList = "https://demotic-recruit.000webhostapp.com/druglist_fetch.php";
    String id, drug_name, manufacturer, composition, extra;
    Context context;
    List<DrugModel> drugsmodel;
    RecyclerView MyCLiRec;
    DrugsRecAdapter drugsRecAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_list);
        MyCLiRec = findViewById(R.id.recyclerView);
        MyCLiRec.setLayoutManager(new LinearLayoutManager(this));
        SetList();


    }

    private void SetList() {
        StringRequest clientRequest = new StringRequest(Request.Method.GET, fetchDrugList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for (int i = 0; i < contactArray.length(); i++) {
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String id = countryObject.getString("id");
                        String drug_name = countryObject.getString("drug_name");
                        String manufacturer = countryObject.getString("manufacturer");
                        String composition = countryObject.getString("composition");
                        String extra = countryObject.getString("extra");
                        int idd = Integer.parseInt(id);
                        DrugModel drugs = new DrugModel();
                        drugs.setDrug_name(drug_name);
                        drugs.setManufacturer(manufacturer);
                        drugs.setComposition(composition);
                        drugs.setExtra(extra);
                        drugs.setId(idd);



                        drugsmodel.add(drugs);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                drugsRecAdapter = new DrugsRecAdapter(DoctorViewDrugs.this,drugsmodel);
                MyCLiRec.setAdapter(drugsRecAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(clientRequest);
    }

}
