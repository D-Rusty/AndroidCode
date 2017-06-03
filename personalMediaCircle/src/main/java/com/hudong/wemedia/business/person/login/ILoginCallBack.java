package com.hudong.wemedia.business.person.login;

import com.hudong.wemedia.basiccomponent.bean.LoginUser;

/**
 * 作者: 方天文
 * 日期: 2017/4/12:下午6:54
 * 概况:
 */

public interface ILoginCallBack {
    void loginResult(LoginUser loginUser);
}