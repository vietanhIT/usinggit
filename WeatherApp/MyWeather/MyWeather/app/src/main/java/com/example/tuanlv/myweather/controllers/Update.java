package com.example.tuanlv.myweather.controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tuanlv.myweather.R;
import com.example.tuanlv.myweather.models.GoogleData;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by Ta Giang on 6/25/2015.
 */
public class Update  implements View.OnClickListener{
    Context context;
    GoogleData data;
    int size;
    String name;
    String[]icons;
    int[]humidity;
    double []speed;
    double[]pressure;
    double[]temperature;
    double[]temperatureMin;
    double[]temperatureMax;
    String[]description,day;
    ArrayList<Integer>  arrImages;
    TextView tvTemperatureToday,tvInforWeatherToday,tvSpeedWind,tvHumidity,tvPressure,tvTempertureMin,tvTempertureMax,tvDay;
    ImageView ivWeather;
    TextView tvTempDay1,tvTempDay2,tvTempDay3,tvTempDay4,tvTempDay5;
    TextView tvDay1,tvDay2,tvDay3,tvDay4,tvDay5;
    ImageButton ibDay1,ibDay2,ibDay3,ibDay4,ibDay5;
    LinearLayout relay_bg_info;
    ImageView[]imageViews;
    Bitmap [] bmap;

