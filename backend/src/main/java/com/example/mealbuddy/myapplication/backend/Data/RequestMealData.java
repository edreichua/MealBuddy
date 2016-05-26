package com.example.mealbuddy.myapplication.backend.Data;

/**
 * Created by edreichua on 5/25/16.
 */
public class RequestMealData {

    // ID for entity
    public static final String RM_PARENT_ENTITY_ID = "RMParent";
    public static final String RM_PARENT_KEY_ID = "RMParent";
    public static final String RM_ENTITY_ID = "ID";
    public static final String FIELD_NAME_ID = "id";

    // String constants for input in entity
    public static final String FIELD_NAME_NAME = "name";
    public static final String FIELD_NAME_CLASSYEAR = "classyear";
    public static final String FIELD_NAME_MAJOR = "major";
    public static final String FIELD_NAME_EMAIL = "email";
    public static final String FIELD_NAME_DATE = "date";
    public static final String FIELD_NAME_TIME = "time";
    public static final String FIELD_NAME_LOCATION = "location";
    public static final String FIELD_NAME_REGID = "regid";

    // Components of entity
    public String mID, mName, mClassYear, mMajor, mEmail, mDate, mTime, mLocation, mRegId;

    // Constructor for entity
    public RequestMealData(String _id, String _name, String _classyear, String _major, String _email,
                           String _date, String _time, String _location, String _regid) {

        // Initialize parameters
        mID = _id;
        mName = _name;
        mClassYear = _classyear;
        mMajor = _major;
        mEmail = _email;
        mDate = _date;
        mTime = _time;
        mLocation = _location;
        mRegId = _regid;

    }
}
