package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JSPassword extends AppCompatActivity {
EditText et_pass,et_confirm_pass;
Button btn_send;
ImageView pass_arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jspassword);

        et_pass = findViewById(R.id.pass);
        et_confirm_pass = findViewById(R.id.confirm_pass);
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
                     validate();

            }
        }); }
  //function for password validation
    public boolean validate()
    {
    String pass= et_pass.getText().toString().trim();
    String confirm_pass = et_confirm_pass.getText().toString().trim();
    boolean temp=true;

        if(!pass.equals(confirm_pass)){
            Toast.makeText(JSPassword.this,"Password Not matching",Toast.LENGTH_SHORT).show();
            temp=false;
        }
        else if (pass.isEmpty())
        {
            Toast.makeText(JSPassword.this,"Enter a password",Toast.LENGTH_SHORT).show();
            temp=false;
        }
        else
        {
            temp=false;
            et_pass.setText("");
            et_confirm_pass.setText("");
            Intent intent = new Intent(getApplicationContext(),JSDetails.class);
            startActivity(intent);
        }
        return temp;
    }
}