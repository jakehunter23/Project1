package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MeetingCreation extends AppCompatActivity {
    Spinner ampm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_creation);


        ampm=findViewById(R.id.ampm);
        ArrayList<String> list1=new ArrayList<>();
        list1.add("AM");
        list1.add("PM");
        ArrayAdapter Adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list1);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ampm.setAdapter(Adapter);

    }
}