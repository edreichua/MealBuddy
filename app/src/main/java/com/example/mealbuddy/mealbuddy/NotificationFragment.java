package com.example.mealbuddy.mealbuddy;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by songtaeho16 on 5/18/16.
 */
public class NotificationFragment extends Fragment {

    private static final String[] NOTIFICATIONS = new String[]  {"You've been matched for Monday 1pm at foco!",
            "You've been matched for Wednesday 5pm at collis", "You've been matched for Friday 2pm at the hop!",
            "You've been matched for Saturday 7pm at the hop!"};

    public static final String POSITION = "position";
    public static final String ACCEPTACTIVITY = ".AcceptActivity";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("test", "test");
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        Log.d("test", "test2");
        AdapterView.OnItemClickListener notificationListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putInt(POSITION, position);

                intent.putExtras(bundle);
                intent.setAction(ACCEPTACTIVITY);

                startActivity(intent);
            }
        };

        ListView notificationList = (ListView)view.findViewById(R.id.notificationListView);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.itemlayout, NOTIFICATIONS);

        notificationList.setAdapter(mAdapter);
        notificationList.setOnItemClickListener(notificationListener);

        return view;
    }
}
