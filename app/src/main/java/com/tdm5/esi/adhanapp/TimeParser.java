package com.tdm5.esi.adhanapp;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Douaa on 14/05/2017.
 */

public class TimeParser {

    public static Date parse(String time) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("hmmaa");

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mm");

        Date date = null;
        try {
            date = dateFormat2.parse(time);


        } catch (ParseException e) {
        }
        return date;
    }

    public static long parse2(String time) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("hmmaa");

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");


        Date date = null;
        try {
            date = dateFormat2.parse(time);


        } catch (ParseException e) {
        }

        Calendar c1 = GregorianCalendar.getInstance();

        c1.setTime(date);

        int hour =c1.get(Calendar.HOUR_OF_DAY);

        int min=c1.get(Calendar.MINUTE);

        return ((hour*3600)+60*min)*1000;
    }
}
