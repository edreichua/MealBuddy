package com.example.mealbuddy.mealbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by songtaeho16 on 5/26/16.
 */
public class NotificationDbHelpher extends SQLiteOpenHelper {
    private static final String TAG = "NOTIFICATION DB HELPER";

    public static final String TABLE_NAME_ENTRIES = "Notifications";
    public static final String KEY_ROWID = "id";
    public static final String KEY_TYPE = "type";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_MATCH_NAME = "match_name";
    public static final String KEY_LOCATION = "location";

    public static final String DATABASE_NAME = "notification.db";
    public static final int DATABASE_VERSION = 1;

    private String[] allColumns = { NotificationDbHelpher.KEY_ROWID,
            NotificationDbHelpher.KEY_TYPE,
            NotificationDbHelpher.KEY_DATE,
            NotificationDbHelpher.KEY_TIME,
            NotificationDbHelpher.KEY_MATCH_NAME,
            NotificationDbHelpher.KEY_LOCATION};

    public static final String CREATE_TABLE_ENTRIES = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME_ENTRIES
            + " ("
            + KEY_ROWID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TYPE
            + " INTEGER NOT NULL, "
            + KEY_DATE
            + " TEXT NOT NULL, "
            + KEY_TIME
            + " TEXT NOT NULL, "
            + KEY_MATCH_NAME
            + " TEXT NOT NULL, "
            + KEY_LOCATION
            + " TEXT NOT NULL, ";

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

        values.put(NotificationDbHelpher.KEY_TYPE, notification.getType());
        values.put(NotificationDbHelpher.KEY_DATE, notification.getDate());
        values.put(NotificationDbHelpher.KEY_TIME, notification.getTime());
        values.put(NotificationDbHelpher.KEY_MATCH_NAME, notification.getMatchName());
        values.put(NotificationDbHelpher.KEY_LOCATION, notification.getMatchName());

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

        Notification history = cursorToNotification(cursor);

        cursor.close();
        mDatabase.close();
        return history;
    }

    public static Notification cursorToNotification(Cursor cursor) {
        Notification notification = new Notification();
        notification.setId(cursor.getLong(0));
        notification.setType(cursor.getInt(1));
        notification.setDate(cursor.getString(2));
        notification.setTime(cursor.getString(3));
        notification.setMatchName(cursor.getString(4));
        notification.setLocation(cursor.getString(5));

        return notification;
    }
}
