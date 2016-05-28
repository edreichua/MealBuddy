package com.example.mealbuddy.myapplication.backend;

import com.example.mealbuddy.myapplication.backend.Data.RequestMealData;
import com.example.mealbuddy.myapplication.backend.Data.RequestMealDataStore;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by edreichua on 5/14/16.
 */
public class MealBuddyAppEngineServlet extends HttpServlet {

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

        // Get all the entities from data store
        ArrayList<RequestMealData> result = RequestMealDataStore.query();
        req.setAttribute("result", result);

        // use query.jsp as template to present the data
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
