package com.example.rizwan.mynotificationapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btn_settings, btn_cancel;
    TextView txt_settings, txt_closeApp;
    public static boolean IS_APP_RUNNING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IS_APP_RUNNING = true;
        txt_settings = (TextView) findViewById(R.id.txt_settings);
        txt_closeApp = (TextView) findViewById(R.id.txt_closeApp);
        btn_cancel = (Button)findViewById(R.id.btn_cancelNotification);

        txt_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });


        txt_closeApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                System.exit(0);
            }
        });
    }

    public void cancelNotification() {

        Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.cancel(pi);


    }


    /*
    * Life Cycle
    * */

    @Override
    protected void onResume() {
        super.onResume();
        IS_APP_RUNNING = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IS_APP_RUNNING = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        IS_APP_RUNNING = false;
    }
}
