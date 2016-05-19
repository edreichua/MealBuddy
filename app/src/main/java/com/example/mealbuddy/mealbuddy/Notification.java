package com.example.mealbuddy.mealbuddy;

/**
 * Created by songtaeho16 on 5/19/16.
 */
public class Notification {

    //type of message eg. 0 for match, 1 for cancel, 2 for pending, 3 for warning (cancel too many times)
    private int type;
    private String date;
    private String matchName;
    private String location;

    // constructor for simple warning notification
    public Notification(int type) {
        this.type = type;
    }

    // constructor for notification with messages
    public Notification(int type, String date, String matchName, String location) {
        this.type = type;
        this.date = date;
        this.matchName = matchName;
        this.location = location;
    }

    // helper getter functions
    public int getType() {return type;}
    public String getDate() {return date;}
    public String getMatchName() {return matchName;}
    public String getLocation() {return location;}

}
