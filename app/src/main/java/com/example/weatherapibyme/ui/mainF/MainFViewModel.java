package com.example.weatherapibyme.ui.mainF;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapibyme.commonn.Resource;
import com.example.weatherapibyme.data.models.Weather;
import com.example.weatherapibyme.repositories.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainFViewModel extends ViewModel {
    private MainRepository repository;
    public LiveData<Resource<Weather>> tempLiveData;

    @Inject
    public MainFViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public void fetchtemp(String lat, String lon){
        tempLiveData = repository.getWeather(lon,lat);
    }

}
