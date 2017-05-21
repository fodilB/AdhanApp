package com.tdm5.esi.adhanapp;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import static com.tdm5.esi.adhanapp.TimeParser.parse2;

/**
 * Created by Douaa on 20/05/2017.
 */

public class TimeManager {

    private long adanTimes[]= new long[5];

    private long actuelTime(){

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());

        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        int mn = calendar.get(Calendar.MINUTE);

        long time = (hour*3600+mn*60)*1000;

        return time;
    }


    private long getTime(String timing){

        long time = actuelTime();

        Log.e("current",""+time);

        Log.e("origin",""+ parse2(timing));

        long diff = Math.abs(parse2(timing)- time);

        return diff;

    }

    public long getNextTiming(Context context) throws JSONException {

        JSONObject timings = PrayerTimeSaver.getValue(context);

        this.adanTimes[0]= getTime(timings.getString("Fajr"));

        Log.e("Fadjr",""+this.adanTimes[0]);

        this.adanTimes[1]= getTime(timings.getString("Dhuhr"));

        Log.e("Dhuhr",""+this.adanTimes[1]);

        this.adanTimes[2]= getTime(timings.getString("Asr"));

        Log.e("Asr",""+this.adanTimes[2]);

        this.adanTimes[3]= getTime(timings.getString("Maghrib"));

        Log.e("Maghrib",""+this.adanTimes[3]);

        this.adanTimes[4]= getTime(timings.getString("Isha"));

        Log.e("Isha",""+this.adanTimes[4]);

        Log.e("Time Remaining:",""+this.adanTimes[getMinValue(this.adanTimes)]);

        //if(getMinValue(this.adanTimes)!=4&& actuelTime()>this.adanTimes[getMinValue(this.adanTimes)])
          //     return this.adanTimes[getMinValue(this.adanTimes)+1];

        if(getMinValue(this.adanTimes)==0&&actuelTime()>parse2(timings.getString("Fajr")))
            return this.adanTimes[getMinValue(this.adanTimes)+1];

        if(getMinValue(this.adanTimes)==3&&actuelTime()>parse2(timings.getString("Maghrib")))
            return this.adanTimes[getMinValue(this.adanTimes)+1];

        if(getMinValue(this.adanTimes)==2&&actuelTime()>parse2(timings.getString("Asr")))
        return this.adanTimes[getMinValue(this.adanTimes)+1];

        if(getMinValue(this.adanTimes)==1&&actuelTime()>parse2(timings.getString("Dhuhr")))
        return this.adanTimes[getMinValue(this.adanTimes)+1];

        if(getMinValue(this.adanTimes)==4 && actuelTime()>getTime(timings.getString("Isha")))
             return (24*3600*1000-this.adanTimes[0]);

        return this.adanTimes[getMinValue(this.adanTimes)];
    }

    private   int getMinValue(long[] array) {
        long minValue = array[0];
        int min = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
                min=i;
            }
        }
        return min;
    }

}
