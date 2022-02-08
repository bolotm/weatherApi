package com.example.weatherapibyme.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapibyme.commonn.Resource;
import com.example.weatherapibyme.data.local.WeatherDao;
import com.example.weatherapibyme.data.models.Weather;
import com.example.weatherapibyme.data.retrofit.WeatherApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private WeatherApi api;
    private WeatherDao dao;

    @Inject
    public MainRepository(WeatherApi api, WeatherDao dao) {
        this.api = api;
        this.dao = dao;
    }

    public MutableLiveData<Resource<Weather>> getWeather(String lon, String lat){
        MutableLiveData<Resource<Weather>> data = new MutableLiveData<>();
        data.setValue(Resource.loading());

        api.getTemperature(lon,lat,"5b1b5566510ddac6f7f20f99d7a80b96","metric").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful() && response.body() != null){
                    data.setValue(Resource.success(response.body()));
                    dao.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                data.setValue(Resource.error(null,t.getLocalizedMessage()));
            }
        });
        return data;
    }

}
