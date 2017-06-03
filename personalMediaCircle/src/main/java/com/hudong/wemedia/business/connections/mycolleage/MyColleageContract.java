package com.hudong.wemedia.business.connections.mycolleage;

/**
 * Created by dyj on 2017/4/17.
 */

public interface MyColleageContract {
    interface MyColleageView {
        //获取到数据
        void onResultSuccess();

        //没有同事
        void onResultEmpty();

        //弹出pop
        void showPop();
    }

    interface MyColleagePresenter {
        //获取同事
        void getContactColleage();
    }
}
