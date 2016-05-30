package com.example.mealbuddy.mealbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by songtaeho16 on 5/25/16.
 */
public class LoginActivity extends AppCompatActivity {
    private LoginButton loginButton;
    CallbackManager callbackManager;
    public static final String PREFS = "Profile_Info";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }

        boolean loggedIn = AccessToken.getCurrentAccessToken() != null;
        Log.d("USER LOGIN?", String.valueOf(loggedIn));

        if (!loggedIn) {
            Log.d("USER LOGIN?", String.valueOf(loggedIn));
            setContentView(R.layout.activity_login);

            loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.setReadPermissions("public_profile");
            callbackManager = CallbackManager.Factory.create();

            LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.d("FACEBOOK LOGIN: ", "user has successfully logged in");
                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {
                                    Log.v("LoginActivity", response.toString());

                                    // Application code
                                    try {

                                        //String firstName = object.getString("first_name");
                                        //String lastName = object.getString("last_name");
                                        String name = object.getString("name");
                                        String id = object.getString("id");


                                        // Save into shared preferences
                                        SharedPreferences prefs = getSharedPreferences(PREFS, 0);
                                        final SharedPreferences.Editor edit = prefs.edit();

                                        edit.putString("savedName", name);
                                        edit.putString("fbId",id);

                                        // Commit change into shared preference
                                        edit.commit();

                                    } catch (JSONException e) {
                                        Log.d("JSON EXCEPTION", "something went wrong with JSON");
                                    }
                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email,gender,birthday");
                    request.setParameters(parameters);
                    request.executeAsync();

                    Intent intent = new Intent(".MainActivity");
                    startActivity(intent);
                }

                @Override
                public void onCancel() {
                    Log.d("FACEBOOK LOGIN: ", "user canceled login");
                    finish();
                }

                @Override
                public void onError(FacebookException exception) {
                    Log.d("FACEBOOK LOGIN: ", "login error!");
                    finish();
                }
            });

        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
