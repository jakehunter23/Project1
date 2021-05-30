package com.example.project1;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StorageActivity extends AppCompatActivity {
    private RecyclerView StorageRecycler;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage);

        StorageRecycler=findViewById(R.id.list_storage);
        StorageRecycler.setLayoutManager(new LinearLayoutManager(this));
        String [] Send ={"Image","PDF","Word File"};
        StorageRecycler.setAdapter(new StorageAdapter(Send));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
