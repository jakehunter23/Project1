package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class MyClients extends AppCompatActivity {
    RecyclerView MyCLiRec;
    MyClientRecAdapter myClientRecAdapter;
    ImageView menu;
    Button add;
    List<ClientModel> clientModels;
    List<CreatorModel> creatorModels;

    String fetchClient = "https://demotic-recruit.000webhostapp.com/client_fetch.php";
    String fetchCreator = "https://demotic-recruit.000webhostapp.com/user_fetch.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_clients);
        MyCLiRec = findViewById(R.id.my_client_recycler);
        MyCLiRec.setLayoutManager(new LinearLayoutManager(this));
        clientModels = new ArrayList<>();
        creatorModels = new ArrayList<>();

        loadClient();
        loadCreator();






        menu =findViewById(R.id.menu_icon_myClient);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NavActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i,2);
            }
        });

        add = findViewById(R.id.client_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyClients.this,Add_client_activity.class);
                startActivity(intent);
            }
        });
    }

    private void loadCreator() {
        StringRequest creatorRequest = new StringRequest(Request.Method.GET, fetchCreator , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("first_name");
                        String lastName = countryObject.getString("last_name");

                        CreatorModel creator = new CreatorModel();
                        creator.setFirst_name(countryName);
                        creator.setLast_name(lastName);

                        creatorModels.add(creator);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                myClientRecAdapter = new MyClientRecAdapter(MyClients.this,clientModels,creatorModels);
                MyCLiRec.setAdapter(myClientRecAdapter);

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
                        String phone = countryObject.getString("phone_number");
                        String email = countryObject.getString("email");
                        String address = countryObject.getString("address");
                        String date = countryObject.getString("created_date");
                        String id = countryObject.getString("id");
                        ClientModel client = new ClientModel();
                        client.setName(countryName);
                        client.setPhone_number(phone);
                        client.setEmail(email);
                        client.setAddress(address);
                        client.setDateCreated(date);
                        client.setId(id);


                        clientModels.add(client);

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyClients.this,
                DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
