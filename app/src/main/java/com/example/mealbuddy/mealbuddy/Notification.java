package com.example.mealbuddy.mealbuddy;

/**
 * Created by songtaeho16 on 5/19/16.
 */
public class Notification {

    //type of message eg. 0 for match, 1 for cancel, 2 for pending, 3 for warning (cancel too many times)
    private long id;
    private int type;
    private String date;
    private String time;
    private String matchName;
    private String location;

    //constructor for cursorToNotification
    public Notification() {

    }

    // constructor for simple warning notification
    public Notification(int type) {
        this.type = type;
    }

    // constructor for notification with messages
    public Notification(int type, String date, String time, String matchName, String location) {
        this.type = type;
        this.date = date;
        this.time = time;
        this.matchName = matchName;
        this.location = location;
    }

    // helper getter functions
    public long getId() {return id;}
    public int getType() {return type;}
    public String getDate() {return date;}
    public String getTime() {return time;}
    public String getMatchName() {return matchName;}
    public String getLocation() {return location;}

    // helper setter functions
    public void setId(long newId) {id = newId;}
    public void setType(int newType) {type = newType;}
    public void setDate(String newDate) {date = newDate;}
    public void setTime(String newTime) {time = newTime;}
    public void setMatchName(String newMatchName) {matchName = newMatchName;}
    public void setLocation(String newLocation) {location = newLocation;}
}
