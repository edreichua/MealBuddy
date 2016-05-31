package com.example.mealbuddy.mealbuddy;

/**
 * Created by seancann on 4/19/2016.
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RequestPrefActivity extends AppCompatActivity {

    // For location and time preferences
    CheckBox loc1,loc2,loc3,loc4,loc5,loc6;
    CheckBox time1,time2,time3,time4,time5,time6,time7,time8,time9,time10,time11;

    private Button submitButton, cancelButton;
    private Spinner classYearSpinner, fieldOfStudySpinner;

    //    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    public static MealRequest meal;

    public static final String PREFS = "Profile_Info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestpref);


        meal = new MealRequest();

        // initialize user profile
        SharedPreferences prefs = getSharedPreferences(PREFS, 0);
        meal.setmName(prefs.getString("savedName", ""));
        meal.setMyMajor(prefs.getString("savedMajor", ""));
        meal.setMyClassYear(prefs.getString("savedYear", ""));
        meal.setmEmail(prefs.getString("savedEmail", ""));


        // Get the ids of the buttons
        submitButton = (Button) findViewById(R.id.button_submit);
        cancelButton = (Button) findViewById(R.id.button_cancel);

        // Sets the font of the buttons
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Ubuntu-B.ttf");
        submitButton.setTypeface(customFont);
        cancelButton.setTypeface(customFont);


        // Get the ids of the location checkboxes
        loc1=(CheckBox)findViewById(R.id.locCheckBox1);
        loc2=(CheckBox)findViewById(R.id.locCheckBox2);
        loc3=(CheckBox)findViewById(R.id.locCheckBox3);
        loc4=(CheckBox)findViewById(R.id.locCheckBox4);
        loc5=(CheckBox)findViewById(R.id.locCheckBox5);
        loc6=(CheckBox)findViewById(R.id.locCheckBox6);

        // Get the ids of the time checkboxes
        time1=(CheckBox)findViewById(R.id.timeCheckBox1);
        time2=(CheckBox)findViewById(R.id.timeCheckBox2);
        time3=(CheckBox)findViewById(R.id.timeCheckBox3);
        time4=(CheckBox)findViewById(R.id.timeCheckBox4);
        time5=(CheckBox)findViewById(R.id.timeCheckBox5);
        time6=(CheckBox)findViewById(R.id.timeCheckBox6);
        time7=(CheckBox)findViewById(R.id.timeCheckBox7);
        time8=(CheckBox)findViewById(R.id.timeCheckBox8);
        time9=(CheckBox)findViewById(R.id.timeCheckBox9);
        time10=(CheckBox)findViewById(R.id.timeCheckBox10);
        time11=(CheckBox)findViewById(R.id.timeCheckBox11);

        // Get the ids of the spinners
        classYearSpinner=(Spinner) findViewById(R.id.spinnerClassYear);
        fieldOfStudySpinner=(Spinner) findViewById(R.id.spinnerFieldOfStudy);

        // Variables to deal with picking a day
        dateView = (TextView) findViewById(R.id.dateTextView);
        calendar = Calendar.getInstance();
        showDate(calendar);

        // Trigger activity on selecting date text
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                Log.d("Testing", "Selecting date!");
                selectManualDate(arg);
            }
        });

        // Trigger activity on selecting start button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {

                // Get the preferred class year and field of study from the spinners
                final String classYearText = classYearSpinner.getSelectedItem().toString();
                final String fieldOfStudyText = fieldOfStudySpinner.getSelectedItem().toString();

                // Get the preferred date

                int mth = calendar.get(Calendar.MONTH) + 1;
                String mth2;
                if(mth<10){
                    mth2 = "0"+mth;
                }else{
                    mth2 = ""+mth;
                }
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String day2;
                if(day<10){
                    day2 = "0"+day;
                }else{
                    day2 = ""+day;
                }


                String date = calendar.get(Calendar.YEAR)+""+mth2+""+
                        day2;
                meal.setmDate(Long.parseLong(date));

                // Set the preferred class year and field of study
                meal.setmClassYearPref(classYearText);
                meal.setmFieldOfStudyPref(fieldOfStudyText);

                // Get data from location checkboxes to set the location list
                if (loc1.isChecked())
                    meal.addmLocation(MealRequest.PRIME[0]);
                if (loc2.isChecked())
                    meal.addmLocation(MealRequest.PRIME[1]);
                if (loc3.isChecked())
                    meal.addmLocation(MealRequest.PRIME[2]);
                if (loc4.isChecked())
                    meal.addmLocation(MealRequest.PRIME[3]);
                if (loc5.isChecked())
                    meal.addmLocation(MealRequest.PRIME[4]);
                if (loc6.isChecked())
                    meal.addmLocation(MealRequest.PRIME[5]);

                // Get data from time checkboxes to set the time list
                if (time1.isChecked())
                    meal.addmTime(MealRequest.PRIME[0]);
                if (time2.isChecked())
                    meal.addmTime(MealRequest.PRIME[1]);
                if (time3.isChecked())
                    meal.addmTime(MealRequest.PRIME[2]);
                if (time4.isChecked())
                    meal.addmTime(MealRequest.PRIME[3]);
                if (time5.isChecked())
                    meal.addmTime(MealRequest.PRIME[4]);
                if (time6.isChecked())
                    meal.addmTime(MealRequest.PRIME[5]);
                if (time7.isChecked())
                    meal.addmTime(MealRequest.PRIME[6]);
                if (time8.isChecked())
                    meal.addmTime(MealRequest.PRIME[7]);
                if (time9.isChecked())
                    meal.addmTime(MealRequest.PRIME[8]);
                if (time10.isChecked())
                    meal.addmTime(MealRequest.PRIME[9]);
                if (time11.isChecked())
                    meal.addmTime(MealRequest.PRIME[10]);

                // Submit the meal request
                submit(meal);
                finish();
            }
        });

        // Trigger activity on selecting cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                finish();
            }
        });
    }

    public void submit(MealRequest meal) {
        Log.d("Testing", "Submitted meal: " + meal);
        Log.d("Testing", "Preferred class year: " + meal.getmClassYearPref());
        Log.d("Testing", "Preferred field of study: " + meal.getmFieldOfStudyPref());
        Log.d("Testing", "Meal locations: " + meal.getmLocation());
        Log.d("Testing", "Meal times: " + meal.getmTime());

        new MealUploader().execute(meal);


        Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_SHORT).show();
    }

    public void selectManualDate(View v) {

        DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                showDate(calendar);
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(RequestPrefActivity.this, mDateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.show();
    }


    private void showDate(Calendar calendar) {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        dateView.setText(intToMonth(month) + " " + day + ", " + year);
    }

    public String intToMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }
}