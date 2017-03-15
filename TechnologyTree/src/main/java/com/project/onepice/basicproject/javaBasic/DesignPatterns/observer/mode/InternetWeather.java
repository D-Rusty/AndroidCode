package com.project.onepice.basicproject.javaBasic.DesignPatterns.observer.mode;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public class InternetWeather {
    public static void main(String[] args){
        CurrentConditions mCurrentConditions;
        ForcastConditions mForcastConditions;
        WeatherDataSt mWeatherDataSt;

        mWeatherDataSt=new WeatherDataSt();
        mCurrentConditions=new CurrentConditions();
        mForcastConditions=new ForcastConditions();

        mWeatherDataSt.registerObserver(mCurrentConditions);
        mWeatherDataSt.registerObserver(mForcastConditions);

        mWeatherDataSt.setData(30, 150, 40);
        mWeatherDataSt.removeObserver(mCurrentConditions);
        mWeatherDataSt.setData(40, 250, 50);


        /***
         *
         * 观察者模式：对象之间多对一依赖的一种设计方案，被依赖的对象为
         *    subject，依赖对象为observe，subject通知observe
         *
         * 一个对象，对数据进行不同的处理方法，一个方法就是一个observe
         *
         * 但是observe 设计不太灵活
         * */

    }
}
