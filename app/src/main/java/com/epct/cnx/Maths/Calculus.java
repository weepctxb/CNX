package com.epct.cnx.Maths;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView; // TODO deprecated?
import android.webkit.WebViewClient; // TODO deprecated?

import com.epct.cnx.R;

public class Calculus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_calculus);

        WebView calculus_webview1 = (WebView) findViewById(R.id.calculus_webview1);
        calculus_webview1.getSettings().setDomStorageEnabled(true);
        calculus_webview1.getSettings().setBuiltInZoomControls(true);
        calculus_webview1.getSettings().setJavaScriptEnabled(true);
        calculus_webview1.getSettings().setLoadWithOverviewMode(true);
        calculus_webview1.getSettings().setUseWideViewPort(true);
        calculus_webview1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        calculus_webview1.loadUrl("file:///android_asset/calculus1.png");

        WebView calculus_webview2 = (WebView) findViewById(R.id.calculus_webview2);
        calculus_webview2.getSettings().setDomStorageEnabled(true);
        calculus_webview2.getSettings().setBuiltInZoomControls(true);
        calculus_webview2.getSettings().setJavaScriptEnabled(true);
        calculus_webview2.getSettings().setLoadWithOverviewMode(true);
        calculus_webview2.getSettings().setUseWideViewPort(true);
        calculus_webview2.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        calculus_webview2.loadUrl("file:///android_asset/calculus2.png");

        WebView calculus_webview3 = (WebView) findViewById(R.id.calculus_webview3);
        calculus_webview3.getSettings().setDomStorageEnabled(true);
        calculus_webview3.getSettings().setBuiltInZoomControls(true);
        calculus_webview3.getSettings().setJavaScriptEnabled(true);
        calculus_webview3.getSettings().setLoadWithOverviewMode(true);
        calculus_webview3.getSettings().setUseWideViewPort(true);
        calculus_webview3.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        calculus_webview3.loadUrl("file:///android_asset/calculus3.png");
    }
}