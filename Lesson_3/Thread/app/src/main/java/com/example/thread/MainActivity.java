package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private int counterThread = 0;
    private TextView textView;
    private TextView textIndicator;
    private EditText seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initUIButton();
        initButtonThread() ;
        initButtonHandlerThread();
    }

    private void initViews() {
        textView = findViewById(R. id. textView) ;
        textIndicator = findViewById(R. id. textIndicator) ;
        seconds = findViewById(R. id. editText) ;
    }

    private void initUIButton() {
        Button button = findViewById(R. id. button) ;
        button. setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View v) {
                String result =
                        Long. toString(calculate(Integer. parseInt(seconds. getText() . toString() ) ) ) ;
                textIndicator. setText("В главном потоке") ;
                textView. setText(result) ;
            }
        } ) ;

    }

    private void initButtonThread() {
        Button calcThread = findViewById(R. id. calcThreadBtn) ;
        calcThread. setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View view) {
                counterThread++;
                final int numberThread = counterThread; // это присвоение делается для потокобезопасности
                                                       // чтобы в случае изменения одной переменной вторая был не изменна
                final int secs = Integer. parseInt(seconds. getText() . toString() ) ;
                textIndicator. setText(String. format("%sСтартуем поток %d  секунд  %d\n",
                        textIndicator. getText() . toString() , numberThread, secs) ) ;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String result = Long.toString(calculate(secs));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textIndicator.setText(String.format("%sИз потока%d\n", textIndicator.getText().toString(), numberThread));
                                textView.setText(result);
                            }
                        });
                    }
                } ) . start() ;
            }
        } ) ;
    }
    private void initButtonHandlerThread() {
        Button calcThread = findViewById(R. id. calcThreadHandler) ;
        final HandlerThread handlerThread = new HandlerThread("HandlerThread") ;
        handlerThread. start() ;
        final Handler handler = new Handler(handlerThread. getLooper() ) ;
        calcThread. setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View view) {
                textIndicator. setText(String. format("%s обращаемся в поток %s\n",
                        textIndicator. getText() . toString() , handlerThread. getName() ) ) ;
                final int secs = Integer. parseInt(seconds. getText() . toString() ) ;
                handler. post(new Runnable() {
                    @Override
                    public void run() {
                        calculate(secs) ;
                        final String nameThread = Thread.currentThread().getName() ;
                        textIndicator. post(new Runnable() {
                            @Override
                            public void run() {
                                textIndicator. setText(String. format("%s Из потока %s\n", textIndicator. getText() . toString() , nameThread) ) ;
                            }
                        } ) ;
                    }
                } ) ;
            }
        } ) ;
    }

    private long calculate(int seconds) {
        Date date = new Date() ;
        long diffInSec;
        do{
            Date currentDate = new Date() ;
            long diffInMs = currentDate. getTime() - date. getTime() ;
            diffInSec = TimeUnit. MILLISECONDS. toSeconds(diffInMs) ;
        } while(diffInSec < seconds) ;
        return diffInSec;
    }
}
