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
    public static final String FIELD_NAME_PREF_CLASSYEAR = "pref_classyear";
    public static final String FIELD_NAME_PREF_MAJOR = "pref_major";
    public static final String FIELD_NAME_EMAIL = "email";
    public static final String FIELD_NAME_DATE = "date";
    public static final String FIELD_NAME_TIME = "time";
    public static final String FIELD_NAME_LOCATION = "location";
    public static final String FIELD_NAME_REGID = "regid";
    public static final String FIELD_NAME_PHONE = "phone";
    public static final String FIELD_NAME_DBA= "dba";
    public static final String FIELD_NAME_STATUS = "status";



    // Components of entity
    public String mID, mName, mClassYear, mMajor, mPrefClassYear, mPrefMajor,
            mEmail, mDate, mRegId, mPhone, mDba, mStatus;
    public long mTime, mLocation;

    // Constructor for entity
    public RequestMealData(String _id, String _name, String _classyear, String _major, String _prefclassyear,
                           String _prefmajor, String _email, String _date, long _time, long _location,
                           String _regid, String _phone, String _dba, String _status) {

        // Initialize parameters
        mID = _id;
        mName = _name;
        mClassYear = _classyear;
        mMajor = _major;
        mPrefClassYear = _prefclassyear;
        mPrefMajor = _prefmajor;
        mEmail = _email;
        mDate = _date;
        mTime = _time;
        mLocation = _location;
        mRegId = _regid;
        mPhone = _phone;
        mDba = _dba;
        mStatus = _status;

    }
}
