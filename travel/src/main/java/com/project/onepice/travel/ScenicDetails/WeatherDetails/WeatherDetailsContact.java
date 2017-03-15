package com.project.onepice.travel.ScenicDetails.WeatherDetails;

import com.project.onepice.travel.BasePresenter;
import com.project.onepice.travel.BaseView;
import com.project.onepice.travel.data.WeatherDetail;

/**
 * Created by onepice2015 on 2016/11/12.
 */

public interface WeatherDetailsContact {
    interface View extends BaseView<WeatherDetailsContact.Presenter> {

        void setRefresh(WeatherDetail ticketDetail);

        void setPresenter(WeatherDetailsContact.Presenter presenter);
    }

    interface  Presenter extends BasePresenter {

        void getRemoteWeather(String weatherId);
        //获取天气状态

    }
}
