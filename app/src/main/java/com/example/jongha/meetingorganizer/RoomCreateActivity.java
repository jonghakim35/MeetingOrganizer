package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class RoomCreateActivity extends Activity {

    private EditText roomNameEdit, roomCodeEdit, hourEdit, minEdit;
    private Button confirmBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_room_create);

        roomNameEdit = findViewById(R.id.room_name);
        roomCodeEdit = findViewById(R.id.room_code);
        hourEdit = findViewById(R.id.estimateHour);
        minEdit = findViewById(R.id.estimateMin);
        confirmBtn = findViewById(R.id.confirm_create_btn);


        confirmBtn.setOnClickListener(new View.OnClickListener(){

            String roomName, roomCode, hour, min;
            @Override
            public void onClick(View v) {
                roomName = roomNameEdit.getText().toString();
                roomCode = roomCodeEdit.getText().toString();
                hour = hourEdit.getText().toString();
                min = minEdit.getText().toString();

                finish();
            }
        });

    }
}