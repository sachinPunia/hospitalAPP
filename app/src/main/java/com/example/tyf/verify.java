package com.example.tyf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;


public class verify extends AppCompatActivity {
    private String verificationId;
    private FirebaseAuth mAuth;
    private ProgressBar ProgressBar;
    private EditText editText2;
    private TextView Email, Password;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        mAuth = FirebaseAuth.getInstance();
        fAuth = FirebaseAuth.getInstance();
        ProgressBar = findViewById(R.id.progressBar);
        editText2 = findViewById(R.id.editText2);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);


        String phonenumber = getIntent().getStringExtra("phonenumber");
        sendVerificationcode(phonenumber);


            findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String code = editText2.getText().toString().trim();

                    if (code.isEmpty() || code.length() < 6) {
                        editText2.setError("Enter Code...");
                        editText2.requestFocus();
                        return;
                    }


                    verifyCode(code);
                }
            });


        }
        private void verifyCode (String code){
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithCredential(credential);
        }

        private void signInWithCredential (PhoneAuthCredential credential){
            mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Intent intent=new Intent(verify.this,profile.class);
                                startActivity(new Intent(getApplicationContext(), After.class));
                                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                // startActivity(intent);


                            } else {
                                Toast.makeText(verify.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
        private void sendVerificationcode (String number){
            ProgressBar.setVisibility(View.VISIBLE);

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    number,
                    60,
                    TimeUnit.SECONDS,
                    TaskExecutors.MAIN_THREAD,
                    mCallBack
            );
        }
        private PhoneAuthProvider.OnVerificationStateChangedCallbacks
                mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
            }


            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();
                String email = getIntent().getStringExtra("Email");
                String password = getIntent().getStringExtra("Password");

                if (code != null) {
                    editText2.setText(code);
                    verifyCode(code);

                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(verify.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(verify.this, "Error 404" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(verify.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
    }