package com.example.rizwan.mynotificationapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    TimePicker timePicker;
    TextView txt_support;
    CheckBox cb_default, cb_user_defined;
    Button btn_save;
    SharedPreferences preferences;
    Notification_receiver nr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

            cb_default = findViewById(R.id.cb_default);
            cb_user_defined = findViewById(R.id.cb_timePicker);
            txt_support = (TextView) findViewById(R.id.txt_support);
            timePicker = (TimePicker)findViewById(R.id.timePicker);
            btn_save = findViewById(R.id.btn_save);

            txt_support.setOnClickListener(this);
            btn_save.setOnClickListener(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();

                if(preferences.contains("checked") && preferences.getBoolean("checked",false) == true) {
                    cb_default.setChecked(true);

                }else {
                    cb_default.setChecked(false);

                }
                if(preferences.contains("checked1") && preferences.getBoolean("checked1",false) == true) {
                    cb_user_defined.setChecked(true);
                }else {
                    cb_user_defined.setChecked(false);

                }

        cb_default.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(cb_default.isChecked()){
                    cb_default.setChecked(true);
                    cb_user_defined.setChecked(false);

                }else{
                    cb_default.setChecked(false);
                    cb_user_defined.setChecked(true);
                }
            }
        });
        cb_user_defined.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cb_user_defined.isChecked()){
                    cb_user_defined.setChecked(true);
                    cb_default.setChecked(false);

                }else{
                    cb_user_defined.setChecked(false);
                    cb_default.setChecked(true);

                }
            }
        });

    }

    public void initDefaultModule(){

        long time;
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 7);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND,0);

// get a Calendar at the current time
        Calendar now = Calendar.getInstance();

        if (now.before(startTime)) {
            // it's not 7:00am yet, start today
            time = startTime.getTimeInMillis();
        } else {
            // start 7:00am tomorrow
            startTime.add(Calendar.DATE, 1);
            time = startTime.getTimeInMillis();
        }

// set the alarm
        Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

            am.setRepeating(AlarmManager.RTC_WAKEUP, time, 3*60*60*1000, pi);  //set repeating every 3 hours


//                Toast.makeText(this, "Start Alarm", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.txt_support){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://www.google.com"));
            startActivity(intent);


        }else if(view.getId()== R.id.btn_save) {

            if (cb_default.isChecked()) {
//                nr.notification_count = 0;
                writePrefsCount(); // write 0 to shared prefs as well.
                initDefaultModule();


                if (cb_default.isChecked()) {
                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("checked", true);
                    editor.putBoolean("checked1", false);
                    editor.apply();
                } else {
                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("checked", false);
                    editor.putBoolean("checked1", true);
                    editor.apply();
                }
                backMainActivity();
            } else {
                writePrefsCount(); // write 0 to shared prefs as well.
                initUserDefinedModule();
                if (cb_user_defined.isChecked()) {
                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("checked1", true);
                    editor.putBoolean("checked", false);

                    editor.apply();
                } else {
                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("checked1", false);
                    editor.putBoolean("checked", true);

                    editor.apply();

                }
                backMainActivity();

            }

        }
    }

    public void  initUserDefinedModule(){

        timePicker.getCurrentHour();
        timePicker.getCurrentMinute();
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        long time;
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, hour);
        startTime.set(Calendar.MINUTE, minute);
        startTime.set(Calendar.SECOND,0);

// get a Calendar at the current time
        Calendar now = Calendar.getInstance();

        if (now.before(startTime)) {
            // it's not 14:00 yet, start today
            time = startTime.getTimeInMillis();
        } else {
            // start 14:00 tomorrow
            startTime.add(Calendar.DATE, 1);
            time = startTime.getTimeInMillis();
        }

        Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        am.setRepeating(AlarmManager.RTC_WAKEUP, time, 3*60*60*1000, pi);  //set repeating every 3 hours
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
    public void backMainActivity(){
        finish();
    }


    private void writePrefsCount(){
        nr.prefs = getSharedPreferences("notification_count", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = nr.prefs.edit();
        editor.putInt("notification_count", 0);
        editor.apply();
    }


}
