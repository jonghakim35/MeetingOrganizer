package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoomEnterActivity extends Activity {

    private Button enterConfirmBtn;
    private EditText roomCodeEdit;
    private DatabaseReference dref;
    private String realRoomCode;
    private ArrayList<String> usersArray = new ArrayList<>();
    private DatabaseReference dref1 = FirebaseDatabase.getInstance().getReference("chatting");
    private String realRoomName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_room_enter);

        enterConfirmBtn = findViewById(R.id.confirm_enter_btn);
        roomCodeEdit = findViewById(R.id.room_code_edit);

        realRoomName = getIntent().getStringExtra("roomName");

        dref1.child(realRoomName).child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                usersArray.add(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        enterConfirmBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String enteredRoomCode;
                enteredRoomCode = roomCodeEdit.getText().toString();

                dref = FirebaseDatabase.getInstance().getReference("chatting").child(realRoomName).child("roomCode");

                dref.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       realRoomCode = dataSnapshot.getValue().toString();
                       //enter 확인
                       if(enteredRoomCode.equals(realRoomCode)){
                           if(!usersArray.contains("test1999")){ dref1.child(realRoomName).child("users").push().setValue("test1999");}


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
