package com.example.tyf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Secondary extends AppCompatActivity {
    private ImageButton imageButton4;
    private Button button , button2;
    private SignInButton signin;
    private String TAG = "MainActivity";
    public CheckBox checkBox;
    private GoogleSignInClient mGoogleSignIn;
    EditText mEmail, mPassword;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        button=findViewById(R.id.button);
        mEmail = findViewById(R.id.Email);
        signin = findViewById(R.id.btnGoogle);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar4);
        button2 = findViewById(R.id.button2);
        fAuth = FirebaseAuth.getInstance();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }
                if(password.length() < 6){
                    mPassword.setError("Password must be greater then 6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //auth

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Secondary.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),After.class));
                        }else {
                            Toast.makeText(Secondary.this, "Error 404"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Secondary.this,profile.class);
                startActivity(intent);
            }
        });
    }
}