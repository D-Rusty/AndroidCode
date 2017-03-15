package com.project.onepice.travel.data.source.remote;

import com.project.onepice.travel.util.CallBack.ICallBackOkhttpListener;
import com.project.onepice.travel.util.CallBack.ICallBackListener;

/**
 * Created by onepice2015 on 2016/11/11.
 */

public interface RemoteDataSource {
    //获取TicketInfo
     void setTicketInfo(String ticket, ICallBackListener callBackListener);

    //获取天气详情
     void getWeatherDetail(String weather_id, ICallBackOkhttpListener httpRemoteUtils);

}
