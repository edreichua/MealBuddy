package com.example.mealbuddy.mealbuddy;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;


public class GcmIntentService extends IntentService {

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    /**
     * Handle intent from wakeful broadcast receiver
     */
    protected void onHandleIntent(Intent intent) {

        // Get the extras from the intent
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        // Retrieve the message type of the intent
        String messageType = gcm.getMessageType(intent);
        Log.d("Testing", "Intent triggered");

        // Make sure that we are receiving a message
        if (extras != null && !extras.isEmpty()) {

            // Make sure the message type is "gcm"
                String str = extras.getString("message");
                Log.d("Testing1",str);
                showToast(str);
                MainActivity.datasource.insertNotification(new Notification(str));
        }

        // Finish the service intent, while keeping device awake
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
    protected void showToast(final String message) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

}