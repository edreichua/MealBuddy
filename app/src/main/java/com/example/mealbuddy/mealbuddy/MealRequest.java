package com.example.mealbuddy.mealbuddy;

/**
 * Created by seancann on 4/19/2016.
 */
public class MealRequest {
    public static final int[] PRIME = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};

    private Long id;
    private String mName;
    private String myClassYear;
    private String myMajor;

    private String mClassYearPref;            // Preference for class year of future friend
    private String mFieldOfStudyPref;         // Preference for field of study of future friend
    private String mEmail;
    private long mDate;                       // Day of availability
    private long mTime;                        // List of available times
    private long mLocation;                    // List of eating locations
    private String mRegId;
    private String mPhone;
    private String mDba;
    private int mStatus;

    // Constructor
    MealRequest(){
        Globals.id = (int) ((Math.random())*(Integer.MAX_VALUE));
        this.id = Globals.id;
        this.mTime = 1;
        this.mLocation = 1;
        this.mRegId = Globals.regID;
        this.mStatus = 0;
        this.mPhone = "Not Available";
        this.mDba = " ";
    }

    // Setter and Getter for phone
    public void setmPhone(String phone) {
        this.mPhone = phone;
    }
    public String getmPhone() {
        return mPhone;
    }

    // Setter and Getter for dba
    public void setmDba(String Dba) {
        this.mDba = Dba;
    }
    public String getmDba() {
        return mDba;
    }

    // Setter and Getter for regid
    public void setmRegId(String id) {
        this.mRegId = id;
    }
    public String getmRegId() {
        return mRegId;
    }

    // Setter and Getter for id
    public void setmId(long id) {
        this.id = id;
    }
    public long getmId() {
        return id;
    }

    // Setter and Getter for name
    public void setmName(String name) {
        this.mName = name;
    }
    public String getmName() {
        return mName;
    }

    // Setter and Getter for class year
    public void setMyClassYear(String myClassYear) {
        this.myClassYear = myClassYear;
    }
    public String getMyClassYear() {
        return myClassYear;
    }

    // Setter and Getter for major
    public void setMyMajor(String myMajor) {
        this.myMajor = myMajor;
    }
    public String getMyMajor() {
        return myMajor;
    }

    // Setter and Getter for email
    public void setmEmail(String Email) {
        this.mEmail = Email;
    }
    public String getmEmail() {
        return mEmail;
    }

    // Setter and Getter for status
    public void setmStatus(int status) {
        this.mStatus = status;
    }
    public int getmStatus() {
        return mStatus;
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
    public long getmDate() {
        return mDate;
    }

    // Setter and Getter for Time List
    public void addmTime(long mTime) { this.mTime *= mTime; }
    public long getmTime() { return mTime; }

    // Setter and Getter for Location List
    public void addmLocation(long mLocation) { this.mLocation *= mLocation; }
    public long getmLocation() { return mLocation; }

}
