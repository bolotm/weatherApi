package com.example.weatherapibyme.data.local.convertor;

import androidx.room.TypeConverter;

import com.example.weatherapibyme.data.models.Weather__1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class Weather1Convertor {

    @TypeConverter
    public String fromMainString(List<Weather__1> weather__1){
        if (weather__1 == null){
            return null;
        }
    Gson gson = new Gson();
    Type type = new TypeToken<List<Weather__1>>() {}.getType();
    return gson.toJson(weather__1,type);
    }
    @TypeConverter
    public List<Weather__1> fromMainString(String weatherString){
        if (weatherString == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather__1>>() {}.getType();
        return gson.fromJson(weatherString,type);
    }


}
