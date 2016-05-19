package com.example.mealbuddy.mealbuddy;

/**
 * Created by seancann on 4/19/2016.
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class RequestPrefActivity extends Activity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestpref);

        meal = new MealRequest();

        // Get the ids of the buttons
        submitButton = (Button) findViewById(R.id.button_submit);
        cancelButton = (Button) findViewById(R.id.button_cancel);

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
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        // Trigger activity on selecting date text
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                Log.d("Testing", "Selecting date!");
                setDate();
            }
        });

        // Trigger activity on selecting start button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {

                // Get the preferred class year and field of study from the spinners
                final String classYearText = classYearSpinner.getSelectedItem().toString();
                final String fieldOfStudyText = fieldOfStudySpinner.getSelectedItem().toString();

                // Set the preferred class year and field of study
                meal.setmClassYearPref(classYearText);
                meal.setmFieldOfStudyPref(fieldOfStudyText);

                // Get data from location checkboxes to set the location list
                if (loc1.isChecked())
                    meal.addmLocationList(1);
                if (loc2.isChecked())
                    meal.addmLocationList(2);
                if (loc3.isChecked())
                    meal.addmLocationList(3);
                if (loc4.isChecked())
                    meal.addmLocationList(4);
                if (loc5.isChecked())
                    meal.addmLocationList(5);
                if (loc6.isChecked())
                    meal.addmLocationList(6);

                // Get data from time checkboxes to set the time list
                if (time1.isChecked())
                    meal.addmTimeList(1);
                if (time2.isChecked())
                    meal.addmTimeList(2);
                if (time3.isChecked())
                    meal.addmTimeList(3);
                if (time4.isChecked())
                    meal.addmTimeList(4);
                if (time5.isChecked())
                    meal.addmTimeList(5);
                if (time6.isChecked())
                    meal.addmTimeList(6);
                if (time7.isChecked())
                    meal.addmTimeList(7);
                if (time8.isChecked())
                    meal.addmTimeList(8);
                if (time9.isChecked())
                    meal.addmTimeList(9);
                if (time10.isChecked())
                    meal.addmTimeList(10);
                if (time11.isChecked())
                    meal.addmTimeList(11);

                // Submit the meal request
                submit(meal);
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
        Log.d("Testing", "Meal locations: " + meal.getmLocationList());
        Log.d("Testing", "Meal times: " + meal.getmTimeList());
        Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("deprecation")
    public void setDate() {
        showDialog(999);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {
            showDate(year, month+1, day);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(intToMonth(month) + " " + day + ", " + year);
    }

    public String intToMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }
}