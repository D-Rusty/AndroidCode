package com.project.onepice.travel.SelectCity;

import com.project.onepice.travel.BasePresenter;
import com.project.onepice.travel.BaseView;

import java.util.ArrayList;

/**
 * Created by onepice2015 on 2016/10/15.
 */

public interface SelectCityContract {

    interface View extends BaseView<SelectCityContract.Presenter> {
        void refresh(ArrayList<SelectCityVo> cityArrayList);
    }

    interface Presenter extends BasePresenter {
        //获取整个城市列表
        void getScenicList();

        String getLocation();
    }

}
