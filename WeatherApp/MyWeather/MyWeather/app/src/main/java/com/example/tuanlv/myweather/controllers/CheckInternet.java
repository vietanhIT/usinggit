package com.example.tuanlv.myweather.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 27/06/2015.
 */
public class CheckInternet {
    private Context mContext;

    //Hàm dựng khởi tạo đối tượng
    public CheckInternet(Context context) {
        this.mContext = context;
    }

    public boolean checkMobileInternetConn() {
        //Tạo đối tương ConnectivityManager để trả về thông tin mạng
        ConnectivityManager connectivity = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //Nếu đối tượng khác null
        if (connectivity != null) {
            //Nhận thông tin mạng
            NetworkInfo infoMobile = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo infoWifi = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (infoMobile != null) {
                //Tìm kiếm thiết bị đã kết nối được internet chưa
                if (infoMobile.isConnected()) {
                    return true;
                }
            }
            if(infoWifi != null){
                if ( infoWifi.isConnected()){
                    return true;
                }
            }
        }
        return false;
    }
    public void showMobileDisableAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage("Network is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Bật wifi",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        Settings.ACTION_WIFI_SETTINGS);
                                mContext.startActivity(callGPSSettingIntent);
                                ((Activity)mContext).finish();
                            }
                        });
        alertDialogBuilder.setNegativeButton("Không",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ((Activity)mContext).finish();
                    }
                });
        alertDialogBuilder.setNeutralButton("Bật 3G",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        swichMobileDataConnection(true);
                        ((Activity)mContext).finish();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    private boolean swichMobileDataConnection(boolean ON)
    {
        try {
            //create instance of connectivity manager and get system connectivity service
            final ConnectivityManager conman = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            //create instance of class and get name of connectivity manager system service class
            final Class conmanClass  = Class.forName(conman.getClass().getName());
            //create instance of field and get mService Declared field
            final Field iConnectivityManagerField= conmanClass.getDeclaredField("mService");
            //Attempt to set the value of the accessible flag to true
            iConnectivityManagerField.setAccessible(true);
            //create instance of object and get the value of field conman
            final Object iConnectivityManager = iConnectivityManagerField.get(conman);
            //create instance of class and get the name of iConnectivityManager field
            final Class iConnectivityManagerClass=  Class.forName(iConnectivityManager.getClass().getName());
            //create instance of method and get declared method and type
            final Method setMobileDataEnabledMethod= iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled",Boolean.TYPE);
            //Attempt to set the value of the accessible flag to true
            setMobileDataEnabledMethod.setAccessible(true);
            //dynamically invoke the iConnectivityManager object according to your need (true/false)
            setMobileDataEnabledMethod.invoke(iConnectivityManager, ON);
        } catch (Exception e){
        }
        return true;
    }
}
