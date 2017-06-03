package com.hudong.wemedia.businesscomponent.coremodel.db.company;

import com.hudong.wemedia.basiccomponent.bean.CompanysParseBean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:30
 * 概况:
 */

public interface ICompanyLocalDataSource {
    String TABLE_NAME = "company";
    String LOGIN_USERID = "login_userid";
    String ID = "id";
    String NAME = "name";
    String LOGO = "logo";
    String INDUSTRY = "industry";
    String INDUSTRYC = "industryc";
    String PROVINCE = "province";
    String CITY = "city";
    String AREA = "area";
    String TOWN = "town";
    String ADDRESS = "address";
    String CREAT_TIME = "creat_time";
    String RZ = "rz";
    String COMPANYWEBSITE = "companywebsite";
    String ADURL = "adurl";
    String SHOPURL = "shopurl";
    String DEFINEDSHOPURL = "definedshopurl";
    String SHOP_TYPE = "shop_type";
    String IS_SHOW_TEL = "is_show_tel";
    String PHONE = "phone";
    String EMAIL = "email";
    String WEIXIN = "weixin";
    String QQ = "qq";
    String STATUS = "status";
    String DETAIL_ADDRESS = "detail_address";
    String INTRODUCTION = "introduction";
    String COMPANY_TOWN = "company_town";
    String ISSELECTED = "isselected";
    String EMPNUM = "empnum";
    String DEPARTNUM = "departnum";
    String FANSNUM = "fansnum";
    String INSIDEMEMBERNUM = "insidemembernum";
    String OUTSIDEMEMBERNUM = "outsidemembernum";
    String LASTDYNAMIC = "lastdynamic";
    String INCOMPANY = "incompany";
    String FRIEND = "friend";

    void saveCompanyList(List<CompanysParseBean.ContentBean> companyList);

    void removeCompanyFromList(List<CompanysParseBean.ContentBean> companyList);

    List<CompanysParseBean.ContentBean> getCompanyList();

    CompanysParseBean.ContentBean getCompanyById(String id);

    int totalCloumSize();
}
