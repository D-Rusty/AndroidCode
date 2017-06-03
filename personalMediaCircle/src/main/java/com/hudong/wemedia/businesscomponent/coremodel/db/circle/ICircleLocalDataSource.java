package com.hudong.wemedia.businesscomponent.coremodel.db.circle;

import com.hudong.wemedia.basiccomponent.bean.CircleBean;

import java.util.List;

import rx.Observable;


/**
 * 作者: 方天文
 * 日期: 2017/4/14:下午2:23
 * 概况:
 */

public interface ICircleLocalDataSource {

    String TABLE_NAME = "mCircle";
    String ID = "id";
    String MID = "mid";
    String WEID = "weid";
    String QUN_TYPE = "type";//0位基本圈子，1为自定义圈子，2为加入的其它圈子
    String GROUP_NAME = "group_name";
    String GROUP_INFO = "group_info";
    String GROUP_QR = "group_qr";
    String CREATE_TIME = "create_time";
    String TOTAL = "total";
    String QUNIMG = "qunimg";
    String AREACODE = "areacode";
    String INDUSTRY = "industry";
    String XINGQUID = "xingquid";
    String APPGROUPID = "appgroupid";
    String INDUSTRYC = "industryc";
    String XINGQUIDC = "xingquidc";
    String LOGIN_USER_ID = "login_user_id";
    String ACTIVITY_COUNT = "activity_num";
    String TYPEC = "typec";

    /**
     * 保存圈子分组
     *
     * @param list
     */
    void saveGroupList(List<CircleBean.ContentBean> list);

    /**
     * @return 返回圈子数量
     */
    int totalCloumSize();

    List<CircleBean.ContentBean> getGroupList();

    List<Object> getGroupCircleList();

//    CircleBean.ContentBean getGroupListById(String id);

    CircleBean.ContentBean getGroupListByHxGid(String hxGid);

    CircleBean.ContentBean findGroup(String params);

    //参考代码
    Observable<List<CircleBean.ContentBean>> getGrouplist();

    Observable<CircleBean.ContentBean> getGroupListById(String id);

}
