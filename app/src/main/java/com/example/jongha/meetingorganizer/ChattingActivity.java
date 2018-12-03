package com.example.jongha.meetingorganizer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ChattingActivity extends AppCompatActivity {

    private String roomName, userName, sendMsg, estHour, estMin;
    private int estTime;
    private TextView roomNameView;
    private EditText sendMsgText;
    private Button createTimeBtn, sendMsgBtn;
    private ListView chatListView;
    private DatabaseReference dref = FirebaseDatabase.getInstance().getReference("chatting");
    private DatabaseReference dref1 = FirebaseDatabase.getInstance().getReference("timetables");
    private ArrayList<String> chatsArray = new ArrayList<>();
    private ArrayList<ScheduleDTO> schedulesArray = new ArrayList<>();
    private ArrayList<String> possibleArray = new ArrayList<>();
    private ArrayList<ScheduleDTO> monArray = new ArrayList<>(), tueArray = new ArrayList<>(), wedArray = new ArrayList<>(), thuArray = new ArrayList<>(), friArray = new ArrayList<>(), satArray = new ArrayList<>(), sunArray = new ArrayList<>();

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


        //시간표 받아오기. 모든 user에 대해 하는걸로 수정해야함
        dref1.child("test1999").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ScheduleDTO scheduleDTO = dataSnapshot.getValue(ScheduleDTO.class);
                schedulesArray.add(scheduleDTO);
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

                //시간 = i 에서 i+estTime, 가능한 i는 possibleArray에 등록됨


                for(int j = 0; j < schedulesArray.size(); j++){
                    ScheduleDTO thisSchedule = schedulesArray.get(j);
                    switch (thisSchedule.getDayOfWeek()){
                        case "월": monArray.add(thisSchedule);
                        break;

                        case "화": tueArray.add(thisSchedule);
                        break;

                        case "수": wedArray.add(thisSchedule);
                        break;

                        case "목": thuArray.add(thisSchedule);
                        break;

                        case "금": friArray.add(thisSchedule);
                        break;

                        case "토": satArray.add(thisSchedule);
                        break;

                        case "일": sunArray.add(thisSchedule);
                        break;
                    }
                }

                checkTimeByDate(monArray, "월");
                checkTimeByDate(tueArray, "화");
                checkTimeByDate(wedArray, "수");
                checkTimeByDate(thuArray, "목");
                checkTimeByDate(friArray, "금");
                checkTimeByDate(satArray, "토");
                checkTimeByDate(sunArray, "일");

                //요일까지 고려하고나서 possibleArray가 0, 1, 2, 3이상일때에 따른 display
                switch(possibleArray.size()){

                    case 0:
                        ChatDTO chat = new ChatDTO("추천 시간", "가능한 시간이 없습니다");
                        dref.child(roomName).child("chats").push().setValue(chat);
                        break;
                    case 1:
                        ChatDTO chat1 = new ChatDTO("추천 시간", possibleArray.get(0));
                        dref.child(roomName).child("chats").push().setValue(chat1);
                        break;
                    case 2:
                        ChatDTO chat2 = new ChatDTO("추천 시간", possibleArray.get(0));
                        dref.child(roomName).child("chats").push().setValue(chat2);
                        chat2 = new ChatDTO("추천 시간", possibleArray.get(1));
                        dref.child(roomName).child("chats").push().setValue(chat2);
                        break;

                    default:
                        Collections.shuffle(possibleArray);
                        for(int i = 0; i < 3; i++){
                            ChatDTO chat3 = new ChatDTO("추천 시간", possibleArray.get(i));
                            dref.child(roomName).child("chats").push().setValue(chat3);
                        }
                        break;
                }



            }
        });

    }

    private int getStartTime(ScheduleDTO schedule){ return Integer.valueOf(schedule.getStartHour()) * 60 + Integer.valueOf(schedule.getStartMin());}
    private int getEndTime(ScheduleDTO schedule) {return Integer.valueOf(schedule.getEndHour()) * 60 + Integer.valueOf(schedule.getEndMin());}
    private String timeToString(int possibleTime) {return possibleTime/60 + "시 " + possibleTime%60 +"분"; }


    private void checkTimeByDate(ArrayList<ScheduleDTO> gotSchedules, String dateOfWeek){

        for(int i = 480; i <= 1320; i+=15){
            int j;
            //j가 있는 for문을 통해 모든 schedulesArray의 경우 다룸
            for(j = 0; j < gotSchedules.size(); j++){
                ScheduleDTO thisSchedule = gotSchedules.get(j);


                if(getStartTime(thisSchedule) < i){
                    if(!(getEndTime(thisSchedule) < i)) {
                        break;
                    }
                }

                else if(getStartTime(thisSchedule) > i){
                    if(!(i + estTime < getStartTime(thisSchedule)))
                        break;
                }

                else
                    break;

            }
            if(possibleArray.size() > 500)
                break;
            if(j == gotSchedules.size() && j!=0)
                possibleArray.add(dateOfWeek + "요일 " + timeToString(i));

        }

        if(gotSchedules.size() == 0){
            for(int i = 480 ; i <=1320; i+=30) {
                possibleArray.add(dateOfWeek + "요일 " + timeToString(i));
                if(possibleArray.size() > 500)
                    break;
            }
        }

    }


}
