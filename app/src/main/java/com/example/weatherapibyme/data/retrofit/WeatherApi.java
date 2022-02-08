package com.example.weatherapibyme.data.retrofit;

import com.example.weatherapibyme.data.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather")
    Call<Weather>
    getTemperature(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("appid") String appId,
            @Query("units") String metric
    );
}
