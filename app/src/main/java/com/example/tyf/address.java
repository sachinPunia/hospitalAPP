package com.example.tyf;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.tyf.CountryData.Time;
import static com.example.tyf.CountryData.day;

public class address extends AppCompatActivity {
    EditText Name,Bldg,Landmark,Pincode,City,Date;
    Button btn;
    TextView tv1;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Bitmap bmp, scaledbmp ;
    int pageWidth = 1200;

    private android.widget.Spinner spin4,spin1,spin2,spin3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_address);
        Name = findViewById(R.id.et1);
        Bldg = findViewById(R.id.et2);
        tv1 = findViewById(R.id.tv1);
        Landmark = findViewById(R.id.et3);
        Pincode = findViewById(R.id.et4);
        City = findViewById(R.id.et5);
        btn = findViewById(R.id.but);
        Date = findViewById(R.id.ett3);
       // spin1=findViewById(R.id.spinner3);
      //  spin2=findViewById(R.id.spinner4);
      //  spin3=findViewById(R.id.spinner5);
        spin4=findViewById(R.id.spinner6);
        String doc = getIntent().getStringExtra("DoctorType");
        //String day = getIntent().getStringExtra("day");
      //  String month = getIntent().getStringExtra("month");
      //  String year = getIntent().getStringExtra("year");

       // spin1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, day));
        //spin2.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,CountryData.month));
       // spin3.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,CountryData.year));
        spin4.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,CountryData.Time));
        String Timee = spin4.getSelectedItem().toString();

        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.docapo);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 1200, 510, false);




        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createPDF();


        tv1.setText( doc );
     //   tv2.setText( day );
       // tv3.setText( month );
      //  tv4.setText( year );

    }

    private void createPDF() {

        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("member");
                String name = Name.getEditableText().toString();
                String bldg = Bldg.getEditableText().toString();
                String doctor = getIntent().getStringExtra("DoctorType");
                String phone = Landmark.getEditableText().toString();
                String pincode = Pincode.getEditableText().toString();
                String city = City.getEditableText().toString();
                String date = Date.getEditableText().toString();
                //   String day = CountryData.day[spin1.getSelectedItemPosition()];
                //   String month = CountryData.month[spin2.getSelectedItemPosition()];
                //   String year = CountryData.year[spin3.getSelectedItemPosition()];
                String time = CountryData.Time[spin4.getSelectedItemPosition()];


                UserHelperClass helperClass = new UserHelperClass(name,bldg,phone,pincode,city,time,doctor,date);
                reference.child(name).setValue(helperClass);
                Intent intent = new Intent(address.this,After.class);
                Toast.makeText(address.this,"Appointment Booked",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                if (Name.getText().toString().length() == 0 ||
                        Landmark.getText().toString().length() == 0 ||
                        Date.getText().toString().length() == 0 ||
                        spin4.getSelectedItem().toString().length() == 0 ||
                        City.getText().toString().length() == -0) {
                    Toast.makeText(address.this,"Some Fields Are Empty", Toast.LENGTH_LONG).show();
                }
                else {


                    PdfDocument mypdfDocument = new PdfDocument();
                    Paint myPaint = new Paint();
                    Paint titlePaint = new Paint();


                    PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
                    PdfDocument.Page myPage1 = mypdfDocument.startPage(myPageInfo1);
                    Canvas canvas = myPage1.getCanvas();

                    canvas.drawBitmap(scaledbmp,0,0, myPaint);

                    //CenterAppoi

                    titlePaint.setTextAlign(Paint.Align.CENTER);
                    titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                    titlePaint.setTextSize(70);
                    canvas.drawText("Your Appoinment",pageWidth/2,650,titlePaint);

                    //rightside  call

                    myPaint.setColor(Color.rgb(0,113,188));
                    myPaint.setTextSize(30f);
                    myPaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText("Call:-022-61578300",1160,40,myPaint);
                    canvas.drawText("022-61578301",1160,80,myPaint);

                    //centerDet

                    titlePaint.setTextAlign(Paint.Align.CENTER);
                    titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.ITALIC));
                    titlePaint.setTextSize(60);
                    canvas.drawText("Prescription",315,1350,myPaint);
                    canvas.drawText("Doctor Sign",1000,1900,myPaint);
                    canvas.drawText("Details",pageWidth/2,750,titlePaint);

                    //NameDateTimeFrom


                    myPaint.setTextAlign(Paint.Align.LEFT);
                    myPaint.setTextSize(35f);
                    myPaint.setColor(Color.BLACK);
                    canvas.drawText("Name : "+Name.getText(),30,850,myPaint);
                    canvas.drawText("Phone No : "+Landmark.getText(),30,950,myPaint);
                    canvas.drawText("Date : "+Date.getText(),30,1050,myPaint);
                    canvas.drawText("Time : "+spin4.getSelectedItem().toString(),30,1150,myPaint);
                    canvas.drawText("Address : "+City.getText(),30,1250,myPaint);
                    canvas.drawText("Type :"+tv1.getText(),30,750,myPaint);




                    myPaint.setColor(Color.BLACK);
                    canvas.drawLine(30,800,700,800,myPaint);
                    canvas.drawLine(30,900,700,900,myPaint);
                    canvas.drawLine(30,1000,700,1000,myPaint);
                    canvas.drawLine(30,1100,700,1100,myPaint);
                    canvas.drawLine(30,1200,700,1200,myPaint);
                    canvas.drawLine(30,1300,1500,1300,myPaint);
                    canvas.drawLine(700,800,700,2000,myPaint);



                    mypdfDocument.finishPage(myPage1);

                    File file = new File(Environment.getExternalStorageDirectory(), "Appiontment.pdf");

                    try {
                        mypdfDocument.writeTo(new FileOutputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mypdfDocument.close();
                }

            }
        });

    }

}