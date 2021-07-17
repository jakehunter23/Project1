package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CreateTicket extends AppCompatActivity {

    ImageView menu;
    ArrayList<String> typeList;
    Spinner type;
    Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_ticket);

        type=findViewById(R.id.spinner54);
        ArrayAdapter typeAdapter;
        typeList=new ArrayList<>();
        typeList.add("All");
        typeList.add("Dummy1");
        typeList.add("Dummy2");
        typeAdapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,typeList);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(typeAdapter);

        menu=findViewById(R.id.menu_icon_jobl);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submit = findViewById(R.id.button24);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateTicket.this,TicketSuccess.class);
                startActivity(intent);
            }
        });
    }
}
