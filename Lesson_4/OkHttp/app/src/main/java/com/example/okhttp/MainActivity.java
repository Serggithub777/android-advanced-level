package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получаем элемент WebView
        final WebView page = findViewById(R. id. page) ;
// Создаём запрос при помощи класса Requester, в качестве параметра
// зададим анонимный класс с обратным вызовом по завершении работы
        OkHttpRequester requester = new OkHttpRequester(new
            OkHttpRequester. OnResponseCompleted() {
                // Вызывается по завершении закачки страницы
                @Override
                public void onCompleted(String content) {
                    page. loadData(content, "text/html; charset=utf-8", "utf-8") ;
                }
            } ) ;
// Запускаем запрос
        requester. run("https://geekbrains.ru") ; // Загружаем страницу
    }
}
