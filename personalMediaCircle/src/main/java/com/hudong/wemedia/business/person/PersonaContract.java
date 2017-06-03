package com.hudong.wemedia.business.person;

import com.hudong.wemedia.basiccomponent.widgets.PersonScrollView;

/**
 * 作者: 方天文
 * 日期: 2017/4/12:上午8:24
 * 概况:
 */

public interface PersonaContract {


    interface View {
        void ReFreshIndustry();

        PersonScrollView getPersonScrollView();
    }

    interface Presenter {
        //获取公司资料信息
        void getCompanyMemberinfo(String compid,String uid);
    }
}



