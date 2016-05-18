package com.example.tuanlv.myweather.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tuanlv.myweather.models.GoogleData;
import com.example.tuanlv.myweather.modelshour.DataHour;

import java.io.IOException;

/**
 * Created by vieta on 14/5/2016.
 */
public class WeatherAsyncTaskHour extends AsyncTask<Void,Void,DataHour>{
    Context mContext;
    String q;
    ProgressDialog dialog;
    double mLat;
    double mLon;
    TypePrediction mType;

    public WeatherAsyncTaskHour(Context context, String q) {
        this.mContext = context;
        this.q = q;
        this.mType = TypePrediction.ADDRESS_NAME_HOUR;
        this.dialog = new ProgressDialog(context);
        this.dialog.setTitle("Đang tải......");
        this.dialog.setMessage("Đợi 1 chút");
        Log.d("Vao", "1");
        this.dialog.setCancelable(true);
    }

    public WeatherAsyncTaskHour(Context context, double lat, double lon) {
        this.mContext = context;
        this.mLat = lat;
        this.mLon = lon;
        this.mType = TypePrediction.LATITUDE_LONGITUDE_HOUR;

        this.dialog = new ProgressDialog(context);
        this.dialog.setTitle("Đang tải......");
        this.dialog.setMessage("Đợi một chút");
        this.dialog.setCancelable(true);
    }

    @Override
    protected DataHour doInBackground(Void... voids) {
        try {
            DataHour dataHour;
            if (mType == TypePrediction.ADDRESS_NAME_HOUR) {
                dataHour = WeatherGSon.getWeatherJsonObjectHour(q, 10);
                Log.d("hehe1", dataHour.getCity().getName() + "");
//                Toast.makeText(mContext,dataHour.getCity().getName()+"",Toast.LENGTH_LONG).show();
            } else if (mType == TypePrediction.LATITUDE_LONGITUDE_HOUR) {
                dataHour = WeatherGSon.getWeatherJSonObjectHour(mLat, mLon, 10);

            } else {
                Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
                return null;
            }
            return dataHour;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.dialog.show();
    }

    @Override
    protected void onPostExecute(DataHour dataHour) {
        super.onPostExecute(dataHour);
        DataHour data = dataHour;
        if (data == null) {
        } else {
            UpdateHour update = new UpdateHour(mContext, data,dialog);
            update.updateDataToView();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
