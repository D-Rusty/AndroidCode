package com.project.onepice.travel.ScenicDetails;

import com.project.onepice.travel.BasePresenter;
import com.project.onepice.travel.BaseView;
import com.project.onepice.travel.data.Scenic;

/**
 * Created by onepice2015 on 2016/10/15.
 */

public interface ScenicDetailsContact {

    interface View extends BaseView<Presenter> {

    }

    interface  Presenter extends BasePresenter {

        //获取景点ID
        Scenic getScenic();

        void startNavigation(String mapName);

        boolean isVaildLocalApp(String appName);
    }
}
