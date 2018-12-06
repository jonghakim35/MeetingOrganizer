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


public class SignInActivity extends AppCompatActivity {


    private EditText emailEdit, pwdEdit;
    private Button signInBtn, signUpBtn;
    private String email, pwd;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user != null){
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        emailEdit = findViewById(R.id.sign_in_email_edit);
        pwdEdit = findViewById(R.id.sign_in_pwd_edit);
        signInBtn = findViewById(R.id.sign_in_submit_btn);
        signUpBtn = findViewById(R.id.sign_in_to_up_btn);


        if(getIntent().hasExtra("email")&&getIntent().hasExtra("pwd")) {

            email = getIntent().getStringExtra("email");
            pwd = getIntent().getStringExtra("pwd");
        }else{
            email = "";
            pwd = "";
        }

        if(!email.isEmpty() && !pwd.isEmpty()){
            // 받아온 email, pwd EditText에 보여주기
            emailEdit.setText(email);
            pwdEdit.setText(pwd);
        }


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailEdit.getText().toString();
                pwd = pwdEdit.getText().toString();

                mAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful())
                                    task.getException().getMessage();
                                else
                                {
                                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 버튼 클릭
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
