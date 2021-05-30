package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyFilesActivity extends AppCompatActivity {

    RecyclerView Rec;
    ImageView back;
    CardView context;
    SeekBar seekbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_files);

        seekbar = findViewById(R.id.seekBar6);
        setSeekBarClickable(seekbar,true);

        Rec =findViewById(R.id.my_file_list);
        Rec.setLayoutManager(new LinearLayoutManager(this));
        Rec.setAdapter(new MyFilesRecAdapter());

        back=findViewById(R.id.menu_icon_fileback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        context =findViewById(R.id.storageHead);
        context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyFilesActivity.this,StorageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSeekBarClickable(SeekBar mSeekBar, boolean clickable) {
        if (clickable) {
            //Enable status
            mSeekBar.setClickable(false);
            mSeekBar.setEnabled(false);
            mSeekBar.setFocusable(false);

        } else {
            // disable state
            mSeekBar.setClickable(false);
            mSeekBar.setEnabled(false);
            mSeekBar.setFocusable(false);
        }
    }

}
