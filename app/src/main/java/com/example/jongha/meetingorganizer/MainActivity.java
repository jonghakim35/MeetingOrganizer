package com.example.jongha.meetingorganizer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button roomCreateBtn, timeTableBtn;
    private ListView listview;
    private DatabaseReference dref;
    private ArrayList<String> roomNameArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //채팅방의 listview 구현 부분
        listview = (ListView) findViewById(R.id.room_list_view);
        dref = FirebaseDatabase.getInstance().getReference("chatting");
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, roomNameArray) ;
        listview.setAdapter(adapter) ;

        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.add(dataSnapshot.getKey());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter.add(dataSnapshot.getKey());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
                //시간표 생성 버튼 클릭
                Intent intent = new Intent(MainActivity.this, TimeTableActivity.class);
                startActivity(intent);
            }
        });

    }

}
