package com.example.project1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EduUpdateActivity extends AppCompatActivity {

    EditText new_skill,new_study,new_instName,new_dateEnd;
    ImageView back_edu;
    Button btn_edu_update;

    String eduId,Skill, Study,instName,dateEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_update);

        new_skill =findViewById(R.id.new_skill);
        new_study =findViewById(R.id.new_study);
        new_instName =findViewById(R.id.new_instName);
        new_dateEnd =findViewById(R.id.new_dateEnd);
        back_edu=findViewById(R.id.back_arrow_edu);
        btn_edu_update = findViewById(R.id.edu_update);

        //setting listener for back button

        back_edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //First we call this
        getAndSetIntentData();

        //setting listener for update button
        btn_edu_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EduDatabaseHelper myDB = new EduDatabaseHelper(EduUpdateActivity.this);
                Skill = new_skill.getText().toString().trim();
                Study = new_study.getText().toString().trim();
                instName = new_instName.getText().toString().trim();
                dateEnd =  new_dateEnd.getText().toString().trim();
                myDB.EduUpdateData(Skill,Study,instName,dateEnd,eduId);

            }
        });

        //setting listener for delete button
        ImageView edu_delete = findViewById(R.id.edu_delete);
        edu_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }
    //method for getting and setting intent data
    void getAndSetIntentData(){
        if(getIntent().hasExtra("Skill") && getIntent().hasExtra("Study") &&
                getIntent().hasExtra("instName") && getIntent().hasExtra("dateEnd")){
            //Getting Data from Intent

            Skill = getIntent().getStringExtra("Skill");
            Study = getIntent().getStringExtra("Study");
            instName = getIntent().getStringExtra("instName");
            dateEnd = getIntent().getStringExtra("dateEnd");
            eduId=getIntent().getStringExtra("_eduId");

            //Setting Intent Data
            new_skill.setText(Skill);
            new_study.setText(Study);
            new_instName.setText(instName);
            new_dateEnd.setText(dateEnd);

            Log.d("", Skill+" "+Study+" "+instName+""+dateEnd);
        }else{
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    //method for dialog box
    void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + Skill + " ?");
        builder.setMessage("Are you sure you want to delete " + Skill + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EduDatabaseHelper myDB = new EduDatabaseHelper(EduUpdateActivity.this);
                myDB.EduDeleteData(Skill);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}