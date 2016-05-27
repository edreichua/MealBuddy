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

    public long insertNotification(Notification notification) {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NotificationDbHelpher.KEY_NAME1, notification.getName1());
        values.put(NotificationDbHelpher.KEY_MAJOR1, notification.getMajor1());
        values.put(NotificationDbHelpher.KEY_CLASS1, notification.getClass1());
        values.put(NotificationDbHelpher.KEY_EMAIL1, notification.getEmail1());
        values.put(NotificationDbHelpher.KEY_NAME2, notification.getName2());
        values.put(NotificationDbHelpher.KEY_MAJOR2, notification.getMajor2());
        values.put(NotificationDbHelpher.KEY_CLASS2, notification.getClass2());
        values.put(NotificationDbHelpher.KEY_EMAIL2, notification.getEmail2());
        values.put(NotificationDbHelpher.KEY_DATE, notification.getDate());
        values.put(NotificationDbHelpher.KEY_TIME, notification.getTime());
        values.put(NotificationDbHelpher.KEY_LOCATION, notification.getLocation());

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
    public Notification fetchEntryByIndex(long rowId) {
        SQLiteDatabase mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.query(NotificationDbHelpher.TABLE_NAME_ENTRIES,
                allColumns, KEY_ROWID + "=" + rowId, null, null, null, null, null);

        Notification notification = cursorToNotification(cursor);

        cursor.close();
        mDatabase.close();
        return notification;
    }

    // returns an arraylist of all the entries
    public ArrayList<Notification> fetchEntries() {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<Notification> histories = new ArrayList<Notification>();

        Cursor cursor = mDatabase.query(NotificationDbHelpher.TABLE_NAME_ENTRIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Notification history = cursorToNotification(cursor);
            histories.add(history);
            cursor.moveToNext();
        }

        //close cursor
        cursor.close();
        mDatabase.close();
        return histories;
    }

    public static Notification cursorToNotification(Cursor cursor) {
        Notification notification = new Notification();
        notification.setId(cursor.getLong(0));
        notification.setName1(cursor.getString(1));
        notification.setMajor1(cursor.getString(2));
        notification.setClass1(cursor.getString(3));
        notification.setEmail1(cursor.getString(4));
        notification.setName2(cursor.getString(5));
        notification.setMajor2(cursor.getString(6));
        notification.setClass2(cursor.getString(7));
        notification.setEmail2(cursor.getString(8));
        notification.setDate(cursor.getString(9));
        notification.setTime(cursor.getString(10));
        notification.setLocation(cursor.getString(11));

        return notification;
    }
}
