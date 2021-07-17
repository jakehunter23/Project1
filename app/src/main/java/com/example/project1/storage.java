package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class storage extends AppCompatActivity {
    private RecyclerView StorageRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        StorageRecycler=findViewById(R.id.list_storage);
        StorageRecycler.setLayoutManager(new LinearLayoutManager(this));
        String [] Send ={"Image","PDF","Word File"};
        StorageRecycler.setAdapter(new StorageAdapter_JS(Send));
        ImageView backstorage=(ImageView)findViewById(R.id.backstorage);
        backstorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createTics = new Intent(storage.this,myfiles.class);
                startActivity(createTics);
            }
        });
    }
}