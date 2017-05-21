package com.tdm5.esi.adhanapp;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Douaa on 14/05/2017.
 */

public class Notification extends IntentService{

    public static String NotificationService="Notification";
    public Notification() {
        super("Notification");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent testIntent = new Intent(this, MainActivity.class);

        String prayer = intent.getStringExtra("adhan");
        PendingIntent pTestIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(),
                testIntent, 0);
        android.app.Notification noti = new android.app.Notification.Builder(this)
                .setContentTitle("Adhan "+prayer)
                .setContentText("it is time to pray "+prayer)
                .setSmallIcon(android.R.drawable.btn_dialog)
                .setContentIntent(pTestIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);
    }


}
