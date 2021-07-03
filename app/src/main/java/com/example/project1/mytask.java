package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class mytask extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<mytaskAdoptar>userList;
    Adaptor adaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytask);
        ImageView navbutton= (ImageView)findViewById(R.id.navbotton);
        navbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navid = new Intent(mytask.this,navigation.class);
                startActivity(navid);
                finish();
            }

        });
        iniData();
        iniRecycleView();
    }

    private void iniData() {
        userList= new ArrayList<>();
        userList.add(new mytaskAdoptar(R.drawable.maskprofile,"David Soft","App store screenshot design"));
        userList.add(new mytaskAdoptar(R.drawable.maskprofile,"Oscar Dulla","UI design for flight management .."));
        userList.add(new mytaskAdoptar(R.drawable.maskprofile,"Davi Soft","App store screenshot design"));
        userList.add(new mytaskAdoptar(R.drawable.maskprofile,"Oscr Dulla","UI design for flight management .."));
    }

    private void iniRecycleView() {
        recyclerView=findViewById(R.id.RecyclerView);
        layoutManager= new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adaptor=new Adaptor(userList);
        recyclerView.setAdapter(adaptor);
        adaptor.notifyDataSetChanged();
    }
}