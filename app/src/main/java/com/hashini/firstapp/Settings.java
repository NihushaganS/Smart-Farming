package com.hashini.firstapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class Settings extends AppCompatActivity implements SensorEventListener {

    private MediaPlayer mp;
    private SensorManager sensorManager;
    private boolean isRunning = false;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        textView = findViewById(R.id.textView1);
        mp = new MediaPlayer();
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.values[0] > 85 && isRunning == false){
            isRunning = true;
            mp = new MediaPlayer();
            try {
                mp.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.s92064685));
                mp.prepare();
                mp.start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accurancy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}