package com.example.jongha.meetingorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

public class TimeTableActivity extends AppCompatActivity {

    private FloatingActionButton addScheduleBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        addScheduleBtn = findViewById(R.id.add_schedule_btn);

        addScheduleBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimeTableActivity.this, NewScheduleActivity.class));
            }
        });

    }
}
