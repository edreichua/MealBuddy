package com.example.mealbuddy.mealbuddy;

import java.util.ArrayList;

/**
 * Created by seancann on 4/19/2016.
 */
public class MealRequest {
    private Long id;
    private String mClassYearPref;    // Preference for class year of future friend
    private String mFieldOfStudyPref; // Preference for field of study of future friend
    private long mDate;               // Day of availability
    private ArrayList<Integer> mTimeList;     // List of available times
    private ArrayList<Integer> mLocationList; // List of eating locations

    // Constructor
    MealRequest(){
        mLocationList = new ArrayList<>();
        mTimeList = new ArrayList<>();
    }
    
    // Setter and Getter for id
    public void setmId(long id) {
        this.id = id;
    }
    public long getmId() {
        return id;
    }

    // Setter and Getter for InputType
    public void setmClassYearPref(String mClassYearPref) {
        this.mClassYearPref = mClassYearPref;
    }
    public String getmClassYearPref() {
        return mClassYearPref;
    }

    // Setter and Getter for ActivityType
    public void setmFieldOfStudyPref(String mFieldOfStudyPref) {
        this.mFieldOfStudyPref = mFieldOfStudyPref;
    }
    public String getmFieldOfStudyPref() {
        return mFieldOfStudyPref;
    }

    // Setter and Getter for DateTime
    public void setmDate(long mDate) {
        this.mDate = mDate;
    }
    public long getmDateTime() {
        return mDate;
    }

    // Setter and Getter for Time List
    public void setmTimeList(ArrayList<Integer> mTimeList) { this.mTimeList = mTimeList; }
    public void addmTimeList(Integer mTime) { mTimeList.add(mTime); }
    public ArrayList<Integer> getmTimeList() { return mTimeList; }

    // Setter and Getter for Location List
    public void setmLocationList(ArrayList<Integer> mLocationList) { this.mLocationList = mLocationList; }
    public void addmLocationList(Integer mLocation) { mLocationList.add(mLocation); }
    public ArrayList<Integer> getmLocationList() { return mLocationList; }

}
