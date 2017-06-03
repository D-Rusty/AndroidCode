package com.hudong.wemedia.businesscomponent.coremodel.db.companymember;

import com.hudong.wemedia.basiccomponent.bean.CompanyMember;
import com.hudong.wemedia.basiccomponent.bean.Connections;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午8:57
 * 概况:
 */

public interface ICompanyMemberLocalDataSource {

    String TABLE_NAME = "companymember";
    String COMPANY_ID = "companyid";
    String LOGIN_USERID = "login_userid";
    String MANAGE = "manage";
    String DEPARTMENT = "department";
    String DEPARTMENTID = "departmentid";
    String JOB = "job";
    String HEADIMG = "headimg";
    String FULLNAME = "fullname";
    String SUBFULLNAME = "subfullname";
    String MID = "mid";
    String DEPARTMENTMANAGER = "departmentmanager";
    String MOBILE = "mobile";


    void saveCompanyMemberList(List<CompanyMember> memberList, String companyid);

    List<CompanyMember> getCompanyMemberList(String companyid);

    List<CompanyMember> getCompanyMemberListByDepartID(String companyid, String departid);

    List<CompanyMember> getCompanyMemberListExceptDepart(String companyid, String departid);

    List<Connections> getConnectionsNotjoinedCompany(List<Connections> mList, String companyid);

    void deleteCompanyMember(String mid, String companyId);

    CompanyMember getMemberById(String id, String companyId);

    CompanyMember getMemberById(String id);

    void updateCompanyMember(String id, String companyId, CompanyMember member);
}
