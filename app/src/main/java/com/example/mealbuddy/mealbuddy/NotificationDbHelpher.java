package com.example.mealbuddy.mealbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by songtaeho16 on 5/26/16.
 */
public class NotificationDbHelpher extends SQLiteOpenHelper {
    private static final String TAG = "NOTIFICATION DB HELPER";

    public static final String TABLE_NAME_ENTRIES = "Notifications";
    public static final String KEY_ROWID = "id";
    public static final String KEY_NAME1 = "name1";
    public static final String KEY_MAJOR1 = "major1";
    public static final String KEY_CLASS1 = "class1";
    public static final String KEY_EMAIL1 = "email1";
    public static final String KEY_NAME2 = "name2";
    public static final String KEY_MAJOR2 = "major2";
    public static final String KEY_CLASS2 = "class2";
    public static final String KEY_EMAIL2 = "email2";
    public static final String KEY_DATE = "data";
    public static final String KEY_TIME = "time";
    public static final String KEY_LOCATION = "location";


    public static final String DATABASE_NAME = "notification.db";
    public static final int DATABASE_VERSION = 1;

    public static final int[] PRIME = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};

    private String[] allColumns = { NotificationDbHelpher.KEY_ROWID,
            NotificationDbHelpher.KEY_NAME1,
            NotificationDbHelpher.KEY_MAJOR1,
            NotificationDbHelpher.KEY_CLASS1,
            NotificationDbHelpher.KEY_EMAIL1,
            NotificationDbHelpher.KEY_NAME2,
            NotificationDbHelpher.KEY_MAJOR2,
            NotificationDbHelpher.KEY_CLASS2,
            NotificationDbHelpher.KEY_EMAIL2,
            NotificationDbHelpher.KEY_DATE,
            NotificationDbHelpher.KEY_TIME,
            NotificationDbHelpher.KEY_LOCATION};

    public static final String CREATE_TABLE_ENTRIES = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME_ENTRIES
            + " ("
            + KEY_ROWID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME1
            + " TEXT NOT NULL, "
            + KEY_MAJOR1
            + " TEXT NOT NULL, "
            + KEY_CLASS1
            + " TEXT NOT NULL, "
            + KEY_EMAIL1
            + " TEXT NOT NULL, "
            + KEY_NAME2
            + " TEXT NOT NULL, "
            + KEY_MAJOR2
            + " TEXT NOT NULL, "
            + KEY_CLASS2
            + " TEXT NOT NULL, "
            + KEY_EMAIL2
            + " TEXT NOT NULL, "
            + KEY_DATE
            + " TEXT NOT NULL, "
            + KEY_TIME
            + " TEXT NOT NULL, "
            + KEY_LOCATION
            + " TEXT NOT NULL "
            + ");";;

    public NotificationDbHelpher(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase database) {database.execSQL((CREATE_TABLE_ENTRIES));}

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        Log.w(NotificationDbHelpher.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS ");
        onCreate(database);
    }

    public long insertNotification(MealNotification mealNotification) {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NotificationDbHelpher.KEY_NAME1, mealNotification.getName1());
        values.put(NotificationDbHelpher.KEY_MAJOR1, mealNotification.getMajor1());
        values.put(NotificationDbHelpher.KEY_CLASS1, mealNotification.getClass1());
        values.put(NotificationDbHelpher.KEY_EMAIL1, mealNotification.getEmail1());
        values.put(NotificationDbHelpher.KEY_NAME2, mealNotification.getName2());
        values.put(NotificationDbHelpher.KEY_MAJOR2, mealNotification.getMajor2());
        values.put(NotificationDbHelpher.KEY_CLASS2, mealNotification.getClass2());
        values.put(NotificationDbHelpher.KEY_EMAIL2, mealNotification.getEmail2());
        values.put(NotificationDbHelpher.KEY_DATE, mealNotification.getDate());
        values.put(NotificationDbHelpher.KEY_TIME, mealNotification.getTime());
        values.put(NotificationDbHelpher.KEY_LOCATION, mealNotification.getLocation());

        long insertId = mDatabase.insert(NotificationDbHelpher.TABLE_NAME_ENTRIES, null, values);
        mDatabase.close();

        return insertId;
    }

    //remove entry give by rowIndex
    public void removeEntry(long rowIndex) {
        Log.d(TAG, "removing index: " + String.valueOf(rowIndex));
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        mDatabase.delete(NotificationDbHelpher.TABLE_NAME_ENTRIES, NotificationDbHelpher.KEY_ROWID
                + " = " + rowIndex, null);

        mDatabase.close();
    }

    // queries database for specific index and returns that exercise entry
    public MealNotification fetchEntryByIndex(long rowId) {
        SQLiteDatabase mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.query(NotificationDbHelpher.TABLE_NAME_ENTRIES,
                allColumns, KEY_ROWID + "=" + rowId, null, null, null, null, null);

        MealNotification mealNotification = cursorToNotification(cursor);

        cursor.close();
        mDatabase.close();
        return mealNotification;
    }

    // returns an arraylist of all the entries
    public ArrayList<MealNotification> fetchEntries() {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<MealNotification> histories = new ArrayList<MealNotification>();

        Cursor cursor = mDatabase.query(NotificationDbHelpher.TABLE_NAME_ENTRIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            MealNotification history = cursorToNotification(cursor);
            histories.add(history);
            cursor.moveToNext();
        }

        //close cursor
        cursor.close();
        mDatabase.close();
        return histories;
    }

    public static MealNotification cursorToNotification(Cursor cursor) {
        MealNotification mealNotification = new MealNotification();
        mealNotification.setId(cursor.getLong(0));
        mealNotification.setName1(cursor.getString(1));
        mealNotification.setMajor1(cursor.getString(2));
        mealNotification.setClass1(cursor.getString(3));
        mealNotification.setEmail1(cursor.getString(4));
        mealNotification.setName2(cursor.getString(5));
        mealNotification.setMajor2(cursor.getString(6));
        mealNotification.setClass2(cursor.getString(7));
        mealNotification.setEmail2(cursor.getString(8));
        mealNotification.setDate(cursor.getString(9));
        mealNotification.setTime(cursor.getString(10));
        mealNotification.setLocation(cursor.getString(11));

        return mealNotification;
    }
}
