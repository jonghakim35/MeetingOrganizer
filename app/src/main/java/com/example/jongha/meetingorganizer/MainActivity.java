package com.example.jongha.meetingorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button roomCreateBtn, timeTableBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomCreateBtn = findViewById(R.id.room_create_btn);
        timeTableBtn = findViewById(R.id.timetable_modify_btn);

        roomCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //방 생성 버튼 클릭
                Intent intent = new Intent(MainActivity.this, RoomCreateActivity.class);
                startActivity(intent);
            }
        });

        timeTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimeTableActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
