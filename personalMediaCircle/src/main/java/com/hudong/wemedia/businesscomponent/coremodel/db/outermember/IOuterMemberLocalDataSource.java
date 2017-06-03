package com.hudong.wemedia.businesscomponent.coremodel.db.outermember;

import com.hudong.wemedia.basiccomponent.bean.OuterMember;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:42
 * 概况:
 */

public interface IOuterMemberLocalDataSource {
    String TABLE_NAME = "outermember";
    String ID = "id";
    String NAME = "name";
    String MOBILE = "mobile";
    String COMPANY = "company";
    String JOB = "job";
    String PROVINCE = "province";
    String CITY = "city";
    String AREA = "area";
    String TOWN = "town";
    String LABEL = "label";
    String IS_MEBER = "is_meber";
    String RELEASENAME = "releasename";
    String REMARKS = "remarks";
    String COMPANY_ID = "companyid";
    String ADDRESS = "address";
    String MID = "mid";
    String FRIENDID = "friendid";


    void saveOuterMemberList(List<OuterMember> memberList, String companyid);

    List<OuterMember> getOutMemberList(String companyid);

    List<OuterMember> getOuterMemberById(String id, String companyId);

    OuterMember getOuterMemberById(String id);

    void deletetable();

    void deleteOutMember(String id, String companyId);
}
