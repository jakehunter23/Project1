package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class faq extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    RecyclerView rec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ImageView navbuttonfaq= (ImageView)findViewById(R.id.navfaq);


        Spinner spinner=findViewById(R.id.spinner55);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.tickettype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner prioritySpinner=findViewById(R.id.spinner56);
        ArrayAdapter<CharSequence> priorityadapter=ArrayAdapter.createFromResource(this,R.array.priority, android.R.layout.simple_spinner_item);
        priorityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityadapter);
        prioritySpinner.setOnItemSelectedListener(this);


        AppCompatButton createticket=(AppCompatButton)findViewById(R.id.create_ticket);
        navbuttonfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intssss = new Intent(faq.this,navigation.class);
                startActivity(intssss);
                finish();

            }
        });
        rec=findViewById(R.id.faq_item_rec);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(new FaqRecAdapter_JS());

        createticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createTic = new Intent(faq.this, CreateTicketJobSeeker.class);
                startActivity(createTic);
                finish();

            }
        });
    }

    private String getColoredSpanned(String name, String color) {
        String input="<font color=" + color+">"+name+"</font>";
        return input;

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