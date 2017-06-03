package com.hudong.wemedia.businesscomponent.coremodel.db.addr;

import com.hudong.wemedia.basiccomponent.bean.AddrBean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午8:34
 * 概况:
 */

public interface IAddrLocalDataSource {

    String TABLE_NAME = "city";
    String CODE = "code";
    String NAME = "name";
    String PARENTID = "parentid";
    String LEVEL = "level";

    void saveAddrList(List<AddrBean.ContentBean> addrList);

    /**
     * 获取一级城市
     *
     * @return
     */
    List<AddrBean.ContentBean> getAllAddr();

    /*获取下一级地址列表*/
    List<AddrBean.ContentBean> getNextLevelAddr(String parentId);
}
