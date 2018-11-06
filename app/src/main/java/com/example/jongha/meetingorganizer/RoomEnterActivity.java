package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class RoomEnterActivity extends Activity {

    private Button enterConfirmBtn;
    private EditText roomCodeEdit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_room_enter);

        enterConfirmBtn = findViewById(R.id.confirm_enter_btn);
        roomCodeEdit = findViewById(R.id.room_code_edit);

        enterConfirmBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String enteredRoomCode;
                enteredRoomCode = roomCodeEdit.getText().toString();

                if(true){
                    // implement method if enteredRoomCode equals to real roomCode from database

                    startActivity(new Intent(RoomEnterActivity.this, ChattingActivity.class));
                    finish();
                }

            }
        });

    }
}
