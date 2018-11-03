package com.example.jongha.meetingorganizer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SingUpActivity extends AppCompatActivity {


    EditText signUpEmail, signUpPw, signUpName;
    Button signUpSubmit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        signUpEmail = findViewById(R.id.sign_in_email_edit);
        signUpPw = findViewById(R.id.sign_up_pwd_edit);
        signUpName = findViewById(R.id.sign_up_name_edit);
        signUpSubmit = findViewById(R.id.sign_up_submit_btn);

        signUpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //회원가입 제출버튼 클릭

            }
        });

    }
}
