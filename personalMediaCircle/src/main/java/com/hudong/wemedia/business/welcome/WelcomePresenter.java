package com.hudong.wemedia.business.welcome;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.hudong.wemedia.basiccomponent.Constant;
import com.hudong.wemedia.basiccomponent.utils.SharedPreferencesUtils;
import com.hudong.wemedia.basiccomponent.utils.comms.tencent.RefreshEvent;
import com.hudong.wemedia.basiccomponent.utils.comms.tencent.TLSConfiguration;
import com.hudong.wemedia.basiccomponent.utils.comms.tencent.TLSService;
import com.tencent.TIMLogLevel;
import com.tencent.TIMManager;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者: 方天文
 * 日期: 2017/4/12:上午9:01
 * 概况:
 */
public class WelcomePresenter implements WelcomeContract.Presenter {
    private Context mContext;
    private IWelComeCallBack iWelComeCallback;

    public WelcomePresenter(Context mContext, IWelComeCallBack iWelComeCallback) {

        this.mContext = mContext;
        this.iWelComeCallback = iWelComeCallback;
    }

    @Override
    public void isLogin() {

//        if (!tencentIsLogin() || !new Configuration().readaIsLogin(mContext)) {//这是需要登录的情况
        iWelComeCallback.isLoginResultCallback(false);
////        } else {
//            iWelComeCallback.isLoginResultCallback(true);
////        }

    }

    @Override
    public void init() {
//        initImSdk();
//        initImTls();
//        initRefreshListener();
        isLogin();
    }


    @Override
    public void checkPermission(Activity activity) {
        List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((activity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.READ_PHONE_STATE);
            if ((activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        iWelComeCallback.checkPermissionCallback(permissionsList);
    }


    /***
     * 初始化腾讯IM SDK
     */
    private void initImSdk() {
        int loglvl = ((Integer) SharedPreferencesUtils.get("loglvl", TIMLogLevel.DEBUG.ordinal())).intValue();
        TIMManager.getInstance().setLogLevel((TIMLogLevel.values()[loglvl]));
        //初始化imsdk
        TIMManager.getInstance().init(mContext);
        //禁止服务器自动代替上报已读
        TIMManager.getInstance().disableAutoReport();
//        Log.d(TAG, "initIMsdk");
    }

    /***
     * 初始化腾讯TLS
     */
    private void initImTls() {
        TLSConfiguration.setSdkAppid(Constant.SDK_APPID);
        TLSConfiguration.setAccountType(Constant.ACCOUNT_TYPE);
        TLSConfiguration.setTimeout(8000);
        TLSConfiguration.setQqAppIdAndAppKey("222222", "CXtj4p63eTEB2gSu");
        TLSConfiguration.setWxAppIdAndAppSecret("wx65f71c2ea2b122da", "1d30d40f8db6d3ad0ee6492e62ad5d57");
    }

    /***
     * 初始化腾讯刷选监听
     */
    private void initRefreshListener() {
        RefreshEvent.getInstance();
    }


    /**
     * 腾讯是否登录了
     */
    private boolean tencentIsLogin() {
        String userId = TLSService.getInstance().getLastUserIdentifier();
        return (userId != null) &&
                (!TLSService.getInstance().needLogin(userId));
    }


}
