package com.example.mealbuddy.mealbuddy;

import android.util.Log;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by songtaeho16 on 5/19/16.
 */
public class MealNotification {

    //type of message eg. 0 for match, 1 for cancel, 2 for pending, 3 for warning (cancel too many times)
    private long id;
    private String name1;
    private String major1;
    private String class1;
    private String email1;
    private String phone1;
    private String dba1;
    private String name2;
    private String major2;
    private String class2;
    private String email2;
    private String phone2;
    private String dba2;
    private String date;
    private String time;
    private String location;

    public static final int[] PRIME = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
    public static final String[] TIME = {"9Ls", "10s", "11s", "12s", "2s",
            "5:00-6:00pm", "5:30-6:30pm", "6:00-7:00pm", "6:30-7:30pm",
            "7:00-8:00pm", "7:30=8:30pm"};
    public static final String[] LOCATION = {"Collis", "KAF", "Novack", "Foco", "The Hop", "Off campus"};
    public static final Map<Integer, Integer> PRIME_INDEX;

    static {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < PRIME.length; i++) {
            map.put(PRIME[i], i);
        }

        PRIME_INDEX = Collections.unmodifiableMap(map);
    }


    //constructor for cursorToNotification
    public MealNotification() {

    }

    //constructor for handling data parsing
    public MealNotification(String informationString) {
        String[] parsed = parseInfoString(informationString);

        this.name1 = parsed[0];
        this.major1 = parsed[1];
        this.class1 = parsed[2];
        this.email1 = parsed[3];
        this.phone1 = parsed[4];
        this.dba1 = parsed[5];
        this.name2 = parsed[6];
        this.major2 = parsed[7];
        this.class2 = parsed[8];
        this.email2 = parsed[9];
        this.phone2 = parsed[10];
        this.dba2 = parsed[11];
        this.date = parsed[12];
        this.time = parsed[13];
        this.location = parsed[14];
    }

    public String[] parseInfoString(String informationString) {
        String[] parsed = informationString.split("/");

        Log.d("parsed length: ", String.valueOf(parsed.length));

        for(int i = 0; i < parsed.length; i++) {
            Log.d("parse content", parsed[i]);
        }

        parsed[12] = convertToDate(parsed[12]);
        parsed[13] = convertToTime(parsed[13]);
        parsed[14] = convertToLocation(parsed[14]);

        return parsed;
    }

    public String convertToDate(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        return month + "/" + day + "/" + year;
    }

    public String convertToTime(String time) {
        int prime = Integer.parseInt(time);
        Log.d("Time",time);
        Log.d("Prime", prime+"");
        int index = PRIME_INDEX.get(prime);

        return TIME[index];
    }

    public String convertToLocation(String location) {
        int prime = Integer.parseInt(location);
        int index = PRIME_INDEX.get(prime);

        return LOCATION[index];
    }

    // helper getter functions
    public long getId() {return id;}
    public String getName1() {return name1;}
    public String getMajor1() {return major1;}
    public String getClass1() {return class1;}
    public String getEmail1() {return email1;}
    public String getPhone1() {return phone1;}
    public String getDba1() {return dba1;}
    public String getName2() {return name2;}
    public String getMajor2() {return major2;}
    public String getClass2() {return class2;}
    public String getEmail2() {return email2;}
    public String getPhone2() {return phone2;}
    public String getDba2() {return dba2;}
    public String getDate() {return date;}
    public String getTime() {return time;}
    public String getLocation() {return location;}

    public void setId(long id) {this.id = id;}
    public void setName1(String name1) {this.name1 = name1;}
    public void setMajor1(String major1) {this.major1 = major1;}
    public void setClass1(String class1) {this.class1 = class1;}
    public void setEmail1(String email1) {this.email1 = email1;}
    public void setPhone1(String phone1) {this.phone1 = phone1;}
    public void setDba1(String dba1) {this.dba1 = dba1;}
    public void setName2(String name2) {this.name2 = name2;}
    public void setMajor2(String major2) {this.major2 = major2;}
    public void setClass2(String class2) {this.class2 = class2;}
    public void setEmail2(String email2) {this.email2 = email2;}
    public void setPhone2(String phone2) {this.phone2 = phone2;}
    public void setDba2(String dba2) {this.dba2 = dba2;}
    public void setDate(String date) {this.date = date;}
    public void setTime(String time) {this.time = time;}
    public void setLocation(String location) {this.location = location;}


    // helper setter functions
}
