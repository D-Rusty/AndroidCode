package com.project.onepice.basicproject;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by onepice2015 on 2017/1/20.
 */

public class MyAplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
}
