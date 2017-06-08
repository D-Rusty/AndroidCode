package com.project.onepice.travel.HomePage;

import com.project.onepice.travel.BasePresenter;
import com.project.onepice.travel.BaseView;

/**
 * Created by onepice2015 on 2016/11/9.
 */

public interface HomePageContract {


    interface View extends BaseView<Presenter> {
        void showDiaolog();

        void dissMissDiaolog();

        void destroyActivity();

        void initData();
    }

    interface Presenter extends BasePresenter {
        //初始化数据库
        void initSqlite();
    }
}
