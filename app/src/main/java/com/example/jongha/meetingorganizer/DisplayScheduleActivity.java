package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DisplayScheduleActivity extends Activity {

    private TextView startHourView, startMinView, endHourView, endMinView, dateOfWeekView, scheduleNameView;
    private Button confirmBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_display_schedule);

        scheduleNameView = findViewById(R.id.scheduleNameView);
        startHourView = findViewById(R.id.startHourView);
        startMinView = findViewById(R.id.startMinView);
        endHourView = findViewById(R.id.endHourView);
        endMinView = findViewById(R.id.endMinView);
        dateOfWeekView = findViewById(R.id.dateOfWeekView);
        confirmBtn = findViewById(R.id.scheduleOkBtn);
        deleteBtn=findViewById(R.id.scheduleDeleteBtn);


        Intent gotintent = getIntent();
        final String[] infoString = getIntent().getStringArrayExtra("dataStrings");

        scheduleNameView.setText(infoString[0]);
        startHourView.setText(infoString[1]);
        startMinView.setText(infoString[2]);
        endHourView.setText(infoString[3]);
        endMinView.setText(infoString[4]);
        dateOfWeekView.setText(infoString[5]);

        //onCreate하면 누른 activity의 이름 등 고유정보를 intent로 받아와서 database에서 해당 정보를 찾아 startHourView 등 Textview 내용을 set하는 부분 작성하기

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DisplayScheduleActivity.this, DeleteScheduleActivity.class);
                String nameToIntent = infoString[0];
                intent.putExtra("scheduleName", nameToIntent);
                startActivity(intent);

                //delete하는 기능 구현하기
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
