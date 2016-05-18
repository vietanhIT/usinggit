package com.example.tuanlv.myweather.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuanlv.myweather.R;

import java.util.ArrayList;

/**
 * Created by vieta on 17/5/2016.
 */
public class AdapterListView extends ArrayAdapter<MenuDrawer>{
    Context context;
    int resource;
    ArrayList<MenuDrawer> objects;

    public AdapterListView(Context context, int resource, ArrayList<MenuDrawer> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = View.inflate(context,resource,null);
        ImageView imageView =(ImageView) rootView.findViewById(R.id.imageView);
        TextView textView =(TextView) rootView.findViewById(R.id.textView);
        imageView.setBackgroundResource(objects.get(position).getLinkImage());
        textView.setText(objects.get(position).getDescription());
        return rootView;
    }
}
