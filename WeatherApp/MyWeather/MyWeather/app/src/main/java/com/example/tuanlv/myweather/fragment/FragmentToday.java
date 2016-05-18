package com.example.tuanlv.myweather.fragment;

import android.app.Fragment;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tuanlv.myweather.R;
import com.example.tuanlv.myweather.controllers.GPSTracker;
import com.example.tuanlv.myweather.controllers.WeatherAsyncTask;

/**
 * Created by Administrator on 25/06/2015.
 */
public class FragmentToday extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, null);



        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        GPSTracker gps = new GPSTracker(getActivity());
        if(gps.canGetLocation()){

            WeatherAsyncTask task = new WeatherAsyncTask(getActivity(), gps.getLatitude(), gps.getLongitude());
            task.execute();

        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
