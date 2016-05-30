package com.example.mealbuddy.myapplication.backend;

import com.example.mealbuddy.myapplication.backend.Data.RequestMealData;
import com.example.mealbuddy.myapplication.backend.Data.RequestMealDataStore;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by edreichua on 5/14/16.
 */
public class PostDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Standard doGet
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        // Get parameters from request
        String jObjectString = req.getParameter("result");
        String regID = req.getParameter("regId");

        // Get JSON object
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(jObjectString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Get all the meal data from JSONObject by parsing
        ArrayList<RequestMealData> result = new ArrayList<>();

        try {

             //Get parameters for meal data from JSON object
            String id = (String) jObject.get(RequestMealData.FIELD_NAME_ID);
            String name = (String) jObject.get(RequestMealData.FIELD_NAME_NAME);
            String classyear = (String) jObject.get(RequestMealData.FIELD_NAME_CLASSYEAR);
            String major = (String) jObject.get(RequestMealData.FIELD_NAME_MAJOR);
            String prefclassyear = (String) jObject.get(RequestMealData.FIELD_NAME_PREF_CLASSYEAR);
            String prefmajor = (String) jObject.get(RequestMealData.FIELD_NAME_PREF_MAJOR);
            String email = (String) jObject.get(RequestMealData.FIELD_NAME_EMAIL);
            String date = (String) jObject.get(RequestMealData.FIELD_NAME_DATE);
            long time = Long.parseLong((String) jObject.get(RequestMealData.FIELD_NAME_TIME));
            long location = Long.parseLong((String) jObject.get(RequestMealData.FIELD_NAME_LOCATION));
            String regid = (String) jObject.get(RequestMealData.FIELD_NAME_REGID);
            String status = (String) jObject.get(RequestMealData.FIELD_NAME_STATUS);
            RequestMealData entry = new RequestMealData(id, name, classyear, major, prefclassyear,
                    prefmajor, email, date, time, location, regid, status);

            CheckMatch check = new CheckMatch(entry);
            RequestMealData mealMatch = check.findMatch(false);

             // Add ExerciseData to data store
            if (mealMatch == null) {
                boolean ret = RequestMealDataStore.add(entry);

                // Add ExerciseData to result ArrayList to be passed to jsp file
                if (ret) {
                    result.add(entry);
                }
            }

            else {
                boolean ret =
                        RequestMealDataStore.delete(mealMatch.mName,mealMatch.mID);

                if (ret) {
                    MessagingEndpoint msg = new MessagingEndpoint();

                    // name, major class email name, major class email date time location
                    String str = mealMatch.mName +"/"+mealMatch.mMajor+"/"+mealMatch.mClassYear+
                            "/"+mealMatch.mEmail+"/"+entry.mName+"/"+entry.mMajor+"/"+entry.mClassYear+"/"+
                            entry.mEmail+"/"+entry.mDate+"/"+check.getCommonTime(mealMatch)+"/"+
                            check.getCommonLocation(mealMatch);
                    //msg.sendMessage(str);
                    msg.sendMessage2Match(str,entry.mRegId,mealMatch.mRegId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        // Set attribute and pass result array to jsp file for formatting
        req.setAttribute("result", result);
        getServletContext().getRequestDispatcher("/query.jsp").forward(
                req, resp);
    }

    /**
     * doPost to call doGet
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }
}