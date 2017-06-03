package com.hudong.wemedia.basiccomponent.utils.comms;

import android.content.Context;
import android.content.SharedPreferences;

import com.hudong.wemedia.basiccomponent.Constant;

/**
 * 作者: 方天文
 * 日期: 2017/4/12:上午11:19
 * 概况:
 */

public class Configuration {
    /**
     * 判断是否登录
     *
     * @param context
     * @param isLogin
     */
    public void writeaIsLogin(Context context, boolean isLogin) {
        if (null == context) {
            return;
        }
        SharedPreferences pref = context.getSharedPreferences(Constant.CONFIGURATION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isLogin", isLogin);
        editor.commit();
    }

    public boolean readaIsLogin(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constant.CONFIGURATION, Context.MODE_PRIVATE);
        boolean isLogin = pref.getBoolean("isLogin", false);
        return isLogin;
    }

}
