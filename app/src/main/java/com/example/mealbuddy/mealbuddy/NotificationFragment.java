package com.example.mealbuddy.mealbuddy;

import android.app.ListFragment;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by songtaeho16 on 5/18/16.
 */
public class NotificationFragment extends ListFragment {

    private static final String[] NOTIFICATIONS = new String[]  {"You've been matched for Monday 1pm at foco!",
            "You've been matched for Wednesday 5pm at collis", "You've been matched for Friday 2pm at the hop!",
            "You've been matched for Saturday 7pm at the hop!"};

    public static final String ACCEPTACTIVITY = ".AcceptActivity";
    public static final String POSITION = "position";
    public static final String ROWID = "rowid";
    public static final String NAME1 = "name1";
    public static final String MAJOR1 = "major1";
    public static final String CLASS1 = "class1";
    public static final String EMAIL1 = "email1";
    public static final String PHONE1 = "phone1";
    public static final String DBA1 = "dba1";
    public static final String NAME2 = "name2";
    public static final String MAJOR2 = "major2";
    public static final String CLASS2 = "class2";
    public static final String EMAIL2 = "email2";
    public static final String PHONE2 = "phone2";
    public static final String DBA2 = "dba2";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String LOCATION = "location";

    public static List<MealNotification> values;

    public static NotificationAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("test", "test");
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        Log.d("test", "test2");

        NotificationLoader loader = new NotificationLoader(getActivity());

        com.example.mealbuddy.mealbuddy.CustomTextViewBold titleWords = (com.example.mealbuddy.mealbuddy.CustomTextViewBold) getActivity().findViewById(R.id.titleWords);
        titleWords.setText("Notifications");

        //List of Notifications
        values = loader.loadInBackground();
        Collections.reverse(values);
        adapter = new NotificationAdapter(getActivity(), values);

        setListAdapter(adapter);

        //ListView notificationList = (ListView)view.findViewById(R.id.notificationListView);

        return view;
    }

    public void onListItemClick(ListView parent, View view, int position, long id) {
        super.onListItemClick(parent, view, position, id);
        MealNotification mealNotification = getNotificationByPosition(position);

        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        bundle.putInt(POSITION, position);
        bundle.putLong(ROWID, mealNotification.getId());
        bundle.putString(NAME1, mealNotification.getName1());
        bundle.putString(MAJOR1, mealNotification.getMajor1());
        bundle.putString(CLASS1, mealNotification.getClass1());
        bundle.putString(EMAIL1, mealNotification.getEmail1());
        bundle.putString(PHONE1, mealNotification.getPhone1());
        bundle.putString(DBA1, mealNotification.getDba1());
        bundle.putString(NAME2, mealNotification.getName2());
        bundle.putString(MAJOR2, mealNotification.getMajor2());
        bundle.putString(CLASS2, mealNotification.getClass2());
        bundle.putString(EMAIL2, mealNotification.getEmail2());
        bundle.putString(PHONE2, mealNotification.getPhone2());
        bundle.putString(DBA2, mealNotification.getDba2());
        bundle.putString(DATE, mealNotification.getDate());
        bundle.putString(TIME, mealNotification.getTime());
        bundle.putString(LOCATION, mealNotification.getLocation());

        intent.putExtras(bundle);
        intent.setAction(ACCEPTACTIVITY);

        startActivity(intent);
    }

    // use asynctaskloader to read in data and get the list of all histories
    private class NotificationLoader extends AsyncTaskLoader<List<MealNotification>> {
        public NotificationLoader(Context context) {
            super(context);
        }

        public List<MealNotification> loadInBackground() {
            return MainActivity.datasource.fetchEntries();
        }
    }

    public static MealNotification getNotificationByPosition(int position) {return values.get(position);}

    /////////////////////// Adapter for listview ///////////////////////

    public class NotificationAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<MealNotification> mealNotifications;

        public NotificationAdapter(Context context, List<MealNotification> entries) {
            this.mealNotifications = entries;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            if (view == null) {
                view = inflater.inflate(R.layout.list_notification, parent, false);
            }

            MealNotification entry = mealNotifications.get(position);

            // Set the first line
            TextView firstLine = (TextView) view.findViewById(R.id.notification_list_first_line);
            firstLine.setText(getFirstLine(entry));

            // Set the second line
            TextView secondLine = (TextView) view.findViewById(R.id.notification_list_second_line);
            secondLine.setText(getSecondLine(entry));

            LinearLayout listLayout = (LinearLayout) view;
            listLayout.setBackgroundResource(getBackground(entry));

//            //Set the id
//            TextView thirdLine = (TextView) view.findViewById(R.id.rowid);
//            thirdLine.setText(entry.getmId()+"");

            return view;
        }

        @Override
        public void notifyDataSetChanged(){
//            if(MainActivity.DBhelper != null) {
//                List<MealNotification> list = MainActivity.DBhelper.fetchEntries();
//                adapter = new NotificationAdapter(getActivity(), list);
//                setListAdapter(adapter);
//            }
            super.notifyDataSetChanged();
        }

        @Override
        public Object getItem(int position) {
            return mealNotifications.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return mealNotifications.size();
        }

        public void setMealNotifications(List<MealNotification> data) {
            if (data != null) mealNotifications.addAll(data);
            notifyDataSetChanged();
        }


        /////////////////////// Helper function ///////////////////////

        /**
         * Helper function to get the first line in the mealNotifications list
         */
        private String getFirstLine(MealNotification notif) {

//            int type = notif.getType();
//            if (type == 0 || type == 3)
//                return "You have a match!";
//            else if (type == 1)
                return "Meal Match!";
//            else if (type == 2)
//                return "Pending meal...";
//            else if (type == 4)
//                return "Warning!";
//
//            return "ERROR";
        }

        /**
         * Helper function to get the second line in the mealNotifications list
         */
        private String getSecondLine(MealNotification notif) {

            String date = notif.getDate();
            String location = notif.getLocation();
            //String person = notif.getMatchName();
            //int type = notif.getType();

//            if (type == 0 || type == 3)
//                return "Click here to view and confirm";
//            else if (type == 1)
                return "Grab a meal on " + AcceptActivity.parseDate(date) + " at " + location;
//            else if (type == 2)
//                return "Finding a future friend to match you with...";
//            else if (type == 4)
//                return "You have cancelled a meal request. " +
//                        "You can only cancel so many until you are suspended for a month!";

            //return "ERROR: type does not exist";
        }

        /**
         * Helper function to get the type for background in the mealNotifications list
         */
        private int getBackground(MealNotification notif) {

//            int type = notif.getType();
//            if (type == 0 || type == 3)
//                return R.drawable.notification;
//            else if (type == 1)
                return R.drawable.notification2;
//            else if (type == 2)
//                return R.drawable.notification3;
//            else if (type == 4)
//                return R.drawable.notification4;
//
//            return R.drawable.notification4;
        }
    }

}
