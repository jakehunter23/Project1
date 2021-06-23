package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    SearchView esearchView;
    ListView elistview;
    ArrayList<String> list;
    RecyclerView recyclerView;
    ArrayAdapter<String> existing_list_adapter;
    TextView ereset;
    LinearLayout linearLayout;

    List<Maindata> datalist=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        esearchView=findViewById(R.id.search_view);
        elistview=findViewById(R.id.listview);
        recyclerView=findViewById(R.id.recyclerView);
        ereset=findViewById(R.id.reset_id);
        linearLayout=findViewById(R.id.linear_layout_id);


        listview_search();


        database=RoomDB.getInstance(this);
        datalist=database.mainDao().getAll();
        if(datalist.isEmpty()){
            linearLayout.setVisibility(View.GONE);
        }

        linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new MainAdapter(SearchActivity.this,datalist);
        recyclerView.setAdapter(adapter);

        HashMap<String, Integer> checker = new HashMap<>();
        elistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String stext= (String) elistview.getItemAtPosition(i);



                if(!stext.equals("")){
                    Maindata data=new Maindata();
                    List<Maindata> items=new ArrayList<>();
                    data.setText(stext);

                    database.mainDao().insert(data);
                    datalist.clear();
                    datalist.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(SearchActivity.this, "Going to Next Activity", Toast.LENGTH_SHORT).show();


                }
            }
        });

        ereset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.mainDao().reset(datalist);
                datalist.clear();
                datalist.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });


    }

    private void listview_search() {

        list=new ArrayList<String>();
        list.add("Machine Learning Intern");
        list.add("Android Developer");
        list.add("React Native Intern");
        list.add("JavaScript Developer");
        list.add("Java Developer");
        list.add("Devops ");
        list.add("Content Writer ");
        list.add("Data Science Intern");
        existing_list_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        elistview.setAdapter(existing_list_adapter);
        elistview.setVisibility(View.GONE);



        esearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                existing_list_adapter.getFilter().filter(s);
                if(TextUtils.isEmpty(s)){
                    elistview.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);

                    //
                }
                else {
                    //
                    linearLayout.setVisibility(View.GONE);
                    elistview.setVisibility(View.VISIBLE);

                }

                return false;
            }
        });

    }
}