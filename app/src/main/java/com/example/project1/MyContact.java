package com.example.project1;

import android.content.Intent;
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

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyContact extends AppCompatActivity {

    RecyclerView conRec;
    ImageView menu;
    Button add;
    List<ContactModel> contactModelList;
    List<ClientModel> clientModelList;
    MyContactRecAdapter myContactRecAdapter;

    String fetchContact = "https://demotic-recruit.000webhostapp.com/contact_fetch.php";
    String fetchClient = "https://demotic-recruit.000webhostapp.com/client_fetch.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_contacts);

        conRec = findViewById(R.id.my_contact_recycler);
        conRec.setLayoutManager(new LinearLayoutManager(this));

        contactModelList = new ArrayList<>();
        clientModelList = new ArrayList<>();

        loadContact();
        loadClient();

        menu=findViewById(R.id.menu_icon_myContact);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NavActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i,2);
            }
        });

        add =findViewById(R.id.contact_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyContact.this,AddContactActivity.class);
                startActivity(intent);
            }
        });
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
                        ClientModel client = new ClientModel();
                        client.setName(countryName);
                        client.setPhone_number(phone);
                        client.setEmail(email);
                        client.setAddress(address);
                        client.setDateCreated(date);

                        clientModelList.add(client);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                myContactRecAdapter =  new MyContactRecAdapter(MyContact.this, contactModelList, clientModelList);
                conRec.setAdapter(myContactRecAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(clientRequest);
    }

    private void loadContact() {
        StringRequest contactRequest = new StringRequest(Request.Method.GET, fetchContact, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String firstName = countryObject.getString("first_name");
                        String lastName = countryObject.getString("last_name");
                        String middleName = countryObject.getString("middle_name");
                        String phone = countryObject.getString("contact_number");
                        String email = countryObject.getString("email");
                        String address = countryObject.getString("address");
                        String date = countryObject.getString("last_contact_date");
                        String id = countryObject.getString("id");

                        ContactModel contactModel = new ContactModel();
                        contactModel.setFirstName(firstName);
                        contactModel.setLastName(lastName);
                        contactModel.setMiddleName(middleName);
                        contactModel.setContactNumber(phone);
                        contactModel.setEmail(email);
                        contactModel.setAddress(address);
                        contactModel.setLastContactDate(date);
                        contactModel.setId(id);

                        contactModelList.add(contactModel);
                        


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
        Volley.newRequestQueue(this).add(contactRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyContact.this,
                DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
