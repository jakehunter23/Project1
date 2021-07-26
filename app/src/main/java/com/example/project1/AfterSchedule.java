package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AfterSchedule extends AppCompatActivity {


    EditText title, location, date,time;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_schedule);

        title = findViewById(R.id.title_id);
        location = findViewById(R.id.location_id);
        date = findViewById(R.id.date_id);
        time = findViewById(R.id.time_id);
        button = findViewById(R.id.button13);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty() &&
                        !date.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, title.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, date.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, "test@yahoo.com");
                    startActivity(intent);

                    insertintoSql();

                } else {
                    Toast.makeText(AfterSchedule.this, "Please Fill All fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertintoSql() {
        DatabaseHelperCalendar myDb=new DatabaseHelperCalendar(AfterSchedule.this);
        myDb.addData(title.getText().toString().trim(),location.getText().toString().trim(),
                date.getText().toString().trim(),time.getText().toString().trim());
    }
}