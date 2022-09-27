package com.example.tyf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class profile extends AppCompatActivity {
    EditText mFullName,mEmail,mPassword,mPhone;
    Button   mReg;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView mLoginBtn, Ct;
    private EditText editText;
    private android.widget.Spinner Spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mReg = findViewById(R.id.button7);
        Spinner=findViewById(R.id.spinner);
        Ct = findViewById(R.id.createText);
        Spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,CountryData.countryNames));
        editText=(EditText)findViewById(R.id.editText);

        mReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.countryAreaCodes[Spinner.getSelectedItemPosition()];
                String number = editText.getText().toString().trim();

                if (number.isEmpty() || number.length()<10 ){
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }
                String phoneNumber = "+" + code + number;
                Intent intent=new Intent(profile.this,verify.class);
                intent.putExtra("phonenumber",phoneNumber);
                startActivity(intent);
            }
        });
        Ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Secondary.class));
            }
        });


        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mLoginBtn = findViewById(R.id.createText);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);
       // if(fAuth.getCurrentUser() != null){
         //   startActivity(new Intent(getApplicationContext(),MainActivity.class));
           // finish();
        //}

        mReg.setOnClickListener(new View.OnClickListener() {
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


                String code = CountryData.countryAreaCodes[Spinner.getSelectedItemPosition()];
                String number = editText.getText().toString().trim();
                if (number.isEmpty() || number.length()<10 ){
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }

                String phoneNumber = "+" + code + number;
                progressBar.setVisibility(View.VISIBLE);
                Intent intent=new Intent(profile.this,verify.class);
                intent.putExtra("phonenumber",phoneNumber);
                intent.putExtra("Email",email);
                intent.putExtra("Password",password);
                startActivity(intent);

                //Register

          //      fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    //@Override
                  //  public void onComplete(@NonNull Task<AuthResult> task) {
            //           if(task.isSuccessful()){
              //             Toast.makeText(profile.this, "Please Wait", Toast.LENGTH_SHORT).show();
                //       }else {
                  //         Toast.makeText(profile.this, "Error 404"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    //       progressBar.setVisibility(View.GONE);
                      // }
                //    }
                //});
            }
        });
       // mLoginBtn.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
           //     startActivity(new Intent(getApplicationContext(),verify.class));
        //    }
     //   });
    }
}
