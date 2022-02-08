package com.example.weatherapibyme.di;

import android.content.Context;

import com.example.weatherapibyme.data.local.AppDataBase;
import com.example.weatherapibyme.data.local.RoomClient;
import com.example.weatherapibyme.data.local.WeatherDao;
import com.example.weatherapibyme.data.retrofit.RetrofitClient;
import com.example.weatherapibyme.data.retrofit.WeatherApi;
import com.example.weatherapibyme.repositories.MainRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static WeatherApi provideApi(){
        return new RetrofitClient().provideApi();
    }

    @Provides
    public static MainRepository provideMainRepository(WeatherApi api, WeatherDao weatherDao){
        return new MainRepository(api,weatherDao);
    }
    @Provides
    public static AppDataBase provideAppDataBase(@ApplicationContext Context context){
        return new RoomClient().provideDataBase(context);
    }
    @Provides
    public static WeatherDao provideWeatherDao(AppDataBase dataBase){
        return dataBase.weatherDao();
    }



}
