package com.project.onepice.basicproject.androidBasic.Exception;

import android.app.Application;

/**
 * Created by onepice2015 on 16/6/22.
 */
public class MyApplication extends Application{
    public MyApplication mInstance= null;

    @Override
    public void onCreate() {
        mInstance=this;
        //注册全局异常捕捉器
        MyCrashHandler handler=new MyCrashHandler();
        handler.init();
        super.onCreate();
    }

    public MyApplication getInstance(){
        return  mInstance;
    }
}
