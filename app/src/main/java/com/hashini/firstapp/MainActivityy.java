package com.hashini.firstapp;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class MainActivityy extends AppCompatActivity {

    private static final String SENSOR_ADDRESS = "00:11:22:33:44:55";
    private static final UUID SENSOR_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private InputStream inputStream;

    private Button btnConnect;
    private TextView tvMoistureLevel;

    private Handler handler;
    private byte[] buffer = new byte[1024];
    private int bytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = findViewById(R.id.btnConnect);
        tvMoistureLevel = findViewById(R.id.tvMoistureLevel);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectToSensor();
            }
        });

        handler = new Handler();
    }

    private void connectToSensor() {
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(SENSOR_ADDRESS);
        try {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            bluetoothSocket = device.createRfcommSocketToServiceRecord(SENSOR_UUID);
            bluetoothSocket.connect();

            inputStream = bluetoothSocket.getInputStream();

            Toast.makeText(this, "Connected to Sensor", Toast.LENGTH_SHORT).show();
            startListeningForData();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to connect to Sensor", Toast.LENGTH_SHORT).show();
        }
    }

    private void startListeningForData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        bytes = inputStream.read(buffer);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String moistureLevel = new String(buffer, 0, bytes);
                                tvMoistureLevel.setText("Moisture Level: " + moistureLevel);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (bluetoothSocket != null) {
                bluetoothSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
 }
}
}