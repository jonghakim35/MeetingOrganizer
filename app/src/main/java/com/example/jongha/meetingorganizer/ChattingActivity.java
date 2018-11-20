package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChattingActivity extends AppCompatActivity {

    private String roomName, userName, sendMsg;
    private TextView roomNameView;
    private EditText sendMsgText;
    private Button createTimeBtn, sendMsgBtn;
    private ListView chatListView;
    private DatabaseReference dref = FirebaseDatabase.getInstance().getReference("chatting");
    private ArrayList<String> chatsArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_room);

        //채팅방 user목록에 사용자 추가하기

       sendMsgText = findViewById(R.id.sendMessageText);
        roomNameView = findViewById(R.id.room_name_view);
        createTimeBtn = findViewById(R.id.create_meet_time_btn);
        chatListView = findViewById(R.id.chat_list_view);
        sendMsgBtn = findViewById(R.id.send_message_btn);

        final String roomName = getIntent().getStringExtra("roomName");
        roomNameView.setText(roomName);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, chatsArray) ;
        chatListView.setAdapter(adapter) ;


        //전송 버튼 클릭
        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = "5";
                //여기 바꾸기

                sendMsg = sendMsgText.getText().toString();
                ChatDTO chat = new ChatDTO(userName, sendMsg);
                dref.child(roomName).child("chats").push().setValue(chat);
                sendMsgText.setText("");
            }
        });



        dref.child(roomName).child("chats").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatDTO chatDTO = dataSnapshot.getValue(ChatDTO.class);
                adapter.add(chatDTO.getUserName() + " : " + chatDTO.getMessage());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                ChatDTO chatDTO = dataSnapshot.getValue(ChatDTO.class);
                adapter.remove(chatDTO.getUserName() + " : " + chatDTO.getMessage());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        createTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //시간표 생성
            }
        });

    }
}
