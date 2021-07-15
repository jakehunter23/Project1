package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecruiterPassword extends AppCompatActivity {
    EditText Pass,ConfirmPass;
    Button savePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recruiter_password);
        Pass=findViewById(R.id.pass);
        ConfirmPass=findViewById(R.id.confirm_pass);
        savePass=findViewById(R.id.pass_save);
        savePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = Pass.getText().toString().trim();
                String confirmpass = ConfirmPass.getText().toString().trim();

                if(password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_LONG).show();
                    Pass.setError("Please Enter Confirm Password");
                    Pass.requestFocus();
                    return;
                }
                if(confirmpass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Confirm Password",Toast.LENGTH_LONG).show();
                    ConfirmPass.setError("Please Enter Confirm Password");
                    ConfirmPass.requestFocus();
                    return;
                }
            }
        });

    }
}