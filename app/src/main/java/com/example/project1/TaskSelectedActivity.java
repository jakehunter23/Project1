package com.example.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class TaskSelectedActivity extends AppCompatActivity {

    RecyclerView rec1;
    RecyclerView rec2;
    RecyclerView rec3;
    ImageView back_button;

    TextView timer, deadlineCounter;
    Button endTask,startPauseTimer ;

    //initializing some variables
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;


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

        //COUNTDOWN TIMER CODE
        endTask=findViewById(R.id.endTask);
        timer = findViewById(R.id.counter_timer);
        deadlineCounter = findViewById(R.id.deadline_counter);
        startPauseTimer = findViewById(R.id.start_timer);


        //setting listener for startPause button
        startPauseTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                } }
        });

        //setting listener for EndTask Button
        endTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling onstop method here
                onStop();

            }
        });
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateWatchInterface();
            }
        }.start();
        mTimerRunning = true;
        updateWatchInterface();
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        timer.setText(timeLeftFormatted);
    }


    private void updateWatchInterface() {
        if (mTimerRunning) {

            startPauseTimer.setText("Pause");
        } else {

            startPauseTimer.setText("Start");
            if (mTimeLeftInMillis < 1000) {
                startPauseTimer.setVisibility(View.INVISIBLE);
            } else {
                startPauseTimer.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);
        editor.apply();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            timer.setText(getString(R.string.timer));
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        //setting time for 70 hrs
        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 252000000);
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        mTimerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        updateWatchInterface();
        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else {
                startTimer();
            }
        }
    }














}




