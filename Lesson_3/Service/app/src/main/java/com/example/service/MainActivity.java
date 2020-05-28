package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final String BROADCAST_ACTION_CALCFINISHED =
            "com.example.service.calculationfinished";
    private TextView textResult;
    private EditText editSeconds;

    private TextView textFibonacci;
    private boolean isBound = false;
    private BoundService.ServiceBinder boundService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initNotificationChannel();
    }

// Для регистрации Broadcast Receiver
    @Override
    protected void onStart() {
        super. onStart() ;
        registerReceiver(calculationFinishedReceiver, new
                IntentFilter(BROADCAST_ACTION_CALCFINISHED) ) ;
    }@Override
    protected void onStop() {
        super. onStop() ;
        unregisterReceiver(calculationFinishedReceiver) ;

        if (isBound) {
            unbindService(boundServiceConnection) ;
        }
    }
    private void initView() {
        Button buttonStartService = findViewById(R. id. buttonStartService) ;
        buttonStartService. setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity. this, MainService. class) ) ;
            }
        } ) ;
        textResult = findViewById(R.id.textResult) ;
        editSeconds = findViewById(R.id.editSeconds) ;
        findViewById(R.id.buttonCalcService).setOnClickListener(new
            View. OnClickListener() {
                @Override
                public void onClick(View v) {
                    int seconds = Integer. parseInt(editSeconds. getText() . toString() ) ;
                    CalculationService. startCalculationService(MainActivity. this,
                            seconds) ;
                }
            } ) ;

        textFibonacci = findViewById(R. id. textFibonacci) ;
        findViewById(R. id. buttonBindService) . setOnClickListener(new
            View. OnClickListener() {
                @Override
                public void onClick(View v) {
                    // По нажатию кнопки соединяемся с сервисом
                    Intent intent = new Intent(MainActivity. this,
                            BoundService. class) ;
                    bindService(intent, boundServiceConnection, BIND_AUTO_CREATE) ;
                }
            } ) ;
        findViewById(R. id. buttonNextFibo) . setOnClickListener(new View. OnClickListener() {
            // Вызовем у сервиса метод, если он был соединён
            @Override
            public void onClick(View v) {
                if (boundService == null) {
                    textFibonacci. setText("Unbound service") ;
                } else {
                    long fibo = boundService. getNextFibonacci() ;
                    textFibonacci. setText(Long. toString(fibo) ) ;
                }
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

    // Получатель широковещательного сообщения
    private BroadcastReceiver calculationFinishedReceiver = new
            BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    final long result =
                            intent. getLongExtra(CalculationService. EXTRA_RESULT, 0) ;
    // Потокобезопасный вывод данных
                    textResult. post(new Runnable() {
                        @Override
                        public void run() {
                            textResult. setText(Long. toString(result) ) ;
                        }
                    } ) ;
                }
            } ;
    // Обработка соединения с сервисом
    private ServiceConnection boundServiceConnection = new ServiceConnection() {
        // При соединении с сервисом
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            boundService = (BoundService.ServiceBinder) service;
            isBound = boundService != null;
        }// При разрыве соединения с сервисом
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            boundService = null;}
    } ;
}
