package com.example.mealbuddy.mealbuddy;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public class MainFragment extends Fragment {
    private LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainfragment, container, false);

        com.example.mealbuddy.mealbuddy.CustomTextViewBold titleWords = (com.example.mealbuddy.mealbuddy.CustomTextViewBold) getActivity().findViewById(R.id.titleWords);
        titleWords.setText("Meal Buddy");

        // Set the font of the main buttons
        Button button1 = (Button) view.findViewById(R.id.buttonStranger);
        Button button2 = (Button) view.findViewById(R.id.buttonFriend);
        Button button3 = (Button) view.findViewById(R.id.buttonCheckFriends);
        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Ubuntu-B.ttf");
        button1.setTypeface(customFont);
        button2.setTypeface(customFont);
        button3.setTypeface(customFont);

        return view;
    }
}