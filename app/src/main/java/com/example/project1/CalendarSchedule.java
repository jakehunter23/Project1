package com.example.project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarSchedule extends AppCompatActivity {

    CardView e1;
    RecyclerView recyclerView;
    DatabaseHelperCalendar myDB;
    ArrayList<Schedule_Class> items;
    Calendatr_Adapter calendatr_adapter;
    TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_schedule);
        e1=findViewById(R.id.scheduleButton_id);
        recyclerView=findViewById(R.id.recycler_calendar);
        time=findViewById(R.id.current_time_id);
        myDB=new DatabaseHelperCalendar(getApplicationContext());
        items=new ArrayList<>();



        displayData();
        //display todays Date
        DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
        String dateString = dateFormat.format(new Date()).toString();
        time.setText(dateString);

        calendatr_adapter=new Calendatr_Adapter(getApplicationContext(),items,CalendarSchedule.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(calendatr_adapter);
        calendatr_adapter.notifyDataSetChanged();


        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AfterSchedule.class));
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    private void displayData() {
        Cursor cursor=myDB.readData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                items.add(new Schedule_Class(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }
        }
    }
}