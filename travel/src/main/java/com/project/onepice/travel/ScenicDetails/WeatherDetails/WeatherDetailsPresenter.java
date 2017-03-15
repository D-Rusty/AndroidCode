package com.project.onepice.travel.ScenicDetails.WeatherDetails;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.project.onepice.travel.data.WeatherDetail;
import com.project.onepice.travel.data.source.remote.RemoteDataRepository;
import com.project.onepice.travel.data.source.remote.RemoteDataSource;
import com.project.onepice.travel.util.CallBack.ICallBackOkhttpListener;

/**
 * Created by onepice2015 on 2016/11/12.
 */

public class WeatherDetailsPresenter implements WeatherDetailsContact.Presenter {

    private  final Context context;
    private  final RemoteDataSource remoteDataSource;
    private  final WeatherDetailsContact.View weatherDetailPage;

    public WeatherDetailsPresenter(Context context, RemoteDataRepository remoteDataRepository, WeatherDetailsContact.View weatherDetailPage){
        this.context=context;
        this.remoteDataSource=remoteDataRepository;
        this.weatherDetailPage=weatherDetailPage;
        weatherDetailPage.setPresenter(this);
    }


    /***
     * 重新封装返回接口，尽量把跨线程屏蔽掉
     * */
    @Override
    public void getRemoteWeather(String city_id) {
        remoteDataSource.getWeatherDetail(city_id, new ICallBackOkhttpListener() {
            @Override
            public void onFailure(String e) {

            }

            @Override
            public void onResponse(String response) {
                Message msg=handler.obtainMessage();
                msg.what=1;
                Bundle bundle = new Bundle();
                bundle.putString("weather",response);
                msg.setData(bundle);
                msg.sendToTarget();

            }
        });

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Gson gson = new Gson();
            WeatherDetail weatherDetail = gson.fromJson(msg.getData().getString("weather").replace("HeWeather data service 3.0","reaData"), WeatherDetail.class);
            weatherDetailPage.setRefresh(weatherDetail);
            super.handleMessage(msg);
        }
    };
}
