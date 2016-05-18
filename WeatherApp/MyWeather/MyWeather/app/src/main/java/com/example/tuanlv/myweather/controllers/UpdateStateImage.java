package com.example.tuanlv.myweather.controllers;

import com.example.tuanlv.myweather.R;


/**
 * Created by Ta Giang on 7/8/2015.
 */
public class UpdateStateImage {
    private double temperature;

    public UpdateStateImage(double temperature) {
        this.temperature = temperature;
    }

    public int getDrawableImage() {
        int imgDescription = 0;
        if (temperature < 10) {
            imgDescription = R.drawable.rain_heavy;
        }
        else if (temperature > 10 && temperature < 20) {
            imgDescription = R.drawable.sky_clear;
        }
        else if (temperature >= 20 && temperature < 30) {
            imgDescription = R.drawable.scattered_cloud;
        }
        else if (temperature >= 30) {
            imgDescription = R.drawable.heavy_intensity_rain;
        }else{
            imgDescription = R.drawable.sky_clear;
        }
        return imgDescription;
    }
}