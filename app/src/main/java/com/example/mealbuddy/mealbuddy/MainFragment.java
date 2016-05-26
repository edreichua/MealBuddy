package com.example.mealbuddy.mealbuddy;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainFragment extends Fragment {
    private LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //
//        boolean loggedIn = AccessToken.getCurrentAccessToken() != null;
//        Log.d("LOGGED IN?", String.valueOf(loggedIn));
//
//        if(!loggedIn) {
//            loginButton = (LoginButton) view.findViewById(R.id.login_button);
//            loginButton.setReadPermissions("email");
//            loginButton.setFragment(this);
//            callbackManager = CallbackManager.Factory.create();
//
//            LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
//            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//                @Override
//                public void onSuccess(LoginResult loginResult) {
//                    Log.d("FACEBOOK LOGIN: ", "user has successfully logged in");
//                    GraphRequest request = GraphRequest.newMeRequest(
//                            loginResult.getAccessToken(),
//                            new GraphRequest.GraphJSONObjectCallback() {
//                                @Override
//                                public void onCompleted(JSONObject object, GraphResponse response) {
//                                    Log.v("LoginActivity", response.toString());
//
//                                    // Application code
//                                    try {
//                                        String email = object.getString("email");
//                                        Log.d("check email", email);
//                                    } catch (JSONException e) {
//                                        Log.d("JSON EXCEPTION", "something went wrong with JSON");
//                                    }
//                                }
//                            });
//                    Bundle parameters = new Bundle();
//                    parameters.putString("fields", "id,name,email,gender,birthday");
//                    request.setParameters(parameters);
//                    request.executeAsync();
//                }
//
//                @Override
//                public void onCancel() {
//                    Log.d("FACEBOOK LOGIN: ", "user canceled login");
//                }
//
//                @Override
//                public void onError(FacebookException exception) {
//                    Log.d("FACEBOOK LOGIN: ", "login error!");
//                }
//            });
//
//        } else {
//            Log.d("FACEBOOK LOGIN: ", "user is already loggedin!");
//        }

        return inflater.inflate(R.layout.mainfragment, container, false);
    }

}