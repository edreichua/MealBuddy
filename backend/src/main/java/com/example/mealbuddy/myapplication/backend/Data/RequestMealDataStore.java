package com.example.mealbuddy.myapplication.backend.Data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;

/**
 * Created by edreichua on 5/25/16.
 */
public class RequestMealDataStore {
    // Obtain data store service as a static constant
    private static final DatastoreService mDatastore = DatastoreServiceFactory
            .getDatastoreService();

    // Retrieve key
    private static Key getKey() {
        return KeyFactory.createKey(RequestMealData.RM_PARENT_ENTITY_ID,
                RequestMealData.RM_PARENT_KEY_ID);
    }

    /**
     * Add entity to exercise data store
     * @param entry
     * @return
     */
    public static boolean add(RequestMealData entry) {

        // Get the key
        Key parentKey = getKey();

        // Create an entity
        Entity entity = new Entity(RequestMealData.RM_ENTITY_ID, entry.mID,
                parentKey);


        // Set the properties of the entity
        entity.setProperty(RequestMealData.FIELD_NAME_ID, entry.mID);
        entity.setProperty(RequestMealData.FIELD_NAME_NAME, entry.mName);
        entity.setProperty(RequestMealData.FIELD_NAME_CLASSYEAR, entry.mClassYear);
        entity.setProperty(RequestMealData.FIELD_NAME_MAJOR, entry.mMajor);
        entity.setProperty(RequestMealData.FIELD_NAME_PREF_MAJOR, entry.mPrefMajor);
        entity.setProperty(RequestMealData.FIELD_NAME_PREF_CLASSYEAR, entry.mPrefClassYear);
        entity.setProperty(RequestMealData.FIELD_NAME_EMAIL, entry.mEmail);
        entity.setProperty(RequestMealData.FIELD_NAME_DATE, entry.mDate);
        entity.setProperty(RequestMealData.FIELD_NAME_TIME, entry.mTime);
        entity.setProperty(RequestMealData.FIELD_NAME_LOCATION, entry.mLocation);
        entity.setProperty(RequestMealData.FIELD_NAME_REGID, entry.mRegId);
        entity.setProperty(RequestMealData.FIELD_NAME_PHONE, entry.mPhone);
        entity.setProperty(RequestMealData.FIELD_NAME_DBA, entry.mDba);
        entity.setProperty(RequestMealData.FIELD_NAME_STATUS, entry.mStatus);
        entity.setProperty(RequestMealData.FIELD_NAME_PREFERRED_FRIEND, entry.mFriend);


        // Add entity to datastore
        mDatastore.put(entity);

        return true;
    }

    /**
     * Delete entity from data store
     * @param id
     * @return true if deleted successfully, and false otherwise
     */
    public static boolean delete(String name, String id) {

        // Get name filter
        Query.Filter nameFilter = new Query.FilterPredicate(RequestMealData.FIELD_NAME_NAME,
                Query.FilterOperator.EQUAL, name);

        // Get id filter
        Query.Filter idFilter = new Query.FilterPredicate(RequestMealData.FIELD_NAME_ID,
                Query.FilterOperator.EQUAL, id);

        // Use CompositeFilter to combine multiple filters
        Query.CompositeFilter combinedFilter =
                Query.CompositeFilterOperator.and(nameFilter, idFilter);

        // Use class Query to assemble a query
        Query query = new Query(RequestMealData.RM_ENTITY_ID);
        query.setFilter(combinedFilter);


        // Use PreparedQuery interface to retrieve results
        PreparedQuery pq = mDatastore.prepare(query);
        Entity result = pq.asSingleEntity();

        // Delete from data store
        boolean ret = false;
        if (result != null) {
            mDatastore.delete(result.getKey());
            ret = true;
        }

        // Return true if deleted successfully, and false otherwise
        return ret;
    }

    /**
     * Query method to return an ArrayList of all the entities in the data store
     * @return
     */
    public static ArrayList<RequestMealData> query() {

        // Initialise the ArrayList to be returned
        ArrayList<RequestMealData> resultList = new ArrayList<>();

        // Get every entity from the data store
        Query query = new Query(RequestMealData.RM_ENTITY_ID);
        query.setFilter(null);
        query.setAncestor(getKey());
        PreparedQuery pq = mDatastore.prepare(query);


        // Use an iterable to retrieve all the entities from data store
        for (Entity entity : pq.asIterable()) {
            RequestMealData data = getRMFromEntity(entity);
            if (data != null) {
                resultList.add(data);
            }
        }

        // return ArrayList
        return resultList;
    }

    /**
     * Query method to return an ArrayList of all the entities in the data store
     * @return
     */
    public static ArrayList<RequestMealData> queryByDate(String date, String status) {

        // Initialise the ArrayList to be returned
        ArrayList<RequestMealData> resultList = new ArrayList<>();

        // Get every entity from the data store
        Query query = new Query(RequestMealData.RM_ENTITY_ID);

        // Prepare a filter for date
        Query.Filter dateFilter = new Query.FilterPredicate(RequestMealData.FIELD_NAME_DATE,
                Query.FilterOperator.EQUAL, date);

        // Prepare a filter for status
        Query.Filter statusFilter = new Query.FilterPredicate(RequestMealData.FIELD_NAME_STATUS,
                Query.FilterOperator.EQUAL, status);

        Query.Filter combinedFilter =
                Query.CompositeFilterOperator.and(dateFilter, statusFilter);

        query.setFilter(combinedFilter);
        query.setAncestor(getKey());
        PreparedQuery pq = mDatastore.prepare(query);


        // Use an iterable to retrieve all the entities from data store
        for (Entity entity : pq.asIterable()) {
            RequestMealData data = getRMFromEntity(entity);
            if (data != null) {
                resultList.add(data);
            }
        }

        // return ArrayList
        return resultList;
    }



    /**
     * Retrieve exercise data from entity
     * @param entity
     * @return
     */
    private static RequestMealData getRMFromEntity(Entity entity) {

        // Error checking to handle a null entity
        if (entity == null) {
            return null;
        }



        // Create new exercise data by calling the constructor
        return new RequestMealData(
                (String) entity.getProperty(RequestMealData.FIELD_NAME_ID),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_NAME),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_CLASSYEAR),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_MAJOR),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_PREF_CLASSYEAR),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_PREF_MAJOR),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_EMAIL),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_DATE),
                (Long) entity.getProperty(RequestMealData.FIELD_NAME_TIME),
                (Long) entity.getProperty(RequestMealData.FIELD_NAME_LOCATION),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_REGID),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_PHONE),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_DBA),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_STATUS),
                (String) entity.getProperty(RequestMealData.FIELD_NAME_PREFERRED_FRIEND));
    }
}
