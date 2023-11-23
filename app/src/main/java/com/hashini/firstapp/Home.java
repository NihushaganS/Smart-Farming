package com.hashini.firstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private Button Userbtn;

    private Button Learnbtn;
    private Button Locationbtn;
    private Button settingbtn;
    private Button Livenbtn;
    private Button user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Userbtn = (Button) findViewById(R.id.Userbtn);
        Userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserguid();
            }
        });



        Learnbtn = (Button) findViewById(R.id.Learnbtn);
        Learnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLearn();
            }
        });
        // show Agriculture Office
        Locationbtn = (Button) findViewById(R.id.Locationbtn);
        Locationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:8.312194, 80.4053671?q=" + Uri.parse("8.312194," + " 80.405367(Agri office)"));
                Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(intent);
            }
        });
        settingbtn = (Button) findViewById(R.id.settingbtn);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensetting();
            }
        });

        Livenbtn = (Button) findViewById(R.id.Livenbtn);
        Livenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlivelocation();
            }
        });

        user = (Button) findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openuseraccount();
            }
        });

        }

    private void openuseraccount() {
        Intent intent = new Intent( this, useraccount.class );
        startActivity(intent);

    }

    private void openlivelocation() {
        Intent intent = new Intent( this, livelocation.class );
        startActivity(intent);
    }

    private void opensetting() {
        Intent intent = new Intent( this, Settings.class );
        startActivity(intent);

    }


    private void openUserguid() {
        Intent intent = new Intent( this, UserGuid.class );
        startActivity(intent);
    }



    private void openLearn(){
        Intent intent = new Intent(this,Learn.class);
        startActivity(intent);
    }

}





