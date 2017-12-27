package com.example.rizwan.mynotificationapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {


            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
              startActivity(new Intent(splash.this, MainActivity.class));
              finish();

            }
        };

        countDownTimer.start();
    }
}
