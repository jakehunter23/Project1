package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JSPersonalInfo extends AppCompatActivity {
Button info_save;
ImageView info_arrow;
String username, mail,id;
String fetchjsId="https://demotic-recruit.000webhostapp.com/specific_js_signup_fetch.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jspersonal_info);

        username = getIntent().getExtras().getString("username");
        mail = getIntent().getExtras().getString("email");

        fetchspecjs();

        info_arrow = findViewById(R.id.info_arrow);
        info_save = findViewById(R.id.info_save);
        EditText FirstName = findViewById(R.id.js_fname);
        EditText LastName = findViewById(R.id.js_lname);
        EditText Email = findViewById(R.id.js_mail);
        EditText Contact = findViewById(R.id.js_contact);
        EditText Address = findViewById(R.id.js_address);
        EditText City = findViewById(R.id.js_city);
        EditText State = findViewById(R.id.js_state);
        EditText Zip = findViewById(R.id.js_zipcode);
        EditText Country = findViewById(R.id.js_country);

        //listener for save button
        info_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),JSCareerInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("first_name",FirstName.getText().toString().trim());
                bundle.putString("last_name",LastName.getText().toString().trim());
                bundle.putString("email",Email.getText().toString().trim());
                bundle.putString("contact",Contact.getText().toString().trim());
                bundle.putString("address",Address.getText().toString().trim());
                bundle.putString("city",City.getText().toString().trim());
                bundle.putString("zipcode",Zip.getText().toString().trim());
                bundle.putString("state",State.getText().toString().trim());
                bundle.putString("country",Country.getText().toString().trim());
                bundle.putString("mail",mail);
                bundle.putString("username",username);
                bundle.putString("signup_id",id);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        //listener for back arrow
        info_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    private void fetchspecjs() {

        StringRequest creatorRequest = new StringRequest(Request.Method.POST, fetchjsId , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                         id = countryObject.getString("id");

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
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String , String> param = new HashMap<>();
                param.put("username",username);
                param.put("email",mail);
                return param;
            }
        };
        Volley.newRequestQueue(this).add(creatorRequest);
    }

}