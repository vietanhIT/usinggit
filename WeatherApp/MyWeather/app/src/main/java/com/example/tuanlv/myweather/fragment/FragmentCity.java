package com.example.tuanlv.myweather.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.example.tuanlv.myweather.R;
import com.example.tuanlv.myweather.controllers.StyleCityCustomLayout;
import com.example.tuanlv.myweather.controllers.WeatherAsyncTask;

import java.util.ArrayList;

/**
 * Created by Administrator on 25/06/2015.
 */
public class FragmentCity extends Fragment {

    private Spinner mSpinner;
    private StyleCityCustomLayout mAdapter;
    ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, null);
        mSpinner = (Spinner) view.findViewById(R.id.spnCity);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new StyleCityCustomLayout(getActivity(), getResources().getStringArray(R.array.arrTinhThanh));
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                WeatherAsyncTask task = new WeatherAsyncTask(getActivity(), mAdapter.getItem(position).toString());
                task.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
