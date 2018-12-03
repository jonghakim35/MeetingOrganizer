package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoomEnterActivity extends Activity {

    private Button enterConfirmBtn;
    private EditText roomCodeEdit;
    private DatabaseReference dref;
    private String realRoomCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_room_enter);

        enterConfirmBtn = findViewById(R.id.confirm_enter_btn);
        roomCodeEdit = findViewById(R.id.room_code_edit);

        enterConfirmBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String enteredRoomCode, realRoomName;
                enteredRoomCode = roomCodeEdit.getText().toString();
                realRoomName = getIntent().getStringExtra("roomName");
                dref = FirebaseDatabase.getInstance().getReference("chatting").child(realRoomName).child("roomCode");

                dref.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       realRoomCode = dataSnapshot.getValue().toString();
                       //enter 확인
                       if(enteredRoomCode.equals(realRoomCode)){
                           Toast.makeText(getApplicationContext(), "성공.", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(RoomEnterActivity.this, ChattingActivity.class);
                           intent.putExtra("roomName", realRoomName);
                           startActivity(intent);
                           finish();
                       }
                       else
                           Toast.makeText(getApplicationContext(), "참여코드가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

            }
        });

    }
}
