package com.hudong.wemedia.business.welcome;

import android.app.Activity;

/**
 * 作者: 方天文
 * 日期: 2017/4/12:上午9:00
 * 概况:
 */

public interface WelcomeContract {
    int REQUEST_PHONE_PERMISSIONS = 0;
    String TAG = WelcomeContract.class.getSimpleName();

    interface View {

        void initView();
    }

    interface Presenter {

        void isLogin();

        /**
         * 初始化各类监听及其检查
         */
        void init();

        /**
         * 检查所需权限
         */
        void checkPermission(Activity activity);

    }
}
