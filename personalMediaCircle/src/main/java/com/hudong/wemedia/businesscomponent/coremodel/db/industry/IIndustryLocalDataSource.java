package com.hudong.wemedia.businesscomponent.coremodel.db.industry;

import com.hudong.wemedia.basiccomponent.bean.IndustryParseBean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午4:28
 * 概况:
 */

public interface IIndustryLocalDataSource {
    String TABLE_NAME = "industry";
    String ID = "id";
    String TITLE = "title";
    String PARENTID = "parentid";

    //保存行业表
    void saveIndustryList(List<IndustryParseBean.ContentBean> industryList);

    //获取行业表
    List<IndustryParseBean.ContentBean> getIndustryList();

    //获取指定范围内行业列表
    List<IndustryParseBean.ContentBean> getIndustryList(String parentid);

    //查询某一个具体的行业
    IndustryParseBean.ContentBean quertIndustryById(String id);
}
