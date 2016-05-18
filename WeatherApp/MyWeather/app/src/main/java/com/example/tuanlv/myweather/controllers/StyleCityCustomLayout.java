package com.example.tuanlv.myweather.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Ta Giang on 7/9/2015.
 */
import com.example.tuanlv.myweather.R;

public class StyleCityCustomLayout extends ArrayAdapter<String> {
    public StyleCityCustomLayout(Context context, String[] arrCity) {
        super(context, R.layout.style_city, arrCity);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.style_city, parent, false);
            holder = new ViewHolder();
            holder.tvCity = (TextView) convertView.findViewById(R.id.tvCity);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCity.setText(getItem(position).toString());
        return convertView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.style_city, parent, false);
            holder = new ViewHolder();
            holder.tvCity = (TextView) convertView.findViewById(R.id.tvCity);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCity.setText(getItem(position).toString());
        return convertView;
    }
}

class ViewHolder {
    TextView tvCity;
}
