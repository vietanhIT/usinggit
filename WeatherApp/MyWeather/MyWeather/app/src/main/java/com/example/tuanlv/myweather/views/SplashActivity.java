package com.example.tuanlv.myweather.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.tuanlv.myweather.R;
import com.example.tuanlv.myweather.controllers.CheckInternet;

/**
 * Created by Administrator on 27/06/2015.
 */
public class SplashActivity extends Activity{
    private Handler mHandler;
    private CheckInternet mCheck;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        mHandler = new Handler();
        mCheck = new CheckInternet(this);
        if (!mCheck.checkMobileInternetConn()){
            mCheck.showMobileDisableAlertToUser();

        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }

    }
}
