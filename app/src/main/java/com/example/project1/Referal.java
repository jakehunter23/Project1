package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Referal extends AppCompatActivity {
    private RecyclerView ReferRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referal);
        ReferRecyclerView=findViewById(R.id.RefRecyclerView);
        ReferRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String [] name={"Shakti Swaroop Biswal","Shakti swaroop biswal","Shakti swaroop Biswal"};
        ReferRecyclerView.setAdapter(new ReferralAdapter_JS(name));
        ImageView navbutton= (ImageView)findViewById(R.id.navbotton);
        navbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navid = new Intent(Referal.this,navigation.class);
                startActivity(navid);
                finish();
            }

        });
    }
}