package com.project.onepice.basicproject.androidBasic.Exception;

/**
 * Created by onepice2015 on 16/6/22.
 */
public class MyCrashHandler implements Thread.UncaughtExceptionHandler {

    //定义单例模式
    public static MyCrashHandler myCrashHandler =null;

    public  MyCrashHandler(){
        myCrashHandler=this;
    }

    public void init(){
        //注册Handler;
        Thread.setDefaultUncaughtExceptionHandler(myCrashHandler);
    }

    private MyCrashHandler getInstance(){

        return myCrashHandler;

    }

    //保存状态
    public void saveState(){

    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
      ex.printStackTrace();
        //根据你的进程Id,杀死该进程，也就退出应用程序
        //最好有一个保存状态的方法
        saveState();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}






