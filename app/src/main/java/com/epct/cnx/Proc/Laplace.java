package com.epct.cnx.Proc;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Button;

import com.epct.cnx.R;

public class Laplace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proc_laplace);

        WebView laplace_webview1 = (WebView) findViewById(R.id.laplace_webview1);
        laplace_webview1.getSettings().setDomStorageEnabled(true);
        laplace_webview1.getSettings().setBuiltInZoomControls(true);
        laplace_webview1.getSettings().setJavaScriptEnabled(true);
        laplace_webview1.getSettings().setLoadWithOverviewMode(true);
        laplace_webview1.getSettings().setUseWideViewPort(true);
        laplace_webview1.loadUrl("file:///android_asset/laplace1.png");

        WebView laplace_webview2 = (WebView) findViewById(R.id.laplace_webview2);
        laplace_webview2.getSettings().setDomStorageEnabled(true);
        laplace_webview2.getSettings().setBuiltInZoomControls(true);
        laplace_webview2.getSettings().setJavaScriptEnabled(true);
        laplace_webview2.getSettings().setLoadWithOverviewMode(true);
        laplace_webview2.getSettings().setUseWideViewPort(true);
        laplace_webview2.loadUrl("file:///android_asset/laplace2.png");
    }
}