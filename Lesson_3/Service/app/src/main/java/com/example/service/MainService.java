package com.example.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MainService extends IntentService {
    int messageId=0;


        public MainService() {
            super("MainService");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            makeNote("onHandleIntent") ;
// Здесь можно сделать обработку
        }

        @Override
        public void onCreate() {
            makeNote("onCreate") ;
            super.onCreate();
        }

        @Override
        public void onDestroy() {
            makeNote("onDestroy") ;
            super.onDestroy();
        }

        // Выводим уведомление в строке состояния
        private void makeNote(String message) {
            NotificationCompat. Builder builder = new NotificationCompat. Builder(this, "2")
                    . setSmallIcon(R. mipmap. ic_launcher)
                    . setContentTitle("Main service notification")
                    . setContentText(message) ;
            NotificationManager notificationManager =
                    (NotificationManager)
                            getSystemService(Context. NOTIFICATION_SERVICE) ;

            notificationManager. notify(messageId++, builder. build() ) ;
        }
    }
