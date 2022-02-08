package com.example.weatherapibyme.data.local;

import android.content.Context;

import androidx.room.Room;

public class RoomClient {

    public AppDataBase provideDataBase(Context context){
        return Room.databaseBuilder(
                context,
                AppDataBase.class,
                "room"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }
}
