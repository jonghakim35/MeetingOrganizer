package com.example.jongha.meetingorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class SignUpActivity extends AppCompatActivity {


    EditText signUpEmail, signUpPw, signUpName;
    Button signUpSubmit;
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpActivity";
    private String email, pwd, name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        signUpEmail = findViewById(R.id.sign_up_email_edit);
        signUpPw = findViewById(R.id.sign_up_pwd_edit);
        signUpName = findViewById(R.id.sign_up_name_edit);
        signUpSubmit = findViewById(R.id.sign_up_submit_btn);

        signUpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = signUpEmail.getText().toString().trim();
                pwd = signUpPw.getText().toString().trim();
                name = signUpName.getText().toString().trim();

                //회원가입 제출버튼 클릭
                if(!email.isEmpty() && !pwd.isEmpty() && !name.isEmpty()){

                    mAuth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();

                                        FirebaseUser user = mAuth.getCurrentUser();

                                        if(user!=null){
                                            UserProfileChangeRequest setUserName = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(name)
                                                    .build();

                                            user.updateProfile(setUserName).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });
                                        }


                                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                        intent.putExtra("email", email);
                                        intent.putExtra("pwd",pwd);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });


                    // 회원가입 및 MainActivity로 데이터 전달 및 실행


                }else{
                    // 입력 메시지 토스트
                    Toast.makeText(getApplicationContext(), "회원정보 중에 누락된 부분이 있습니다. 입력해주세요.", Toast.LENGTH_SHORT).show();
                }


            }

        });

    }

}
