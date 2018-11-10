package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewScheduleActivity extends Activity {

    private EditText scheduleNameEdit, startHourEdit, startMinEdit, endHourEdit, endMinEdit, dayOfWeekEdit;
    private Button scheduleConfirmBtn;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

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
                //userID = database에서 받아오기(현재 사용자 ID)
                String userID = "test1999";
                ScheduleDTO sche = new ScheduleDTO(startHour, startMin, endHour, endMin, dayOfWeek);

                myRef.child("timetables").child(userID).child(activityName).setValue(sche);

                Toast.makeText(getApplicationContext(), "일정이 저장되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}
