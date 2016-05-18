package com.example.tuanlv.myweather.controllers;

import com.example.tuanlv.myweather.R;


/**
 * Created by Ta Giang on 7/8/2015.
 */
public class UpdateStateImage {
    private double temperature;


    public int getDrawableImage(String a) {
        int imgDescription = R.drawable.sky_clear;
        if(a.equals("01")){
            imgDescription= R.drawable.clearsky1;
        }else if(a.equals("02")||a.equals("03")|a.equals("04")){
            imgDescription= R.drawable.clouds1;
        }else if(a.equals("09")||a.equals("10")){
            imgDescription = R.drawable.rain1;
        }else if(a.equals("11")){
            imgDescription = R.drawable.thunderstorm2;
        }else if (a.equals("13")){
            imgDescription = R.drawable.snow1;
        }else {
            imgDescription = R.drawable.sky_clear;
        }
        return imgDescription;
    }

    public String getState(String icon){
        String a = icon.substring(0,2);
        return a;
    }
}