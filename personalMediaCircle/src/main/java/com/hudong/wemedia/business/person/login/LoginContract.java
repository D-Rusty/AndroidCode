package com.hudong.wemedia.business.person.login;

import com.hudong.wemedia.BasePresenter;
import com.hudong.wemedia.BaseView;

import java.util.HashMap;

/**
 * 作者: 方天文
 * 日期: 2017/4/12:上午8:24
 * 概况:
 */

public interface LoginContract {


    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter{
        //普通登录
        void login(HashMap<String, String> hashMap);

        //忘记密码
        void forgetPwd();

        //微信登录
        void weLogin();
    }
}



