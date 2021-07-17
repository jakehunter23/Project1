package com.example.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FaqActivity extends AppCompatActivity {

    RecyclerView rec;
    Spinner type,priority;
    ImageView menu;
    Button ticket;
    ArrayList<String> typeList;
    ArrayList<String> priorityList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);



        ArrayAdapter typeAdapter,priorityAdapter;

        type=findViewById(R.id.spinnerTickType);
        priority=findViewById(R.id.spinnerPriority);

        typeList=new ArrayList<>();
        priorityList=new ArrayList<>();

        typeList.add("All");
        typeList.add("Dummy1");
        typeList.add("Dummy2");

        priorityList.add("High");
        priorityList.add("Medium");
        priorityList.add("Low");

        typeAdapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,typeList);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(typeAdapter);

       priorityAdapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,priorityList);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priority.setAdapter(priorityAdapter);


        rec=findViewById(R.id.faq_item_rec);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(new FaqRecAdapter());

        menu=findViewById(R.id.menu_icon_jobl);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NavActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i,2);
            }
        });

        ticket=findViewById(R.id.create_ticket);
        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(FaqActivity.this,CreateTicket.class);
                startActivity(intent);
            }
        });
    }
}
