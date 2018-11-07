package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class DeleteScheduleActivity extends Activity {

    private Button deleteConfirmBtn, deleteRejectBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_delete_schedule);

        deleteConfirmBtn = findViewById(R.id.delete_confirm_btn);
        deleteRejectBtn = findViewById(R.id.delete_reject_btn);

        deleteConfirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //데이터베이스에서 삭제하는 기능 구현하기
                finish();
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
