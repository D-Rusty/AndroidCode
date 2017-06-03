package com.hudong.wemedia.businesscomponent.coremodel.db.department;

import com.hudong.wemedia.basiccomponent.bean.Department;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午8:44
 * 概况:
 */

public interface IDepartmentLocalDataSource {

    String COMPANY_ID = "companyid";
    String TABLE_NAME = "department";
    String ID = "id";
    String LOGIN_USERID = "login_userid";
    String DEPARTMENT = "department";
    String PARENTID = "parentid";
    String COUNT = "count";
    String OPENING = "opening";
    String USEL = "usel";


    void saveDepartmentList(List<Department> departList, String companyid);

    List<Department> getDepartmentList(String companyid);

    void deleteTable();

    void deleteDepartment(String id, String companyId);
}
