package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_JS extends AppCompatActivity {
    TextView sginup;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_js);
        sginup = (TextView)findViewById(R.id.signup);
        loginButton=(Button)findViewById(R.id.loginbutton);
        sginup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity_JS.this,signin.class);
                startActivity(i);

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is = new Intent(MainActivity_JS.this,dashboard.class);
                startActivity(is);
            }
        });
    }
}