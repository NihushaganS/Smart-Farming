package com.hashini.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Learn extends AppCompatActivity {
    private Button button4;
    private Button button3;
    private Button button6;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVedio();
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openBlog();
            }

        });

        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openarticlescreen();
            }

        });

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openinstruction();
            }

        });

    }


    private void openinstruction() {
        Intent intent = new Intent(this,instruction.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "This will interfere with exting from the app", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }


    private void openarticlescreen() {
        Intent intent = new Intent(this,articlescreen.class);
        startActivity(intent);
    }

    private void openBlog() {
        Intent intent = new Intent(this,BlogScreen.class);
        startActivity(intent);
    }

    private void openVedio() {
        Intent intent = new Intent( this, VideoScreen.class);
        startActivity(intent);
    }
    }
