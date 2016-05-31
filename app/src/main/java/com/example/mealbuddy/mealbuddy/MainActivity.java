package com.example.mealbuddy.mealbuddy;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected static NotificationDbHelpher datasource;

    public static final String PREFS = "Profile_Info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check if the activity was started because of AlarmReceiver and run alarm
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getBoolean("alarm")) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        datasource = new NotificationDbHelpher(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set to default view
        if (savedInstanceState == null ) {
            Fragment frag = new MainFragment();
            getFragmentManager().beginTransaction().
                    add(R.id.content_frame, frag).commit();
        }

        // Register device with Google Cloud Messaging
        new GcmRegistrationAsyncTask(this).execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void getMeal(View view) {
        SharedPreferences prefs = getSharedPreferences(PREFS, 0);
        if(prefs.getString("savedPhone","").equals("") ||
                prefs.getString("savedEmail","").equals("")){
            Toast.makeText(getApplicationContext(), "Please complete your profile settings " ,
                    Toast.LENGTH_SHORT).show();
        } else if(!prefs.getString("savedEmail", "").contains("@dartmouth.edu")){
            Toast.makeText(getApplicationContext(), "Please specify your dartmouth.edu email address " ,
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, RequestPrefActivity.class);
            startActivity(intent);
        }
    }

    public void getMealWithFriend(View view) {
        SharedPreferences prefs = getSharedPreferences(PREFS, 0);
        if(prefs.getString("savedPhone","").equals("") ||
                prefs.getString("savedEmail","").equals("")){
            Toast.makeText(getApplicationContext(), "Please complete your profile settings" +
                            " and enable phone sharing" ,
                    Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, SetupMealAvailabilityActivity.class);
            startActivity(intent);
        }
    }

    public void checkFriend(View view) {
        Toast.makeText(getApplicationContext(), "Feature coming up soon",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment frag = null;

        if(id == R.id.nav_menu) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.hours) {
            Intent intent = new Intent(this, HoursActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.nav_settings){
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
            return true;
        }

        // Find the appropriate fragment
        if (id == R.id.nav_main) {
            frag = new MainFragment();
        } else if (id == R.id.nav_notification) {
            frag = new NotificationFragment();
        } else if (id == R.id.nav_about) {
            frag = new AboutFragment();
        } else if (id == R.id.logout) {
            Log.d("LOGGED IN?: ", String.valueOf(AccessToken.getCurrentAccessToken() != null));
            LoginManager.getInstance().logOut();
            finish();
            return true;
        }


        // Replace content frame with fragment
        FragmentManager fragManager = getFragmentManager();
        fragManager.beginTransaction().replace(R.id.content_frame, frag).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
