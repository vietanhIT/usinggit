package com.example.tuanlv.myweather.controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tuanlv.myweather.R;
import com.example.tuanlv.myweather.modelshour.DataHour;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by vieta on 14/5/2016.
 */
public class UpdateHour implements View.OnClickListener{
    ProgressDialog dialog;
    private static final int SIZE = 5;
    Context context;
    DataHour data;
    int size;
    String name;
    String[] icons;
    int[] humidity;
    double[] speed;
    double[] pressure;
    double[] temperature;
    double[] temperatureMin;
    double[] temperatureMax;
    int[] id;
    String[] description, day;
    ArrayList<Integer> arrImages;
    TextView tvTemperatureToday, tvInforWeatherToday, tvSpeedWind, tvHumidity, tvPressure, tvTempertureMin, tvTempertureMax, tvDay,tvInfoLocation;
    ImageView ivWeather;
    TextView tvTempHour1, tvTempHour2, tvTempHour3, tvTempHour4, tvTempHour5;
    TextView tvHour1, tvHour2, tvHour3, tvHour4, tvHour5,tvHour;
    ImageButton ibHour1, ibHour2, ibHour3, ibHour4, ibHour5;
    //LinearLayout relay_bg_info;
    ImageView[] imageViews;
    Bitmap[] myBitmap;
    LinearLayout background_main;
    UpdateStateImage updateStateImage;
    String[] day_api,day_api1;
    int index;
    public UpdateHour(Context context, DataHour data,ProgressDialog dialog) {
        this.context = context;
        this.data = data;
        size = data.getList().length;
        this.dialog=dialog;
        //
        day_api1 =new String[size];
        for(int i=0;i<size;i++){
            day_api1[i]=data.getList()[i].getDt_txt();
        }
        index = getIndex(day_api1);
        Log.d("Index",index+"");
        //
        innitView();
        innitData();
        addEvent();
    }

    private void innitView(){
        tvHumidity = (TextView) ((Activity) context).findViewById(R.id.tvHumidity);
        tvInforWeatherToday = (TextView) ((Activity) context).findViewById(R.id.tvInfoWeatherToday);
        tvPressure = (TextView) ((Activity) context).findViewById(R.id.tvPressure);
        tvSpeedWind = (TextView) ((Activity) context).findViewById(R.id.tvSpeedWind);
        tvTemperatureToday = (TextView) ((Activity) context).findViewById(R.id.tvTemperatureToday);
        tvTempertureMax = (TextView) ((Activity) context).findViewById(R.id.tvTemperatureMax);
        tvTempertureMin = (TextView) ((Activity) context).findViewById(R.id.tvTemperatureMin);
        tvInfoLocation =(TextView) ((Activity) context).findViewById(R.id.tvInfoLocation);
        ivWeather = (ImageView) ((Activity) context).findViewById(R.id.ivWeather);
        // relay_bg_info = (LinearLayout) ((Activity) context).findViewById(R.id.relay_main);
        tvDay = (TextView) ((Activity) context).findViewById(R.id.tvDay);
        background_main = (LinearLayout) ((Activity)context). findViewById(R.id.background_main);
        description = new String[SIZE];
        arrImages = new ArrayList<>();
        day = new String[SIZE];

        tvHour1 = (TextView) ((Activity) context).findViewById(R.id.tvDay1);
        tvHour2 = (TextView) ((Activity) context).findViewById(R.id.tvDay2);
        tvHour3 = (TextView) ((Activity) context).findViewById(R.id.tvDay3);
        tvHour4 = (TextView) ((Activity) context).findViewById(R.id.tvDay4);
        tvHour5 = (TextView) ((Activity) context).findViewById(R.id.tvDay5);
        tvHour = (TextView) ((Activity) context).findViewById(R.id.tvHour);

        tvTempHour1 = (TextView) ((Activity) context).findViewById(R.id.tvTempDay1);
        tvTempHour2 = (TextView) ((Activity) context).findViewById(R.id.tvTempDay2);
        tvTempHour3 = (TextView) ((Activity) context).findViewById(R.id.tvTempDay3);
        tvTempHour4 = (TextView) ((Activity) context).findViewById(R.id.tvTempDay4);
        tvTempHour5 = (TextView) ((Activity) context).findViewById(R.id.tvTempDay5);

        ibHour1 = (ImageButton) ((Activity) context).findViewById(R.id.ib1);
        ibHour2 = (ImageButton) ((Activity) context).findViewById(R.id.ib2);
        ibHour3 = (ImageButton) ((Activity) context).findViewById(R.id.ib3);
        ibHour4 = (ImageButton) ((Activity) context).findViewById(R.id.ib4);
        ibHour5 = (ImageButton) ((Activity) context).findViewById(R.id.ib5);

    }

