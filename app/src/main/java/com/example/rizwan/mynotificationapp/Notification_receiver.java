package com.example.rizwan.mynotificationapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Rizwan on 12/17/2017.
 */

public class Notification_receiver  extends BroadcastReceiver {

    NotificationManager nm;
    long pattern[] = {500, 500};
    private Uri notifsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    private NotificationCompat.BigTextStyle contentStyle;
    private List contentTexts, contentTitles;
    String className;
    public static int count;
    public static int notification_count;
    int requestcode;
    public static SharedPreferences prefs;

    @Override
    public void onReceive(Context context, Intent intent) {

        Calendar cal = Calendar.getInstance();

        int millisecond = cal.get(Calendar.MILLISECOND);
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        //12 hour format
        int hour = cal.get(Calendar.HOUR);
        //24 hour format
        int currentHour = cal.get(Calendar.HOUR_OF_DAY);


        if (currentHour >= 7 && currentHour < 22) {

            contentTexts = new ArrayList<String>();
            contentTitles = new ArrayList<String>();
            prepareContentTitles();
            prepareContentTexts();

            SharedPreferences prefs = context.getSharedPreferences("notification_count", context.MODE_PRIVATE);
            count = prefs.getInt("notification_count", 0);

            contentStyle = new android.support.v4.app.NotificationCompat.BigTextStyle();
            contentStyle.bigText((CharSequence) contentTexts.get(count));
            contentStyle.setBigContentTitle((CharSequence) contentTitles.get(count));
            contentStyle.setSummaryText("Click to see the quote");
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
//        notificationManager.cancel(100);


// to lounch new activity by clicking notification...
            switch (count) {
                case 0:
                    className = "com.example.rizwan.mynotificationapp.Repeating";
                    break;
                case 1:
                    className = "com.example.rizwan.mynotificationapp.Repeating2";
                    break;
                case 2:
                    className = "com.example.rizwan.mynotificationapp.Repeating3";
                    break;
                case 3:
                    className = "com.example.rizwan.mynotificationapp.Repeating4";
                    break;
                case 4:
                    className = "com.example.rizwan.mynotificationapp.Repeating5";
                    break;
                case 5:
                    className = "com.example.rizwan.mynotificationapp.Repeating6";
                    break;
                default:
                    break;
            }
            Intent repeating_intent = null;
            try {
                repeating_intent = new Intent(context, Class.forName(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            repeating_intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pi = PendingIntent.getActivity(context, 100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);


            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "M_CH_ID");
            notificationBuilder.setAutoCancel(true)
                    .setContentIntent(pi)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.mmskullicon)
                    .setTicker("My Note")
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle("My Notifications")
                    .setContentText("Today will be great when you remember your death")
                    .setStyle(contentStyle)
                    .setSound(notifsound)
                    .setVibrate(pattern);

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(100, notificationBuilder.build());
//        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);



            if (count == contentTexts.size() - 1) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("notification_count", 0);
                editor.commit();
            } else {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("notification_count", count + 1);
                editor.commit();
            }
        }

    }
    public void prepareContentTexts() {
        contentTexts.add("Today will be great when you remember your death............. ");
        contentTexts.add("You could leave life right now. Let that determine what you will do and say........... " );
        contentTexts.add("Live this moment while you can, for it may be your last............ " );
        contentTexts.add("Will what you're doing right now really matter?.......... " );
        contentTexts.add("You may be with these people for the very last time. Love........  " );
        contentTexts.add("Did you Live? Love? Matter?..........  " );

    }
    public void prepareContentTitles() {
        contentTitles.add("Momentom");
        contentTitles.add("Momentom");
        contentTitles.add("Momentom");
        contentTitles.add("Momentom");
        contentTitles.add("Momentom");
        contentTitles.add("Momentom");
    }

}

