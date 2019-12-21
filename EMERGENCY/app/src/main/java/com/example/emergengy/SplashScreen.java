package com.example.emergengy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (firebaseUser != null)
                {
                    startActivity(new Intent(SplashScreen.this,CategoryActivity.class));
                }
                else {
                    startActivity(new Intent(SplashScreen.this, login.class));
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
