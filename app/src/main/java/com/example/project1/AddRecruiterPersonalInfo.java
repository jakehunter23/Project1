package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddRecruiterPersonalInfo extends AppCompatActivity {
    EditText Firstname,Lastname,Email,ContactNumber,Address,City,Zipcode;
    Button infoSave;
    String firstname, lastname, email, phoneNumber, address,city,zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recruiter_personal_info);

       ImageView recInfo_arrow = findViewById(R.id.recInfo_arrow);
        Firstname=findViewById(R.id.firstname);
        Lastname=findViewById(R.id.lastname);
        Email=findViewById(R.id.email);
        ContactNumber=findViewById(R.id.contact_number);
        City=findViewById(R.id.city);
        Address=findViewById(R.id.address);
        Zipcode=findViewById(R.id.zipcode);
        infoSave=findViewById(R.id.info_save);

        TextView status = findViewById(R.id.info_status);
        TextView state = findViewById(R.id.info_state);
        TextView country = findViewById(R.id.info_country);

        infoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstname = Firstname.getText().toString().trim();
                 lastname = Lastname.getText().toString().trim();
                 email = Email.getText().toString().trim();
                phoneNumber = ContactNumber.getText().toString().trim();
                 address = Address.getText().toString().trim();
                 city = City.getText().toString().trim();
                 zipcode = Zipcode.getText().toString().trim();


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


                if(email.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter MainEmail",Toast.LENGTH_LONG).show();
                    Email.setError("Please Enter Main Email");
                    Email.requestFocus();
                    return;
                }
                if(phoneNumber.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Phone Number",Toast.LENGTH_LONG).show();
                    ContactNumber.setError("Please Enter PhoneNumber");
                    ContactNumber.requestFocus();
                    return;
                }
                if(phoneNumber.length() < 10) {
                    Toast.makeText(getApplicationContext(),"Phone Number Length must be 10",Toast.LENGTH_LONG).show();
                    ContactNumber.setError("Phone Number Length must be 10");
                    ContactNumber.requestFocus();
                    return;
                }
                if(address.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Address",Toast.LENGTH_LONG).show();
                    Address.setError("Please Enter Address");
                    Address.requestFocus();
                    return;
                }
                if(city.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter City",Toast.LENGTH_LONG).show();
                    City.setError("Please Enter City");
                    City.requestFocus();
                    return;
                }
                if(zipcode.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Zipcode",Toast.LENGTH_LONG).show();
                    Zipcode.setError("Please Enter Zipcode");
                    Zipcode.requestFocus();
                    return;
                }

               //putting the data

                Intent intent = new Intent (getApplicationContext(),AddRecruiterProfessionalInfo.class);
               Bundle bundle = new Bundle();

               bundle.putString("First Name", firstname);
                bundle.putString("Last Name", lastname);
               bundle.putString("Email", email);
                bundle.putString("Phone Number", phoneNumber);
                bundle.putString("Address", address);
               bundle.putString("City", city);
                bundle.putString("Zipcode", zipcode);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        //listener for back arrow
        recInfo_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}