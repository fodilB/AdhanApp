package com.tdm5.esi.adhanapp;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Douaa on 14/05/2017.
 */

public class PrayerTimeSaver {

    private static String PREFS_NAME="Mawakite_salate";

    private static String PREFS_KEY="mawakit";

    public PrayerTimeSaver() {
    }

    public static void save(Context context, JSONObject object) {

        SharedPreferences settings;

        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1

        editor = settings.edit(); //2

        editor.putString(PREFS_KEY, object.toString()); //3

        editor.commit(); //4
    }

    public void clearSharedPreference(Context context) {

        SharedPreferences settings;

        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        editor = settings.edit();

        editor.clear();

        editor.commit();
    }

    public static JSONObject getValue(Context context) {

        SharedPreferences settings;

        JSONObject text = new JSONObject();

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1

        try {

            text = new JSONObject(settings.getString(PREFS_KEY, null)); //2

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return text;
    }
}
