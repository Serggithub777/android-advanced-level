package com.example.okhttp;


import android.os.Handler;

import com.example.okhttp.data.WeatherRequest;
import com.example.okhttp.model.Weather;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Здесь будем вызывать запросы
public class OkHttpRequester {
    private OnResponseCompleted listener; // Интерфейс с обратным вызовом

    public OkHttpRequester(OnResponseCompleted listener) {
        this. listener = listener;
    }

    // Синхронный запрос страницы
    public void run(String url) {
        OkHttpClient client = new OkHttpClient() ; // Клиент
        Request. Builder builder = new Request. Builder() ; // Создаём строителя
        builder. url(url) ; // Указываем адрес
// сервера
        Request request = builder. build() ; // Строим запрос
        Call call = client. newCall(request) ; // Ставим запрос
// в очередь
        call. enqueue(new Callback() {
            // Этот хендлер нужен для синхронизации потоков: если его
// сконструировать, он запомнит поток и в дальнейшем будет с ним
// синхронизироваться
            final Handler handler = new Handler() ;
            // Срабатывает по приходе ответа от сервера
            public void onResponse(Call call, Response response)
                    throws IOException {
                final String answer = response. body() . string() ;
                Gson gson = new Gson();
                WeatherRequest weatherRequest = gson.fromJson(answer, WeatherRequest.class);

                //Маппинг следует выносить в отдельный класс или метод здесь же установим
                // только один параметр температкру
                final Weather weather = new Weather();
                weather.setTemperature((int)weatherRequest.getMain().getTemp());

// Синхронизируем поток с потоком UI
                handler. post(new Runnable() {
                    @Override
                    public void run() {
                        listener. onCompleted(weather) ; // Вызываем метод
// обратного вызова
                    }
                } ) ;
            }// При сбое
            public void onFailure(Call call, IOException e) {
            }
        } ) ;
    }



    // Интерфейс обратного вызова; метод onCompleted вызывается по окончании
    // загрузки страницы
    public interface OnResponseCompleted {
        void onCompleted(Weather content) ;
    }
}
