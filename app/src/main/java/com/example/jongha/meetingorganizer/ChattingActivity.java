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
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChattingActivity extends AppCompatActivity {

    private String roomName, userName, sendMsg, estHour, estMin;
    private int estTime;
    private TextView roomNameView;
    private EditText sendMsgText;
    private Button createTimeBtn, sendMsgBtn;
    private ListView chatListView;
    private DatabaseReference dref = FirebaseDatabase.getInstance().getReference("chatting");
    private ArrayList<String> chatsArray = new ArrayList<>();
    private ArrayList<ScheduleDTO> schedulesArray = new ArrayList<>();
    private ArrayList<String> possibleArray = new ArrayList<>();
    private DatabaseReference estHourRef, estMinRef;

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

        roomName = getIntent().getStringExtra("roomName");
        roomNameView.setText(roomName);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, chatsArray) ;
        chatListView.setAdapter(adapter) ;

        dref.child(roomName).child("estHour").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                estHour = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dref.child(roomName).child("estMin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                estMin = dataSnapshot.getValue(String.class);
                estTime =  60 * Integer.parseInt(estHour) + Integer.parseInt(estMin);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*realtime database에서 child 자료를 가져올떄 유의사항. 일단 onCreate 모든 문장 실행 이후 addValueEventListener가 실행되는 것 같음.
        즉, 위와 같이 코드를 짜고 이 주석이 있는 부분에 estTime을 출력하는 Toast를 띄우면 0이 나오는데, 그 이유는 onCreate(Toast 실행) -> Listner(estTime 계산)이기 때문
        따라서, onCreate -> Listner(estTime 계산) -> onClick(Toast 실행) 하면 잘 된다.
         */



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

                Toast.makeText(getApplicationContext(), String.valueOf(estTime), Toast.LENGTH_SHORT).show();


                //ScheduleDTO 생성자 String activityName, String startHour, String startMin, String endHour, String endMin, String dayOfWeek

                //모든 유저의 스케쥴들을 받아와서 schedulesArray에 저장했다고 가정
                /*schedulesArray.add(new ScheduleDTO("monTest1", "12", "00", "14", "00", "월"));
                schedulesArray.add(new ScheduleDTO("monTest2", "15", "00", "16", "00", "월"));
                schedulesArray.add(new ScheduleDTO("monTest3", "8", "00", "11", "00", "월"));
                schedulesArray.add(new ScheduleDTO("tueTest1", "15", "00", "16", "00", "화"));
                schedulesArray.add(new ScheduleDTO("tueTest2", "15", "30", "17", "00", "화"));
                schedulesArray.add(new ScheduleDTO("tuetest3", "15", "30", "15", "45", "화"));
                */

                /*
                for(int i = 480; i <= 1320; i += 15){
                    for(schedule : schedulesArray){


                    }
                }
                */


            }
        });

    }
}
