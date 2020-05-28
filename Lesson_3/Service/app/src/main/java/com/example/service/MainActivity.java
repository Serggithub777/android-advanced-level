package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initNotificationChannel();
    }


    private void initView() {
        Button buttonStartService = findViewById(R. id. buttonStartService) ;
        buttonStartService. setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity. this, MainService. class) ) ;
            }
        } ) ;
    }
    // На Android OS версии 2. 6 и выше нужно создать канал нотификации.
    // На старых версиях канал создавать не надо
    private void initNotificationChannel() {
        if (Build. VERSION. SDK_INT >= Build. VERSION_CODES. O) {
            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(Context. NOTIFICATION_SERVICE) ;
            int importance = NotificationManager. IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel("2", "name",
                    importance) ;
            notificationManager. createNotificationChannel(mChannel) ;
        }
    }
}
