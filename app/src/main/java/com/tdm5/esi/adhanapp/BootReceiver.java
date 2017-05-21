package com.tdm5.esi.adhanapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;

/**
 * Created by Douaa on 14/05/2017.
 */

public class BootReceiver extends BroadcastReceiver {

    private TimeManager timeManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        long interval = 180000;

        timeManager = new TimeManager();

        try {
            interval = timeManager.getNextTiming(context);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Interval remaining","time"+interval);

         //interval = 180000;

        Scheduler.schedule(context,interval);

    }

    }

