package com.example.weatherapibyme.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weatherapibyme.data.local.convertor.MainConvertor;
import com.example.weatherapibyme.data.models.Weather;

@Database(entities = {Weather.class},version = 1)
@TypeConverters({MainConvertor.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
