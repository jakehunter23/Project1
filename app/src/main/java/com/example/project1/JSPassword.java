package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class JSPassword extends AppCompatActivity {
EditText et_pass,et_confirm_pass, Email, Username;
Button btn_send;
ImageView pass_arrow;
String signup = "https://demotic-recruit.000webhostapp.com/js_signup.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jspassword);

        et_pass = findViewById(R.id.pass);
        et_confirm_pass = findViewById(R.id.confirm_pass);
        Username = findViewById(R.id.username);
        Email = findViewById(R.id.email_signjs);
        btn_send = findViewById(R.id.pass_save);
        pass_arrow = findViewById(R.id.pass_arrow);


        //listener for back arrow
        pass_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent (getApplicationContext(),JSCareerInfo.class);
              //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
              //  startActivity(intent);
                finish();
            }
        });

        //setting listener for save button
        btn_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // validating password
                     jsSingup();

            }
        }); }
  //function for password validation

    private void jsSingup() {
        String pass= et_pass.getText().toString().trim();
        String confirm_pass = et_confirm_pass.getText().toString().trim();
        String username = Username.getText().toString().trim();
        String email = Email.getText().toString().trim();

        if(pass.isEmpty()){
            Toast.makeText(getBaseContext(),"Please Enter Company Name",Toast.LENGTH_LONG).show();
            et_pass.setError("Please Enter A Password");
            et_pass.requestFocus();
            return;
        }
        if(username.isEmpty()){
            Toast.makeText(getBaseContext(),"Please Enter Company URL",Toast.LENGTH_LONG).show();
            Username.setError("Please Enter Username");
            Username.requestFocus();
            return;
        }
        if(email.isEmpty()){
            Toast.makeText(getBaseContext(),"Please Enter Description",Toast.LENGTH_LONG).show();
            Email.setError("Please Enter Email");
            Email.requestFocus();
            return;
        }
        if(confirm_pass.isEmpty()){
            Toast.makeText(getBaseContext(),"Please Enter Description",Toast.LENGTH_LONG).show();
            et_confirm_pass.setError("Please Re-Enter The Password");
            et_confirm_pass.requestFocus();
            return;
        }

        if(!pass.equals(confirm_pass)){
            Toast.makeText(JSPassword.this,"Password Not matching",Toast.LENGTH_SHORT).show();
            et_confirm_pass.setError("Password did not match");
            et_confirm_pass.requestFocus();
            return;
        }


        StringRequest insertRequest = new StringRequest(Request.Method.POST, signup, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(getApplicationContext(),JSDetails.class);
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
                param.put("email", email);
                param.put("password", confirm_pass);


                return param;
            }
        };




        Volley.newRequestQueue(getBaseContext()).add(insertRequest);

    }
}