    private void innitData() {
        //name city
        name = data.getCity().getName();
        Log.d("Name",name);
        //icons
        icons = new String[SIZE];
        int j=index;

        for (int i = 0; i < SIZE; i++) {
            icons[i] = data.getList()[j].getWeather()[0].getIcon();
            j++;
        }
        j=index;
        //humidity %
        humidity = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            humidity[i] = data.getList()[j].getMain().getHumidity();
            j++;
        }
        //speed
        j=index;
        speed = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            speed[i] = data.getList()[j].getWind().getSpeed();
            j++;
        }
        //pressure
        j=index;
        pressure = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            pressure[i] = data.getList()[j].getMain().getPressure();
            j++;
        }
        //tempearture
        j=index;
        temperature = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            temperature[i] = data.getList()[j].getMain().getTemp();
            j++;
        }
        //temp max
        j=index;
        temperatureMax = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            temperatureMax[i] = data.getList()[j].getMain().getTemp_max();
            j++;
        }

        //temp min
        j=index;
        temperatureMin = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            temperatureMin[i] = data.getList()[j].getMain().getTemp_min();
            j++;
        }

        //description
        j=index;
        description = new String[SIZE];
        for (int i = 0; i < SIZE; i++) {
            description[i] = data.getList()[j].getWeather()[0].getDescription();
            //Lay ra mo ta va ten anh
            updateStateImage = new UpdateStateImage();
            int descriptionImage = updateStateImage.getDrawableImage(updateStateImage.getState(icons[i]));
            arrImages.add(descriptionImage);
            j++;
        }

        //id description
        j=index;
        id=new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            id[i] = data.getList()[j].getWeather()[0].getId();
            j++;
        }

        for (int i = 0; i < SIZE; i++) {
            Translator translator=new Translator();

            description[i] = translator.getDescription(id[i]);
            //Lay ra mo ta va ten anh
//            UpdateStateImage updateStateImage = new UpdateStateImage(temperature[i]);
//            int descriptionImage = updateStateImage.getDrawableImage();
//            arrImages.add(descriptionImage);
        }
        //get day
        day_api = new String[SIZE];
        j=index;
        for(int i=0;i<SIZE;i++){
            day_api[i]=data.getList()[j].getDt_txt();
            j++;
        }
        // Dua ra dinh dang ngay
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        Date date = new Date();
        day[0] = dateFormat.format(date);

        imageViews = new ImageView[]{ivWeather, ibHour1, ibHour2, ibHour3, ibHour4, ibHour5};
        tvInfoLocation.setText(name);
        DateFormat dateFormat1 = new SimpleDateFormat("HH:mm");
        Date date1 = new Date();
        tvHour.setText("Cập nhật gần nhất lúc "+dateFormat1.format(date1)+"");
    }

    public void updateDataToView() {
        tvTempertureMin.setText(changeKToC(temperatureMin[0]) + "");
        tvTempertureMax.setText(changeKToC(temperatureMax[0]) + "");
        tvTemperatureToday.setText(changeKToC(temperature[0]) + "");
        tvSpeedWind.setText(speed[0] + " m/s");
        tvHumidity.setText(humidity[0] + " % ");
        tvPressure.setText(pressure[0] + " hPa");
        tvDay.setText(getDayAPI(day_api[0]) + "/"+ getMonthAPI(day_api[0])+" ("+getHourAPI(day_api[0])+":"+getMinuteAPI(day_api[0])+")");
        tvInforWeatherToday.setText(description[0] + "");
        setIcons(imageViews);

        tvTempHour1.setText(changeKToC(temperature[0]) + "");
        tvTempHour2.setText(changeKToC(temperature[1]) + "");
        tvTempHour3.setText(changeKToC(temperature[2]) + "");
        tvTempHour4.setText(changeKToC(temperature[3]) + "");
        tvTempHour5.setText(changeKToC(temperature[4]) + "");

        tvHour1.setText(getHourAPI(day_api[0])+"h");
        tvHour2.setText(getHourAPI(day_api[1])+"h");
        tvHour3.setText(getHourAPI(day_api[2])+"h");
        tvHour4.setText(getHourAPI(day_api[3])+"h");
        tvHour5.setText(getHourAPI(day_api[4])+"h");
        background_main.setBackgroundResource(arrImages.get(0));

    }

    public int changeKToC(double K) {
        return (int) Math.round(K - 273.15);
    }
    private void setIcons(ImageView[] iv) {
        try {
            UpdateIcon1 updateIcon = new UpdateIcon1(context, icons, iv,dialog);
            updateIcon.execute();
            myBitmap = updateIcon.getBitmap();
        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib1:
                updateAfterClick(0);
                break;
            case R.id.ib2:
                updateAfterClick(1);
                break;
            case R.id.ib3:
                updateAfterClick(2);
                break;
            case R.id.ib4:
                updateAfterClick(3);
                break;
            case R.id.ib5:
                updateAfterClick(4);
                break;
        }
    }
    private void updateAfterClick(int position) {
        tvTempertureMin.setText(changeKToC(temperatureMin[position]) + "");
        tvTempertureMax.setText(changeKToC(temperatureMax[position]) + "");
        tvTemperatureToday.setText(changeKToC(temperature[position]) + "");
        tvSpeedWind.setText(speed[position] + " m/s");
        tvHumidity.setText(humidity[position] + " % ");
        tvPressure.setText(pressure[position] + " hPa");
        tvInforWeatherToday.setText(description[position] + "");
        ivWeather.setImageBitmap(myBitmap[position]);
        tvDay.setText(getDayAPI(day_api[position]) + "/"+ getMonthAPI(day_api[position])+" ("+getHourAPI(day_api[position])+":"+getMinuteAPI(day_api[position])+")");
        background_main.setBackgroundResource(arrImages.get(position));
    }

    private void addEvent() {
        ibHour1.setOnClickListener(this);
        ibHour2.setOnClickListener(this);
        ibHour3.setOnClickListener(this);
        ibHour4.setOnClickListener(this);
        ibHour5.setOnClickListener(this);
    }

    private String getHourAPI(String a){
        String hour=null;
        hour = a.substring(11,13);
        return hour;
    }

    private String getMinuteAPI(String a){
        String minute=null;
        minute = a.substring(14,16);
        return minute;
    }
    private String getDayAPI(String a){
        String day=null;
        day = a.substring(8,10);
        return day;
    }
    private String getMonthAPI(String a){
        String month=null;
        month = a.substring(5,7);
        return month;
    }

    private int getIndex(String a[]){
        int hourCurent;
        int minuteCurrent; //them
        int hourAPI;
        int subHour;
        int index=0;
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date();
        hourCurent = Integer.parseInt(dateFormat.format(date));
        DateFormat dateFormat1 = new SimpleDateFormat("mm");
        Date date1 = new Date();
        minuteCurrent = Integer.parseInt(dateFormat1.format(date1));
//        DateFormat dateFormat1 = new SimpleDateFormat("dd");
//        Date date1 = new Date();
        for(int i=0;i<size;i++){
            hourAPI = Integer.parseInt(getHourAPI(a[i]));
            subHour = hourCurent - hourAPI;

            if((subHour == 0)&&(minuteCurrent > 0)||(subHour > 0) && (subHour <=3)){
                index =i;
                break;
            }
        }
        return index;
    }
}

class UpdateIcon1 extends AsyncTask<Void, Void, Void> {
    Context context;
    String[] icon;
    ImageView[] iv;
    Bitmap[] myBitmap = new Bitmap[5];
    ProgressDialog progressDialog;

    public UpdateIcon1(Context context, String[] icon, ImageView[] iv,ProgressDialog dialog) {
        this.context = context;
        this.icon = icon;
        this.iv = iv;
        progressDialog=dialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        iv[0].setImageBitmap(myBitmap[0]);
        iv[1].setImageBitmap(myBitmap[0]);
        iv[2].setImageBitmap(myBitmap[1]);
        iv[3].setImageBitmap(myBitmap[2]);
        iv[4].setImageBitmap(myBitmap[3]);
        iv[5].setImageBitmap(myBitmap[4]);
        progressDialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String[] urlIcon = new String[5];
            for (int i = 0; i < 5; i++) {
                urlIcon[i] = "http://openweathermap.org/img/w/" + icon[i] + ".png";
                URL urlConnection = new URL(urlIcon[i]);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                myBitmap[i] = BitmapFactory.decodeStream(input);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bitmap[] getBitmap(){
        return myBitmap;
    }
}
