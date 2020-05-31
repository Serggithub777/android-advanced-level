package com.example.retrofit;

import com.example.retrofit.data.WeatherRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeather {
    @GET("data/2.5/weather")
    Call<WeatherRequest> loadWeather(@Query("q") String cityCountry, @Query("units")  String metric ,@Query("appid") String keyApi);
}
