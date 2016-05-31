package com.example.mealbuddy.mealbuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by songtaeho16 on 5/18/16.
 */
public class AcceptActivity extends AppCompatActivity {
    private static long rowId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);

        Bundle bundle = this.getIntent().getExtras();

        rowId = bundle.getLong(NotificationFragment.ROWID);
        String name1 = bundle.getString(NotificationFragment.NAME1);
        String major1 = bundle.getString(NotificationFragment.MAJOR1);
        String class1 = bundle.getString(NotificationFragment.CLASS1);
        String email1 = bundle.getString(NotificationFragment.EMAIL1);
        String phone1 = bundle.getString(NotificationFragment.PHONE1);
        String dba1 = bundle.getString(NotificationFragment.DBA1);
        String name2 = bundle.getString(NotificationFragment.NAME2);
        String major2 = bundle.getString(NotificationFragment.MAJOR2);
        String class2 = bundle.getString(NotificationFragment.CLASS2);
        String email2 = bundle.getString(NotificationFragment.EMAIL2);
        String phone2 = bundle.getString(NotificationFragment.PHONE2);
        String dba2 = bundle.getString(NotificationFragment.DBA2);
        String date = bundle.getString(NotificationFragment.DATE);
        String time = bundle.getString(NotificationFragment.TIME);
        String location = bundle.getString(NotificationFragment.LOCATION);

        Log.d("phone1: ", phone1);
        Log.d("dba1: ", dba1);
        Log.d("phone2: ", phone2);
        Log.d("dba2: ", dba2);

        TextView title = (TextView)findViewById(R.id.upcomingMealText);
        TextView name1Text = (TextView)findViewById(R.id.name1);
        TextView name2Text = (TextView)findViewById(R.id.name2);
        TextView dateText = (TextView)findViewById(R.id.date);
        TextView major1Text = (TextView)findViewById(R.id.major1);
        TextView major2Text = (TextView)findViewById(R.id.major2);
        TextView class1Text = (TextView)findViewById(R.id.class1);
        TextView class2Text = (TextView)findViewById(R.id.class2);
        TextView email1Text = (TextView)findViewById(R.id.email1);
        TextView email2Text = (TextView)findViewById(R.id.email2);
        TextView phone1Text = (TextView)findViewById(R.id.phone1);
        TextView phone2Text = (TextView)findViewById(R.id.phone2);

        name1Text.setText(name1);
        name2Text.setText(name2);
        dateText.setText("Date: " + parseDate(date) + "\n" + "Time: " + time + "\n" + "Location: " + location);
        major1Text.setText(major1);
        major2Text.setText(major2);
        class1Text.setText(class1);
        class2Text.setText(class2);
        email1Text.setText(email1);
        email2Text.setText(email2);
        phone1Text.setText(phone1);
        phone2Text.setText(phone2);
    }

    public static String parseDate(String dateIn) {
        String dateOut = "";
        for (int i = 0; i<dateIn.length(); i++) {
            if (dateIn.charAt(i) == ' ') {
                dateOut += "/";
            }
            else
                dateOut += dateIn.charAt(i);
        }
        return dateOut;
    }
}
