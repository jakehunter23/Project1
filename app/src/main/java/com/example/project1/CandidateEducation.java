package com.example.project1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CandidateEducation extends AppCompatActivity {
    Button visibility_btn, cancel_btn,btn_next,btn_save, btn_recycler;
    ImageView back_btn_edu;
    TextView tv_showHistory1,tv_showHistory2;
    RelativeLayout relativeLayout;

    // declaring variables
    EditText new_Title,new_Name,new_dateFrom,new_dateTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_candidate_education);


        back_btn_edu = findViewById(R.id.back_arrow_edu);

        cancel_btn = findViewById(R.id.cancel);
        relativeLayout = findViewById(R.id.Visibility_layout);
        visibility_btn = findViewById(R.id.visibility_layout_button);
        btn_save = findViewById(R.id.button_save);
        btn_next = findViewById(R.id.next);
        btn_recycler = findViewById(R.id.recycler_button);
        tv_showHistory1 = findViewById(R.id.tv_showHistory1);
        tv_showHistory2 = findViewById(R.id.tv_showHistory2);


        //getting variables
        new_Title = findViewById(R.id.et_Title);
        new_Name = findViewById(R.id.et_Name);
        new_dateFrom = findViewById(R.id.et_DateFrom);
        new_dateTo = findViewById(R.id.et_DateTo);

        //calling viewAll methods
        viewAll1();

        //listener for back button
        back_btn_edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(getApplicationContext(), CandidateEducation.class);
                //  startActivity(intent);
                finish();
            }
        });

        //listener for next button
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CandidateReview.class);
                 startActivity(intent);
            }
        });



        //setting visibility
        visibility_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });

        //setting invisibility
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.GONE);
            }
        });

        //setting listener on SAVE BUTTON
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processInsert(new_Title.getText().toString().trim(), new_Name.getText().toString().trim(), new_dateFrom.getText().toString().trim(), new_dateTo.getText().toString().trim());
            }
        });

        //redirecting to recycler view
        btn_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RecyclerView.class));
            }
        });

    }

    //METHOD FOR VIEWDATA
    public void viewAll1() {
        //setting listener for showDataButton 1
        tv_showHistory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(CandidateEducation.this);
                Cursor cursor = myDB.readTop1();
                assert cursor != null;
                cursor.moveToFirst();

                if (cursor.getCount() == 0) {
                    //show messages
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                }

                while (cursor.moveToNext()) {
                    tv_showHistory1.setText("Title : " + cursor.getString(1)+" \n" + "Company Name : " +cursor.getString(2)+" \n"+ "Date From : " +cursor.getString(3)+" \n" + "Date To : " +cursor.getString(4));
                }
            }
        });

        //setting listener for showDataButton 2
        tv_showHistory2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(CandidateEducation.this);
                Cursor cursor = myDB.readTop2();
                assert cursor != null;

                if (cursor.getCount() == 0) {
                    //show messages
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                }
                if(cursor.moveToFirst()){
                    do {
                        tv_showHistory2.setText("Title : " + cursor.getString(1) + " \n" + "Company Name : " + cursor.getString(2) + " \n" + "Date From : " + cursor.getString(3) + " \n" + "Date To : " + cursor.getString(4));
                    }
                    while(cursor.moveToNext());
                }}

        });
    }


    //method for inserting data into database
    private void processInsert(String title, String name, String dateFrom, String dateTo) {

        MyDatabaseHelper myDB = new MyDatabaseHelper(CandidateEducation.this);
        myDB.addData(title,name,dateFrom,dateTo);
        new_Title.setText("");
        new_Name.setText("");
        new_dateFrom.setText("");
        new_dateTo.setText("");

    }


}
