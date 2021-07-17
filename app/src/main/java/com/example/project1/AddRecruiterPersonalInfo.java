package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecruiterPersonalInfo extends AppCompatActivity {
    EditText Firstname,Lastname,Email,ContactNumber,Address,City,Zipcode;
    Button infoSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recruiter_personal_info);
        Firstname=findViewById(R.id.firstname);
        Lastname=findViewById(R.id.lastname);
        Email=findViewById(R.id.email);
        ContactNumber=findViewById(R.id.contact_number);
        City=findViewById(R.id.city);
        Address=findViewById(R.id.address);
        Zipcode=findViewById(R.id.zipcode);
        infoSave=findViewById(R.id.info_save);

        infoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = Firstname.getText().toString().trim();
                String lastname = Lastname.getText().toString().trim();
                String emails = Email.getText().toString().trim();
                String phoneNumbers = ContactNumber.getText().toString().trim();
                String addresss = Address.getText().toString().trim();
                String citys = City.getText().toString().trim();
                String zipcodes = Zipcode.getText().toString().trim();
                if(firstname.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter First Name",Toast.LENGTH_LONG).show();
                    Firstname.setError("Please Enter First Name");
                    Firstname.requestFocus();
                    return;
                }
                if(lastname.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Last Name",Toast.LENGTH_LONG).show();
                    Lastname.setError("Please Enter Last Name");
                    Lastname.requestFocus();
                    return;
                }


                if(emails.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter MainEmail",Toast.LENGTH_LONG).show();
                    Email.setError("Please Enter Main Email");
                    Email.requestFocus();
                    return;
                }
                if(phoneNumbers.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Phone Number",Toast.LENGTH_LONG).show();
                    ContactNumber.setError("Please Enter PhoneNumber");
                    ContactNumber.requestFocus();
                    return;
                }
                if(phoneNumbers.length() < 10) {
                    Toast.makeText(getApplicationContext(),"Phone Number Length must be 10",Toast.LENGTH_LONG).show();
                    ContactNumber.setError("Phone Number Length must be 10");
                    ContactNumber.requestFocus();
                    return;
                }
                if(addresss.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Address",Toast.LENGTH_LONG).show();
                    Address.setError("Please Enter Address");
                    Address.requestFocus();
                    return;
                }
                if(citys.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter City",Toast.LENGTH_LONG).show();
                    City.setError("Please Enter City");
                    City.requestFocus();
                    return;
                }
                if(zipcodes.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Zipcode",Toast.LENGTH_LONG).show();
                    Zipcode.setError("Please Enter Zipcode");
                    Zipcode.requestFocus();
                    return;
                }
                Intent intent = new Intent (getApplicationContext(),AddRecruiterProfessionalInfo.class);
                startActivity(intent);
            }
        });
    }
}