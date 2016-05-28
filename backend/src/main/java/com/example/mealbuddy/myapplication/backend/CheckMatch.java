package com.example.mealbuddy.myapplication.backend;

import com.example.mealbuddy.myapplication.backend.Data.*;

import org.omg.CORBA.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by edreichua on 5/26/16.
 */
public class CheckMatch {

    RequestMealData myData;
    public static final int[] PRIME = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
    ArrayList<Integer> times, location;

    public CheckMatch(RequestMealData entry){
        myData = entry;
    }


    public ArrayList<Integer> factorPrime(long composite){

        ArrayList<Integer> res = new ArrayList<>();
        for(int i: PRIME){
            if(composite % i == 0){
                res.add(i);
            }
        }
        return res;
    }

    public int getCommonTime(RequestMealData data){
        ArrayList<Integer> time2 = factorPrime(data.mTime);
        int i, j;
        i = j = 0;
        while(i<times.size() && j<time2.size()){
            if(times.get(i)==time2.get(j)){
                return times.get(i);
            }else if( times.get(i) > time2.get(j)){
                j++;
            }else{
                i++;
            }
        }
        return -1;
    }

    public int getCommonLocation(RequestMealData data){
        ArrayList<Integer> loc2 = factorPrime(data.mLocation);
        int i, j;
        i = j = 0;
        while(i<location.size() && j<loc2.size()){
            if(location.get(i)==loc2.get(j)){
                return location.get(i);
            }else if( location.get(i) > loc2.get(j)){
                j++;
            }else{
                i++;
            }
        }
        return -1;
    }

    public RequestMealData findMatch(boolean isRandom){

        isRandom = isRandom || ((myData.mPrefClassYear.equals("No preference")) &&
                (myData.mPrefMajor.equals("No preference")));

        times = factorPrime(myData.mTime);
        location = factorPrime(myData.mLocation);
        String date = myData.mDate;

        ArrayList<RequestMealData> possibleMatches1 = RequestMealDataStore.queryByDate(date);
        ArrayList<RequestMealData> possibleMatches2 = queryByTime(possibleMatches1, times);
        ArrayList<RequestMealData> possibleMatches3 = queryByLocation(possibleMatches2, location);
        ArrayList<RequestMealData> possibleMatches = checkNotMyself(possibleMatches3);

        if(possibleMatches == null || possibleMatches.isEmpty()){
            return null;
        }else if(isRandom){
            int rand = (int) (Math.random()*possibleMatches.size());
            return possibleMatches.get(rand);
        }else{
            ArrayList<RequestMealData> matches = null;
            if(myData.mPrefClassYear.equals("No preference")) {
                matches = queryByMajor(possibleMatches);
            }else if(myData.mPrefMajor.equals("No preference")) {
                matches = queryByYear(possibleMatches);
            }else{
                matches = queryByMajorAndYear(possibleMatches);
            }
            if(matches == null || matches.isEmpty())
                return null;
            int rand = (int) (Math.random() * matches.size());
            return matches.get(rand);
        }
    }
    public ArrayList<RequestMealData> queryByTime(ArrayList<RequestMealData> possibleMatches,
                                                  ArrayList<Integer> times) {
        if(possibleMatches == null ||  times == null || times.isEmpty())
            return null;

        ArrayList<RequestMealData> result = new ArrayList<>();
        for (RequestMealData entry : possibleMatches) {
            for(int time: times){
                if (entry.mTime % time == 0) {
                    result.add(entry);
                    break;
                }
            }
        }
        return result;
    }

    public ArrayList<RequestMealData> queryByLocation(ArrayList<RequestMealData> possibleMatches,
                                                  ArrayList<Integer> location) {
        if(possibleMatches == null || location == null || location.isEmpty())
            return null;

        ArrayList<RequestMealData> result = new ArrayList<>();
        for (RequestMealData entry : possibleMatches) {
            for(int loc: location){
                if (entry.mLocation % loc == 0) {
                    result.add(entry);
                    break;
                }
            }
        }
        return result;
    }

    public ArrayList<RequestMealData> queryByMajorAndYear(ArrayList<RequestMealData> possibleMatches) {
        if(possibleMatches == null)
            return null;
        String myMajor = myData.mPrefMajor;
        String myClassYear = myData.mPrefClassYear;
        ArrayList<RequestMealData> result = new ArrayList<>();
        for (RequestMealData entry : possibleMatches) {
            if (entry.mMajor.equals(myMajor) && entry.mClassYear.equals(myClassYear)) {
                if((entry.mPrefMajor.equals("No preference") || entry.mPrefMajor.equals(myData.mMajor))
                        && (entry.mPrefClassYear.equals("No preference") || entry.mPrefClassYear.equals(myData.mClassYear)) )
                result.add(entry);
            }
        }
        return result;
    }

    public ArrayList<RequestMealData> queryByMajor(ArrayList<RequestMealData> possibleMatches) {
        if(possibleMatches == null)
            return null;
        String myMajor = myData.mPrefMajor;
        ArrayList<RequestMealData> result = new ArrayList<>();
        for (RequestMealData entry : possibleMatches) {
            if (entry.mMajor.equals(myMajor) &&
                    (entry.mPrefMajor.equals(myData.mMajor) || entry.mPrefMajor.equals("No preference"))) {
                result.add(entry);
            }
        }
        return result;
    }

    public ArrayList<RequestMealData> queryByYear(ArrayList<RequestMealData> possibleMatches) {
        if(possibleMatches == null)
            return null;
        String myClassYear = myData.mPrefClassYear;
        ArrayList<RequestMealData> result = new ArrayList<>();
        for (RequestMealData entry : possibleMatches) {
            if (entry.mClassYear.equals(myClassYear) &&
                    (entry.mPrefClassYear.equals(myData.mClassYear) || entry.mPrefClassYear.equals("No preference"))) {
                result.add(entry);
            }
        }
        return result;
    }

    public ArrayList<RequestMealData> checkNotMyself(ArrayList<RequestMealData> possibleMatches){
        if(possibleMatches == null)
            return null;
        String myName = myData.mName;
        ArrayList<RequestMealData> notMyself = new ArrayList<>();
        for (RequestMealData entry : possibleMatches) {
            if (!entry.mName.equals(myName)) {
                notMyself.add(entry);
            }
        }

        return notMyself;
    }
}