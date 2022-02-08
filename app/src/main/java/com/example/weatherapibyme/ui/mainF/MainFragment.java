package com.example.weatherapibyme.ui.mainF;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.weatherapibyme.R;
import com.example.weatherapibyme.base.BaseFragment;
import com.example.weatherapibyme.data.local.WeatherDao;
import com.example.weatherapibyme.data.models.Main;
import com.example.weatherapibyme.data.models.Sys;
import com.example.weatherapibyme.data.models.Weather;
import com.example.weatherapibyme.data.models.Weather__1;
import com.example.weatherapibyme.data.models.Wind;
import com.example.weatherapibyme.databinding.FragmentMainBinding;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends BaseFragment<FragmentMainBinding> {
    private NavController navController;
    private MainFViewModel model;
    private Wind wind;
    private Main main;
    private MainFragmentArgs args;
    private Weather weather;
    private Sys er;
    private ArrayList<Weather__1> weather__1s = new ArrayList<>();
    @Inject
    WeatherDao dao;


    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity()
                .getSupportFragmentManager().findFragmentById(R.id.nav_host);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        if (getArguments() != null)
            args = MainFragmentArgs.fromBundle(getArguments());
    }

    @Override
    protected FragmentMainBinding bind() {
        return FragmentMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
        binding.cityBtn.setOnClickListener(v
                -> navController.navigate(R.id.action_mainF3_to_searchF));
        model = new ViewModelProvider(requireActivity()).get(MainFViewModel.class);
        model.fetchtemp(args.getLongitude(),args.getLatitude());
    }

    @Override
    protected void setupObserves() {
        model.tempLiveData.observe(getViewLifecycleOwner(), response -> {
            switch (response.status) {
                case SUCCESSFUL:
                    wind = response.data.getWind();
                    weather = response.data;
                    main = response.data.getMain();
                    er = response.data.getSys();
                    weather__1s = (ArrayList<Weather__1>) response.data.getWeather();
                    binding.progress.setVisibility(View.GONE);
                    setWeather();
                    break;

                case LOADING:
                    binding.progress.setVisibility(View.VISIBLE);
                    break;

                case ERROR:
                    Toast.makeText(requireContext(), "Internet not connected!Loading last data", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                    wind = dao.getWeather().getWind();
                    main = dao.getWeather().getMain();
                    er = dao.getWeather().getSys();
                    weather = dao.getWeather();
                    weather__1s = (ArrayList<Weather__1>) dao.getWeather().getWeather();
                    // setWeather();
                    break;
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setWeather() {
        binding.weatherNowTv.setText(weather__1s.get(0).getMain());
        Glide.with(requireContext())
                .load("https://openweathermap.org/img/wn/" + weather__1s.get(0).getIcon() + ".png")
                .override(100, 100)
                .into(binding.weatherIv);
        binding.tempmaxTv.setText(String.valueOf((int) Math.round(main.getTempMax())));
        binding.windTv.setText((int) Math.round(wind.getSpeed()) + " m/ s");
        binding.cityBtn.setText(weather.getName());
        binding.tempnowTv.setText(String.valueOf((int) Math.round(main.getTemp())));
        binding.barometrTv.setText(main.getPressure() + "mBar");
        binding.textViewHumidity.setText(main.getHumidity() + "%");
        binding.tempminTv.setText(String.valueOf((int) Math.round(main.getTempMin())));
    }
}