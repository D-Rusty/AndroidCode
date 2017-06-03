package com.hudong.wemedia.basiccomponent;

import com.hudong.wemedia.BuildConfig;

/**
 * 作者: 方天文
 * 日期: 2017/4/13:下午4:39
 * 概况: 公共常量管理类，不牵扯具体模块内容
 */

public class Constant {
    /*服务器地址*/
    public static final String HOST = BuildConfig.BASE_HOST + "/app/index.php?i=13&c=entry&do=appregister&m=amouse_ecard";

    public static final String SHARE_HOST = BuildConfig.BASE_HOST + "/addons/amouse_ecard/";//微信分享链接
    public static final String IMG_PATH_HOST = BuildConfig.BASE_HOST + "/../addons/amouse_ecard/style/img";

    /*微信登录*/
    public static final String WX_APP_ID = "wx454b3be13415cf51";
    public static final String WX_APP_SECRET = "804aca49400452b19e615781e610b97a";

    public static final int APPID = new Integer(BuildConfig.TENCENT_APP_ID).intValue();
    public static final int ACCOUNTTYPE = new Integer(BuildConfig.TENCENT_ACCOUNTTYPE).intValue();


    public static final String RIGHT = "right";
    public static final String LEFT = "left";


    /*本地缓存*/
    public final static String CONFIGURATION = "configuration";


    public static final int ACCOUNT_TYPE = 792;
    //sdk appid 由腾讯分配
    public static final int SDK_APPID = 1400001533;
}
