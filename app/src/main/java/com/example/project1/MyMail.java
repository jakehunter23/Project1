package com.example.project1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyMail extends AppCompatActivity {

    private RecyclerView MailRecyclerView;
    private Spinner spinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_email);
        MailRecyclerView=findViewById(R.id.MailRecyclerView);
        MailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String Name="JOHN DOE";
        String Sub="Subject";
        String Context="The mail content will be shown here in the first line";
        MailRecyclerView.setAdapter(new MyMailAdapter(Name,Sub,Context));

        spinner=findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Inbox");
        categories.add("Starred");
        categories.add("Drafts");
        categories.add("Trash");
        categories.add("Spam");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }
}
