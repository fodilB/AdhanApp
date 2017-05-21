package com.tdm5.esi.adhanapp;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Douaa on 14/05/2017.
 */

public class JsonInjector {

    public static void inject(JSONObject timings, Activity activity) throws JSONException {

        TextView Fadjr =(TextView) activity.findViewById(R.id.Fadjr);
        Fadjr.setText(timings.getString("Fajr"));


        TextView Sunris =(TextView) activity.findViewById(R.id.Chorouk);
        Sunris.setText(timings.getString("Sunrise"));


        TextView Dhuhre =(TextView) activity.findViewById(R.id.Duhr);
        Dhuhre.setText(timings.getString("Dhuhr"));


        TextView Asre =(TextView) activity.findViewById(R.id.Asr);
        Asre.setText(timings.getString("Asr"));


        TextView Maghribe =(TextView) activity.findViewById(R.id.Maghreb);
        Maghribe.setText(timings.getString("Maghrib"));


        TextView Ishaa =(TextView) activity.findViewById(R.id.Ishaa);
        Ishaa.setText(timings.getString("Isha"));

    }
}
