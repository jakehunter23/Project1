package com.example.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import androidx.appcompat.app.AppCompatActivity;

public class JobLoginActivity extends AppCompatActivity {
TextView tv_signup;
ImageView back_login;
Button btn_submit;
String login = "https://demotic-recruit.000webhostapp.com/js_login.php";
EditText Username, Email;
String fetchSpecToken = "https://demotic-recruit.000webhostapp.com/jobseeker_info_fetch.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_submit = findViewById(R.id.buttonsubmit);
        Username = findViewById(R.id.js_user);
        Email = findViewById(R.id.js_pass);
        //setting listener for submit button
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsLogin();
                btn_submit.setEnabled(false);

            }
        });

        back_login = findViewById(R.id.back_arrow_login);
        //setting listener for back button

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SplashScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });


        //setting colors
        tv_signup = findViewById(R.id.signup);
        String text = "<font color=#00000033> Don't have an account yet?</font><font color=#3CA6DA> Sign Up.</font>";
        tv_signup.setText(Html.fromHtml(text));

        //setting listener for signup
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),JSPassword.class);
                startActivity(i);
            }
        });

        CheckBox JS_rememberMe = findViewById(R.id.JS_remember_me);

        //creating shared preference object
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true"))
        {//calling login method
            Intent i = new Intent(getApplicationContext(),dashboard.class);
            startActivity(i);
        }else if (checkbox.equals("false"))
        {
            Toast.makeText(getApplicationContext(), "Please Login", Toast.LENGTH_SHORT).show();
        }

        //listener for remember me
        JS_rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    //creating shared preferences object
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor =preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();


                }else if(!buttonView.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor =preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();

                }
            }
        });

    }

    private void jsLogin() {
        String username = Username.getText().toString().trim();
        String pass = Email.getText().toString().trim();
        StringRequest insertRequest = new StringRequest(Request.Method.POST, login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray contactArray = null;
                try {
                    contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++) {
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        Constants.JS_USERNAME = countryObject.getString("username");
                        Constants.JS_PASS = countryObject.getString("password");
                        Constants.JS_ID = countryObject.getString("id");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                fetchToken();


                Intent intent = new Intent(getBaseContext(),dashboard.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> param = new HashMap<String, String>();
                param.put("username", username);
                param.put("password", pass);




                return param;
            }
        };




        Volley.newRequestQueue(getBaseContext()).add(insertRequest);
    }

    private void fetchToken() {
        StringRequest insertRequest = new StringRequest(Request.Method.POST, fetchSpecToken, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray contactArray = null;
                try {
                    contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++) {
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        Constants.JS_TOKEN = countryObject.getString("token");
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
                param.put("id", Constants.JS_ID);


                return param;
            }
        };




        Volley.newRequestQueue(getBaseContext()).add(insertRequest);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        btn_submit.setEnabled(true);
    }
}