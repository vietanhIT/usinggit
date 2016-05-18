package com.example.tuanlv.myweather.controllers;


import android.util.Log;

import com.example.tuanlv.myweather.models.GoogleData;
import com.example.tuanlv.myweather.modelshour.DataHour;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Ta Giang on 6/25/2015.
 */
public class WeatherGSon {
    //phuong thuc static lay ra doi tuong googleData theo tinh
    public static GoogleData getWeatherJsonObject(String q, int cnt) throws IOException {
        try {
            String location = URLEncoder.encode(q, "UTF-8");
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=" + location + "&cnt=" + cnt + "&APPID=00a917ea3254116e56c612589188b5e0");
            InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
            GoogleData results = new Gson().fromJson(reader, GoogleData.class);

            return results;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            Log.e("json2", "---");
        }
        return null;
    }

    //phuong thuc static lay ra doi tuong googleData theo toa do
    public static GoogleData getWeatherJSonObject(double lat, double lon, int cnt) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + lat + "&lon=" + lon + "&cnt=" + cnt + "&mode=json" + "&APPID=00a917ea3254116e56c612589188b5e0");
        InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
        GoogleData results = new Gson().fromJson(reader, GoogleData.class);
        return results;
    }
    //phuong thuc static lay ra doi tuong HourData theo tinh theo gio
    public static DataHour getWeatherJsonObjectHour(String q, int cnt) throws IOException {
        try {
            String location = URLEncoder.encode(q, "UTF-8");
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + location + "&cnt=" + cnt + "&APPID=00a917ea3254116e56c612589188b5e0");
            InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
            DataHour results = new Gson().fromJson(reader, DataHour.class);
            Log.d("hehe",results.getCity().getName()+"");
            return results;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            Log.e("json2", "---");
        }
        return null;
    }

    //phuong thuc static lay ra doi tuong HourData theo toa do theo gio
    public static DataHour getWeatherJSonObjectHour(double lat, double lon, int cnt) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&cnt=" + cnt + "&mode=json" + "&APPID=00a917ea3254116e56c612589188b5e0");
        InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
        DataHour results = new Gson().fromJson(reader, DataHour.class);
        return results;
    }
}
