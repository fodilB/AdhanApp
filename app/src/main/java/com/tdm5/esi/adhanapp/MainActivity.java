package com.tdm5.esi.adhanapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class MainActivity extends AppCompatActivity {

    private ScheduledExecutorService scheduleTaskExecutor;

    private MediaPlayer mediaPlayer;

    private TimeManager timeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        setAdhan();

        //scheduleTaskExecutor = Executors.newScheduledThreadPool(5);

        /*scheduleTaskExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mekka);

                mediaPlayer.start();
            }
        },0,3, TimeUnit.MINUTES);*/

        //startAlarm();


    }



    private void setAdhan(){

        AsyncHttpClient client = new AsyncHttpClient();

        String url="https://aladhan.p.mashape.com/timingsByCity?city=ouedsmar&country=algeria";

        client.addHeader("X-Mashape-Key","N81RoUxolUmshSYcN03hZqgzdR1Qp1lwE9MjsnkrVToc5cNAne");

        client.addHeader("Accept","application/json");

        client.get(url,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                log.i("Successsssssssssssssssss","yesssssssssssssssssssssss");

                try {
                    JSONObject data = response.getJSONObject("data");

                    JSONObject timings = data.getJSONObject("timings");

                    PrayerTimeSaver.save(getApplicationContext(), timings);

                    log.i("object",timings.toString());

                    JsonInjector.inject(timings,MainActivity.this);

                    setAlarme();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                log.i("Successsssssssssssssssss2","yesssssssssssssssssssssss");
                log.i("object",response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                log.i("Failuuuuuuuuuuuuuuuuuuuuuuuuuuuuure","khssaraaaaaaaaaaaaaaa :");
            }
        });


        setAlarme();

    }



    public void setAlarme(){
        long interval = 180000;

        timeManager = new TimeManager();

        try {
            interval = timeManager.getNextTiming(MainActivity.this);

        } catch (JSONException e) {
            e.printStackTrace();
        }

         //interval = 180000;

        Log.e("waiting time ",""+interval);

       // interval = 180000;

        Scheduler.schedule(MainActivity.this,interval);
    }
}
