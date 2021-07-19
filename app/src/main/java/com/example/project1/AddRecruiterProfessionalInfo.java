package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecruiterProfessionalInfo extends AppCompatActivity {
    EditText Companyname, Designation, BusinessEmail;
    Button info_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recruiter_professional_info);
        Companyname=findViewById(R.id.company_name);
        Designation=findViewById(R.id.designation);
        BusinessEmail= findViewById(R.id.business_email);
        info_next=findViewById(R.id.career_save);
        info_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String companyname = Companyname.getText().toString().trim();
                String designation = Designation.getText().toString().trim();
                String businssEmail =BusinessEmail.getText().toString().trim();
                if(companyname.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Company Name",Toast.LENGTH_LONG).show();
                    Companyname.setError("Please Enter Company Name");
                    Companyname.requestFocus();
                    return;
                }
                if(designation.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Designation",Toast.LENGTH_LONG).show();
                    Designation.setError("Please Enter Designation");
                    Designation.requestFocus();
                    return;
                }
                if(businssEmail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter BusinessEmail",Toast.LENGTH_LONG).show();
                    BusinessEmail.setError("Please Enter BusinessEmail");
                    BusinessEmail.requestFocus();
                    return;
                }
                Intent intent = new Intent (getApplicationContext(),AddRecruiterPassword.class);
                startActivity(intent);
            }
        });


    }
}