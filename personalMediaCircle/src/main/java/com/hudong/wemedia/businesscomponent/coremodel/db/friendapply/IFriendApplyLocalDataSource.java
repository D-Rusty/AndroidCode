package com.hudong.wemedia.businesscomponent.coremodel.db.friendapply;

import com.hudong.wemedia.basiccomponent.bean.Connections;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:11
 * 概况:
 */

public interface IFriendApplyLocalDataSource {

    String TABLE_NAME = "friend_apply";
    String ID = "id";
    String REALNAME = "realname";
    String SEX = "sex";
    String MOBILE = "mobile";
    String INDUSTRY = "industry";
    String COMPANY = "company";
    String DEPARTMENT = "department";
    String JOB = "job";
    String HEADIMG = "headimg";
    String USERNAME = "username";
    String NOW_PROVINCE = "now_province";
    String NOW_CITY = "now_city";
    String NOW_AREA = "now_area";
    String NOW_TOWN = "now_town";
    String NOW_ADDRESS = "now_address";
    String LOGIN_USERID = "login_userid";
    String IS_PHONE_CONTACT = "is_phone_contact";


    void saveFriendApplyList(List<Connections> list);

    List<Connections> getFriendApplyList();
}
