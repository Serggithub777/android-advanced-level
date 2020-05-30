package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.okhttp.model.Weather;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получаем элемент WebView
        final TextView temperature = findViewById(R. id.textTemp) ;
// Создаём запрос при помощи класса Requester, в качестве параметра
// зададим анонимный класс с обратным вызовом по завершении работы
        OkHttpRequester requester = new OkHttpRequester(new
            OkHttpRequester. OnResponseCompleted() {
                // Вызывается по завершении закачки страницы
                @Override
                public void onCompleted(Weather content) {
                    temperature.setText(String.format(Locale.getDefault(),"%d",content.getTemperature()));
                    //page. loadData(content, "text/html; charset=utf-8", "utf-8") ;
                }
            } ) ;
// Запускаем запрос
        requester. run("https://api.openweathermap.org/data/2.5/weather?q=moscow&units=metric&appid=240af58b6f095eb759a3ecd2d282d448") ;
        // Загружаем страницу
    }
}
