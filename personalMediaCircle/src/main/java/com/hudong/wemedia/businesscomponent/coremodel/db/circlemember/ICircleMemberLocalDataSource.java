package com.hudong.wemedia.businesscomponent.coremodel.db.circlemember;

import com.hudong.wemedia.basiccomponent.bean.CircleMemberBean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/23:下午5:25
 * 概况:
 */

public interface ICircleMemberLocalDataSource {
    String TABLE_NAME = "CircleMember";
    String ID = "id";
    String WID = "wid";
    String MID = "mid";
    String CID = "cid";
    String INTROPENID = "intropenid";
    String HEADIMG = "headimg";
    String CREATE_TIME = "create_time";
    String STATTUS = "status";
    String MANAGER = "manage";
    String GID = "gid";
    String REALNAME = "realname";
    String PROVINCE = "province";
    String CITY = "city";
    String COUNTY = "county";
    String TOWN = "town";
    String PROVINCEC = "provincec";
    String CITYC = "cityc";
    String COUNTYC = "countyc";
    String TOWNC = "townc";
    String ISFRIEND = "isfriend";
    String APPLYGROUPINFO = "applygroupinfo";
    String APPLYCELEBRITYINFO = "applycelebrityinfo";
    String QUNNAME = "qunname";


    //保存圈子成员
    void saveCircleMemeber(List<CircleMemberBean.ContentBean> list);

    //通过个人id查询整个对象
    CircleMemberBean.ContentBean getCircleMember(String memberId);
}
