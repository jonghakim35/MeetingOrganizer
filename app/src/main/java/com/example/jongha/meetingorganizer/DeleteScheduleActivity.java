package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DeleteScheduleActivity extends Activity {

    private Button deleteConfirmBtn, deleteRejectBtn;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;
    private FirebaseUser user;
    //logged-in user의 path로 바꾸기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_delete_schedule);

        user = FirebaseAuth.getInstance().getCurrentUser();

        myRef = database.getReference("timetables").child(user.getDisplayName());


        deleteConfirmBtn = findViewById(R.id.delete_confirm_btn);
        deleteRejectBtn = findViewById(R.id.delete_reject_btn);

        deleteConfirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //데이터베이스에서 삭제하는 기능 구현하기
                Intent gotintent = getIntent();
                String schName = getIntent().getStringExtra("scheduleName");
                myRef.child(schName).removeValue();

                finish();
                //삭제하면 이상해짐(결과 두개, 새로고침 안됨 고치기)
            }
        });

        deleteRejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
