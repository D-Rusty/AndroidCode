package com.project.onepice.travel.data.source.remote.weather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by onepice2015 on 2016/11/11.
 */

public interface WeatherService {
    //景点信息表
    @GET("heweather/pro/attractions")
    Observable<String> getWeatherDetail(@Query("cityid")String cityid);
}
