package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyTools extends AppCompatActivity {
    private RecyclerView toolsRecyclerView;
    ImageView menu;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tools);
        toolsRecyclerView=findViewById(R.id.toolsRecycler);
        toolsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String [] Names={"Job seeker database","Online interviews","Chatbot","Job portal posting","Analytics","Interview schedule","Documents verifier","Online assesments","Resume sorting"};
        String desc="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed";
        int [] icon_drawable={R.drawable.database,R.drawable.interview,R.drawable.ic_launcher_background,R.drawable.job_portal,R.drawable.analytics,R.drawable.interview_schedule,R.drawable.doc_verify,R.drawable.assessment,R.drawable.resume};
        toolsRecyclerView.setAdapter(new MyToolsAdapter(Names,desc,icon_drawable));

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
