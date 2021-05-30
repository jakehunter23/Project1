package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Referral extends AppCompatActivity {

    private RecyclerView ReferRecyclerView;
    ImageView menu;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.referral);
        ReferRecyclerView=findViewById(R.id.RefRecyclerView);
        ReferRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String [] name={"Shakti Swaroop Biswal","Shakti swaroop biswal","Shakti swaroop Biswal"};
        ReferRecyclerView.setAdapter(new ReferralAdapter(name));

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
