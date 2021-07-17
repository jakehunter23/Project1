package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class myfiles extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    RecyclerView Rec;
    CardView context;
    AppCompatButton addfiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfiles_js);
        Rec =findViewById(R.id.my_file_list);
        Rec.setLayoutManager(new LinearLayoutManager(this));
        Rec.setAdapter(new MyFilesRecAdapter_JS());
        ImageView navbuttonfaq= (ImageView)findViewById(R.id.navfaq);

        Spinner spinner=findViewById(R.id.Allfiles);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.files, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        addfiles=(AppCompatButton)findViewById(R.id.addfiles);
        addfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add= new Intent(myfiles.this,myFiles_3.class);
                startActivity(add);

            }
        });

        context =findViewById(R.id.storageHead);
        context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myfiles.this,storage.class);
                startActivity(intent);

            }
        });
        navbuttonfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intssss = new Intent(myfiles.this,navigation.class);
                startActivity(intssss);
                finish();

            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
        ((TextView) parent.getChildAt(0)).setTextSize(18);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}