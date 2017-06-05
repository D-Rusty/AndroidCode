package com.project.onepice.travel.HomePage;

import android.app.Activity;

import com.project.onepice.travel.BasePresenter;
import com.project.onepice.travel.BaseView;

/**
 * Created by onepice2015 on 2016/11/9.
 */

public interface HomePageContract {
    int REQUEST_PHONE_PERMISSIONS = 0;

    interface View extends BaseView<Presenter> {
        void showDiaolog();

        void dissMissDiaolog();

        void destroyActivity();
    }

    interface Presenter extends BasePresenter {
        //初始化数据库
        void initSqlite();

        /**
         * 检查所需权限
         */
        void checkPermission(Activity activity);
    }
}
