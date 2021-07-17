package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class navigation extends AppCompatActivity {
    private RecyclerView mNavrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ImageView backbutton= (ImageView)findViewById(R.id.back_button);
        mNavrecyclerView=findViewById(R.id.nav_recycler);
        mNavrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] lang={"Dashboard", "My Task","My Requests","Applicatons Status", "My Schedule","My Files", "Messages", "Referal","F.A.Q", "Contact Us"};
        int[] Ids={R.drawable.dashboard,R.drawable.handyman,R.drawable.contacts_cal,R.drawable.clients,R.drawable.work_outline,R.drawable.database_icon,R.drawable.settings,R.drawable.confirmation_number,R.drawable.help,R.drawable.call};
        mNavrecyclerView.setAdapter(new NavAdapter_JS(lang, Ids));
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navid = new Intent(navigation.this,dashboard.class);
                startActivity(navid);
                finish();
            }
        });

    }
}