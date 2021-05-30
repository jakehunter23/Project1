package com.example.project1;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NavActivity extends AppCompatActivity {
    private RecyclerView mNavrecyclerView;
    private ImageView back_button;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_panel);
        mNavrecyclerView=findViewById(R.id.nav_recycler);
        mNavrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] lang={"Dashboard", "My Tools","My Contacts","My Clients", "Job Listing","Database", "Settings", "Referal","F.A.Q", "Contact Us"};
        int[] Ids={R.drawable.dashboard,R.drawable.handyman,R.drawable.contacts_cal,R.drawable.clients,R.drawable.work_outline,R.drawable.database_icon,R.drawable.settings,R.drawable.confirmation_number,R.drawable.help,R.drawable.call};
        mNavrecyclerView.setAdapter(new NavAdapter(lang, Ids));

        back_button=findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }
}
