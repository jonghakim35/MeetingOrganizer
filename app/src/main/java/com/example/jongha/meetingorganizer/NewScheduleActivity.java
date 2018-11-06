package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class NewScheduleActivity extends Activity {

    private EditText scheduleNameEdit, startHourEdit, startMinEdit, endHourEdit, endMinEdit, dayOfWeekEdit;
    private Button scheduleConfirmBtn;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_schedule);

        scheduleNameEdit = findViewById(R.id.schedule_name);
        startHourEdit  = findViewById(R.id.start_hour);
        startMinEdit = findViewById(R.id.start_min);
        endHourEdit = findViewById(R.id.end_hour);
        endMinEdit = findViewById(R.id.end_min);
        dayOfWeekEdit = findViewById(R.id.day_of_week);
        scheduleConfirmBtn = findViewById(R.id.new_schedule_confirm_btn);


        scheduleConfirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String activityName, startHour, startMin, endHour, endMin, dayOfWeek;
                activityName = scheduleNameEdit.getText().toString();
                startHour = startHourEdit.getText().toString();
                startMin = startMinEdit.getText().toString();
                endHour = endHourEdit.getText().toString();
                endMin = endMinEdit.getText().toString();
                dayOfWeek = dayOfWeekEdit.getText().toString();

                //입력 받아서 to database 하는 기능 구현하기

                finish();
            }
        });


    }
}
