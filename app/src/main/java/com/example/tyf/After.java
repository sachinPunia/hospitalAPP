package com.example.tyf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.tyf.CountryData.day;
import static com.example.tyf.CountryData.month;
import static com.example.tyf.CountryData.year;

public class After extends AppCompatActivity {

    Button btn5;
    private android.widget.Spinner spin,spin1,spin2,spin3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after);

        btn5 = findViewById(R.id.button4);
        spin=findViewById(R.id.spinner2);

        spin.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,CountryData.Doctor));

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DoctorType = CountryData.Doctor[spin.getSelectedItemPosition()];

                Intent inttt = new Intent(After.this, address.class);
                inttt.putExtra("DoctorType",DoctorType);

                startActivity(inttt);
            }
        });



    }

}

