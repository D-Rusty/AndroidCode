package com.hudong.wemedia.businesscomponent.coremodel.db.foucuscompany;

import com.hudong.wemedia.basiccomponent.bean.ForcusCompanysParseBean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:22
 * 概况:
 */

public interface IFoucusCompanyLocalDataSource {

    static final String TABLE_NAME = "FocusCompany";
    static final String LOGIN_USERID = "login_userid";
    static final String ID = "id";
    static final String NAME = "name";
    static final String LOGO = "logo";
    static final String INDUSTRY = "industry";
    static final String PROVINCE = "province";
    static final String CITY = "city";
    static final String AREA = "area";
    static final String TOWN = "town";
    static final String ADDRESS = "address";
    static final String CREAT_TIME = "creat_time";
    static final String RZ = "rz";
    static final String COMPANYWEBSITE = "companywebsite";
    static final String SHOPURL = "shopurl";
    static final String PHONE = "phone";
    static final String EMAIL = "email";
    static final String WEIXIN = "weixin";
    static final String FRIEND = "friend";
    static final String QQ = "qq";
    static final String STATUS = "status";
    static final String DETAIL_ADDRESS = "detail_address";
    static final String INTRODUCTION = "introduction";
    static final String COMPANY_TOWN = "company_town";
    static final String ADURL = "adurl";

    void saveCompanyList(List<ForcusCompanysParseBean.ContentBean> companyList);

    void removeCompanyFromList(List<ForcusCompanysParseBean.ContentBean> companyList);

    List<ForcusCompanysParseBean.ContentBean> getCompanyList();

    ForcusCompanysParseBean.ContentBean getCompanyById(String id);

    int totalCloumSize();

}
