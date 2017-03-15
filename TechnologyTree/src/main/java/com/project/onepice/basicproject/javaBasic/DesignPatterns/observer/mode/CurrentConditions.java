package com.project.onepice.basicproject.javaBasic.DesignPatterns.observer.mode;


import com.project.onepice.basicproject.javaBasic.DesignPatterns.observer.observer.Observer;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public class CurrentConditions implements Observer {
    private float mTemperatrue;
    private float mPressure;
    private float mHumidity;

    @Override
    public void update(float mTemperatrue, float mPressure, float mHumidity) {
        // TODO Auto-generated method stub
        this.mHumidity = mHumidity;
        this.mPressure = mPressure;
        this.mTemperatrue = mTemperatrue;
        display();
    }

    public void display() {
        System.out.println("***Today mTemperatrue:" + mTemperatrue + "***");
        System.out.println("***Today mPressure:" + mPressure + "***");
        System.out.println("***Today mHumidity:" + mHumidity + "***");

    }
}
