package com.example.mealbuddy.mealbuddy;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by edreichua on 5/26/16.
 */
public class MealUploader extends AsyncTask<Object, Void, String> {

    @Override
    protected String doInBackground(Object... arg) {

        // Create a json object for each entry
        JSONObject jObject = new JSONObject();

        MealRequest meal = (MealRequest) arg[0];

        // Insert components of entries into json object
        try {
            jObject.put(Globals.FIELD_NAME_ID,
                    Long.toString(meal.getmId()));
            jObject.put(Globals.FIELD_NAME_NAME,
                    meal.getmName());
            jObject.put(Globals.FIELD_NAME_CLASSYEAR,
                    meal.getMyClassYear());
            jObject.put(Globals.FIELD_NAME_MAJOR,
                    meal.getMyMajor());
            jObject.put(Globals.FIELD_NAME_PREF_CLASSYEAR,
                    meal.getmClassYearPref());
            jObject.put(Globals.FIELD_NAME_PREF_MAJOR,
                    meal.getmFieldOfStudyPref());
            jObject.put(Globals.FIELD_NAME_EMAIL,
                    meal.getmEmail());
            jObject.put(Globals.FIELD_NAME_DATE,
                    Long.toString(meal.getmDate()));
            jObject.put(Globals.FIELD_NAME_TIME,
                    Long.toString(meal.getmTime()));
            jObject.put(Globals.FIELD_NAME_LOCATION,
                    Long.toString(meal.getmLocation()));
            jObject.put(Globals.FIELD_NAME_PHONE,
                    meal.getmPhone());
            jObject.put(Globals.FIELD_NAME_DBA,
                    meal.getmDba());
            jObject.put(Globals.FIELD_NAME_REGID,
                    meal.getmRegId());
            jObject.put(Globals.FIELD_NAME_STATUS,
                    Integer.toString(meal.getmStatus()));

            long time = Long.parseLong((String) jObject.get(Globals.FIELD_NAME_TIME));
            long location = Long.parseLong((String) jObject.get(Globals.FIELD_NAME_LOCATION));
            Log.d("Time and Location", Long.toString(time) + Long.toString(location));
            Log.d("Test", "Printing Correctly");

            // Save parameters to map
            Map<String,String> params = new HashMap<>();
            params.put("result",jObject.toString());
            params.put("regId", Globals.regID);

            // Call server utilities
            ServerUtilities.post(Globals.URL + "/PostData.do", params);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}