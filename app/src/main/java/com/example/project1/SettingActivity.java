package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SettingActivity extends AppCompatActivity {

    private RecyclerView SetRecyclerView;
    ImageView menu;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        SetRecyclerView=findViewById(R.id.sett_recyclerview);
        SetRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[]Headings={"Account","Privacy","Notifications","Preferences","Messages","Updates","Notifcations"};
        String description ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In et ultricies odio";
        SetRecyclerView.setAdapter(new SettingAdapter(Headings,description));

        menu=findViewById(R.id.menu_icon_jobl);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NavActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i,2);
            }
        });

    }
}
