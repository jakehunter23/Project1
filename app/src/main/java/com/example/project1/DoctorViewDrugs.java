package com.example.project1;

import android.content.Context;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class DoctorViewDrugs extends AppCompatActivity {
    String fetchDrugList = "https://demotic-recruit.000webhostapp.com/druglist_fetch.php";
    String  drug_name, manufacturer, composition, extra;
    int id;
    Context context;
    List<DrugModel> drugsmodel = new ArrayList<>();
    RecyclerView MyCLiRec;
    DrugsRecAdapter drugsRecAdapter;
    SearchView searchView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_list);
        MyCLiRec = findViewById(R.id.recyclerView9);
        MyCLiRec.setLayoutManager(new LinearLayoutManager(this));
        SetList();
        searchView=findViewById(R.id.search_view);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return true;
            }
        });


    }

    private void filterList(String s) {
        List<DrugModel> filteredList = new ArrayList<>();
        for(DrugModel item : drugsmodel){
            if(item.getDrug_name().toLowerCase().contains(s.toLowerCase())){
                filteredList.add(item);
            }

            if(filteredList.isEmpty()){
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
            }
            else{
                    drugsRecAdapter.setFilteredList(filteredList);
            }
        }
    }


    private void SetList() {
        StringRequest clientRequest = new StringRequest(Request.Method.GET, fetchDrugList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for (int i = 0; i < contactArray.length(); i++) {
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        id = countryObject.getInt("id");
                        drug_name = countryObject.getString("drug_name");
                        manufacturer = countryObject.getString("manufacturer");
                        composition = countryObject.getString("composition");
                        extra = countryObject.getString("extra");
                        DrugModel drugs = new DrugModel();
                        drugs.setDrug_name(drug_name);
                        drugs.setManufacturer(manufacturer);
                        drugs.setComposition(composition);
                        drugs.setExtra(extra);
                        drugs.setId(id);



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
