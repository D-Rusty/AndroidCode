package com.hudong.wemedia.basiccomponent.utils.comms.tencent;

import android.content.Context;

import tencent.tls.platform.TLSAccountHelper;
import tencent.tls.platform.TLSLoginHelper;
import tencent.tls.platform.TLSUserInfo;

/**
 * 作者: 方天文
 * 日期: 2017/4/24:下午7:36
 * 概况:
 */

public class TLSService {
    private static TLSService tlsService = null;
    private TLSLoginHelper loginHelper;
    private TLSAccountHelper accountHelper;

    private static int lastErrno = -1;


    private TLSService() {
    }

    public static TLSService getInstance() {
        if (tlsService == null) {
            tlsService = new TLSService();
        }
        return tlsService;
    }

    /**
     * @param context: 关联的activity
     * @function: 初始化TLS SDK, 必须在使用TLS SDK相关服务之前调用
     */
    public void initTlsSdk(Context context) {
        loginHelper = TLSLoginHelper.getInstance().init(context.getApplicationContext(),
                TLSConfiguration.SDK_APPID, TLSConfiguration.ACCOUNT_TYPE, TLSConfiguration.APP_VERSION);
        loginHelper.setTimeOut(TLSConfiguration.TIMEOUT);
        loginHelper.setLocalId(TLSConfiguration.LANGUAGE_CODE);
        loginHelper.setTestHost("", true);// 走sso

        accountHelper = TLSAccountHelper.getInstance().init(context.getApplicationContext(),
                TLSConfiguration.SDK_APPID, TLSConfiguration.ACCOUNT_TYPE, TLSConfiguration.APP_VERSION);
        accountHelper.setCountry(Integer.parseInt(TLSConfiguration.COUNTRY_CODE)); // 存储注册时所在国家，只须在初始化时调用一次
        accountHelper.setTimeOut(TLSConfiguration.TIMEOUT);
        accountHelper.setLocalId(TLSConfiguration.LANGUAGE_CODE);
        accountHelper.setTestHost("", true);
    }

    public void clearUserInfo(String identifier) {
        loginHelper.clearUserInfo(identifier);
        lastErrno = -1;
    }

    public String getLastUserIdentifier() {
        TLSUserInfo userInfo = getLastUserInfo();
        if (userInfo != null)
            return userInfo.identifier;
        else
            return null;
    }

    public TLSUserInfo getLastUserInfo() {
        return loginHelper.getLastUserInfo();
    }

    public String getUserSig(String identify) {
        return loginHelper.getUserSig(identify);
    }

    public boolean needLogin(String identifier) {
        if (identifier == null)
            return true;
        return loginHelper.needLogin(identifier);
    }

}






























