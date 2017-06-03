package com.hudong.wemedia.basiccomponent.utils.comms.tencent;

import android.content.Context;

import com.hudong.wemedia.basiccomponent.Constant;

/**
 * 作者: 方天文
 * 日期: 2017/4/24:下午7:35
 * 概况:
 */

public class TlsBusiness {

    private TlsBusiness() {
    }

    public static void init(Context context) {
        TLSConfiguration.setSdkAppid(Constant.SDK_APPID);
        TLSConfiguration.setAccountType(Constant.ACCOUNT_TYPE);
        TLSConfiguration.setTimeout(8000);
        TLSConfiguration.setQqAppIdAndAppKey("222222", "CXtj4p63eTEB2gSu");
        TLSConfiguration.setWxAppIdAndAppSecret("wx65f71c2ea2b122da", "1d30d40f8db6d3ad0ee6492e62ad5d57");
        TLSService.getInstance().initTlsSdk(context);
    }

    public static void logout(String id) {
        TLSService.getInstance().clearUserInfo(id);
    }


}