    public  Update(Context context,GoogleData data){
        this.context=context;
        this.data=data;
        size=data.getList().length;
        innitView();
        innitData();
        addEvent();
    }
    private  void  innitView(){
        tvHumidity=(TextView)((Activity)context).findViewById(R.id.tvHumidity);
        tvInforWeatherToday=(TextView)((Activity)context).findViewById(R.id.tvInfoWeatherToday);
        tvPressure=(TextView)((Activity)context).findViewById(R.id.tvPressure);
        tvSpeedWind=(TextView)((Activity)context).findViewById(R.id.tvSpeedWind);
        tvTemperatureToday=(TextView)((Activity)context).findViewById(R.id.tvTemperatureToday);
        tvTempertureMax=(TextView)((Activity)context).findViewById(R.id.tvTemperatureMax);
        tvTempertureMin=(TextView)((Activity)context).findViewById(R.id.tvTemperatureMin);
        ivWeather=(ImageView)((Activity)context).findViewById(R.id.ivWeather);
        relay_bg_info=(LinearLayout)((Activity)context).findViewById(R.id.relay_main);
        tvDay=(TextView)((Activity)context).findViewById(R.id.tvDay);
        arrImages=new ArrayList<>();
        day=new  String[5];

        tvDay1 =(TextView)((Activity)context).findViewById(R.id.tvDay1);
        tvDay2 =(TextView)((Activity)context).findViewById(R.id.tvDay2);
        tvDay3 =(TextView)((Activity)context).findViewById(R.id.tvDay3);
        tvDay4 =(TextView)((Activity)context).findViewById(R.id.tvDay4);
        tvDay5 =(TextView)((Activity)context).findViewById(R.id.tvDay5);

        tvTempDay1 =(TextView)((Activity)context).findViewById(R.id.tvTempDay1);
        tvTempDay2 =(TextView)((Activity)context).findViewById(R.id.tvTempDay2);
        tvTempDay3 =(TextView)((Activity)context).findViewById(R.id.tvTempDay3);
        tvTempDay4 =(TextView)((Activity)context).findViewById(R.id.tvTempDay4);
        tvTempDay5 =(TextView)((Activity)context).findViewById(R.id.tvTempDay5);

        ibDay1 =(ImageButton)((Activity)context).findViewById(R.id.ib1);
        ibDay2 =(ImageButton)((Activity)context).findViewById(R.id.ib2);
        ibDay3 =(ImageButton)((Activity)context).findViewById(R.id.ib3);
        ibDay4 =(ImageButton)((Activity)context).findViewById(R.id.ib4);
        ibDay5 =(ImageButton)((Activity)context).findViewById(R.id.ib5);


    }
    private void addEvent(){
        ibDay1.setOnClickListener(this);
        ibDay2.setOnClickListener(this);
        ibDay3.setOnClickListener(this);
        ibDay4.setOnClickListener(this);
        ibDay5.setOnClickListener(this);
    }
    private  void  innitData(){
        //name city
        name=data.getCity().getName();
        //icons
        icons=new String[size];
        for(int i=0;i<size;i++){
            icons[i]=data.getList()[i].getWeather()[0].getIcon();
        }
        //humidity %
        humidity=new int[size];
        for(int i=0;i<size;i++){
            humidity[i]=data.getList()[i].getHumidity();
        }
        //speed
        speed=new double[size];
        for(int i=0;i<size;i++){
            speed[i]=data.getList()[i].getSpeed();
        }
        //pressure
        pressure=new double[size];
        for(int i=0;i<size;i++){
            pressure[i]=data.getList()[i].getPressure();
        }
        temperature=new double[size];
        for(int i=0;i<size;i++){
            temperature[i]=data.getList()[i].getTemp().getDay();
        }
        temperatureMax=new double[size];
        for(int i=0;i<size;i++){
            temperatureMax[i]=data.getList()[i].getTemp().getMax();
        }
        temperatureMin=new double[size];
        for(int i=0;i<size;i++){
            temperatureMin[i]=data.getList()[i].getTemp().getMin();
        }
        description=new String[size];
        for(int i=0;i<size;i++){
            description[i]=data.getList()[i].getWeather()[0].getDescription();
            //Lay ra mo ta va ten anh
            UpdateStateImage updateStateImage=new UpdateStateImage(temperature[i]);
            int descriptionImage=updateStateImage.getDrawableImage();
            arrImages.add(descriptionImage);
        }
        // Dua ra dinh dang ngay
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        Date date = new Date();
        day[0]=dateFormat.format(date);
        for(int i=1;i<5;i++){
            day[i]=getNextDate(day[i-1]);
        }
        imageViews=new ImageView[]{ivWeather,ibDay1,ibDay2,ibDay3,ibDay4,ibDay5};
    }
    public  void updateDataToView(){
        tvTempertureMin.setText(changeKToC(temperatureMin[0])+"");
        tvTempertureMax.setText(changeKToC(temperatureMax[0])+"");
        tvTemperatureToday.setText(changeKToC(temperature[0])+"");
        tvSpeedWind.setText(speed[0]+" m/s");
        tvHumidity.setText(humidity[0]+" % ");
        tvPressure.setText(pressure[0]+" hPa");
        tvDay.setText(day[0]+"");
        setIcons(imageViews);

        tvTempDay1.setText(changeKToC(temperature[0]) + "°C");
        tvTempDay2.setText(changeKToC(temperature[1])+"°C");
        tvTempDay3.setText(changeKToC(temperature[2])+"°C");
        tvTempDay4.setText(changeKToC(temperature[3])+"°C");
        tvTempDay5.setText(changeKToC(temperature[4])+"°C");

        tvDay1.setText(day[0]);
        tvDay2.setText(day[1]);
        tvDay3.setText(day[2]);
        tvDay4.setText(day[3]);
        tvDay5.setText(day[4]);

    }
    private void setIcons(ImageView []iv){
        try {
            UpdateIcon updateIcon=new UpdateIcon(context,icons,iv);
            updateIcon.execute();
            bmap = updateIcon.getBitmap();
        } catch (Exception e) {
        }
    }
    public  int changeKToC(double K){
        return (int)Math.round(K-273.15);
    }
    //Viet Anh them
    public  String getNextDate(String  curDate) {
        final SimpleDateFormat format = new SimpleDateFormat("dd/mm");
        Date date = null;
        try {
            date = format.parse(curDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return format.format(calendar.getTime());
    }
    // Viet Anh them
    @Override
    public void onClick(View v) {
        switch (v.getId()){
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

    private void updateAfterClick(int position){
        tvTempertureMin.setText(changeKToC(temperatureMin[position])+"");
        tvTempertureMax.setText(changeKToC(temperatureMax[position])+"");
        tvTemperatureToday.setText(changeKToC(temperature[position])+"");
        tvSpeedWind.setText(speed[position]+" m/s");
        tvHumidity.setText(humidity[position]+" % ");
        tvPressure.setText(pressure[position]+" hPa");
        tvDay.setText(day[position]);


        ivWeather.setImageBitmap(bmap[position]);


    }

    class UpdateIcon extends AsyncTask<Void,Void,Void> {
        Context context;
        String []icon;
        ImageView[] iv;
        Bitmap [] myBitmap =new Bitmap[5];

        public UpdateIcon(Context context, String []icon, ImageView[] iv) {
            this.context = context;
            this.icon = icon;
            this.iv = iv;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            iv[0].setImageBitmap(myBitmap[0]);
            iv[1].setImageBitmap(myBitmap[0]);
            iv[2].setImageBitmap(myBitmap[1]);
            iv[3].setImageBitmap(myBitmap[2]);
            iv[4].setImageBitmap(myBitmap[3]);
            iv[5].setImageBitmap(myBitmap[4]);
            super.onPostExecute(aVoid);
        }

        public Bitmap[] getBitmap(){
            return this.myBitmap;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String []urlIcon = new String[5];
                for(int i=0;i<5;i++){
                    urlIcon[i]="http://openweathermap.org/img/w/"+icon[i]+".png";
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
    }
}

