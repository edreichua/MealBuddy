package com.example.mealbuddy.mealbuddy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by dongjunsuh on 5/29/16.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        startActivity(context);
    }

    // start the activity
    private void startActivity(Context context) {
        Intent emaIntent = new Intent(context, MainActivity.class); //The activity you  want to start.
        Bundle extras = emaIntent.getExtras();
        extras.putBoolean("alarm", true);
        emaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(emaIntent);
    }
}
