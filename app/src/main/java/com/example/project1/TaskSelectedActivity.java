package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TaskSelectedActivity extends AppCompatActivity {

    RecyclerView rec1;
    RecyclerView rec2;
    RecyclerView rec3;
    ImageView back_button;

    int [] icon = {R.drawable.screen_share_black,R.drawable.photo_camera_black,R.drawable.screen_lock_portrait_black,R.drawable.face_black_24dp};
    String [] title = {"Screen monitoring","Candidate monitoring","Tab lock","Face unlock"};
    int [] dp = {R.drawable.ellipse_234,R.drawable.ellipse_1,R.drawable.small_dp_1,R.drawable.dp_1};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_task_selected_act);

        rec1 =findViewById(R.id.task_sel_recycler1);
        rec1.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        rec1.setAdapter(new TaskSelectedRec1Adapter(icon,title));
        rec2=findViewById(R.id.task_sel_recycler_2);
        rec2.setLayoutManager(new LinearLayoutManager(this));
        rec2.setAdapter(new TaskSelRec2Adapter(dp));
        rec3=findViewById(R.id.task_sel_recycler_3);
        rec3.setLayoutManager(new LinearLayoutManager(this));
        rec3.setAdapter(new TaskSelRec3Adapter());
        back_button =findViewById(R.id.menu_icon_jobl);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
