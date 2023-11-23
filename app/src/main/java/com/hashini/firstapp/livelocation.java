package com.hashini.firstapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class livelocation extends AppCompatActivity {

    private EditText source;
    private  EditText destination;
    private Button btnsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livelocation);

        source = findViewById(R.id.source);
        destination = findViewById(R.id.destination);
        btnsearch = findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(view -> {
            String userLocation = source.getText().toString();
            String userDestination = destination.getText().toString();

            if(userLocation.equals("") || userDestination.equals("")) {
                Toast.makeText(this, "Please Enter Your Location & Destination ", Toast.LENGTH_SHORT).show();
            } else {
                getDirection(userLocation, userDestination);
            }
        });

    }
    private void getDirection(String from, String to) {
        try{
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + from +"/" + to);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException exception) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }
}