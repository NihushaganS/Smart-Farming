package com.hashini.firstapp;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class instruction extends AppCompatActivity {
    WebView webView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        webView2 = findViewById(R.id.webView2);
        webView2.setWebViewClient(new WebViewClient());
        webView2.loadUrl("https://eos.com/blog/soil-testing/");
    }

    @Override
    public void onBackPressed() {
        if (webView2.canGoBack()) {
            webView2.goBack();
            Toast.makeText(this, "Going back inside a WebView", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Going back inside a WebView", Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();
    }
}
