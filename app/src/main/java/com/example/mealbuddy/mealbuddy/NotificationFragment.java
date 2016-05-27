package com.example.mealbuddy.mealbuddy;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
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
    public static final String NAME2 = "name2";
    public static final String MAJOR2 = "major2";
    public static final String CLASS2 = "class2";
    public static final String EMAIL2 = "email2";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String LOCATION = "location";

    public static List<Notification> values;

    public static NotificationAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("test", "test");
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        Log.d("test", "test2");

        NotificationLoader loader = new NotificationLoader(getActivity());

        //List of Notifications
        //values = loader.loadInBackground();
        values = new ArrayList<Notification>();

        Notification notification = new Notification();
        notification.setLocation("Collis");
        notification.setDate("5 20 2016");

        Notification notification2 = new Notification();
        notification2.setLocation("Foco");
        notification2.setDate("5 21 2016");

        Notification notification3 = new Notification();
        notification3.setLocation("Hop");
        notification3.setDate("5 22 2016");

        values.add(notification);
        values.add(notification2);
        values.add(notification3);
        adapter = new NotificationAdapter(getActivity(), values);

        setListAdapter(adapter);

        //ListView notificationList = (ListView)view.findViewById(R.id.notificationListView);

        return view;
    }

    public void onListItemClick(ListView parent, View view, int position, long id) {
        super.onListItemClick(parent, view, position, id);
        Notification notification = getNotificationByPosition(position);

        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        bundle.putInt(POSITION, position);
        bundle.putLong(ROWID, notification.getId());
        bundle.putString(NAME1, notification.getName1());
        bundle.putString(MAJOR1, notification.getMajor1());
        bundle.putString(CLASS1, notification.getClass1());
        bundle.putString(EMAIL1, notification.getEmail1());
        bundle.putString(NAME2, notification.getName2());
        bundle.putString(MAJOR2, notification.getMajor2());
        bundle.putString(CLASS2, notification.getClass2());
        bundle.putString(EMAIL2, notification.getEmail2());
        bundle.putString(DATE, notification.getDate());
        bundle.putString(TIME, notification.getTime());
        bundle.putString(LOCATION, notification.getLocation());

        intent.putExtras(bundle);
        intent.setAction(ACCEPTACTIVITY);

        startActivity(intent);
    }

    // use asynctaskloader to read in data and get the list of all histories
    private class NotificationLoader extends AsyncTaskLoader<List<Notification>> {
        public NotificationLoader(Context context) {
            super(context);
        }

        public List<Notification> loadInBackground() {
            return MainActivity.datasource.fetchEntries();
        }
    }

    public static Notification getNotificationByPosition(int position) {return values.get(position);}

    /////////////////////// Adapter for listview ///////////////////////

    public class NotificationAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<Notification> notifications;

        public NotificationAdapter(Context context, List<Notification> entries) {
            this.notifications = entries;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            if (view == null) {
                view = inflater.inflate(R.layout.list_notification, parent, false);
            }

            Notification entry = notifications.get(position);

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
//                List<Notification> list = MainActivity.DBhelper.fetchEntries();
//                adapter = new NotificationAdapter(getActivity(), list);
//                setListAdapter(adapter);
//            }
            super.notifyDataSetChanged();
        }

        @Override
        public Object getItem(int position) {
            return notifications.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return notifications.size();
        }

        public void setNotifications(List<Notification> data) {
            if (data != null) notifications.addAll(data);
            notifyDataSetChanged();
        }


        /////////////////////// Helper function ///////////////////////

        /**
         * Helper function to get the first line in the notifications list
         */
        private String getFirstLine(Notification notif) {

//            int type = notif.getType();
//            if (type == 0 || type == 3)
//                return "You have a match!";
//            else if (type == 1)
                return "Upcoming meal!";
//            else if (type == 2)
//                return "Pending meal...";
//            else if (type == 4)
//                return "Warning!";
//
//            return "ERROR";
        }

        /**
         * Helper function to get the second line in the notifications list
         */
        private String getSecondLine(Notification notif) {

            String date = notif.getDate();
            String location = notif.getLocation();
            //String person = notif.getMatchName();
            //int type = notif.getType();

//            if (type == 0 || type == 3)
//                return "Click here to view and confirm";
//            else if (type == 1)
                return "Grab a meal on " + date + " at " + location;
//            else if (type == 2)
//                return "Finding a future friend to match you with...";
//            else if (type == 4)
//                return "You have cancelled a meal request. " +
//                        "You can only cancel so many until you are suspended for a month!";

            //return "ERROR: type does not exist";
        }

        /**
         * Helper function to get the type for background in the notifications list
         */
        private int getBackground(Notification notif) {

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
