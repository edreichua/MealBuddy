package com.example.mealbuddy.mealbuddy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;


public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Testing", "Awake");

        // Let the GcmIntentService handle the intent
        ComponentName comp = new ComponentName(context.getPackageName(),
                GcmIntentService.class.getName());

        // Trigger the service and keep the device awake
        startWakefulService(context, (intent.setComponent(comp)));

        // Indicate that the operation was successful
        setResultCode(Activity.RESULT_OK);
    }
}