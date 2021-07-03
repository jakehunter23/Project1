package com.example.project1;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyTaskActivity extends AppCompatActivity {

    RecyclerView TaskRec;
    Spinner spinner1,spinner2;
    ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_task);

        TaskRec = findViewById(R.id.task_rec_item);
        TaskRec.setLayoutManager(new LinearLayoutManager(this));
        TaskRec.setAdapter(new MyTaskRecAdapter(this));

        spinner1=findViewById(R.id.spinner55);
        ArrayList<String> list1=new ArrayList<>();
        list1.add("High");
        list1.add("Medium");
        list1.add("Low");
        ArrayAdapter Adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list1);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(Adapter);

        spinner2=findViewById(R.id.spinner56);
        ArrayList<String> list=new ArrayList<>();
        list.add("High");
        list.add("Medium");
        list.add("Low");
        ArrayAdapter Adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list);
        Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(Adapter1);

        back=findViewById(R.id.menu_icon_backtask);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
