package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoomCreateActivity extends Activity {

    private EditText roomNameEdit, roomCodeEdit, hourEdit, minEdit;
    private Button confirmBtn;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

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

                ChatRoomDTO newRoom = new ChatRoomDTO(roomName, roomCode, hour, min);
                myRef.child("chatting").child(roomName).setValue(newRoom);
                Toast.makeText(getApplicationContext(), "채팅방이 생성되었습니다.", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

    }
}
