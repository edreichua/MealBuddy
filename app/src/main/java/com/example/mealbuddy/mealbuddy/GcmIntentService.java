package com.example.mealbuddy.mealbuddy;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;

import com.google.android.gms.gcm.GoogleCloudMessaging;


public class GcmIntentService extends IntentService {

    public GcmIntentService() {
        super("GcmIntentService");
    }
    private NotificationManager mNotificationManager;

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
            // Make sure the message type is "gcm"
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                String str = extras.getString("message");
                Log.d("Testing1", str);
                showToast(str);
                MealNotification newNotification = new MealNotification(str);

                // get the date of the meal
                String date = newNotification.getDate();
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(4, 6));
                int day = Integer.parseInt(date.substring(6, 8));

                // get the "time" of the meal
                String time = newNotification.getTime();
                String convertedTime = newNotification.convertToTime(time);

                // set appropriate alarms for each time (10 minutes before the meal)
                if (convertedTime.equals("9Ls")) {
                    AlarmScheduler.setSchedule(getBaseContext(), year, month, day, 8, 35);
                }

                else if (convertedTime.equals("10s")) {
                    AlarmScheduler.setSchedule(getBaseContext(), year, month, day, 9, 50);
                }

                else if (convertedTime.equals("11s")) {
                    AlarmScheduler.setSchedule(getBaseContext(), year, month, day, 11, 5);
                }

                else if (convertedTime.equals("12s")) {
                    AlarmScheduler.setSchedule(getBaseContext(), year, month, day, 12, 20);
                }

                else if (convertedTime.equals("2s")) {
                    AlarmScheduler.setSchedule(getBaseContext(), year, month, day, 13, 35);
                }

                else if (convertedTime.equals("5:00-6:00pm")) {
                    AlarmScheduler.setSchedule(getBaseContext(), year, month, day, 16, 50);
                }

                else if (convertedTime.equals("6:00-7:00pm")) {
                    AlarmScheduler.setSchedule(getBaseContext(), year, month, day, 17, 50);
                }

                else if (convertedTime.equals("6:30-7:30pm")) {
                    AlarmScheduler.setSchedule(getBaseContext(), year, month, day, 18, 20);
                }

                else if (convertedTime.equals("7:00-8:00pm")) {
                    AlarmScheduler.setSchedule(getBaseContext(), year, month, day, 18, 50);
                }

                else if (convertedTime.equals("7:30=8:30pm")) {
                    AlarmScheduler.setSchedule(getBaseContext(), year, month, day, 19, 20);
                }

                NotificationDbHelpher datasource = new NotificationDbHelpher(this);
                datasource.insertNotification(newNotification);
                displayNotification(newNotification);
            }
        }

        // Finish the service intent, while keeping device awake
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
    protected void showToast(final String message) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "You've been matched!", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void displayNotification(MealNotification mealNotification) {
        Intent intent = new Intent(this, AcceptActivity.class);
        Bundle bundle = new Bundle();

        //bundle.putInt(NotificationFragment.POSITION, position);
        bundle.putLong(NotificationFragment.ROWID, mealNotification.getId());
        bundle.putString(NotificationFragment.NAME1, mealNotification.getName1());
        bundle.putString(NotificationFragment.MAJOR1, mealNotification.getMajor1());
        bundle.putString(NotificationFragment.CLASS1, mealNotification.getClass1());
        bundle.putString(NotificationFragment.EMAIL1, mealNotification.getEmail1());
        bundle.putString(NotificationFragment.PHONE1, mealNotification.getPhone1());
        bundle.putString(NotificationFragment.DBA1, mealNotification.getDba1());
        bundle.putString(NotificationFragment.NAME2, mealNotification.getName2());
        bundle.putString(NotificationFragment.MAJOR2, mealNotification.getMajor2());
        bundle.putString(NotificationFragment.CLASS2, mealNotification.getClass2());
        bundle.putString(NotificationFragment.EMAIL2, mealNotification.getEmail2());
        bundle.putString(NotificationFragment.PHONE2, mealNotification.getPhone2());
        bundle.putString(NotificationFragment.DBA2, mealNotification.getDba2());
        bundle.putString(NotificationFragment.DATE, mealNotification.getDate());
        bundle.putString(NotificationFragment.TIME, mealNotification.getTime());
        bundle.putString(NotificationFragment.LOCATION, mealNotification.getLocation());

        intent.putExtras(bundle);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(
                        getResources().getString(R.string.display_notification))
                .setSmallIcon(R.mipmap.meal_buddy_logo)
                .setAutoCancel(true)
                .setContentIntent(contentIntent).build();

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, notification);
    }

}