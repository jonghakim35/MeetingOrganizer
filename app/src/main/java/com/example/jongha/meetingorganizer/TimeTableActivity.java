package com.example.jongha.meetingorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TimeTableActivity extends AppCompatActivity {

    private FloatingActionButton addScheduleBtn;

    DatabaseReference dref;
    ListView listview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        //listview 연결, adapter 생성과 연결, dref연결
        listview=(ListView)findViewById(R.id.schedule_list_view);
        final ScheduleListViewAdapter adapter = new ScheduleListViewAdapter();
        listview.setAdapter(adapter);
        dref = FirebaseDatabase.getInstance().getReference("timetables/test1999");
        //dref change to path of logged-in user하기


        //realtime database 데이터 변화에 대한 반응
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.addItem(dataSnapshot.getValue(ScheduleDTO.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter.addItem(dataSnapshot.getValue(ScheduleDTO.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        addScheduleBtn = findViewById(R.id.add_schedule_btn);
        addScheduleBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimeTableActivity.this, NewScheduleActivity.class));
            }
        });



    }
}
