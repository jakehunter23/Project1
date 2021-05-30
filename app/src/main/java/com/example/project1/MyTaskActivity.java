package com.example.project1;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyTaskActivity extends AppCompatActivity {

    RecyclerView TaskRec;
    ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_task);

        TaskRec = findViewById(R.id.task_rec_item);
        TaskRec.setLayoutManager(new LinearLayoutManager(this));
        TaskRec.setAdapter(new MyTaskRecAdapter(this));

        back=findViewById(R.id.menu_icon_backtask);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
