package com.example.mealbuddy.mealbuddy;

import android.os.Bundle;
import android.preference.PreferenceFragment;


/**
 * Created by edreichua on 5/18/16.
 */
import android.os.Bundle;


public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the settings_preferences from an XML resource
        addPreferencesFromResource(R.xml.fragment_settings);

    }
}