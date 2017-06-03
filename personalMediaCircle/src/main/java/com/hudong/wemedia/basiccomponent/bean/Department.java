package com.hudong.wemedia.basiccomponent.bean;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午8:47
 * 概况:
 */

public class Department {
    private String id;
    private String department;
    private String parentid;
    private String count;
    private String opening;//部门是否对外开放   0.不对外开放     1.对外开放
    private String usel;//部门是否是系统自带的   0.系统自带  1.用户自定义添加
    private String isChoice;//公司-发布新闻-选择对内开放部门--是否被选中

    public Department(){

    }

    public Department(String id, String department, String parentid, String count, String opening, String usel, String isChoice) {
        this.id = id;
        this.department = department;
        this.parentid = parentid;
        this.count = count;
        this.opening = opening;
        this.usel = usel;
        this.isChoice = isChoice;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", parentid='" + parentid + '\'' +
                ", count='" + count + '\'' +
                ", opening='" + opening + '\'' +
                ", usel='" + usel + '\'' +
                ", isChoice='" + isChoice + '\'' +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getUsel() {
        return usel;
    }

    public void setUsel(String usel) {
        this.usel = usel;
    }

    public String getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(String isChoice) {
        this.isChoice = isChoice;
    }
}

