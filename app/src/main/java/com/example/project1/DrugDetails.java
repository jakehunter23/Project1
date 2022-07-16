package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DrugDetails extends AppCompatActivity {
    String name, manufacturer, composition;
    TextView Name;
    TextView Manu;
    TextView comp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_details);

        Intent intent = getIntent();
        name = getIntent().getExtras().getString("drug_name");
        manufacturer = getIntent().getExtras().getString("manufacturer");
        composition = getIntent().getExtras().getString("composition");

        Name = findViewById(R.id.textView455);
        Manu = findViewById(R.id.textView457);
        comp = findViewById(R.id.textView456);

        Name.setText(name);
        Manu.setText(manufacturer);
        comp.setText(composition);


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DrugDetails.this,
                DoctorViewDrugs.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
