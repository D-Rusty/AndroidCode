package com.hudong.wemedia;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.hudong.wemedia.basiccomponent.bean.LoginUser;

/**
 * 作者: 方天文
 * 日期: 2017/4/11:上午10:34
 * 概况: 全局activity
 */

public class MediaCircleApplication extends MultiDexApplication {

    public static LoginUser loginUser = null; //登录完成后，将登录用户保存为全局变量方便使用

    public String ROOT_PATH;

    private static class SingleTonHolder {
        //假设单例对象构造方法会抛出异常时的写法
        private static final MediaCircleApplication instance;
        static {
            instance = new MediaCircleApplication();
        }
    }

    //获取单例对象实例
    public static MediaCircleApplication getInstance() {
        return SingleTonHolder.instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}

