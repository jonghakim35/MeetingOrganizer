package com.example.jongha.meetingorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {


    EditText signUpEmail, signUpPw, signUpName;
    Button signUpSubmit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpEmail = findViewById(R.id.sign_up_email_edit);
        signUpPw = findViewById(R.id.sign_up_pwd_edit);
        signUpName = findViewById(R.id.sign_up_name_edit);
        signUpSubmit = findViewById(R.id.sign_up_submit_btn);

        signUpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, pwd, name;

                email = signUpEmail.getText().toString();
                pwd = signUpPw.getText().toString();
                name = signUpName.getText().toString();

                //회원가입 제출버튼 클릭
                if(!email.isEmpty() && !pwd.isEmpty() && !name.isEmpty()){

                    // 회원가입 및 MainActivity로 데이터 전달 및 실행
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    intent.putExtra("email", email);
                    intent.putExtra("pwd",pwd);
                    startActivity(intent);
                    finish();

                }else{
                    // 입력 메시지 토스트
                    Toast.makeText(getApplicationContext(), "회원정보 중에 누락된 부분이 있습니다. 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
