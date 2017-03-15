package com.project.onepice.travel.Scenic;

import com.project.onepice.travel.BasePresenter;
import com.project.onepice.travel.BaseView;
import com.project.onepice.travel.data.Scenic;

import java.util.ArrayList;

/**
 * Created by onepice2015 on 2016/10/15.
 */

public interface ScenicContract {

    interface View extends BaseView<Presenter> {
        void refresh(ArrayList<Scenic> scenicsArrayList);

        String getLocation();

        void setLocation(String location);
    }

    interface Presenter extends BasePresenter {
        //根据城市名称获取对应城市ID
        void getCityId(String cityName);

        //获取数据库中该城市景点列表
        void getScenicList(String city_id, String pageNumber);


    }


}
