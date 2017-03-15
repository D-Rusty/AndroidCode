package com.project.onepice.travel.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.project.onepice.travel.data.WeatherDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by onepice2015 on 2016/11/14.
 */

public class Utils {


    /**
     * 通过包名获取应用信息
     * @param context
     * @param packageName
     * @return
     */
    public static boolean getAppInfoByPak(Context context, String packageName){
        PackageManager packageManager = context.getPackageManager();
        ArrayList<String> mapApp=new ArrayList<>();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfos) {
            if (packageName.equals(packageInfo.packageName)) {
               return true;
            }
        }
        return false;
    }

    public static AMapLocation getMapCityName(Context context){
        //声明AMapLocationClient类对象
        AMapLocationClient mLocationClient = null;
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        AMapLocationClientOption mLocationOption=null;
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
//        mLocationClient.setLocationListener(mAMapLocationListener);
        mLocationClient.startLocation();
        return mLocationClient.getLastKnownLocation();
    }

    public static String weatherStatus(WeatherDetail.Cond status){
        String url="http://files.heweather.com/cond_icon/100.png";

        switch (status.code_d==null?status.code_n:status.code_d){
            case "100":
                url="http://files.heweather.com/cond_icon/100.png";
                break;
            case "101":
                url="http://files.heweather.com/cond_icon/101.png";
                break;
            case "102":
                url="http://files.heweather.com/cond_icon/102.png";
                break;
            case " 103":
                url="http://files.heweather.com/cond_icon/103.png";
                break;
            case "104":
                url="http://files.heweather.com/cond_icon/104.png";
                break;
            case "200":
                url="http://files.heweather.com/cond_icon/200.png";
                break;
            case " 201":
                url="http://files.heweather.com/cond_icon/201.png";
                break;
            case " 202":
                url="http://files.heweather.com/cond_icon/202.png";
                break;
            case "203":
                url="http://files.heweather.com/cond_icon/203.png";
                break;
            case "204":
                url="http://files.heweather.com/cond_icon/204.png";
                break;
            case "205":
                url="http://files.heweather.com/cond_icon/205.png";
                break;
            case "206":
                url="http://files.heweather.com/cond_icon/206.png";
                break;
            case "207":
                url="http://files.heweather.com/cond_icon/207.png";
                break;
            case " 208":
                url="http://files.heweather.com/cond_icon/208.png";
                break;
            case "  209":
                url="http://files.heweather.com/cond_icon/209.png";
                break;
            case " 210":
                url="http://files.heweather.com/cond_icon/210.png";
                break;
            case "211":
                url="http://files.heweather.com/cond_icon/211.png";
                break;
            case "212":
                url="http://files.heweather.com/cond_icon/212.png";
                break;
            case "213":
                url="http://files.heweather.com/cond_icon/213.png";
                break;
            case "300":
                url="http://files.heweather.com/cond_icon/300.png";
                break;
            case "301":
                url="http://files.heweather.com/cond_icon/301.png";
                break;
            case "302":
                url="http://files.heweather.com/cond_icon/302.png";
                break;
            case "303":
                url="http://files.heweather.com/cond_icon/303.png";
                break;
            case "304":
                url="http://files.heweather.com/cond_icon/304.png";
                break;
            case "305":
                url="http://files.heweather.com/cond_icon/305.png";
                break;
            case "306":
                url="http://files.heweather.com/cond_icon/306.png";
                break;
            case "307":
                url="http://files.heweather.com/cond_icon/307.png";
                break;
            case "308":
                url="http://files.heweather.com/cond_icon/308.png";
                break;
            case "309":
                url="http://files.heweather.com/cond_icon/309.png";
                break;
            case "310":
                url="http://files.heweather.com/cond_icon/310.png";
                break;
            case "311":
                url="http://files.heweather.com/cond_icon/311.png";
                break;
            case "312":
                url="http://files.heweather.com/cond_icon/312.png";
                break;
            case "313":
                url="http://files.heweather.com/cond_icon/313.png";
                break;
            case "400":
                url="http://files.heweather.com/cond_icon/400.png";
                break;
            case "401":
                url="http://files.heweather.com/cond_icon/401.png";
                break;
            case " 402":
                url="http://files.heweather.com/cond_icon/402.png";
                break;
            case "403":
                url="http://files.heweather.com/cond_icon/403.png";
                break;
            case "404":
                url="http://files.heweather.com/cond_icon/404.png";
                break;
            case "405":
                url="http://files.heweather.com/cond_icon/405.png";
                break;
            case "406":
                url="http://files.heweather.com/cond_icon/406.png";
                break;
            case "407":
                url="http://files.heweather.com/cond_icon/407.png";
                break;
            case "500":
                url="http://files.heweather.com/cond_icon/500.png";
                break;
            case "501":
                url="http://files.heweather.com/cond_icon/501.png";
                break;
            case "502":
                url="http://files.heweather.com/cond_icon/502.png";
                break;
            case "503":
                url="http://files.heweather.com/cond_icon/503.png";
                break;
            case " 504":
                url="http://files.heweather.com/cond_icon/504.png";
                break;
            case " 507":
                url="http://files.heweather.com/cond_icon/507.png";
                break;
            case "508":
                url="http://files.heweather.com/cond_icon/508.png";
                break;
            case "900":
                url="http://files.heweather.com/cond_icon/900.png";
                break;
            case "901":
                url="http://files.heweather.com/cond_icon/901.png";
                break;
            case "999":
                url="http://files.heweather.com/cond_icon/999.png";
                break;
        }
        return url;
    }

}
