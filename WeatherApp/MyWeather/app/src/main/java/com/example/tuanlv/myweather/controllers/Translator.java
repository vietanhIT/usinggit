package com.example.tuanlv.myweather.controllers;

import java.util.HashMap;

/**
 * Created by vieta on 13/5/2016.
 */
public class Translator {
    HashMap<Integer,String> listDescription = new HashMap<>();
    public Translator(){
        addData();
    }

    public void addData() {
        listDescription.put(200, "Bão với mưa nhỏ");     //thunderstorm with light rain
        listDescription.put(201, "Bão với mưa");         //thunderstorm with rain
        listDescription.put(202, "Bão với mưa lớn");     //thunderstorm with heavy rain
        listDescription.put(210, "Bão nhỏ");             //light thunderstorm
        listDescription.put(211, "Bão");                 //thunderstorm
        listDescription.put(212, "Bão lớn");             //heavy thunderstorm
        listDescription.put(221, "Bão ");                //ragged thunderstorm
        listDescription.put(230, "Bão với mưa phùn nhẹ");//thunderstorm with light drizzle
        listDescription.put(231, "Bão với mưa phùn");    //thunderstorm with drizzle
        listDescription.put(232, "Bão với mưa phùn nặng");//thunderstorm with heavy drizzle

        listDescription.put(300, "Mưa phùn lất phất");  //light intensity drizzle
        listDescription.put(301, "Mưa phùn");           //drizzle
        listDescription.put(302, "Mưa phùn dày hạt");   //heavy intensity drizzle
        listDescription.put(310, "Mưa phùn lất phất");  //light intensity drizzle rain
        listDescription.put(311, "Mưa phùn");           //drizzle rain
        listDescription.put(312, "Mưa phùn dày hạt");   //heavy intensity drizzle rain
        listDescription.put(313, "Mưa rào và mưa phùn");//shower rain and drizzle
        listDescription.put(314,"");                    //heavy shower rain and drizzle
        listDescription.put(321,"");                    //shower drizzle

        listDescription.put(500, "Mưa nhỏ");             //light rain
        listDescription.put(501, "Mưa rải rác");         //moderate rain
        listDescription.put(502, "Mưa to");              //heavy intensity rain
        listDescription.put(503, "Mưa rất to");          //very heavy rain
        listDescription.put(504, "Mưa cực đoan");        //extreme rain
        listDescription.put(511, "Mưa đóng băng");       //freezing rain
        listDescription.put(520, "Mưa rào nhẹ");         //light intensity shower rain
        listDescription.put(521, "Mưa rào");             //shower rain
        listDescription.put(522, "Mưa rào nặng");        //heavy intensity shower rain
        listDescription.put(531, "Mưa rào rải rác");                    //ragged shower rain

        listDescription.put(600, "Tuyết rơi nhỏ");       //light snow
        listDescription.put(601, "Có tuyết");            //snow
        listDescription.put(602, "Tuyết rơi mạnh");      //heavy snow
        listDescription.put(611, "Mưa đá");              //sleet
        listDescription.put(612, "Mưa rào đá");          //shower sleet
        listDescription.put(615, "Mưa nhỏ và có tuyết"); //light rain and snow
        listDescription.put(616, "Mưa và có tuyết");     //rain and snow
        listDescription.put(620, "Mưa tuyết nhỏ");       //light shower snow
        listDescription.put(621, "Mưa tuyết");           //shower snow
        listDescription.put(622, "Mưa tuyết lớn");       //heavy shower snow

        listDescription.put(800, "Trời quang");          //clear sky

        listDescription.put(801, "Một ít mây");          //few clouds
        listDescription.put(802, "Mây rải rác");         //scattered clouds
        listDescription.put(803, "Nhiều mây, nắng xuyên qua"); //broken clouds
        listDescription.put(804, "Mây âm u");            //overcast clouds

        listDescription.put(900, "Lốc xoáy");            //tornado
        listDescription.put(901, "Bão nhiệt đới");       //tropical storm
        listDescription.put(902, "Bão");                 //hurricane
        listDescription.put(903, "Lạnh");                //cold
        listDescription.put(904, "Nóng");                //hot
        listDescription.put(905, "Có gió");              //windy
        listDescription.put(906, "Mưa đá");              //hail

        listDescription.put(952, "Gió nhẹ");            //light breeze
        listDescription.put(953, "Gió nhẹ");            //gentle breeze
        listDescription.put(954, "Gió vừa phải");       //moderate breeze
        listDescription.put(955, "Gió lạnh");           //fresh breeze
        listDescription.put(956, "Gió mạnh");           //strong breeze
        listDescription.put(957, "Gió hiu hiu");        //high wind, near gale
        listDescription.put(958, "Gió hiu hiu");        //gale
        listDescription.put(959, "Gió mạnh");           //severe gale
        listDescription.put(960, "Bão");                //storm
        listDescription.put(961, "Bão mạnh");           //violent storm
        listDescription.put(962, "Bão mạnh");           //hurricane

    }

    public String getDescription(int key){
        return listDescription.get(key);
    }

    public HashMap<Integer,String> getListDescription(){
        return listDescription;
    }
}
