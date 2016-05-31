package com.example.mealbuddy.mealbuddy;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        com.example.mealbuddy.mealbuddy.CustomTextViewBold titleWords = (com.example.mealbuddy.mealbuddy.CustomTextViewBold) getActivity().findViewById(R.id.titleWords);
        titleWords.setText("About");

        return inflater.inflate(R.layout.aboutfragment, container, false);
    }
}
