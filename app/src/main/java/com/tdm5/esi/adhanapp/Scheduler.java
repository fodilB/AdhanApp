package com.tdm5.esi.adhanapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Douaa on 14/05/2017.
 */

public class Scheduler {


    public static void schedule(Context context  , long interval) {

        Intent alarmIntent = new Intent(context, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        AlarmManager  manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        manager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+interval, interval, pendingIntent);

        interval =interval/1000;

        int hour = (int)interval/3600;

        int mn=(int)(interval%3600)/60;

        int seconds=(int)((interval%3600)%60);



        Toast.makeText(context, "Alarm Set , time remaining : "+hour+"Hour & "+mn+" minutes & "+seconds+" seconds", Toast.LENGTH_SHORT).show();
    }

}
