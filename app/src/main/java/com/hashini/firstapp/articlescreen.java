package com.hashini.firstapp;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class articlescreen extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articlescreen);

        webView = findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://foreignpolicy.com/2022/03/05/sri-lanka-organic-farming-crisis/");


    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            Toast.makeText(this, "Going back inside a WebView", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Going back inside a WebView", Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();
    }
}