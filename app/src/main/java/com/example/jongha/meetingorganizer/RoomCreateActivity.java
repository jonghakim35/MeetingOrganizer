package com.example.jongha.meetingorganizer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.EditText;

public class RoomCreateActivity extends Activity {

    private EditText roomNameEdit, roomCodeEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_room_create);

        roomNameEdit = findViewById(R.id.room_name);
        roomCodeEdit = findViewById(R.id.room_code);



    }
}
