package com.example.mealbuddy.mealbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

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

/**
 * Created by songtaeho16 on 5/25/16.
 */
public class LoginActivity extends AppCompatActivity {
    private LoginButton loginButton;
    CallbackManager callbackManager;

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
            loginButton.setReadPermissions("email");
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
                                        String email = object.getString("email");
                                        Log.d("check email", email);
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
