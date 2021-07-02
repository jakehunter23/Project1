package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateTicketJobSeeker extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket_js);
        ImageView backbutton= (ImageView)findViewById(R.id.backbutton);
        AppCompatButton buttonsubmit=(AppCompatButton)findViewById(R.id.buttonsubmit);

        Spinner spinner=findViewById(R.id.tickettype);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.tickettype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backbt = new Intent(CreateTicketJobSeeker.this,faq.class);
                startActivity(backbt);
                finish();
            }
        });
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitbt = new Intent(CreateTicketJobSeeker.this,faq.class);
                startActivity(submitbt);
                finish();

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
        ((TextView) parent.getChildAt(0)).setTextSize(18);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}