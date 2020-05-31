package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.retrofit.data.WeatherRequest;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private OpenWeather openWeather;
    private TextView textTemp; // Температура
    private TextInputEditText editCity;
    private TextInputEditText editApiKey;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRetorfit() ;
        initGui() ;
        initPreferences() ;
        initEvents() ;
    }

    private void initPreferences() {
        sharedPref = getPreferences(MODE_PRIVATE) ;
        loadPreferences() ; // Загружаем настройки
    }

    // Инициализируем пользовательские элементы
    private void initGui() {
        textTemp = findViewById(R. id. textTemp) ;
        editApiKey = findViewById(R. id. editApiKey) ;
        editCity = findViewById(R. id. editCity) ;
    }
    // Создаём обработку клика кнопки
    private void initEvents() {
        Button button = findViewById(R. id. button) ;
        button. setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences() ; // Сохраняем настройки
                requestRetrofit(editCity. getText() . toString() ,
                        editApiKey. getText() . toString() ) ;
            }
        } ) ;

    }@Override
    protected void onSaveInstanceState(Bundle outState) {
        super. onSaveInstanceState(outState) ;
        savePreferences() ;
    }

    // Сохраняем настройки
    private void savePreferences() {
        SharedPreferences. Editor editor = sharedPref. edit() ;
        editor.putString("apiKey", editApiKey. getText().toString() ) ;
        editor.apply() ;
       }


   // Загружаем настройки в шаблон запроса
    private void loadPreferences() {
        String loadedApiKey = sharedPref. getString("apiKey",
                "240af58b6f095eb759a3ecd2d282d448") ;
        editApiKey. setText(loadedApiKey) ;
    }

    private void initRetorfit() {
        Retrofit retrofit;
        retrofit = new Retrofit. Builder()
        .baseUrl("https://api.openweathermap.org/") // Базовая часть
                                                   // адреса

        // Конвертер, необходимый для преобразования JSON
        // в объекты
        .addConverterFactory(GsonConverterFactory.create() )
        .build() ;
        // Создаём объект, при помощи которого будем выполнять запросы
        openWeather = retrofit.create(OpenWeather.class) ;
        }

        private void requestRetrofit(String city, String keyApi) {
        openWeather. loadWeather(city, "metric", keyApi)
             .enqueue(new Callback<WeatherRequest>() {
        @Override
        public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                if (response. body() != null) {
                float result = response.body().getMain().getTemp();
                textTemp. setText(String.format(Locale.getDefault(),"%.2f",result)) ;
             }
        }
        @Override
        public void onFailure(Call<WeatherRequest> call, Throwable t){
        textTemp. setText("Error") ;
        }

      } ) ;
   }
}
