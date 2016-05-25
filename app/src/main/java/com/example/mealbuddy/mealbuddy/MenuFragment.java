package com.example.mealbuddy.mealbuddy;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by songtaeho16 on 5/25/16.
 */
public class MenuFragment extends Fragment {
    private WebView web;
    private String menuUrl = "http://129.170.134.38:9088";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menufragment, container, false);

        web = (WebView) view.findViewById(R.id.webView);
        web.setWebViewClient(new MyBrowser());

        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web.loadUrl(menuUrl);

        Log.d("WEBVIEW", "loaded" + menuUrl);

        return view;

    }

    // allow in app web activity for web browsing
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
