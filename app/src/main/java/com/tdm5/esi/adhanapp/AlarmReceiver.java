package com.tdm5.esi.adhanapp;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.util.Calendar;

import static com.tdm5.esi.adhanapp.R.raw.mekka;

/**
 * Created by Douaa on 14/05/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private  MediaPlayer mediaPlayer ;

    private TimeManager timeManager;

    private NotificationManager mNotificationManager;

    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Adhan", Toast.LENGTH_SHORT).show();

        mediaPlayer = MediaPlayer.create(context, R.raw.mekka);

        mediaPlayer.start();

        long interval = 180000;

        timeManager = new TimeManager();

        try {
            interval = timeManager.getNextTiming(context);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("intervale broadcast",""+interval);

        //interval = 180000;

        sendNotification(context,"It is time for praying");

        Scheduler.schedule(context,interval);

    }


    private void sendNotification(Context context,String msg) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.adhan)
                        .setContentTitle(context.getString(R.string.doodle_alert))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}


