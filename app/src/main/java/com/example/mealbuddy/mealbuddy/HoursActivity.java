package com.example.mealbuddy.mealbuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by songtaeho16 on 5/25/16.
 */
public class HoursActivity extends AppCompatActivity {
    private static WebView web;
    private String menuUrl = "http://www.dartmouth.edu/dining/locations/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().setTitle("Hours");

        web = (WebView) findViewById(R.id.webView);

        web.setWebViewClient(new MyBrowser());

        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web.loadUrl(menuUrl);
    }

    // allow in app web activity for web browsing
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();

        } else {
            super.onBackPressed();
        }
    }
}
