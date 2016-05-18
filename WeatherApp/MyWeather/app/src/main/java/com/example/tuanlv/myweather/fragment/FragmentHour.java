package com.example.tuanlv.myweather.fragment;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tuanlv.myweather.R;
import com.example.tuanlv.myweather.controllers.GPSTracker;
import com.example.tuanlv.myweather.controllers.WeatherAsyncTaskHour;

/**
 * Created by vieta on 14/5/2016.
 */
public class FragmentHour extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hour, null);
        Location lastLocation =null;
        GPSTracker gps= new GPSTracker(getActivity());
        LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            gps.showSettingsAlert();
        }
        if(gps.canGetLocation()){
            lastLocation = gps.getLocation();
            WeatherAsyncTaskHour task = new WeatherAsyncTaskHour(getActivity(), lastLocation.getLatitude(),lastLocation.getLongitude());
            task.execute();
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
