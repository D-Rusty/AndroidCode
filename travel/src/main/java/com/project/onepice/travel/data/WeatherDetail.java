package com.project.onepice.travel.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by onepice2015 on 2016/11/12.
 */

public class WeatherDetail {

  public ArrayList<ReaData>  reaData;

    public static class ReaData{
        public Basic basic;
        public List<DailyForecast> daily_forecast;
        public String status;
    }
    public static class upDate{
        public String loc;
        public String utc;
    }

    public static class Basic{
        public String city;
        public String cnty;
        public String id;
        public String lat;
        public String lon;
        public upDate update;

    }

    public static class DailyForecast{
        public Astro astro;
        public Cond cond;
        public Tmp tmp;
        public Wind wind;
        public String date;
    }
    public static class Cond{
        public String code_d;
        public String code_n;
        public String txt_d;
        public String txt_n;

    }

    public static class Tmp{
        public String min;
        public String max;
    }

    public static class Wind{
        public String dir;
        public String sc;
    }

    public static class Astro{
        public String sr;
        public String ss;
    }
}

































