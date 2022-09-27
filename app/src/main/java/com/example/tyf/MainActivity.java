package com.example.tyf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.api.Backend;
import com.google.firebase.auth.FirebaseAuth;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
    Animation bottomAni;
    ImageView image;
    private static int SPLASH_SCREEN=3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        bottomAni= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image=findViewById(R.id.imageView14);
        image.setAnimation(bottomAni);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Secondary.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}


