package com.example.alertdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alert1 = findViewById(R.id.alertDialog1);
        alert1.setOnClickListener(clickAlertDialog1);


        findViewById(R.id.alertDialog3).setOnClickListener(clickAlertDialog3);

    }

    private View.OnClickListener clickAlertDialog1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Создаём билдер и передаём контекст приложения
            AlertDialog. Builder builder = new
                    AlertDialog. Builder(MainActivity. this) ;
        // В билдере указываем заголовок окна (можно указывать как ресурс,
        // так и строку)
            builder. setTitle(R. string. exclamation)
        // Указываем сообщение в окне (также есть вариант со
        // строковым параметром)
                    . setMessage(R. string. press_button)
        // Можно указать и пиктограмму
                    . setIcon(R. mipmap. ic_launcher_round)
        // Из этого окна нельзя выйти кнопкой Back
                    . setCancelable(false)
        // Устанавливаем кнопку (название кнопки также можно
        // задавать строкой)
                    . setPositiveButton(R. string. button,
        // Ставим слушатель, нажатие будем обрабатывать
                            new DialogInterface. OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast. makeText(MainActivity.this, "Кнопка нажата", Toast. LENGTH_SHORT).show() ;
                                }
                            } ) ;
            AlertDialog alert = builder. create() ;
            alert. show() ;
            Toast. makeText(MainActivity. this, "Диалог открыт",
                    Toast. LENGTH_SHORT) . show() ;
        }
    } ;
    View.OnClickListener clickAlertDialog3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context context;
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.exclamation)
                    .setMessage("2 * 2 = 4?")
                    .setCancelable(true)
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Нет!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNeutralButton(R.string.dunno, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Не знаю!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Да!", Toast.LENGTH_SHORT).show();
                        }
                    });

                   AlertDialog alert = builder.create();
                   alert.show();
        }
    };
}

