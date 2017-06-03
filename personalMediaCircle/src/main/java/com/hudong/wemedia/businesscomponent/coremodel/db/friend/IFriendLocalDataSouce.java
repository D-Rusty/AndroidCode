package com.hudong.wemedia.businesscomponent.coremodel.db.friend;

import com.hudong.wemedia.basiccomponent.bean.Connections;
import com.hudong.wemedia.basiccomponent.bean.IndustryParseBean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午4:36
 * 概况:
 */

public interface IFriendLocalDataSouce {
    String TABLE_NAME = "friend";
    String ID = "id";
    String REALNAME = "realname";
    String SEX = "sex";
    String BIRTH = "birth";
    String MOBILE = "mobile";
    String COMPANY = "company";
    String INDUSTRY = "industry";
    String DEPARTMENT = "department";
    String COMPANY_ADDRESS = "company_address";
    String HEADIMG = "headimg";
    String HOME_ADDRESS = "home_address";
    String NOW_ADDRESS = "now_address";
    String TOP_IDENTITY = "top_identity";
    String BOTTOM_IDENTITY = "bottom_identity";
    String JOB = "job";
    String NOW_PROVINCE = "now_province";
    String NOW_CITY = "now_city";
    String ISFRIEND = "isfriend";
    String ISAPPLIED = "isapplied";
    String ISREFUSE = "isrefuse";
    String ISACCEPT = "isaccept";
    String LOGIN_USERID = "login_userid";
    String USERNAME = "username";


    /*保存好友关系表*/
    long saveFriendList(List<Connections> list);

    /*获取好友关系表*/
    List<Connections> getFriendList();

    /*获取该行业下所有好友*/
    List<Connections> getFriendList(String industry);

    /*依据好友名称获取好友关系*/
    Connections getFriendByUsername(String username);

    /*获取好友行业列表*/
    List<IndustryParseBean.ContentBean> getFriendIndustryList(List<Connections> friendList);
}
